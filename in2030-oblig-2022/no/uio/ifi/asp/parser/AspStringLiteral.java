package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspStringLiteral extends AspAtom {
    AspStringLiteral ast;
    String stringLit;

    AspStringLiteral(int n) {
        super(n);
    }

    static AspStringLiteral parse(Scanner s) {
        enterParser("string literal");

        AspStringLiteral asl = new AspStringLiteral(s.curLineNum());
        asl.stringLit = s.curToken().stringLit;
        s.readNextToken();

        leaveParser("string literal");
        return asl;
    }

    @Override
    void prettyPrint() {

        if (stringLit.length() == 0) {
            prettyWrite("\"\"");
            return;
        }
        
        for (int i = 0; i < stringLit.length(); i++) {
            if (stringLit.charAt(i) == '\"') {
                prettyWrite("\'");
                prettyWrite(stringLit);
                prettyWrite("\'");
            } else {
                prettyWrite("\"");
                prettyWrite(stringLit);
                prettyWrite("\"");
            }
            return;
        }
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return new RuntimeStringValue(stringLit);
    }
}
