package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.scanner.Scanner;

public class AspName extends AspAtom {
    String name;
    
    AspName(int n) {
        super(n);
    }

    static AspName parse(Scanner s) {
        enterParser("Name");

        AspName an = new AspName(s.curLineNum());
        an.name = s.curToken().an;

        //skip(s, nameToken);
        leaveParser("Name");
        return an;
    }
    
    @Override
    public void prettyPrint() {
        prettyWrite(name);
    }
}
