package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspSmallStmtList extends AspStmt{
    ArrayList<AspSmallStmt> smallstmt = new ArrayList<>();
    String p;
    
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

        if (s.curToken().kind == TokenKind.semicolonToken) {
            skip(s, TokenKind.semicolonToken);
            assl.p = TokenKind.semicolonToken.toString();
        }
        skip(s, TokenKind.newLineToken);

        leaveParser("small stmt list");
        return assl;
    }


    @Override
    void prettyPrint() {
        int cnt = 0;
        for (AspSmallStmt aspSmallStmt : smallstmt) {
            if (cnt > 0) {
                prettyWrite(TokenKind.semicolonToken.toString() + " ");
            }
            aspSmallStmt.prettyPrint();
            cnt++;
        }
        prettyWriteLn();
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        for (AspSmallStmt aspSmallStmt : smallstmt) {
            aspSmallStmt.eval(curScope);
        }
        return null;


    }
}
