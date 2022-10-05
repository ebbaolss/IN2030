package no.uio.ifi.asp.parser;

public class AspNoneLiteral extends AspAtom {
    
    String noneLiteral;

    AspNoneLiteral(int n) {
        super(n);
    }

    static AspNoneLiteral parse(Scanner s) {
        enterParser("None Literal");

        AspNoneLiteral anl = new AspNoneLiteral(s.curLineNum());
        anl.name = s.curToken().anl;

        skip(s, noneToken);
        leaveParser("None Literal");
        return anl;
    }

    @Override
    public void prettyPrint() {
        prettyWrite(noneLiteral);
    }


}
