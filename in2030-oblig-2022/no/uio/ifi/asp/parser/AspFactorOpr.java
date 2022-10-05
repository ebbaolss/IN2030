package no.uio.ifi.asp.parser;

public class AspFactorOpr extends AspSyntax {
    
    AspFactorOpr(int n) {
        super(n);
    }
    
    static AspFactorOpr parse(Scanner s) {
        enterParser("factor opr");
        AspFactorOpr afo = null;
        TokenKind cur = s.curToken().kind;
        if (cur == astToken) {
            skip (s, astToken);
        }
        else if (cur == slashToken) {
            skip (s, slashToken);
        }
        else if (cur == percentToken) {
            skip (s, percentToken);
        }
        else if (cur == doubleSlashToken) {
            skip (s, doubleSlashToken);
        }
        leaveParser("factor opr");
        return afo;
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
