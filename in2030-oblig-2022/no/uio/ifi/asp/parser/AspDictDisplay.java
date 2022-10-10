package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.scanner.TokenKind;

public class AspDictDisplay extends AspAtom {
    ArrayList<AspDictDisplay> dictdisp = new ArrayList<>();
    String dictDisplay;

    AspDictDisplay(int n) {
        super(n);
    }

    static AspDictDisplay parse(Scaddner s) {
        enterParser("dict display");

        skip(s, TokenKind.leftBraceToken);
        AspDictDisplay add = new AspDictDisplay(s.curLineNum());
        add.name = s.curToken().add;

        while (s.curToken().kind != TokenKind.rightBraceToken) {
            aa.dictdisp.add(AspStringLiteral.parse(s));
            if (s.curToken().kind == TokenKind.colonToken) {
                skip(s, TokenKind.colonToken);
            }
            aa.dictdisp.add(AspExpr.parse(s));

            //tror denne blir feil
            if (s.curToken.kind == TokenKind.commaToken) {
                skip(s, TokenKind.commaToken);
            }
        }

        skip(s, TokenKind.rightBraceToken);
        leaveParser("dict display");
        return add;
    }

    @Override
    void prettyPrint() {
        /*
         * int nPrinted = 0;
         * 
         * for (AspNotTest ant : notTests) {
         * if (nPrinted > 0) {
         * prettyWrite(" and ");
         * }
         * ant.prettyPrint();
         * ++nPrinted;
         * }
         */
    }
}
