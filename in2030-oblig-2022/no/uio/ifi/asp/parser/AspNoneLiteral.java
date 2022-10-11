package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspNoneLiteral extends AspAtom {
    String noneLiteral;

    AspNoneLiteral(int n) {
        super(n);
    }
    
    static AspNoneLiteral parse(Scanner s) {
        enterParser("none literal");

        AspNoneLiteral anl = new AspNoneLiteral(s.curLineNum());
        // AspNoneLiteral anl = new AspNoneLiteral(s.curLineNum());
        anl.noneLiteral = s.curToken().name;
        skip(s, noneToken);

        leaveParser("none literal");
        return anl;
    }

    @Override
    void prettyPrint() {
        prettyWrite(noneLiteral);
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
