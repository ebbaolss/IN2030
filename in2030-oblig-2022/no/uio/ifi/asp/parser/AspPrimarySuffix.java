package no.uio.ifi.asp.parser;

public abstract class AspPrimarySuffix extends AspSyntax {
    
    AspPrimarySuffix(int n) {
        super(n);
    }
    
    static AspPrimarySuffix parse(Scanner s) {
        enterParser("primary suffix");
        AspPrimarySuffix aps = null;
        TokenKind cur = s.curToken().kind;
        /*if (cur == ) {
            aps = AspExpr.parse(s);
        }*/
        leaveParser("primary suffix");
        return aps;
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
