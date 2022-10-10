package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspSubscription extends AspPrimarySuffix {
    ArrayList<AspExpr> exp = new ArrayList<>();
    String subscription;

    AspSubscription(int n) {
        super(n);
    }

    static AspSubscription parse(Scanner s) {
        enterParser("subscription");

        skip(s, leftBracketToken);
        AspSubscription as = new AspSubscription(s.curLineNum());
        as.subscription = s.curToken().as;
        while (s.curToken().kind != rightBracketToken) {
            as.exp.add(AspExpr.parse(s));
        }
        skip(s, rightBracketToken);
        
        leaveParser("subscription");
        return as;
    }

    @Override
    void prettyPrint() {
        
        int nPrinted = 0;
        for (AspExpr x : exp) {
            if (nPrinted > 0) {
                prettyWrite(" expr ");
            }
            x.prettyPrint();
            ++nPrinted;
        }
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
