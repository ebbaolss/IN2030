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
    
    @Override
    void prettyPrint() {
        prettyWrite(null);
    }
    
}