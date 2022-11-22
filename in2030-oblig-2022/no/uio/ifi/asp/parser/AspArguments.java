package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspArguments extends AspPrimarySuffix {
    ArrayList<AspExpr> exp = new ArrayList<>();
    String arguments;

    AspArguments(int n) {
        super(n);
    }

    static AspArguments parse(Scanner s) {
        enterParser("arguments");

        skip(s, leftParToken);
        AspArguments aa = new AspArguments(s.curLineNum());
        aa.arguments = s.curToken().name;
        
        while (s.curToken().kind != rightParToken) {
            aa.exp.add(AspExpr.parse(s));
            if (s.curToken().kind == commaToken) {
                skip(s, commaToken);
            }
        }
        skip(s, rightParToken);

        leaveParser("arguments");
        return aa;
    }

    @Override
    public void prettyPrint() {
        prettyWrite(TokenKind.leftParToken.toString());
        
        if (exp == null) {
            
        } else {
            int cnt = 0;
            for (AspExpr aspExpr : exp) {
                if (cnt > 0) {
                    prettyWrite(TokenKind.commaToken.toString() + " ");
                }
                aspExpr.prettyPrint();
                cnt++;
            }
        }
        prettyWrite(TokenKind.rightParToken.toString());
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        ArrayList<RuntimeValue> valueList = new ArrayList<>();

        for (AspExpr v : exp) {
            valueList.add(v.eval(curScope));
        }
        

        return new RuntimeListValue(valueList);
    }
}
