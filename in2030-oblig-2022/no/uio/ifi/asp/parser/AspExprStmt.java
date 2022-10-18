package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspExprStmt extends AspSmallStmt{
    AspExprStmt exprstmt;

    AspExprStmt(int n) {
        super(n);
    }
    
    static AspExprStmt parse(Scanner s) {
        enterParser("expr stmt");
        
        AspExprStmt aes = new AspExprStmt(s.curLineNum());
        aes.exprstmt = AspExprStmt.parse(s);
        
        leaveParser("expr stmt");
        return aes;
    }

    @Override
    void prettyPrint() {
        prettyWrite("exprstmttm");
    }
}
