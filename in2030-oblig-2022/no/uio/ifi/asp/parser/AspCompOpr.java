package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspCompOpr extends AspSyntax {
    String CompOpr;

    AspCompOpr(int n) {
        super(n);
    }
    
    static AspCompOpr parse(Scanner s) {
        enterParser("comp opr");
        AspCompOpr aco = new AspCompOpr(s.curLineNum());
        aco.CompOpr = s.curToken().toString();
        TokenKind cur = s.curToken().kind;

        skip(s, cur);
        
        leaveParser("comp opr");
        return aco;
    }

    @Override
    void prettyPrint() {
        prettyWrite(" " + CompOpr + " ");
    }

    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        // -- Must be changed in part 3:
        return null;
    }
}
