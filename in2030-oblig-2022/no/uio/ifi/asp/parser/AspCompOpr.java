package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;

public class AspCompOpr extends AspSyntax {
    String CompOpr;
    TokenKind kind;

    AspCompOpr(int n) {
        super(n);
    }
    
    static AspCompOpr parse(Scanner s) {
        enterParser("comp opr");
        
        AspCompOpr aco = new AspCompOpr(s.curLineNum());
        aco.CompOpr = s.curToken().toString();
        aco.kind = s.curToken().kind;

        skip(s, s.curToken().kind);
        
        leaveParser("comp opr");
        return aco;
    }

    @Override
    void prettyPrint() {
        prettyWrite(" " + CompOpr + " ");
    }

    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
