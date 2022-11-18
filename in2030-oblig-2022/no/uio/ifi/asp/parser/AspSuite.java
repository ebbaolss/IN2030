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

            while (s.curToken().kind != TokenKind.dedentToken) {
                as.stmt.add(AspStmt.parse(s));
                //System.out.println(as.stmt);
            }

            skip(s, TokenKind.dedentToken);
        } else {
            as.smallstmt = AspSmallStmtList.parse(s);
        }

        leaveParser("suite");
        return as;
    }

    @Override
    void prettyPrint() {
        if (smallstmt == null) {
            prettyWriteLn();
            prettyIndent();

            for (AspStmt aspStmt : stmt) {
                aspStmt.prettyPrint();
            }
            prettyDedent();
        } else {
            smallstmt.prettyPrint();
        }
    }

    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        RuntimeValue v = null;
        
        if (smallstmt == null) {
            for (int i = 0; i < stmt.size(); i++) {
                v = stmt.get(i).eval(curScope);
            }
        } else {
            v = smallstmt.eval(curScope);
        }
        return v;
    }
}
