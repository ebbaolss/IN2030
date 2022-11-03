package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

abstract class AspSmallStmt extends AspSyntax {

    AspSmallStmt(int n) {
        super(n);
    }
    
    static AspSmallStmt parse(Scanner s) {
        enterParser("small stmt");
    
        AspSmallStmt as = null;
        
        if (s.curToken().kind == globalToken) {
            as = AspGlobalStmt.parse(s);
        }
        else if (s.curToken().kind == passToken) {
            as = AspPassStmt.parse(s);
        }
        else if (s.curToken().kind == returnToken) {
            as = AspReturnStmt.parse(s);
        }
        else if (s.curToken().kind == nameToken && s.anyEqualToken()) {
            as = AspAssignment.parse(s);
        } else {
            as = AspExprStmt.parse(s);
        }

        leaveParser("small stmt");
        return as;
    }

    @Override
    void prettyPrint() {
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
