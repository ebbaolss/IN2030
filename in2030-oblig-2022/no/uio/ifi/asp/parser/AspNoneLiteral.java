package no.uio.ifi.asp.parser;

public class AspNoneLiteral extends AspAtom {
    String noneLiteral;

    AspNoneLiteral(int n) {
        super(n);
    }
    
    static AspNoneLiteral parse(Scanner s) {
        enterParser("none literal");
        AspNoneLiteral anl = null;
        TokenKind cur = s.curToken().kind;
        if (cur == noneToken) {
            skip (s, noneToken);
        }
        leaveParser("none literal");
        return anl;
    }

    @Override
    public void prettyPrint() {
        prettyWrite(noneLiteral);
    }


}
