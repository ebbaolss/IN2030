package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspDictDisplay extends AspAtom {
    ArrayList<AspExpr> exp = new ArrayList<>();
    String dictDisplay;

    AspDictDisplay(int n) {
        super(n);
    }

    static AspDictDisplay parse(Scanner s) {
        enterParser("dict display");

        skip(s, leftBraceToken);
        AspDictDisplay add = new AspDictDisplay(s.curLineNum());
        add.dictDisplay = s.curToken().add;

        while (s.curToken().kind != rightBraceToken) {
            aa.dictdisp.add(AspStringLiteral.parse(s));
            if (s.curToken().kind == colonToken) {
                skip(s, colonToken);
            }
            aa.dictdisp.add(AspExpr.parse(s));

            //tror denne blir feil
            if (s.curToken.kind == commaToken) {
                skip(s, commaToken);
            }
        }
        skip(s, rightBraceToken);

        leaveParser("dict display");
        return add;
    }

    @Override
    void prettyPrint() {
        int nPrinted = 0;
        for (AspExpr expr : exprs) {
            if (nPrinted < exprs.size()) {
                prettyWrite(",");
            }
            ++nPrinted;
        }
        prettyWrite(dictDisplay);
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
