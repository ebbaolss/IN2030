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
			//System.out.println("***>>>" + sourceFile);
			line = sourceFile.readLine();
			if (line == null) {
				sourceFile.close();
				sourceFile = null;
				curLineTokens.add(new Token(eofToken, curLineNum()));	//Indicates E-O-F
			} else {
				Main.log.noteSourceLine(curLineNum(), line);
			}
		} catch (IOException e) {
			sourceFile = null;
			scannerError("Unspecified I/O error!");
		}

		//-- Must be changed in part 1:
		if (line != null) {
			if (line.isBlank() || line.charAt(0) == '#')return;

			line = expandLeadingTabs(line);
			int n = findIndent(line);

			if (n > indents.peek()) {

				indents.push(n);
				curLineTokens.add(new Token(indentToken,curLineNum()));
			}
			if (n < indents.peek()) {
				indents.pop();
				curLineTokens.add(new Token(dedentToken, curLineNum()));
			}	
			
			if (n != indents.peek()) {
				System.out.println("-------Indenteringsfeil--------");
			}
		}	

		for (int i = 0; i < indents.size(); i++) {
			if (i > indents.get(i)) {
				curLineTokens.add(new Token(dedentToken));
			}
		}


		if (line != null) {
			
			/*sjekkliste

			 * lete etter quotes
			 * float fiks
			 */

			String s = "";
			int teller = 0;
			char[] ch = line.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				teller = i;
				if (isDigit(ch[i])) {

					
					//grei men må fikses for FLOAT

					/*if (i+1 < ch.length) {
						s += ch[i];
						if (ch[i + 1] == '.') {
							
							if (isDigit(ch[i + 1])) {
								i++;
								s+= ch[i + 1];
							}
							Token t = new Token(floatToken, curLineNum());
							t.floatLit = Float.parseFloat(s);
							curLineTokens.add(t);

						}
						else if (isDigit(ch[i + 1])) {
							Token t = new Token(integerToken, curLineNum());
							t.integerLit = Integer.parseInt(s);
							curLineTokens.add(t);
						} 
						else {
							Token t = new Token(integerToken, curLineNum());
							t.integerLit = Integer.parseInt(String.valueOf(ch[i]));
							curLineTokens.add(t);
						}*/
					
				}
				
			
				else if (isLetterAZ(ch[i])) {
					s += ch[i];

					for (int j = i; j < ch.length; j++) {
						if (j + 1 >= ch.length) {
							break;
						}
						if (isOperator(String.valueOf(ch[j + 1])) || isDelimiter(ch[j + 1]) || ch[j + 1] == ' ') {

							Token n = new Token(nameToken, curLineNum());
							n.name = s;
							// sjekker for keywords
							Boolean keyword = false;
							for (TokenKind t : EnumSet.range(andToken, yieldToken)) {
								if (n.name.equals(t.toString())) {
									keyword = true;
									curLineTokens.add(new Token(t, curLineNum()));
								}
							}
							//hvis den ikke finner keyword er det et nameToken
							if (keyword == false) {
								n.stringLit = s;
								curLineTokens.add(n);
							}
		
							teller = j;
							s = "";
							break;
						}
						else {
							s += ch[j + 1];
						}
						
					}
					i = teller;
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
					for (TokenKind t : EnumSet.range(colonToken, semicolonToken)) {
						if (n.name.equals(t.toString())) {
							curLineTokens.add(new Token(t, curLineNum()));
						}
					}	
				}
				
				else if (isOperator(String.valueOf(ch[i]))) {
					Token n = new Token(nameToken, curLineNum());
					n.name = Character.toString(ch[i]);
					for (TokenKind t : EnumSet.range(astToken, slashToken)) {
						if (n.name.equals(t.toString())) {
							curLineTokens.add(new Token(t, curLineNum()));
						}
					}
				}
			}

			// Terminate line:
			curLineTokens.add(new Token(newLineToken,curLineNum()));
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
		char[] delimiters = {':', ',', '=', '{', '[', '(', '}', ']', ')', ';'};
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