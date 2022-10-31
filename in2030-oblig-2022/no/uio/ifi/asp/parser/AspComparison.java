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

        if (comopr.size() == 0) {
            ter.get(0).prettyPrint();
        } else {
            for (AspTerm aspTerm : ter) {
                aspTerm.prettyPrint();
                if (comopr.size() > cnt) {
                    comopr.get(cnt).prettyPrint();
                }
                cnt++;
            }
        }
    }

    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        // -- Must be changed in part 3:
        //return null;
        RuntimeValue v = ter.get(0).eval(curScope);
        
        for (int i = 1; i < ter.size(); ++i) {
            TokenKind k = comopr.get(i - 1).kind;

            switch(k) {
                case lessToken:
                    v = v.evalLess(ter.get(i).eval(curScope), this);
                    break;
                case greaterToken:
                    v = v.evalGreater(ter.get(i).eval(curScope), this);
                    break;
                case doubleEqualToken:
                    v = v.evalEqual(ter.get(i).eval(curScope), this);
                    break;
                case greaterEqualToken:
                    v = v.evalGreaterEqual(ter.get(i).eval(curScope), this);
                    break;
                case lessEqualToken:
                    v = v.evalLessEqual(ter.get(i).eval(curScope), this);
                    break;
                case notEqualToken:
                    v = v.evalNotEqual(ter.get(i).eval(curScope), this);  
                    break;
                default:
                    Main.panic("Illegal comp operator: " + k + "!");       
            }
        }
        return v;
    }
}
