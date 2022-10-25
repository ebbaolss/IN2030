package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFactorPrefix extends AspSyntax {

    String factorPrefix;
    
    AspFactorPrefix(int n) {
        super(n);
    }
    
    static AspFactorPrefix parse(Scanner s) {
        enterParser("factor prefix");
        AspFactorPrefix afp = new AspFactorPrefix(s.curLineNum());
        afp.factorPrefix = s.curToken().name;
        TokenKind cur = s.curToken().kind;
        
        if (cur == TokenKind.plusToken) {
            skip (s, TokenKind.plusToken);
        }
        else if (cur == TokenKind.minusToken) {
            skip (s, TokenKind.minusToken);
        }
        leaveParser("factor prefix");
        return afp;
    }

    @Override
    public void prettyPrint() {
        prettyWrite(" " + factorPrefix + " ");
    }
    
    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
