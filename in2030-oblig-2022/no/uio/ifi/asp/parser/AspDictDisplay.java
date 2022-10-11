package no.uio.ifi.asp.parser;
import java.lang.reflect.Array;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspDictDisplay extends AspAtom {
    ArrayList<AspExpr> exp = new ArrayList<>();
    ArrayList<AspStringLiteral> strl = new ArrayList<>();
    String dictDisplay;

    AspDictDisplay(int n) {
        super(n);
    }

    static AspDictDisplay parse(Scanner s) {
        enterParser("dict display");

        skip(s, leftBraceToken);
        AspDictDisplay add = new AspDictDisplay(s.curLineNum());
        add.dictDisplay = s.curToken().name;
        TokenKind cur = s.curToken().kind;

        while (s.curToken().kind != rightBraceToken) {
            add.strl.add(AspStringLiteral.parse(s));
            if (cur == colonToken) {
                skip(s, colonToken);
            }
            add.exp.add(AspExpr.parse(s));

            //tror denne blir feil
            if (cur == commaToken) {
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
        for (AspExpr expr : exp) {
            if (nPrinted < exp.size()) {
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
