package no.uio.ifi.asp.parser;

public class AspFactorOpr extends AspSyntax {
    

    @Override
    void prettyPrint() {
        int nPrinted = 0;
        
        for (AspNotTest ant : notTests) {
            if (nPrinted > 0) {
                prettyWrite(" and ");
            }
            ant.prettyPrint(); 
            ++nPrinted;
        }
    }
}
