package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

abstract class AspSmallStmt extends AspSyntax {
    AspSmallStmt as = null;

    AspSmallStmt(int n) {
        super(n);
    }
    
    static AspSmallStmt parse(Scanner s) {
        enterParser("small stmt");
        
        TokenKind cur = s.curToken().kind;

        if (s.anyEqualToken()) {
            as = AspAssignment.parse(s);
        } else {
            if (cur == globalToken) {
                as = AspGlobalStmt.parse(s);
            }
            else if (cur == passToken) {
                as = AspPassStmt.parse(s);
            }
            else if (cur == returnToken) {
                as = AspReturnStmt.parse(s);
            } else {
                as = AspExprStmt.parse(s);
            }
        }

        leaveParser("small stmt");
        return as;
    }

    @Override
    void prettyPrint() {
        prettyWrite(null);
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
