package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

abstract class AspSmallStmt extends AspSyntax {
    static AspSmallStmt ass = null;
    AspSmallStmt(int n) {
        super(n);
    }
    
    static AspSmallStmt parse(Scanner s) {
        enterParser("small stmt");
    
        
        
        if (s.curToken().kind == globalToken) {
            ass = AspGlobalStmt.parse(s);
        }
        else if (s.curToken().kind == passToken) {
            ass = AspPassStmt.parse(s);
        }
        else if (s.curToken().kind == returnToken) {
            ass = AspReturnStmt.parse(s);
        }
        else if (s.curToken().kind == nameToken && s.anyEqualToken()) {
            ass = AspAssignment.parse(s);
        } else {
            ass = AspExprStmt.parse(s);
        }

        leaveParser("small stmt");
        return ass;
    }

    @Override
    void prettyPrint() {
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        RuntimeValue v = ass.eval(curScope);
        return v;
    }
}
