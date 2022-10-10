package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspArguments extends AspPrimarySuffix {
    ArrayList<AspExpr> exp = new ArrayList<>();
    String arguments;

    AspArguments(int n) {
        super(n);
    }

    static AspArguments parse(Scaaner s) {
        enterParser("arguments");

        skip(s, leftParToken);
        AspArguments aa = new AspArguments(s.curLineNum());
        aa.arguments = s.curToken().aa;
        while (s.curToken().kind != rightParToken) {
            aa.exp.add(AspExpr.parse(s));
            if (s.curToken().kind == commaToken) {
                skip(s, commaToken);
            }
        }
        skip(s, rightParToken);

        leaveParser("Arguments");
        return aa;
    }

    @Override
    public void prettyPrint() {
        int nPrinted = 0;
        for (AspExpr expr : exprs) {
            if (nPrinted < exprs.size()) {
                prettyWrite(",");
            }
            ++nPrinted;
        }
        prettyWrite(arguments);
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
