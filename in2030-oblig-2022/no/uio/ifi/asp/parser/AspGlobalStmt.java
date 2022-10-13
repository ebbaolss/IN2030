package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspGlobalStmt extends AspSmallStmt{
    ArrayList<AspName> name = new ArrayList<>();

    AspGlobalStmt(int n) {
        super(n);
    }

    static AspGlobalStmt parse(Scanner s) {
        enterParser("global stmt");

        AspGlobalStmt ags = new AspGlobalStmt(s.curLineNum());

        skip(s, TokenKind.globalToken);

        boolean f = true;
        while (f == true) {
            ags.name.add(AspName.parse(s));
            f = false;
            if (s.curToken().kind == TokenKind.commaToken) {
                skip(s, TokenKind.commaToken);
                f = true;
            }
        }
        
        leaveParser("global stmt");
        return ags;
    }
    @Override
    void prettyPrint() {
        prettyWrite(null);
    }
}
