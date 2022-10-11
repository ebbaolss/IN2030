package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspBooleanLiteral extends AspAtom {
    String booleanLiteral;

    AspBooleanLiteral(int n) {
        super(n);
    }
    
    static AspBooleanLiteral parse(Scanner s) {
        enterParser("boolean literal");

        AspBooleanLiteral abl = null;
        abl.booleanLiteral = s.curToken().name;
        TokenKind cur = s.curToken().kind;
        if (cur == trueToken) {
            skip(s, trueToken);
        }
        else if (cur == falseToken) {
            skip(s, falseToken);
        }

        leaveParser("boolean literal");
        return abl;
    }

    @Override
    public void prettyPrint() {
        prettyWrite(booleanLiteral);
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
