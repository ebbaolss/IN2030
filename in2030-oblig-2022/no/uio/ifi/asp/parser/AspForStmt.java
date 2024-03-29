package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspForStmt extends AspCompoundStmt{
    AspName name;
    AspExpr expr;
    AspSuite sui;
    String forStmt;
    
    AspForStmt(int n) {
        super(n);
    }

    static AspForStmt parse(Scanner s) {
        enterParser("for stmt");

        AspForStmt afs = new AspForStmt(s.curLineNum());
        afs.forStmt = s.curToken().name;
        
        skip(s, TokenKind.forToken);
        afs.name = AspName.parse(s); 
        skip(s, TokenKind.inToken);
        afs.expr = AspExpr.parse(s);
        skip(s, TokenKind.colonToken);
        afs.sui = AspSuite.parse(s);
        
        leaveParser("for stmt");
        return afs;
    }

    @Override
    void prettyPrint() {
        prettyWrite(TokenKind.forToken.toString() + " ");
        name.prettyPrint();
        prettyWrite(" " + TokenKind.inToken.toString() + " ");
        expr.prettyPrint();
        prettyWrite(TokenKind.colonToken.toString() + " ");
        sui.prettyPrint();
    }

    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        RuntimeValue v = expr.eval(curScope);

        if (v instanceof RuntimeListValue) {
            RuntimeListValue listValue = (RuntimeListValue) v;
            ArrayList<RuntimeValue> listArray = listValue.value;
            
            for (int i = 0; i < listArray.size(); i++) {
                curScope.assign(name.p, listArray.get(i)); 
                
                trace("for #" + (i+1) + ": " + name.p + " = " + listArray.get(i).showInfo());
                v = sui.eval(curScope);
            }
        }
        return v;
    }
}
