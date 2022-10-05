package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.scanner.Scanner;

public class AspFloatLiteral extends AspAtom {
    String floatLiteral;

    AspFloatLiteral(String n) {
        super(n);
    }

    static AspFloatLiteral parse(Scanner s) {
        enterParser("Float Literal");

        AspFloatLiteral afl = new AspFloatLiteral(s.curLineNum());
        afl.floatLiteral = s.curToken().afl;

        skip(s, floatToken);
        leaveParser("String Literal");
        return afl;
    }

    @Override
    public void prettyPrint() {
        prettyWrite(floatLiteral);
    }
}
