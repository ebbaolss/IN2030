package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFactorPrefix extends AspSyntax {
    String factorPrefix;
    TokenKind kind;
    
    AspFactorPrefix(int n) {
        super(n);
    }
    
    static AspFactorPrefix parse(Scanner s) {
        enterParser("factor prefix");
        
        AspFactorPrefix afp = new AspFactorPrefix(s.curLineNum());
        afp.factorPrefix = s.curToken().name;
        afp.kind = s.curToken().kind;
        
        if (s.curToken().kind == TokenKind.plusToken) {
            skip (s, TokenKind.plusToken);
            afp.factorPrefix = "+";
            
            leaveParser("factor prefix");
            return afp;
        }
        else if (s.curToken().kind == TokenKind.minusToken) {
            skip (s, TokenKind.minusToken);
            afp.factorPrefix = "-";
            
            leaveParser("factor prefix");
            return afp;
        }
        return afp;
    }

    @Override
    public void prettyPrint() {
        prettyWrite(factorPrefix + " ");
    }
    
    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return new RuntimeStringValue(factorPrefix);
    }
}
