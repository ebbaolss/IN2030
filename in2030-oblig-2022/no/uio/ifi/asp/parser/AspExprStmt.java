package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspExprStmt extends AspSmallStmt{
    AspExpr expr;
    String s;

    AspExprStmt(int n) {
        super(n);
    }
    
    public static AspExprStmt parse(Scanner s) {
        enterParser("expr stmt");
        
        AspExprStmt aes = new AspExprStmt(s.curLineNum());
        aes.s = s.curToken().name;
        aes.expr = AspExpr.parse(s);
        
        leaveParser("expr stmt");
        return aes;
    }

    @Override
    void prettyPrint() {
        expr.prettyPrint();   
    }

    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        RuntimeValue v = expr.eval(curScope);
        //trace(v.showInfo());
        return v;
    }
}
