package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

abstract class AspCompoundStmt extends AspStmt {
    AspCompoundStmt(int n) {
        super(n);
    }
    
    static AspCompoundStmt parse(Scanner s) {
        enterParser("compound stmt");
        AspCompoundStmt acs = null;
        TokenKind cur = s.curToken().kind;
        if (cur == TokenKind.forToken) {
            acs = AspForStmt.parse(s);
        }
        else if (cur == TokenKind.ifToken) {
            acs = AspIfStmt.parse(s);
        }
        else if (cur == TokenKind.ifToken) {
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
        /*
         * int nPrinted = 0;
         * 
         * for (AspNotTest ant : notTests) {
         * if (nPrinted > 0) {
         * prettyWrite(" and ");
         * }
         * ant.prettyPrint();
         * ++nPrinted;
         * }
         */
    }
}
