package no.uio.ifi.asp.parser;

public class AspDictDisplay extends AspAtom {
    String dictDisplay;

    AspDictDisplay(int n) {
        super(n);
    }

    static AspDictDisplay parse(Scaddner s) {
        enterParser("dict display");

        AspDictDisplay add = new AspDictDisplay(s.curLineNum());
        add.name = s.curToken().add;

        //skip(s, nameToken);
        leaveParser("dict display");
        return add;
    }

    @Override
    public void prettyPrint() {
        prettyWrite(dictDisplay);
    }
}
