package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import java.util.ArrayList.*;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspSuite extends AspSyntax {
    AspSmallStmtList smallstmt;
    ArrayList<AspStmt> stmt = new ArrayList<>();

    AspSuite(int n) {
        super(n);
    }

    static AspSuite parse(Scanner s) {
        enterParser("suite");
        
        AspSuite as = new AspSuite(s.curLineNum());
    
        if (s.curToken().kind == TokenKind.newLineToken){
            skip(s, TokenKind.newLineToken);
            skip(s, TokenKind.indentToken);
            boolean f = true;
            while (f == true) {
                as.stmt.add(AspStmt.parse(s));
                if (s.curToken().kind == TokenKind.dedentToken) {
                    f = false;
                }
                skip(s, TokenKind.dedentToken);
            }
        } else {
            as.smallstmt = AspSmallStmtList.parse(s);
        }
        leaveParser("suite");
        return as;
        } 

        @Override
        void prettyPrint() {
            prettyWrite("suiiiieeewt");
        }

        @Override
        RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
            return null;
        }
}
