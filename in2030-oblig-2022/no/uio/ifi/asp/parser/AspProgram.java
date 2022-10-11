// © 2021 Dag Langmyhr, Institutt for informatikk, Universitetet i Oslo

package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspProgram extends AspSyntax {
    //-- Must be changed in part 2:
    //ArrayList<AspStmt> stmts = new ArrayList<>();

    AspProgram(int n) {
        super(n);
    }


    public static AspProgram parse(Scanner s) {
        enterParser("program");

        AspProgram ap = new AspProgram(s.curLineNum());
        while (s.curToken().kind != eofToken) {
            //-- Must be changed in part 2:
            //ap.stmts.add(AspStmt.parse(s));
        }
        if (s.curToken().kind == eofToken) {
            skip(s, eofToken);
        }

        leaveParser("program");
        return ap;
    }

    @Override
    void prettyPrint() {
        prettyWrite(null);
    }

    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 4:
	return null;
    }
}
