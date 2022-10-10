package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspSuite extends AspSyntax {
    
    Arraylist<AspSmallStmtList> list = new ArrayList<>();

    AspSuite(int n) {
        super(n);
    }

    static AspSuite parse(Scanner s) {
        enterParser("AspSuite");
        AspSuite as = new AspSuite(s.curLineNum());
    
        if (s.curToken().kind == newLineToken){
            skip(s, TokenKind.newLineToken);
            skip(s, TokenKind.indentToken);
            while(s.curToken().kind != TokenKind.dedentToken) {
                as.list.add(AspStmt.parse(s));
            }
            skip(s, TokenKind.dedentToken);
        } else {
            as.asl = AspSmallStmtList.parse(s);
        }
        leaveParser("AspSuite");
        return as;
        } 

        @Override
        void prettyPrint() {
            int nPrinted = 0;

            for (AspSmallStmtList stmt : list) {
                if (nPrinted > 0) {
                    prettyWrite(" suite ");
                }
                stmt.prettyPrint();
                ++nPrinted;
            }
        }

        @Override
        RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
            RuntimeValue v = list.get(0).eval(curScope);
            for (int i = 1; i < list.size(); ++i) {
                if (!v.getBoolValue("suite", this)) {
                    return v;
                }
                v = list.get(i).eval(curScope);
            }
            return v;
        }
}
