package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.scanner.Scanner;

public class AspInnerExpr extends AspAtom {
    //String stringLiteral;

    AspInnerExpr(String n) {
        super(n);
    }

    static AspInnerExpr parse(Scanner s) {
        enterParser("String Literal");

        AspInnerExpr aie = new AspInnerExpr(s.curLineNum());
        aie.stringLiteral = s.curToken().aie;

        //skip(s, stringToken);
        leaveParser("Inner Expresion");
        return aie;
    }

    @Override
    public void prettyPrint() {
        prettyWrite(stringLiteral);
    }
}

