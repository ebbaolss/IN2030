package no.uio.ifi.asp.runtime;

import java.util.ArrayList;

import no.uio.ifi.asp.parser.AspFuncDef;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeFunc extends RuntimeValue{
    AspFuncDef def;
    RuntimeScope defScope;
    String name;

    @Override
    String typeName() {
        // TODO Auto-generated method stub
        return name;
    }

    public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
        try {
            def.eval(defScope);
            // def.body.eval(newScope); rett fra siste forelesning. får den ikke til å fungere
        } catch (RuntimeReturnValue rrv) {
            return rrv.value;
        }
        return new RuntimeNoneValue();
    }

}
