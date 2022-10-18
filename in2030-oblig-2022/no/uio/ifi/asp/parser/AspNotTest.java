package no.uio.ifi.asp.parser;
import java.util.ArrayList;

import javax.swing.RowFilter.ComparisonType;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspNotTest extends AspSyntax {
    ArrayList<AspComparison> com = new ArrayList<>();
    
    AspNotTest(int n) {
        super(n);
    }

    static AspNotTest parse(Scanner s) {
        enterParser("not test");

        AspNotTest ant = new AspNotTest(s.curLineNum());
        TokenKind cur = s.curToken().kind;
        
        if (cur == TokenKind.notToken) {
            skip(s, TokenKind.notToken);
        }
        ant.com.add(AspComparison.parse(s));
        
        leaveParser("not test");
        return ant;
    }

    @Override
    void prettyPrint() {
        
    }

    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        // -- Must be changed in part 3:
        return null;
    }
}
