package no.uio.ifi.asp.parser;

public class AspBooleanLiteral extends AspAtom {
    String booleanLiteral;

    AspBooleanLiteral(int n) {
        super(n);
    }
    
    static AspBooleanLiteral parse(Scanner s) {
        enterParser("boolean literal");
        AspBooleanLiteral abl = null;
        TokenKind cur = s.curToken().kind;
        if (cur == trueToken) {
            skip (s, trueToken);
        }
        else if (cur == falseToken) {
            skip (s, falseToken);
        }
        leaveParser("boolean literal");
        return abl;
    }

    @Override
    public void prettyPrint() {
        prettyWrite(booleanLiteral);
    }
}
