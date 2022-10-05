package no.uio.ifi.asp.parser;

public class AspCompOpr extends AspSyntax {
    AspCompOpr(int n) {
        super(n);
    }
    
    static AspCompOpr parse(Scanner s) {
        enterParser("comp opr");
        AspCompOpr aco = null;
        TokenKind cur = s.curToken().kind;
        if (cur == lessToken) {
            skip (s, lessToken);
        }
        else if (cur == greaterToken) {
            skip (s, greaterToken);
        }
        else if (cur == doubleEqualToken) {
            skip (s, doubleEqualToken);
        }
        else if (cur == greaterEqualToken) {
            skip (s, greaterEqualToken);
        }
        else if (cur == lessEqualToken) {
            skip (s, lessEqualToken);
        }
        else if (cur == notEqualToken) {
            skip (s, notEqualToken);
        }
        leaveParser("comp opr");
        return aco;
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
