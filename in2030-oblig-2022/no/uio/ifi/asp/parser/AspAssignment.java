package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspAssignment extends AspSmallStmt{
    AspName name;
    ArrayList<AspSubscription> sub = new ArrayList<>();
    AspExpr expr;
    
    AspAssignment(int n) {
        super(n);
    }

    static AspAssignment parse(Scanner s) {
        enterParser("assignment");

        AspAssignment aa = new AspAssignment(s.curLineNum());
        aa.name = AspName.parse(s);

        TokenKind cur = s.curToken().kind;
        while (cur == TokenKind.equalToken) {
            aa.sub.add(AspSubscription.parse(s));
        }
        skip(s, TokenKind.equalToken);
        aa.expr = AspExpr.parse(s);

        leaveParser("assignment");
        return aa;
    } 
}
