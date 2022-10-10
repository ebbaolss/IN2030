package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspNoneLiteral extends AspAtom {
    String noneLiteral;

    AspNoneLiteral() {
        
    }
    
    static AspNoneLiteral parse(Scanner s) {
        enterParser("none literal");

        AspNoneLiteral anl = new AspNoneLiteral();
        // AspNoneLiteral anl = new AspNoneLiteral(s.curLineNum());
        anl.noneLiteral = s.curToken();
        //anl.noneLiteral = s.curToken().anl;
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
