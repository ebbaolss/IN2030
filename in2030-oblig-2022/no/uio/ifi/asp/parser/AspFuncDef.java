package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFuncDef extends AspCompoundStmt{
    ArrayList<AspName> nam = new ArrayList<>();
    AspName name;
    AspSuite sui;
    String p;
    
    AspFuncDef(int n) {
        super(n);
    }

    static AspFuncDef parse(Scanner s) {
        enterParser("func def");

        AspFuncDef afd = new AspFuncDef(s.curLineNum());
        afd.p = s.curToken().name;
        skip(s, defToken);
        afd.name = AspName.parse(s);
        skip(s, leftParToken);
        
        while (s.curToken().kind != TokenKind.rightParToken) {
            afd.nam.add(AspName.parse(s));
            
            if(s.curToken().kind != TokenKind.commaToken) {
                break;
            }
            
            skip(s, TokenKind.commaToken);
        }

        skip(s, rightParToken);
        skip(s, colonToken);
        afd.sui = AspSuite.parse(s);

        leaveParser("func def");
        return afd;
    }
    
    @Override
    void prettyPrint() {
        prettyWrite(p + " ");
        name.prettyPrint();
        prettyWrite(" (");
        int cnt = 0;
        for (AspName aspName : nam) {
            if (cnt > 0) {
                prettyWrite(", ");
            }
            aspName.prettyPrint();

        }
        prettyWrite(")");
        prettyWrite(":");
        sui.prettyPrint();
    }
}
