package no.uio.ifi.asp.parser;

public class AspIntegerLiteral extends AspAtom {
    String integerLiteral;

    AspIntegerLiteral(int n) {
        super(n);
    }

    static AspIntegerLiteral parse(Scanner s) {
        enterParser("Integer Literal");

        AspIntegerLiteral ail = new AspIntegerLiteral(s.curLineNum());
        ail.integerLiteral = s.curToken().ail;

        skip(s, integerToken);
        leaveParser("Integer Literal");
        return ail;
    }

    @Override
    public void prettyPrint() {
        prettyWrite(integerLiteral);
    }
}
