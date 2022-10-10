package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.scanner.Scanner;

public class AspStringLiteral extends AspAtom {
    String stringLiteral;

    AspStringLiteral(String n) {
        super(n);
    }

    static AspStringLiteral parse(Scanner s) {
        enterParser("string literal");

        AspStringLiteral asl = new AspBooleanLiteral(s.curLineNum());
        asl.stringLiteral = s.curToken().asl;

        skip(s, stringToken);
        leaveParser("string literal");
        return asl;
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
