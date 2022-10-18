package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspIfStmt extends AspCompoundStmt{
    ArrayList<AspExpr> expr = new ArrayList<>();
    ArrayList<AspSuite> sui = new ArrayList<>();

    AspIfStmt(int n) {
        super(n);
    }

    public static AspIfStmt parse(Scanner s) {
        enterParser("if stmt");
        AspIfStmt is = new AspIfStmt(s.curLineNum());
        
        skip(s, TokenKind.ifToken);

        boolean f = true;
        while(f == true) {
            is.expr.add(AspExpr.parse(s));
            skip(s, TokenKind.colonToken);
            is.sui.add(AspSuite.parse(s));
            f = false;

            if (s.curToken().kind == TokenKind.elifToken) {
                skip(s, TokenKind.elifToken);
                f = true;
            }
        }
    
        if (s.curToken().kind == TokenKind.elseToken) {
            skip(s, TokenKind.elseToken);
            skip(s, TokenKind.colonToken);
            is.sui.add(AspSuite.parse(s));
        }
        
        leaveParser("if stmt");
        return is;
    }

    @Override
    void prettyPrint() {
        prettyWrite("ifstmmtttt");
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
