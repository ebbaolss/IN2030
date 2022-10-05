package no.uio.ifi.asp.parser;

public class AspBooleanLiteral extends AspAtom {
    boolean booleanLiteral;

    AspBooleanLiteral(int n) {
        super(n);
    }

    static AspBooleanLiteral parse(Scanner s) {
        enterParser("Boolean Literal");

        AspBooleanLiteral abl = new AspBooleanLiteral(s.curLineNum());
        abl.booleanLiteral = s.curToken().abl;

        //mangler false?
        skip(s, trueToken);
        leaveParser("Boolean Literal");
        return abl;
    }

    @Override
    public void prettyPrint() {
        prettyWrite(booleanLiteral);
    }
}
