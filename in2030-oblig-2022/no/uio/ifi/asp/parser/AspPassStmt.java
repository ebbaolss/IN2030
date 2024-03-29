package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspPassStmt extends AspSmallStmt {

    AspPassStmt(int n) {
        super(n);
    }

    static AspPassStmt parse(Scanner s) {
        enterParser("pass");

        AspPassStmt aps = new AspPassStmt(s.curLineNum());
        skip(s, passToken);

        leaveParser("pass");
        return aps;
    }
    
    @Override
    void prettyPrint() {
        prettyWrite(TokenKind.passToken.toString());
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        trace("pass");
        return null;
    }
    
}