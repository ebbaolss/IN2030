package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

abstract class AspCompoundStmt extends AspStmt {
    //String compoundStmt;
    
    AspCompoundStmt(int n) {
        super(n);
    }
    
    static AspCompoundStmt parse(Scanner s) {
        enterParser("compound stmt");
        AspCompoundStmt acs = null;
        //acs.compoundStmt = s.curToken().name;
        TokenKind cur = s.curToken().kind;
        
        if (cur == TokenKind.forToken) {
            acs = AspForStmt.parse(s);
            return acs;
        }
        else if (cur == TokenKind.ifToken) {
            acs = AspIfStmt.parse(s);
            return acs;
        }
        else if (cur == TokenKind.whileToken) {
            acs = AspWhileStmt.parse(s);
        }
        else if (cur == TokenKind.defToken) {
            acs = AspFuncDef.parse(s);
            return acs;
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
