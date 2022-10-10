package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.scanner.TokenKind;

public class AspArguments extends AspPrimarySuffix {
    String arguments;

    AspArguments(int n) {
        super(n);
    }

    static AspArguments parse(Scaaner s) {
        enterParser("arguments");
        skip(s, TokenKind.leftParToken);
        AspArguments aa = new AspArguments(s.curLineNum());
        //aa.arguments = s.curToken().aa;
        while (s.curToken().kind != TokenKind.rightParToken) {
            aa.exprs.add(AspExpr.parse(s));
            if (s.curToken().kind == TokenKind.commaToken) {
                skip(s, TokenKind.commaToken);
            }
        }

        skip(s, TokenKind.rightParToken);
        leaveParser("Arguments");
        return aa;
    }

    @Override
    public void prettyPrint() {
        int nPrinted = 0;
        for (AspExpr expr : exprs) {
            if (nPrinted < exprs.size()) {
                prettyWrite(",");
            }
            ++nPrinted;
        }
        prettyWrite(arguments);
    }
}
