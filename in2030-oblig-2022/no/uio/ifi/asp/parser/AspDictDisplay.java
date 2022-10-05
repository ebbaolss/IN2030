package no.uio.ifi.asp.parser;

public class AspDictDisplay extends AspAtom {
    String dictDisplay;

    AspDictDisplay(int n) {
        super(n);
    }

    static AspDictDisplay parse(Scaddner s) {
        enterParser("Dict Display");

        AspDictDisplay add = new AspDictDisplay(s.curLineNum());
        add.name = s.curToken().add;

        //skip(s, nameToken);
        leaveParser("Dict Display");
        return add;
    }

    @Override
    public void prettyPrint() {
        prettyWrite(dictDisplay);
    }
}
