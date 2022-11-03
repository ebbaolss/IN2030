package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFactorOpr extends AspSyntax {
    Token factorOpr;
    
    AspFactorOpr(int n) {
        super(n);
    }
    
    static AspFactorOpr parse(Scanner s) {
        enterParser("factor opr");
        
        AspFactorOpr afo = new AspFactorOpr(s.curLineNum());
        afo.factorOpr = s.curToken();
        if (s.curToken().kind == TokenKind.astToken) {
            skip(s, TokenKind.astToken);
        }
        else if (s.curToken().kind == TokenKind.doubleSlashToken) {
            skip(s, TokenKind.doubleSlashToken);

        }
        else if (s.curToken().kind == TokenKind.slashToken) {
            skip(s, TokenKind.slashToken);
        }
        else if (s.curToken().kind == TokenKind.percentToken) {
            skip(s, TokenKind.percentToken);
        }
        
        leaveParser("factor opr");
        return afo;
    }

    @Override
    void prettyPrint() {
        prettyWrite(" " + factorOpr.toString() + " ");
        
    }

    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        // -- Must be changed in part 3:
        return null;
    }
}
