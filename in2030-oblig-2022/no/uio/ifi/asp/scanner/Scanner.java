// © 2021 Dag Langmyhr, Institutt for informatikk, Universitetet i Oslo

package no.uio.ifi.asp.scanner;

import java.io.*;
import java.util.*;

import no.uio.ifi.asp.main.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class Scanner {
	private LineNumberReader sourceFile = null;
	private String curFileName;
	private ArrayList<Token> curLineTokens = new ArrayList<>(); //
	private Stack<Integer> indents = new Stack<>();
	private final int TABDIST = 4;

	public Scanner(String fileName) {
		curFileName = fileName;
		indents.push(0);

		try {
			sourceFile = new LineNumberReader(
					new InputStreamReader(
							new FileInputStream(fileName),
							"UTF-8"));
		} catch (IOException e) {
			scannerError("Cannot read " + fileName + "!");
		}
	}

	private void scannerError(String message) {
		String m = "Asp scanner error";
		if (curLineNum() > 0)
			m += " on line " + curLineNum();
		m += ": " + message;

		Main.error(m);
	}

	public Token curToken() {
		while (curLineTokens.isEmpty()) {
			readNextLine();
		}
		return curLineTokens.get(0);
	}

	public void readNextToken() {
		if (!curLineTokens.isEmpty())
			curLineTokens.remove(0);
	}

	private void readNextLine() {
		curLineTokens.clear();

		// Read the next line:
		String line = null;
		try {
			// System.out.println("***>>>" + sourceFile);
			line = sourceFile.readLine();
			if (line == null) {
				sourceFile.close();
				sourceFile = null;
				curLineTokens.add(new Token(eofToken, curLineNum())); // Indicates E-O-F
			} else {
				Main.log.noteSourceLine(curLineNum(), line);
			}
		} catch (IOException e) {
			sourceFile = null;
			scannerError("Unspecified I/O error!");
		}

		// -- Must be changed in part 1:
		if (line != null) {
			if (line.isBlank() || line.charAt(0) == '#')
				return;

			line = expandLeadingTabs(line);
			int n = findIndent(line);

			if (n > indents.peek()) {

				indents.push(n);
				curLineTokens.add(new Token(indentToken, curLineNum()));
			}
			if (n < indents.peek()) {
				indents.pop();
				curLineTokens.add(new Token(dedentToken, curLineNum()));	
			}

			if (n != indents.peek()) {
				System.out.println("-------Indenteringsfeil--------" + curLineNum());
			}

			for (int i = n; i < indents.size(); i++) {
				if (indents.get(i) > 0) {
					System.out.println(indents);
					indents.pop();
					curLineTokens.add(new Token(dedentToken, curLineNum()));
				}
				else{
					break;
				}
			}
		}

		if (line != null) {

			String s = "";
			int teller = 0;
			char[] ch = line.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				teller = i;
				if (isDigit(ch[i])) {
					s += ch[i];
					boolean f = false;
					for (int j = i; j < ch.length; j++) {
						if (j + 1 >= ch.length) {
							teller = j;
							break;
						}
						// float check
						f = false;
						if (ch[j + 1] == '.') {
							// s += ch[j + 1];
							f = true;
							j++;
							teller = j;
							break;
						}

						if (isOperator(String.valueOf(ch[j + 1])) || isDelimiter(ch[j + 1]) || ch[j + 1] == ' ') {
							// hvis tall er float

							teller = j;

							break;
						} else {
							s += ch[j + 1];
						}

					}
					if (f == true) { // får ikke denne til å fungere, noe feil med true/false
						Token t = new Token(floatToken, curLineNum());
						t.floatLit = Double.parseDouble(s);
						curLineTokens.add(t);
					} else {
						Token t = new Token(integerToken, curLineNum());
						t.integerLit = Long.parseLong(s);
						curLineTokens.add(t);
					}
					s = "";
					i = teller;
				}

				else if (isLetterAZ(ch[i])) {
					s += ch[i];

					for (int j = i; j < ch.length; j++) {

						if (j + 1 >= ch.length) {
							teller = j;
							break;
						}

						if (isOperator(String.valueOf(ch[j + 1])) || isDelimiter(ch[j + 1]) || ch[j + 1] == ' ') {

							teller = j;
							break;
						} 
						
						else {
							s += ch[j + 1];
						}
					}
					Token n = new Token(nameToken, curLineNum());
					n.name = s;
					s = "";
					n.checkResWords();
					i = teller;
					curLineTokens.add(n);
				}

				else if (ch[i] == ' ') {
					continue;
				}

				else if (ch[i] == '#') {
					curLineTokens.add(new Token(newLineToken, curLineNum()));
				}

				else if (isDelimiter(ch[i])) {
					Token n = new Token(nameToken, curLineNum());
					n.name = Character.toString(ch[i]);
					if (ch[i] == '=' && ch[i + 1] != '=') {
						if (ch[i - 1] == '!' && ch[i] == '=') {
							curLineTokens.add(new Token(notEqualToken, curLineNum()));
						} else {
							curLineTokens.add(new Token(equalToken, curLineNum()));
						}
					} else if (ch[i] == '=' && ch[i + 1] == '=') {
						curLineTokens.add(new Token(doubleEqualToken, curLineNum()));
						teller = i + 1;
					}
					else if (ch[i] == '/' && ch[i + 1] == '/') {
						curLineTokens.add(new Token(doubleSlashToken, curLineNum()));
						teller = i + 1;
					}
					else {
						for (TokenKind t : EnumSet.range(colonToken, semicolonToken)) {
							if (n.name.equals(t.toString())) {
								if (Character.toString('=') != t.toString()) {
									curLineTokens.add(new Token(t, curLineNum()));
								}
							}
						}
					}
					i = teller;
				}

				else if (isOperator(String.valueOf(ch[i]))) {
					Token n = new Token(nameToken, curLineNum());
					n.name = Character.toString(ch[i]);
					if (ch[i] == '>' && ch[i + 1] == '=') {
						curLineTokens.add(new Token(greaterEqualToken, curLineNum()));
						teller = i + 1;
					} 
					else if (ch[i] == '<' && ch[i + 1] == '=') {
						curLineTokens.add(new Token(lessEqualToken, curLineNum()));
						teller = i + 1;
					} 
					else {
						for (TokenKind t : EnumSet.range(astToken, slashToken)) {
							if (n.name.equals(t.toString())) {
								curLineTokens.add(new Token(t, curLineNum()));
							}
						}
					}
					i = teller;
				}

				else if (isSingleQuoteMark(ch[i])) {
					Token sq = new Token(stringToken, curLineNum());
					String q = "";
					boolean found = false;
					for (int k = i + 1; k < ch.length; k++) {
						q += ch[k];
						if (ch[k] == '\'') {
							q = q.substring(0, q.length() - 1);
							sq.stringLit = q;
							curLineTokens.add(sq);
							// if sq found
							found = true;
						}
						teller = k;
						if (found) {
							break;
						}
					}
					i = teller;
				}

				else if (isDoubleQuoteMark(ch[i])) {
					Token sq = new Token(stringToken, curLineNum());
					String q = "";
					boolean found = false;
					for (int k = i + 1; k < ch.length; k++) {
						q += ch[k];
						if (ch[k] == '"') {
							q = q.substring(0, q.length() - 1);
							sq.stringLit = q;
							curLineTokens.add(sq);
							// if sq found
							found = true;
						}
						teller = k;
						if (found) {
							break;
						}
					}
					i = teller;
				}
			}

			// Terminate line:
			curLineTokens.add(new Token(newLineToken, curLineNum()));
		}

		for (Token t : curLineTokens) {
			Main.log.noteToken(t);
		}
	}

	public int curLineNum() {
		return sourceFile != null ? sourceFile.getLineNumber() : 0;
	}

	private int findIndent(String s) {
		int indent = 0;

		while (indent < s.length() && s.charAt(indent) == ' ')
			indent++;
		return indent;
	}

	private String expandLeadingTabs(String s) {
		// -- Must be changed in part 1:
		int cnt = 0;
		String s2 = "";

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ' ') {
				s2 += " ";
				cnt++;
			} else if (c == '\t') {
				int nReplace = 4 - (cnt % 4);
				for (int j = 0; j < nReplace; j++) {
					s2 += " ";
				}
				cnt += nReplace;
			} else {
				s2 += s.substring(i);
				break;
			}
		}
		return s2;
	}

	private boolean isLetterAZ(char c) {
		return ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z') || (c == '_');
	}

	private boolean isDigit(char c) {
		return '0' <= c && c <= '9';
	}

	private boolean isOperator(String s) {
		String[] operators = { "*", "==", "//", ">", ">=", "<", "<=", "-", "!=", "%", "+", "/" };
		for (String o : operators) {
			if (s.equals(o)) {
				return true;
			}
		}
		return false;
	}

	private boolean isDelimiter(char c) {
		char[] delimiters = { ':', ',', '=', '{', '[', '(', '}', ']', ')', ';' };
		for (char d : delimiters) {
			if (c == d) {
				return true;
			}
		}
		return false;
	}

	private boolean isSingleQuoteMark(char c) {
		if (c == '\'') {
			return true;
		}
		return false;
	}

	private boolean isDoubleQuoteMark(char c) {
		if (c == '"') {
			return true;
		}
		return false;
	}

	public boolean isCompOpr() {
		TokenKind k = curToken().kind;
		// -- Must be changed in part 2:
		return false;
	}

	public boolean isFactorPrefix() {
		TokenKind k = curToken().kind;
		// -- Must be changed in part 2:
		return false;
	}

	public boolean isFactorOpr() {
		TokenKind k = curToken().kind;
		// -- Must be changed in part 2:
		return false;
	}

	public boolean isTermOpr() {
		TokenKind k = curToken().kind;
		// -- Must be changed in part 2:
		return false;
	}

	public boolean anyEqualToken() {
		for (Token t : curLineTokens) {
			if (t.kind == equalToken)
				return true;
			if (t.kind == semicolonToken)
				return false;
		}
		return false;
	}
}