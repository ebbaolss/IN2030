package no.uio.ifi.asp.parser;
import java.lang.reflect.Array;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspDictDisplay extends AspAtom {
    ArrayList<AspExpr> exp = new ArrayList<>();
    ArrayList<AspStringLiteral> strl = new ArrayList<>();
    String dictDisplay;

    AspDictDisplay(int n) {
        super(n);
    }

    static AspDictDisplay parse(Scanner s) {
        enterParser("dict display");

        skip(s, leftBraceToken);
        AspDictDisplay add = new AspDictDisplay(s.curLineNum());
        add.dictDisplay = s.curToken().name;
        TokenKind cur = s.curToken().kind;

        while (s.curToken().kind != TokenKind.rightBraceToken) {
            add.strl.add(AspStringLiteral.parse(s));
            skip(s, TokenKind.colonToken);
            add.exp.add(AspExpr.parse(s));

            if (cur != TokenKind.commaToken) {
                break;
            } else {
                skip(s, TokenKind.commaToken);
            }
        }
        skip(s, TokenKind.rightBraceToken);

        leaveParser("dict display");
        return add;
    }

    @Override
    void prettyPrint() {
        prettyWrite(TokenKind.leftBracketToken.toString());
        int cnt = 0;
        for (AspStringLiteral aspStringLiteral : strl) {
            if (cnt > 0) {
                prettyWrite(TokenKind.commaToken.toString() + " ");
            }
            aspStringLiteral.prettyPrint();
            prettyWrite(TokenKind.colonToken.toString());
            exp.get(0).prettyPrint();
        }
        prettyWrite(TokenKind.rightBracketToken.toString());
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
        //RuntimeDictValue returnDictValue = new RuntimeDictValue(new ArrayList<RuntimeValue>());

        //return returnDictValue;
    }
}
