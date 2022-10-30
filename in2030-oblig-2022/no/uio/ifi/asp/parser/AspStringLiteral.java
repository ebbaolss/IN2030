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
        if (stringLit.charAt(0) == '\"') {
            prettyWrite("\'");
            prettyWrite(stringLit);
            prettyWrite("\'");
        }
        else {
            prettyWrite("\"");
            prettyWrite(stringLit);
            prettyWrite("\"");
        }

    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
