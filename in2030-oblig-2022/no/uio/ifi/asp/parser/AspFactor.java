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

    AspFactor(int n) {
        super(n);
    }

    public static AspFactor parse(Scanner s) {
        enterParser("factor");
        AspFactor fac = new AspFactor(s.curLineNum());

        if (s.isFactorPrefix()) {
            fac.facpre.add(AspFactorPrefix.parse(s));
        } else {
            fac.facpre.add(null);
        }
        fac.prim.add(AspPrimary.parse(s));

        if (s.isFactorOpr()) {
            fac.facopr.add(AspFactorOpr.parse(s));
        }
        
        leaveParser("factor");
        return fac;
    }

    @Override
    void prettyPrint() {
        prettyWrite(null);
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
