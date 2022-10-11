package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

abstract class AspCompoundStmt extends AspStmt {

    String compoundStmt;
    AspCompoundStmt(int n) {
        super(n);
    }
    
    static AspCompoundStmt parse(Scanner s) {
        enterParser("compound stmt");
        AspCompoundStmt acs = null;
        acs.compoundStmt = s.curToken().name;
        TokenKind cur = s.curToken().kind;
        if (cur == TokenKind.forToken) {
            acs = AspForStmt.parse(s);
        }
        else if (cur == TokenKind.ifToken) {
            acs = AspIfStmt.parse(s);
        }
        else if (cur == TokenKind.ifToken) {
            acs = AspWhileStmt.parse(s);
        }
        else {
            acs = AspFuncDef.parse(s);
        }
        leaveParser("compound stmt");
        return acs;
    }

    @Override
    void prettyPrint() {
        prettyWrite(null);
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
