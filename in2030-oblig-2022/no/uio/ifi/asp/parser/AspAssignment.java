package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspAssignment extends AspSmallStmt{
    AspName name;
    ArrayList<AspSubscription> sub = new ArrayList<>();
    AspExpr expr;
    boolean bool = false;
    
    AspAssignment(int n) {
        super(n);
    }

    static AspAssignment parse(Scanner s) {
        enterParser("assignment");

        AspAssignment aa = new AspAssignment(s.curLineNum());
        aa.name = AspName.parse(s);

        while (s.curToken().kind != equalToken) {
            aa.sub.add(AspSubscription.parse(s));
            aa.bool = true;
        }

        skip(s, equalToken);

        aa.expr = AspExpr.parse(s);

        leaveParser("assignment");
        return aa;
    } 

    @Override
    void prettyPrint() {
        name.prettyPrint();
        
        if (bool == false) {
            for (AspSubscription aspSubscription : sub) {
                aspSubscription.prettyPrint();
            }
        }
        prettyWrite(" " + TokenKind.equalToken.toString() + " ");
        expr.prettyPrint();
    }

    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        RuntimeValue v = expr.eval(curScope);
        String trace = name.p;
        
        if (bool == false) {
            for (AspSubscription aspSubscription : sub) {
                trace += " " + aspSubscription.exp.toString();
                curScope.assign(name.p, v);
            }
        }
        else {
            for (int i = 0; i < sub.size(); i++) {
                trace += "[" + sub.get(i).eval(curScope);
                curScope.assign(name.p, v);
            }
            trace += "]";
        }       
        if (v != null) {
            trace += " = " + v.toString();
        }
        curScope.assign(name.p, v);
        curScope.find(name.p, this);
        trace(trace);

        //assign the last element in the sub-listen 
        //v.evalAssignElem(sub.get(sub.size() - 1).eval(curScope), v, this);
        
        return v;
    }
}
