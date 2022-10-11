package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspListDisplay extends AspAtom {
    //Arraylist<AspListDisplay> listdisp = new ArrayList<>();
    String listDisplay;
    
    AspListDisplay(int n) {
        super(n);
    }

    static AspListDisplay parse(Scanner s) {
        enterParser("list display");

        AspListDisplay ald = new AspListDisplay(s.curLineNum());
        ald.listDisplay = s.curToken().name;

        skip(s, leftBracketToken);
        
        while (s.curToken().kind != rightBracketToken) {
            //ald.listdisp.add(AspExpr.parse(s));
            if (s.curToken().kind == commaToken) {
                skip(s, commaToken);
            }
        }

        skip(s, rightBracketToken);

        leaveParser("list display");
        return ald;
    }

    @Override
    void prettyPrint() {
        prettyWrite(listDisplay);
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
