package no.uio.ifi.asp.runtime;

import java.util.ArrayList;
import no.uio.ifi.asp.parser.AspFuncDef;
import no.uio.ifi.asp.parser.AspName;
import no.uio.ifi.asp.parser.AspSyntax;
import no.uio.ifi.asp.main.*;


public class RuntimeFunc extends RuntimeValue {

    ArrayList<AspName> parameters = new ArrayList<>();
    AspFuncDef funcdef;
    RuntimeScope funcScope;
    String name;
    AspName an;
    

    public RuntimeFunc(AspFuncDef def, RuntimeScope scope, String v) {
        funcdef = def;
        funcScope = scope;
        name = v;
    }

    public RuntimeFunc(String v) {
        name = v;
    }

    @Override
    String typeName() {
        return "Func";
    }

    @Override
    public String toString(){
        return name;
    }

    public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
        

        if (actualParams == null){
            actualParams = new ArrayList<RuntimeValue>();
        }

        // Check parameters
        checkNumParams(actualParams, parameters.size(), "int", where);
        
        if (parameters.size() != actualParams.size()) {
            runtimeError("func error ", where);
        }

        // Create new scope
        RuntimeScope newScope = new RuntimeScope(funcScope);
        
        for (int index = 0; index < parameters.size(); index++) {
            String fp = parameters.get(index).p;
            newScope.assign(fp, actualParams.get(index));
        }

        try {
            funcdef.sui.eval(newScope);
        } 
        catch (RuntimeReturnValue rrv) {
            return rrv.value;
        }
        return new RuntimeNoneValue();
    }

    private void checkNumParams(ArrayList<RuntimeValue> actArgs, int nCorrect, String id, AspSyntax where) {
        if (actArgs.size() != nCorrect) {
            RuntimeValue.runtimeError("Wrong number of parameters to " + id + "!", where);
        }
    }
}
