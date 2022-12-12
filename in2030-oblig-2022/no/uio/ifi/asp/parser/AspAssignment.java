package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspAssignment extends AspSmallStmt{
    ArrayList<AspSubscription> sub = new ArrayList<>();
    AspName name;
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
        RuntimeValue rt = expr.eval(curScope);
        RuntimeValue rt2 = null;

        if (sub.isEmpty()) {
            if (curScope.hasGlobalName(name.p)) {
                Main.globalScope.assign(name.p, rt);
                trace(name.p + " = " + rt.showInfo());
            } else {
                curScope.assign(name.p, rt);
                trace(name.p + " = " + rt.showInfo());
            }
        }
        else if (!sub.isEmpty()){
            rt2 = name.eval(curScope);
            for (int i = 0; i < sub.size() - 1; i++){
                rt2 = rt2.evalSubscription(sub.get(i).eval(curScope), this);
            }
            RuntimeValue lastPos = sub.get(sub.size() - 1).eval(curScope);
            rt2.evalAssignElem(lastPos, rt, this);
            trace(name.p + "[" + lastPos + "] = " + rt);
            return rt;
        } /*else {
            curScope.assign(name.p, rt);
            trace(name.p + " = " + rt.toString());
            return rt;
        }*/
        return rt;
    }
}
