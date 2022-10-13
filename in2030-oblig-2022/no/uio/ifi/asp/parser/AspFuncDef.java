package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFuncDef extends AspCompoundStmt{
    ArrayList<AspName> nam = new ArrayList<>();
    ArrayList<AspSuite> sui = new ArrayList<>();
    
    AspFuncDef(int n) {
        super(n);
    }

    static AspFuncDef parse(Scanner s) {

        enterParser("func def");
        AspFuncDef afd = new AspFuncDef(s.curLineNum());
        
        skip(s, defToken);
        afd.nam.add(AspName.parse(s));
        skip(s, leftParToken);
        
        TokenKind cur = s.curToken().kind;
        boolean f = true;

        while (f == true) {
            if (cur == TokenKind.nameToken) {
                afd.nam.add(AspName.parse(s));
            }
            f = false;
            if (cur == TokenKind.commaToken) {
                skip(s, commaToken);
                f = true;
            }
        }

        skip(s, rightParToken);
        skip(s, colonToken);
        afd.sui.add(AspSuite.parse(s));

        leaveParser("func def");
        return afd;
    }
    
    @Override
    void prettyPrint() {
        prettyWrite("funcccy");
    }
}
