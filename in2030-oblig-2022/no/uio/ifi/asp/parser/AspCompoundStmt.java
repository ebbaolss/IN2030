package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

abstract class AspCompoundStmt extends AspStmt {
    
    AspCompoundStmt(int n) {
        super(n);
    }
    
    static AspCompoundStmt parse(Scanner s) {
        enterParser("compound stmt");
        
        AspCompoundStmt acs = null;
        
        if (s.curToken().kind == TokenKind.forToken) {
            acs = AspForStmt.parse(s);
        }
        else if (s.curToken().kind == TokenKind.ifToken) {
            acs = AspIfStmt.parse(s);
        }
        else if (s.curToken().kind == TokenKind.whileToken) {
            acs = AspWhileStmt.parse(s);
        }
        else if (s.curToken().kind == TokenKind.defToken) {
            acs = AspFuncDef.parse(s);
        }
        else {
            parserError("aspcompopr", s.curLineNum());
        }
        
        leaveParser("compound stmt");
        return acs;
    }

    @Override
    void prettyPrint() {
        prettyWrite("cmpndstmt");
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
