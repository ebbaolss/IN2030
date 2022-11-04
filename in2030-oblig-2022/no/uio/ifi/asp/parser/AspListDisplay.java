package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspListDisplay extends AspAtom {
    ArrayList<AspExpr> exp = new ArrayList<>();
    String listDisplay;
    
    AspListDisplay(int n) {
        super(n);
    }

    static AspListDisplay parse(Scanner s) {
        enterParser("list display");
        
        skip(s, leftBracketToken);
        AspListDisplay ald = new AspListDisplay(s.curLineNum());
        ald.listDisplay = s.curToken().name;
        
        if (s.curToken().kind != rightBracketToken) {
            while (true) {
                ald.exp.add(AspExpr.parse(s));
                
                if (s.curToken().kind != TokenKind.commaToken) {
                    break;
                }
                skip(s, commaToken);
            }
        }
        skip(s, rightBracketToken);

        leaveParser("list display");
        return ald;
    }

    @Override
    void prettyPrint() {
        prettyWrite(TokenKind.leftBracketToken.toString());
        
        int cnt = 0;
        if (exp == null) {
            
        }
        else {
            for (AspExpr aspExpr : exp) {
                if (cnt > 0) {
                    prettyWrite(TokenKind.commaToken.toString() + " ");
                }
                aspExpr.prettyPrint();
                cnt++;
            }
        }
        prettyWrite(TokenKind.rightBracketToken.toString());
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        ArrayList<RuntimeValue> runtimeList = new ArrayList<>();

        for (AspExpr ae : exp) {
            runtimeList.add(ae.eval(curScope));
        }
        return new RuntimeListValue(runtimeList);
    }
}
