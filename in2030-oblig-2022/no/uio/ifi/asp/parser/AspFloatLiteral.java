package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFloatLiteral extends AspAtom {
    double floatLit;

    AspFloatLiteral(int n) {
        super(n);
    }

    static AspFloatLiteral parse(Scanner s) {
        enterParser("float literal");
        
        AspFloatLiteral afl = new AspFloatLiteral(s.curLineNum());
        afl.floatLit = s.curToken().floatLit;
        skip(s, floatToken);
        
        leaveParser("float literal");
        return afl;
    }

    @Override
    void prettyPrint() {
        prettyWrite("" + floatLit);
    }

    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return new RuntimeFloatValue(floatLit);
    }
}
