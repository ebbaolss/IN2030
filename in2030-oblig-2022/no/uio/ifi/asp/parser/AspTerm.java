package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspTerm extends AspSyntax {
    ArrayList<AspFactor> fac = new ArrayList<>();
    ArrayList<AspTermOpr> teropr = new ArrayList<>();

    AspTerm(int n) {
        super(n);
    }

    static AspTerm parse(Scanner s) {
        enterParser("term");

        AspTerm ter = new AspTerm(s.curLineNum());

        boolean f = true;
        while (f == true) {
            ter.fac.add(AspFactor.parse(s));
            f = false;
            if (s.isTermOpr()) { 
                ter.teropr.add(AspTermOpr.parse(s));
                f = true;
            } else {
                break;
            }
        }

        leaveParser("term");
        return ter;
    }
    
    @Override
    void prettyPrint() {
        for (int i = 0; i < fac.size(); i++) {
            fac.get(i).prettyPrint();
            
            if (i < teropr.size()) {
                teropr.get(i).prettyPrint();
            }
        }
    }

    @Override
    //hentet fra forelesningsfoiler
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
        RuntimeValue v = fac.get(0).eval(curScope);
        
        for (int i = 1; i < fac.size(); ++i) {
            TokenKind k = teropr.get(i - 1).cur;

            switch(k) {
                case minusToken:
                    v = v.evalSubtract(fac.get(i).eval(curScope), this);
                    break;
                case plusToken:
                    v = v.evalAdd(fac.get(i).eval(curScope), this);
                    break;
                default:
                    Main.panic("Illegal term operator: " + k + "!");
            }
        }
        return v;
    }
}
