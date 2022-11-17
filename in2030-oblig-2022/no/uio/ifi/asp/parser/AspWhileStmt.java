package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspWhileStmt extends AspCompoundStmt {
    AspExpr expr;
    AspSuite sui;
    String p;

    AspWhileStmt(int n) {
        super(n);
    }

    static AspWhileStmt parse(Scanner s) {
        enterParser("while stmt");

        AspWhileStmt aws = new AspWhileStmt(s.curLineNum()); 
        aws.p = TokenKind.whileToken.toString();

        skip(s, whileToken); 

        aws.expr = AspExpr.parse(s); 
        skip(s, colonToken); 
        aws.sui = AspSuite.parse(s);
        
        leaveParser("while stmt");
        return aws; 
    }

    @Override
    void prettyPrint() {
        prettyWrite(p + " ");
        expr.prettyPrint();
        prettyWrite(TokenKind.colonToken.toString() + " ");
        sui.prettyPrint();
    }

    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        while (true) {
            RuntimeValue v = expr.eval(curScope);
            
            if (!v.getBoolValue("while loop", this)) {
                break;
            }

            trace("while True: ...");
            sui.eval(curScope);
        }

        trace("while False:");
        return null;
    }
}
