package no.uio.ifi.asp.parser;

public class AspArguments extends AspPrimarySuffix {
    String arguments;

    AspArguments(int n) {
        super(n);
    }

    static AspArguments parse(Scaaner s) {
        enterParser("Arguments");

        AspArguments aa = new AspArguments(s.curLineNum());
        aa.arguments = s.curToken().aa;

        //skip(s, arg);
        leaveParser("Arguments");
        return aa;
    }

    @Override
    public void prettyPrint() {
        prettyWrite(arguments);
    }
}
