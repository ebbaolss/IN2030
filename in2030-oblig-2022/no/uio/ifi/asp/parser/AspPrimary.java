package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspPrimary extends AspSyntax {
    ArrayList<AspPrimarySuffix> prisuf = new ArrayList<>();
    AspAtom atm = null;
    String primary;

    AspPrimary(int n) {
        super(n);
    }

    static AspPrimary parse(Scanner s) {
        enterParser("primary");

        AspPrimary ap = new AspPrimary(s.curLineNum());
        ap.primary = s.curToken().name;
        ap.atm = AspAtom.parse(s);

        TokenKind cur = s.curToken().kind;

        if(cur == TokenKind.leftParToken|| cur == TokenKind.leftBracketToken) {
            ap.prisuf.add(AspPrimarySuffix.parse(s));
        }
            
        leaveParser("primary");
        return ap;
    }
    
    @Override
    void prettyPrint() {
        prettyWrite(primary);
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
