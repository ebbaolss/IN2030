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

        } else {
            for (AspPrimarySuffix aspPrimarySuffix : prisuf) {
                aspPrimarySuffix.prettyPrint();
            }
        }
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        RuntimeValue v = atm.eval(curScope);
        String trace = "";

        for (AspPrimarySuffix aspPrimarySuffix : prisuf) {
            RuntimeValue w = aspPrimarySuffix.eval(curScope); //verdien til f
            ArrayList<RuntimeValue> liste = new ArrayList<>();
            
            if (aspPrimarySuffix instanceof AspSubscription) {
                v = v.evalSubscription(aspPrimarySuffix.eval(curScope), this);
            } else { //arguments aka. en funksjon
                RuntimeListValue args = (RuntimeListValue) w;
                trace = "Call function " + primary + " with params " + w;
                ArrayList<RuntimeValue> lv = args.getListValue();

                for (int i = 0; i < lv.size(); i++) {
                    liste.add(lv.get(i));
                }
                v = v.evalFuncCall(liste, aspPrimarySuffix);
                trace(trace);
            }
        }

        if (v instanceof RuntimeNoneValue) {
            trace = "None";
            trace(trace);
        }
        return v;
    }
}
