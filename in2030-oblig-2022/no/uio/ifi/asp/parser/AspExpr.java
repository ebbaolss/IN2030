// © 2021 Dag Langmyhr, Institutt for informatikk, Universitetet i Oslo

package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspExpr extends AspSyntax {
    ArrayList<AspAndTest> andTests = new ArrayList<>();

    AspExpr(int n) {
        super(n);
    }

    public static AspExpr parse(Scanner s) {
        enterParser("expr");

        AspExpr ae = new AspExpr(s.curLineNum());

        while (true) {
            ae.andTests.add(AspAndTest.parse(s));
            if (s.curToken().kind != TokenKind.orToken) {
                break;
            } else {
                skip(s, TokenKind.orToken);
            }
        }

        leaveParser("expr");
        return ae;
    }

    @Override
    public
    void prettyPrint() {
        int nPrinted = 0;

        for (AspAndTest aat : andTests) {
            if (nPrinted > 0) {
                prettyWrite(" or ");
            }
            aat.prettyPrint();
            ++nPrinted;
        }

    }

    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        RuntimeValue rt = andTests.get(0).eval(curScope);

        for(int i = 1; i < andTests.size(); i++){
            
            if(rt.getBoolValue("expr", this) && rt != null){
                return rt;
            }
            rt = andTests.get(i).eval(curScope);
        }
        return rt;
    }
}
