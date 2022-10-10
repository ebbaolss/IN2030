package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.scanner.Scanner;
import no.uio.ifi.asp.scanner.TokenKind;

public class AspFloatLiteral extends AspAtom {
    String floatLiteral;

    AspFloatLiteral(String n) {
        super(n);
    }

    static AspFloatLiteral parse(Scanner s) {
        enterParser("float literal");

        AspFloatLiteral afl = new AspFloatLiteral(s.curLineNum());
        //afl.floatLiteral = s.curToken().afl;

        skip(s, TokenKind.floatToken);
        leaveParser("float literal");
        return afl;
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
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        // -- Must be changed in part 3:
        return null;
    }
}
