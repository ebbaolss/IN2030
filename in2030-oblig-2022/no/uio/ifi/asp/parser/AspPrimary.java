package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspPrimary extends AspSyntax {
    ArrayList<AspPrimarySuffix> prisuf = new ArrayList<>();
    AspAtom atm;
    String primary;
    TokenKind kind;

    AspPrimary(int n) {
        super(n);
    }

    static AspPrimary parse(Scanner s) {
        enterParser("primary");

        AspPrimary ap = new AspPrimary(s.curLineNum());
        ap.primary = s.curToken().name;
        ap.atm = AspAtom.parse(s);
        ap.kind = s.curToken().kind;
        if(s.curToken().kind == TokenKind.leftParToken|| s.curToken().kind == TokenKind.leftBracketToken) {
            ap.prisuf.add(AspPrimarySuffix.parse(s));
        }
            
        leaveParser("primary");
        return ap;
    }
    
    @Override
    void prettyPrint() {
    
        atm.prettyPrint();
        
        if (prisuf == null) {
        }

        else {
            for (AspPrimarySuffix aspPrimarySuffix : prisuf) {
                aspPrimarySuffix.prettyPrint();
            }
        }
        
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        RuntimeValue v = atm.eval(curScope);

        for (AspPrimarySuffix aspPrimarySuffix : prisuf) {
            if (aspPrimarySuffix instanceof AspSubscription) {
                v = v.evalSubscription(aspPrimarySuffix.eval(curScope), this);
            } 
            else {
                //del4
            }
        }
        return v;
    }
}
