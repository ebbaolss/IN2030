package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspIfStmt extends AspCompoundStmt{
    ArrayList<AspExpr> expr = new ArrayList<>();
    ArrayList<AspSuite> sui = new ArrayList<>();
    AspSuite sui2;
    boolean b = false;

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
            } else {
                break;
            }
        }
        
        if (s.curToken().kind == TokenKind.elseToken) {
            skip(s, TokenKind.elseToken);
            skip(s, TokenKind.colonToken);
            is.sui2 = AspSuite.parse(s);
            //is.sui.add(is.sui2);
            is.b = true;
        }
        
        leaveParser("if stmt");
        return is;
    }

    @Override
    void prettyPrint() {
        prettyWrite(TokenKind.ifToken.toString() + " ");

        int cnt = 0;
        for (AspExpr aspExpr : expr) {
            if (cnt > 0) {
                prettyWrite(TokenKind.elifToken.toString());
            }
            aspExpr.prettyPrint();
            prettyWrite(TokenKind.colonToken.toString() + " ");
            if (sui.size() > cnt) {
                sui.get(cnt).prettyPrint();
            }
            cnt++;
        }

        if (b) {
            prettyWrite(TokenKind.elseToken.toString());
            prettyWrite(TokenKind.colonToken.toString() + " ");
            sui2.prettyPrint();
        }    
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        int cnt = 0;
        boolean bool = false;
        RuntimeValue v = null;
        String trace = "";
        for (AspExpr aspExpr : expr) {
            if (cnt > 0) {
                trace += " elif ";
            }
            v = aspExpr.eval(curScope);
            if (v.getBoolValue("if stmt", this)) {
                trace += "if " + v.toString() + " alt #" + (cnt+1) + ": ";
                if (sui.size() > cnt) {
                    trace += "...";
                    trace(trace);
                    v = sui.get(cnt).eval(curScope); //denne er null
                    bool = true;
                    return v;
                }
            }
            cnt++;
        }
        
        if (b) {
            trace("else: ...");
            v = sui2.eval(curScope);
        }
        return v;
    }
}
