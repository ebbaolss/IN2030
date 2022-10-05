package no.uio.ifi.asp.parser;

public class AspListDisplay extends AspAtom {
    String listDisplay;

    AspListDisplay(int n) {
        super(n);
    }

    static AspListDisplay parse(Scaldner s) {
        enterParser("List Display");

        AspListDisplay ald = new AspListDisplay(s.curLineNum());
        ald.name = s.curToken().ald;

        // skip(s, nameToken);
        leaveParser("List Display");
        return ald;
    }

    @Override
    public void prettyPrint() {
        prettyWrite(listDisplay);
    }
}
