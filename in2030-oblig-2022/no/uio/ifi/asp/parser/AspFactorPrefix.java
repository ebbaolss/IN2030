package no.uio.ifi.asp.parser;

public class AspFactorPrefix extends AspSyntax {

    AspFactorPrefix(int n) {
        super(n);
    }
    
    static AspFactorPrefix parse(Scanner s) {
        enterParser("factor prefix");
        AspFactorPrefix afp = null;
        TokenKind cur = s.curToken().kind;
        if (cur == plusToken) {
            skip (s, plusToken);
        }
        else if (cur == minusToken) {
            skip (s, minusToken);
        }
        leaveParser("factor prefix");
        return afp;
    }

    @Override
    void prettyPrint() {
        int nPrinted = 0;
        
        for (AspNotTest ant : notTests) {
            if (nPrinted > 0) {
                prettyWrite(" and ");
            }
            ant.prettyPrint(); 
            ++nPrinted;
        }
    }
}
