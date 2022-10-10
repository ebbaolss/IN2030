package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspCompOpr extends AspSyntax {
    String CompOpr;

    AspCompOpr(int n) {
        super(n);
    }
    
    static AspCompOpr parse(Scanner s) {
        enterParser("comp opr");
        AspCompOpr aco = null;
        TokenKind cur = s.curToken().kind;
        if (cur == TokenKind.lessToken) {
            skip (s, TokenKind.lessToken);
        }
        else if (cur == greaterToken) {
            skip (s, greaterToken);
        }
        else if (cur == doubleEqualToken) {
            skip (s, doubleEqualToken);
        }
        else if (cur == greaterEqualToken) {
            skip (s, greaterEqualToken);
        }
        else if (cur == lessEqualToken) {
            skip (s, lessEqualToken);
        }
        else if (cur == notEqualToken) {
            skip (s, notEqualToken);
        }
        leaveParser("comp opr");
        return aco;
    }

    @Override
    void prettyPrint() {
        prettyWrite(CompOpr);
    }
}
