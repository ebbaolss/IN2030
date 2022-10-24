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
        int cnt = 0;

        for (AspFactor aspFactor : fac) {
            aspFactor.prettyPrint();

            if (teropr.size() > cnt) {
                teropr.get(cnt).prettyPrint();
            }

            cnt++;
        }
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
