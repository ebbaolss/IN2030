package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspExprStmt extends AspSmallStmt{
    AspExprStmt(int n) {
        super(n);
    }
    
    static AspExprStmt parse(Scanner s) {
        enterParser("expr statement");
        AspExprStmt aes = null;
        TokenKind cur = s.curToken().kind;
        if (cur == expr) {
            aes = AspExpr.parse(s);
        }
        leaveParser("expr statement");
        return aes;
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
