package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspPrimary extends AspSyntax {
    ArrayList<AspPrimarySuffix> prisuf = new ArrayList<>();
    AspAtom atm;
    String primary;
    TokenKind kind;

    AspPrimary(int n) {
        super(n);
    }

    static AspPrimary parse(Scanner s) {
        enterParser("primary");

        AspPrimary ap = new AspPrimary(s.curLineNum());
        ap.primary = s.curToken().name;
        ap.atm = AspAtom.parse(s);
        ap.kind = s.curToken().kind;
        
        if (s.curToken().kind == TokenKind.leftParToken || s.curToken().kind == TokenKind.leftBracketToken) {
            ap.prisuf.add(AspPrimarySuffix.parse(s));
        }

        leaveParser("primary");
        return ap;
    }
    
    @Override
    void prettyPrint() {
        atm.prettyPrint();
        
        if (prisuf == null) {

        } else {
            for (AspPrimarySuffix aspPrimarySuffix : prisuf) {
                aspPrimarySuffix.prettyPrint();
            }
        }
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        RuntimeValue v = atm.eval(curScope);
        String trace = "";
        
        if (prisuf.isEmpty()) {
            
        }
        else {
            for (AspPrimarySuffix aspPrimarySuffix : prisuf) {
                RuntimeValue w = aspPrimarySuffix.eval(curScope); // verdien til f
                ArrayList<RuntimeValue> liste = new ArrayList<>();

                if (aspPrimarySuffix instanceof AspSubscription) {
                    v = v.evalSubscription(aspPrimarySuffix.eval(curScope), this);
                } 
                else if (aspPrimarySuffix instanceof AspArguments) { // arguments aka. en funksjon
                    RuntimeListValue args = (RuntimeListValue) v;
                    System.out.println(args.getStringValue("trace", this));
                    //trace = "Call function " + primary + " with params " + w;
                    trace = "Call function " + v.showInfo() + " with params [" + args.getStringValue("", this);
                    ArrayList<RuntimeValue> lv = args.getListValue();
                    System.out.println(args.getListValue());


                    for (int i = 0; i < lv.size(); i++) {
                        liste.add(lv.get(i));

                        if (i < lv.size() - 1) {
                            trace += lv.get(i).showInfo() + ", ";
                        } else {
                            trace += lv.get(i).showInfo() + "]";
                        }
                    }

                    if (lv.size() == 0) {
                        trace += "]";
                    }
                    
                    //Main.log.traceEval(trace, this);
                    trace(trace);
                    v = v.evalFuncCall(liste, aspPrimarySuffix);
                    
                }
            }
        }

        if (v instanceof RuntimeNoneValue) {
            trace = "None";
            trace(trace);
        }
        return v;
    }
}
