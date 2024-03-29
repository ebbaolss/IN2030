package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspBooleanLiteral extends AspAtom {
    boolean booleanLiteral;

    AspBooleanLiteral(int n) {
        super(n);
    }
    
    static AspBooleanLiteral parse(Scanner s) {
        enterParser("boolean literal");

        AspBooleanLiteral abl = new AspBooleanLiteral(s.curLineNum());
        
        if (s.curToken().kind == trueToken) {
            abl.booleanLiteral = true;
            skip(s, trueToken);
        }
        else if (s.curToken().kind == falseToken) {
            skip(s, falseToken);
        }

        leaveParser("boolean literal");
        return abl;
    }

    @Override
    public void prettyPrint() {
        if (booleanLiteral) {
            prettyWrite("True");
        } else {
            prettyWrite("False");
        }
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return new RuntimeBoolValue(booleanLiteral);
    }
}
