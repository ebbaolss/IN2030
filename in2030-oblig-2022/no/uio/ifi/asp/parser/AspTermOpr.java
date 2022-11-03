package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspTermOpr extends AspSyntax {
    String p;
    TokenKind cur;

    AspTermOpr(int n) {
        super(n);
    }
    
    static AspTermOpr parse(Scanner s) {
        enterParser("term opr");
        AspTermOpr ato = new AspTermOpr(s.curLineNum());
        ato.p = s.curToken().toString();
        
        if (s.curToken().kind == plusToken) {  
            skip (s, plusToken);
        }
        else if (s.curToken().kind == minusToken) {
            skip (s, minusToken);
        }
        leaveParser("term opr");
        return ato;
    }

    @Override
    void prettyPrint() {
        prettyWrite(" " + p + " ");
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
