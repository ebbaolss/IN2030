package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.scanner.TokenKind;

public class AspSubscription extends AspPrimarySuffix {
    ArrayList<AspSubscription> subs = new ArrayList<>();
    String subscription;

    AspSubscription(String n) {
        super(n);
    }

    static AspSubscription parse(Scanner s) {
        enterParser("subscription");
        skip(s, TokenKind.leftBracketToken);
        AspSubscription as = new AspSubscription(s.curLineNum());
        as.subscription = s.curToken().as;
        while (s.curToken().kind != TokenKind.rightBracketToken) {
            aa.subs.add(AspExpr.parse(s));
        }

        skip(s, TokenKind.rightBracketToken);
        leaveParser("subscription");
        return as;
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
