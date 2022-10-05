package no.uio.ifi.asp.parser;

public class AspNotTest extends AspSyntax {
    

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
