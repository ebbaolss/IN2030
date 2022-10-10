package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.scanner.TokenKind;

public class AspFactorOpr extends AspSyntax {
    
    AspFactorOpr(int n) {
        super(n);
    }
    
    static AspFactorOpr parse(Scanner s) {
        enterParser("factor opr");
        AspFactorOpr afo = null;
        TokenKind cur = s.curToken().kind;
        if (cur == Tokenkind.astToken) {
            skip(s, Tokenkind.astToken);
        }
        else if (cur == Tokenkind.slashToken) {
            skip(s, Tokenkind.slashToken);
        }
        else if (cur == Tokenkind.percentToken) {
            skip(s, Tokenkind.percentToken);
        }
        else if (cur == Tokenkind.doubleSlashToken) {
            skip(s, Tokenkind.doubleSlashToken);
        }
        leaveParser("factor opr");
        return afo;
    }

    @Override
    void prettyPrint() {
        /*int nPrinted = 0;
        
        for (AspNotTest ant : notTests) {
            if (nPrinted > 0) {
                prettyWrite(" and ");
            }
            ant.prettyPrint(); 
            ++nPrinted;
        }*/
    }

    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        // -- Must be changed in part 3:
        return null;
    }
}
