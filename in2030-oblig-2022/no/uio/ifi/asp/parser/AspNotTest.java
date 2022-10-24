package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import javax.swing.RowFilter.ComparisonType;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspNotTest extends AspSyntax {
    AspComparison com;
    static String p;
    
    AspNotTest(int n) {
        super(n);
    } 

    static AspNotTest parse(Scanner s) {
        enterParser("not test");

        AspNotTest ant = new AspNotTest(s.curLineNum());
        TokenKind cur = s.curToken().kind;
        
        if (cur == TokenKind.notToken) {
            p = TokenKind.notToken.toString();
            skip(s, TokenKind.notToken);
        }
        ant.com = AspComparison.parse(s);
        
        leaveParser("not test");
        return ant;
    }

    @Override
    void prettyPrint() {
        if (p == null) {
            prettyWrite(p);
        }

        com.prettyPrint();
    }

    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        // -- Must be changed in part 3:
        return null;
    }
}
