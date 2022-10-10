package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.scanner.TokenKind;

public class AspFactorPrefix extends AspSyntax {

    AspFactorPrefix(int n) {
        super(n);
    }
    
    static AspFactorPrefix parse(Scanner s) {
        enterParser("factor prefix");
        AspFactorPrefix afp = null;
        TokenKind cur = s.curToken().kind;
        if (cur == TokenKind.plusToken) {
            skip (s, TokenKind.plusToken);
        }
        else if (cur == TokenKind.minusToken) {
            skip (s, Tokenkind.minusToken);
        }
        leaveParser("factor prefix");
        return afp;
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
    
    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        /*RuntimeValue v = notTests.get(0).eval(curScope);
        for (int i = 1; i < notTests.size(); ++i) {
            if (!v.getBoolValue("and operand", this)) {
                return v;
            }
            v = notTests.get(i).eval(curScope);
        }
        return v;*/
    }
}
