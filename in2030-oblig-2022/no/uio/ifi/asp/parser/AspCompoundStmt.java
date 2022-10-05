package no.uio.ifi.asp.parser;

abstract class AspCompoundStmt extends AspStmt {
    AspCompoundStmt(int n) {
        super(n);
    }
    
    static AspCompoundStmt parse(Scanner s) {
        enterParser("compound stmt");
        AspCompoundStmt acs = null;
        TokenKind cur = s.curToken().kind;
        if (cur == forToken) {
            acs = AspForStmt.parse(s);
        }
        else if (cur == ifToken) {
            acs = AspIfStmt.parse(s);
        }
        else if (cur == whileToken) {
            acs = AspWhileStmt.parse(s);
        }
        else {
            acs = AspFuncDef.parse(s);
        }
        leaveParser("compound stmt");
        return acs;
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
