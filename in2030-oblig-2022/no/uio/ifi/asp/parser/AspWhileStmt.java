package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspWhileStmt extends AspCompoundStmt {
    AspExpr test;
    AspSuite body;
    String p;

    AspWhileStmt(int n) {
        super(n);
    }

    static AspWhileStmt parse(Scanner s) {
        enterParser("while stmt");

        AspWhileStmt aws = new AspWhileStmt(s.curLineNum()); 
        aws.p = TokenKind.whileToken.toString();

        skip(s, whileToken); 

        aws.test = AspExpr.parse(s); 
        skip(s, colonToken); 
        aws.body = AspSuite.parse(s);
        
        leaveParser("while stmt");
        return aws; 
    }

    @Override
    void prettyPrint() {
        prettyWrite(p + " ");
        test.prettyPrint();
        prettyWrite(TokenKind.colonToken.toString() + " ");
        body.prettyPrint();
    }
}