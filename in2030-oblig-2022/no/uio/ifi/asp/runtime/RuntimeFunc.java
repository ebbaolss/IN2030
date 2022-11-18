package no.uio.ifi.asp.runtime;

import java.util.ArrayList;
import no.uio.ifi.asp.parser.AspFuncDef;
import no.uio.ifi.asp.parser.AspName;
import no.uio.ifi.asp.parser.AspSyntax;
import no.uio.ifi.asp.main.*;


public class RuntimeFunc extends RuntimeValue{
    AspFuncDef def;
    RuntimeScope defScope;
    String name;
    AspName an;

    public RuntimeFunc(String v) {
        name = v;
    }

    @Override
    String typeName() {
        // TODO Auto-generated method stub
        return name;
    }

    public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
        //denne må endres
        ArrayList<AspName> parameters = def.nam;

        if (actualParams.size() == parameters.size()) {
            System.out.println("yess");
        }

        // Create new scope
        RuntimeScope newScope = new RuntimeScope(defScope);

        // Assign the arguments
        for (int i = 0; i < actualParams.size(); i++) {
            newScope.assign(parameters.get(i).p, actualParams.get(i));
        }

        try {
            def.sui.eval(newScope);
        } catch (RuntimeReturnValue rrv) {
            return rrv.value;
        }

        return new RuntimeNoneValue();
        /*try {

            def.eval(defScope);
            RuntimeScope newScope = new RuntimeScope(defScope);
            
            AspFuncDef du;
        } catch (RuntimeReturnValue rrv) {
            return rrv.value;
        }
        return new RuntimeNoneValue();*/
    }
}
