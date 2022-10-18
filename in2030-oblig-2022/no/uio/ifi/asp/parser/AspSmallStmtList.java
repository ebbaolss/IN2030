package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspSmallStmtList extends AspStmt{
    ArrayList<AspSmallStmt> smallstmt = new ArrayList<>();
    
    AspSmallStmtList(int n) {
        super(n);
    }

    static AspSmallStmtList parse(Scanner s) {
        enterParser("small stmt list");

        AspSmallStmtList assl = new AspSmallStmtList(s.curLineNum());

        boolean f = true;
        while (f == true) {
            assl.smallstmt.add(AspSmallStmt.parse(s));
            f = false;
            if (s.curToken().kind == TokenKind.semicolonToken) {
                skip(s, TokenKind.semicolonToken);
                f = true;
            }
        }

        TokenKind cur = s.curToken().kind;
        if (cur == TokenKind.semicolonToken) {
            skip(s, TokenKind.semicolonToken);
        }
        skip(s, TokenKind.newLineToken);

        leaveParser("small stmt list");
        return assl;
    }


    @Override
    void prettyPrint() {
        prettyWrite("assl");
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
