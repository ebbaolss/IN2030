package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFactor extends AspSyntax {
    ArrayList<AspFactorPrefix> facpre = new ArrayList<>();
    ArrayList<AspFactorOpr> facopr = new ArrayList<>();
    ArrayList<AspPrimary> prim = new ArrayList<>();
    static Boolean b = false;

    AspFactor(int n) {
        super(n);
    }

    public static AspFactor parse(Scanner s) {
        enterParser("factor");
        AspFactor fac = new AspFactor(s.curLineNum());

        boolean f = true;
        while (f == true) {
            if (s.isFactorPrefix()) {
                fac.facpre.add(AspFactorPrefix.parse(s));
                b = true;
            }
            fac.prim.add(AspPrimary.parse(s));

            f = false;
            if (s.isFactorOpr()) {
                fac.facopr.add(AspFactorOpr.parse(s));
                f = true;
            }
            
        }
        

        
        leaveParser("factor");
        return fac;
    }

    @Override
    void prettyPrint() {
        
        int cnt = 0;
        
        for (AspPrimary aspPrimary : prim) {
            if (cnt > 0) {
                facopr.get(cnt).prettyPrint();
            }
            if (facpre.size() > cnt) {
                facpre.get(cnt).prettyPrint();
            }
            aspPrimary.prettyPrint();
            cnt++;
        }
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
