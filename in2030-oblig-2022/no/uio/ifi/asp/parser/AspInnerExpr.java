package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.scanner.Scanner;

public class AspInnerExpr extends AspAtom {
    //String stringLiteral;

    AspInnerExpr(String n) {
        super(n);
    }

    static AspInnerExpr parse(Scanner s) {
        enterParser("inner expr");

        AspInnerExpr aie = new AspInnerExpr(s.curLineNum());
        aie.stringLiteral = s.curToken().aie;

        //skip(s, stringToken);
        leaveParser("inner expr");
        return aie;
    }

    @Override
    public void prettyPrint() {
        prettyWrite(stringLiteral);
    }
}

