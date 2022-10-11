package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFactorOpr extends AspSyntax {
    String factorOpr;
    AspFactorOpr(int n) {
        super(n);
    }
    
    static AspFactorOpr parse(Scanner s) {
        enterParser("factor opr");
        AspFactorOpr afo = new AspFactorOpr(s.curLineNum());
        afo.factorOpr = s.curToken().name;
        TokenKind cur = s.curToken().kind;
        if (cur == TokenKind.astToken) {
            skip(s, TokenKind.astToken);
        }
        else if (cur == TokenKind.slashToken) {
            skip(s, TokenKind.slashToken);
        }
        else if (cur == TokenKind.percentToken) {
            skip(s, TokenKind.percentToken);
        }
        else if (cur == TokenKind.doubleSlashToken) {
            skip(s, TokenKind.doubleSlashToken);
        }
        leaveParser("factor opr");
        return afo;
    }

    @Override
    void prettyPrint() {
        prettyWrite(factorOpr);
    }

    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        // -- Must be changed in part 3:
        return null;
    }
}
