package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspComparison extends AspSyntax {
    ArrayList<AspTerm> ter = new ArrayList<>();
    ArrayList<AspCompOpr> comopr = new ArrayList<>();
    
    AspComparison(int n) {
        super(n);
    }

    static AspComparison parse(Scanner s) {
        enterParser("comparison");

        AspComparison com = new AspComparison(s.curLineNum());

        boolean f = true;
        while (f == true) {
            com.ter.add(AspTerm.parse(s));
            f = false;
            if (s.isCompOpr()) {
                com.comopr.add(AspCompOpr.parse(s));
                f = true;
            } else {
                break;
            }
        }

        leaveParser("comparison");
        return com;
    }

    @Override
    void prettyPrint() {
        int cnt = 0;

        for (AspTerm aspTerm : ter) {
            aspTerm.prettyPrint();
            if (comopr.size() > cnt) {
                comopr.get(cnt).prettyPrint();
            }
            cnt++;
        }
    }

    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        // -- Must be changed in part 3:
        return null;
    }
}
