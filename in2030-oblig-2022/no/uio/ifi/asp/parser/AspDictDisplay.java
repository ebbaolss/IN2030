package no.uio.ifi.asp.parser;
import java.lang.reflect.Array;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import java.util.HashMap;
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

        while (s.curToken().kind != TokenKind.rightBraceToken) {
            add.strl.add(AspStringLiteral.parse(s));
            skip(s, TokenKind.colonToken);
            add.exp.add(AspExpr.parse(s));

            if (s.curToken().kind != TokenKind.commaToken) {
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
        prettyWrite(TokenKind.leftBraceToken.toString());

        for (int i = 0; i < strl.size(); i++) {
            if (i > 0) {
                prettyWrite(TokenKind.commaToken.toString() + " ");
            }

            strl.get(i).prettyPrint();
            prettyWrite(TokenKind.colonToken.toString());
            exp.get(i).prettyPrint();
        }
        prettyWrite(TokenKind.rightBraceToken.toString());
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        HashMap<String, RuntimeValue> hMap = new HashMap<>();
        
        if(!strl.isEmpty()){
            for (int i = 0; i < strl.size() ; i++ ) {
                hMap.put(strl.get(i).eval(curScope).toString(), exp.get(i).eval(curScope));
            }
        }
        return new RuntimeDictValue(hMap);

    }   
}
