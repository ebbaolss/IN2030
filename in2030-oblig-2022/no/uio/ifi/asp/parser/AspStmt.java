package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

abstract class AspStmt extends AspSyntax {
    AspSmallStmtList assl;
    AspCompoundStmt acs;

    AspStmt(int n) {
        super(n);
    }

    static AspStmt parse(Scanner s) {
        enterParser("stmt");

        AspStmt as = null;

        if (s.isCompoundStmt()) {
            as = AspCompoundStmt.parse(s);
        } else {
            as = AspSmallStmtList.parse(s);
        }

        leaveParser("stmt");
        return as;
    }

    @Override
    void prettyPrint() {
        
    }
}
