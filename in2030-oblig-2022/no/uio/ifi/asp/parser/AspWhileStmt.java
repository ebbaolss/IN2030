package no.uio.ifi.asp.parser;

public class AspWhileStmt extends AspCompoundStmt {
    
    static AspWhileStmt parse (Scanner s) {
        //Sjekk at vi har lest while
        AspExpr.parse(s);
        //Sjekk at vi har lest
        AspSuite.parse(s);
    }
}
