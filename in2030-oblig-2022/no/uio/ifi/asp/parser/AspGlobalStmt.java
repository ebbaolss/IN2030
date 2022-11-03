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
        ags.name = new ArrayList <> ();
        skip(s, TokenKind.globalToken);
        ags.name.add(AspName.parse(s));
        
        while (s.curToken().kind == TokenKind.commaToken) {
            skip(s, TokenKind.commaToken);
            ags.name.add(AspName.parse(s));
        }

        leaveParser("global stmt");
        return ags;
    }

    @Override
    void prettyPrint() {
        
        prettyWrite("global ");
        int cnt = 0;
        for (AspName aspName : name) {
            if (cnt > 0) {
                prettyWrite(", ");
            }
            aspName.prettyPrint();
        }
    }
}
