package no.uio.ifi.asp.parser;

public class AspSubscription extends AspPrimarySuffix {
    //String subscription;

    AspFloatLiteral(String n) {
        super(n);
    }

    static AspSubscription parse(Scanner s) {
        enterParser("subscription");

        AspSubscription as = new AspSubscription(s.curLineNum());
        as.subscription = s.curToken().as;

        //skip(s, );
        leaveParser("subscription");
        return as;
    }

    @Override
    public void prettyPrint() {
        prettyWrite(subscription);
    }
}
