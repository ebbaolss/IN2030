package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspInnerExpr extends AspAtom {
    AspExpr exp;
    String innerExpr;

    AspInnerExpr(int n) {
        super(n);
    }

    static AspInnerExpr parse(Scanner s) {
        enterParser("inner expr");

        skip(s, leftParToken);
        AspInnerExpr aie = new AspInnerExpr(s.curLineNum());
        aie.innerExpr = s.curToken().name;

        aie.exp = AspExpr.parse(s);
        skip(s, rightParToken);
        
        leaveParser("inner expr");
        return aie;
    }

    @Override
    void prettyPrint() {
        prettyWrite(TokenKind.leftParToken.toString());
        exp.prettyPrint();
        prettyWrite(TokenKind.rightParToken.toString());
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return exp.eval(curScope);
    }
}

