package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import javax.swing.RowFilter.ComparisonType;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspNotTest extends AspSyntax {
    AspComparison com;
    String p;
    
    AspNotTest(int n) {
        super(n);
    } 

    static AspNotTest parse(Scanner s) {
        enterParser("not test");

        AspNotTest ant = new AspNotTest(s.curLineNum());
        
        if (s.curToken().kind == TokenKind.notToken) {
            ant.p = TokenKind.notToken.toString();
            skip(s, TokenKind.notToken);
        }
        ant.com = AspComparison.parse(s);
        
        leaveParser("not test");
        return ant;
    }

    @Override
    void prettyPrint() {
        if (p != null){
            prettyWrite("not ");
        }
        com.prettyPrint();
    }

    //hentet fra forelesningsfoiler
    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        RuntimeValue v = com.eval(curScope);
        
        if (p != null) {
            v = v.evalNot(this);
        }
        return v;
    }

}
