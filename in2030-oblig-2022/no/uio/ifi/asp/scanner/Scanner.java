// © 2021 Dag Langmyhr, Institutt for informatikk, Universitetet i Oslo

package no.uio.ifi.asp.scanner;

import java.io.*;
import java.util.*;

import no.uio.ifi.asp.main.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class Scanner {
    private LineNumberReader sourceFile = null;
    private String curFileName;
    private ArrayList<Token> curLineTokens = new ArrayList<>();
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
		if (! curLineTokens.isEmpty()) {
			curLineTokens.remove(0);
		}
	}		
    private void readNextLine() {
		curLineTokens.clear();

		// Read the next line:
		String line = null;
		try {
			line = sourceFile.readLine();
			if (line == null) {
				sourceFile.close();
				sourceFile = null;
			}
			if (line != null) {
				String indentString = expandLeadingTabs(line);
				int n = findIndent(indentString);
				if (line.isBlank() || line.charAt(0) == '#') { //denne er feil fordi første symbol hvor det er indentert først vil aldri bli #
																	// kan bruke en egen metode for å finne første tegn som ikke er mellomrom.
				}
				else {
					//3c
					//int n = findIndent(indentString);
					//int pushN = 4;
					if (n > indents.peek()) {

						System.out.println("tall: " + n);
						indents.push(n);
						int x = n/4;
						for (int i = 0; i < x; i++) {
							curLineTokens.add(new Token(indentToken,curLineNum()));
							System.out.println("indent added");
						}
						System.out.println(indents);
					}
					else { // sjekk ut denne ekstra 3d(ii) - loop?
						while (n < indents.peek()) {
						System.out.println("tall: " + n);
						indents.pop();
						int x = n / 4;
						for (int i = 0; i < x; i++) {
							curLineTokens.add(new Token(dedentToken, curLineNum()));
							System.out.println("dedent added");
						}
						System.out.println(indents);
						}
					}
				}	
				if (n != indents.peek()) {
					System.out.println("-------Indenteringsfeil--------");
				}
			} 
			else {
				Main.log.noteSourceLine(curLineNum(), line);
			}
		} catch (IOException e) {
			sourceFile = null;
			scannerError("Unspecified I/O error!");
		}

		for (int i = 0; i < indents.size(); i++) {
			if (i > indents.get(i)) {
				curLineTokens.add(new Token(dedentToken));
			}
		}
		// Terminate line:
		curLineTokens.add(new Token(newLineToken,curLineNum()));

		for (Token t: curLineTokens) 
			Main.log.noteToken(t);
		}

    public int curLineNum() {
		return sourceFile!=null ? sourceFile.getLineNumber() : 0;
    	}

    private int findIndent(String s) {
		int indent = 0;

		while (indent<s.length() && s.charAt(indent)==' ') indent++;
		return indent;
    }

    private String expandLeadingTabs(String s) {
		System.out.println(s);
		int cnt = 0;
		String s2 = "";

    	for (int i = 0;  i < s.length();  i++) {
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
	return ('A'<=c && c<='Z') || ('a'<=c && c<='z') || (c=='_');
    }


    private boolean isDigit(char c) {
	return '0'<=c && c<='9';
    }


    public boolean isCompOpr() {
	TokenKind k = curToken().kind;
	//-- Must be changed in part 2:
	return false;
    }


    public boolean isFactorPrefix() {
	TokenKind k = curToken().kind;
	//-- Must be changed in part 2:
	return false;
    }


    public boolean isFactorOpr() {
	TokenKind k = curToken().kind;
	//-- Must be changed in part 2:
	return false;
    }
	

    public boolean isTermOpr() {
	TokenKind k = curToken().kind;
	//-- Must be changed in part 2:
	return false;
    }


    public boolean anyEqualToken() {
	for (Token t: curLineTokens) {
	    if (t.kind == equalToken) return true;
	    if (t.kind == semicolonToken) return false;
	}
	return false;
    }
}
