package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspSubscription extends AspPrimarySuffix {
    AspExpr exp;
    String subscription;

    AspSubscription(int n) {
        super(n);
    }

    static AspSubscription parse(Scanner s) {
        enterParser("subscription");

        skip(s, TokenKind.leftBracketToken);
        AspSubscription as = new AspSubscription(s.curLineNum());
        as.subscription = s.curToken().name;
        while (s.curToken().kind != rightBracketToken) {
            as.exp = AspExpr.parse(s);
        }
        skip(s, TokenKind.rightBracketToken);
        
        leaveParser("subscription");
        return as;
    }

    @Override
    void prettyPrint() {
        prettyWrite(TokenKind.leftBracketToken.toString());
        exp.prettyPrint();
        prettyWrite(TokenKind.rightBracketToken.toString());
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
