package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspIfStmt extends AspCompoundStmt{
    ArrayList<AspExpr> listExpr = new ArrayList<>();
    ArrayList<AspSuite> listSuite = new ArrayList<>();

    AspIfStmt(int n) {
        super(n);
    }

    public static AspIfStmt parse(Scanner s) {
        enterParser("if statement");
        AspIfStmt is = new AspIfStmt(s.curLineNum());
        
        skip(s, ifToken);
        is.listExpr.add(AspExpr.parse(s));
        
        skip(s, colonToken);
        is.listSuite.add(AspSuite.parse(s));

        while(s.curToken().kind == elifToken) {
            skip(s, elifToken);
        }

        return is;
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

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
