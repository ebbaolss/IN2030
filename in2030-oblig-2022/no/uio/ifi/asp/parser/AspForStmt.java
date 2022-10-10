package no.uio.ifi.asp.parser;

public class AspForStmt extends AspCompoundStmt{
    
    AspForStmt() {

    }

    @Override
    void prettyPrint() {
        /*
         * int nPrinted = 0;
         * 
         * for (AspNotTest ant : notTests) {
         * if (nPrinted > 0) {
         * prettyWrite(" and ");
         * }
         * ant.prettyPrint();
         * ++nPrinted;
         * }
         */
    }

    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        // -- Must be changed in part 3:
        return null;
    }
}
