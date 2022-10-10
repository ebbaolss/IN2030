package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.scanner.TokenKind;

public class AspListDisplay extends AspAtom {
    Arraylist<AspListDisplay> listdisp = new ArrayList<>();
    String listDisplay;

    AspListDisplay(int n) {
        super(n);
    }

    static AspListDisplay parse(Scaldner s) {
        enterParser("list display");

        AspListDisplay ald = new AspListDisplay(s.curLineNum());
        ald.name = s.curToken().ald;

        skip(s, TokenKind.leftBracketToken);
        
        while (s.curToken().kind != TokenKind.rightBracketToken) {
            ald.listdisp.add(AspExpr.parse(s));
            if (s.curToken().kind == TokenKind.commaToken) {
                skip(s, TokenKind.commaToken);
            }
        }

        skip(s, TokenKind.rightBracketToken);

        leaveParser("list display");
        return ald;
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
