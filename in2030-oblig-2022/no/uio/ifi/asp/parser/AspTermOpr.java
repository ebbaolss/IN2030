package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspTermOpr extends AspSyntax {
    
    AspTermOpr(int n) {
        super(n);
    }
    
    static AspTermOpr parse(Scanner s) {
        enterParser("term opr");
        AspTermOpr ato = null;
        TokenKind cur = s.curToken().kind;
        if (cur == plusToken) {
            skip (s, plusToken);
        }
        else if (cur == minusToken) {
            skip (s, minusToken);
        }
        leaveParser("term opr");
        return ato;
    }

    @Override
    void prettyPrint() {
        /*int nPrinted = 0;
        
        for (AspNotTest ant : notTests) {
            if (nPrinted > 0) {
                prettyWrite(" and ");
            }
            ant.prettyPrint(); 
            ++nPrinted;
        }*/
    }
}
