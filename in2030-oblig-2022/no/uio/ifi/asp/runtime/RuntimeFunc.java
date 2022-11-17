package no.uio.ifi.asp.runtime;

import java.util.ArrayList;
import no.uio.ifi.asp.parser.AspFuncDef;
import no.uio.ifi.asp.parser.AspSyntax;
import no.uio.ifi.asp.main.*;


public class RuntimeFunc extends RuntimeValue{
    AspFuncDef def;
    RuntimeScope defScope;
    String name;

    public RuntimeFunc(String v) {
        name = v;
    }

    @Override
    String typeName() {
        // TODO Auto-generated method stub
        return name;
    }

    public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
        try {
            def.eval(defScope);
            RuntimeScope newScope = new RuntimeScope(defScope);
            
            AspFuncDef du;
        } catch (RuntimeReturnValue rrv) {
            return rrv.value;
        }
        return new RuntimeNoneValue();
    }
}
