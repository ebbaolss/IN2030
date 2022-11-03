package no.uio.ifi.asp.runtime;
import java.util.ArrayList;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeListValue extends RuntimeValue {
    ArrayList<RuntimeValue> value;

    public RuntimeListValue(ArrayList<RuntimeValue> v) {
        value = v;
    }

    @Override
    protected String typeName() {
        return "[]";
    }
    
    @Override
    public boolean getBoolValue(String what, AspSyntax where) {
        if (value == null) {
            return false;
        }
        return true;
    }

    @Override
    public RuntimeValue evalLen(AspSyntax where) {
        return new RuntimeIntegerValue(value.size());
    }

    @Override
    public String getStringValue(String what, AspSyntax where) {
        runtimeError("Type error: " + what + " is not a text string!", where);
        return value.toString();
    }

    @Override
    public RuntimeValue evalNot(AspSyntax where) {
        if (value == null) {
            return new RuntimeBoolValue(true);
        }
        return new RuntimeBoolValue(false);
    }
    
    public RuntimeValue evalMultiply(RuntimeValue v, AspSyntax where) {
        if (v instanceof RuntimeIntegerValue) {
            //int valueV = (int) v.getIntValue("* operand", where);
            //int length = value.size()* valueV;
            ArrayList<RuntimeValue> returnList = new ArrayList<>();
            for (int j = 0; j<v.getIntValue(v.toString(), where); j++) {
                for (int i = 0; i < value.size(); i++) {
                    //RuntimeValue a = value.get(i).toString().repeat((int) v.getIntValue("*", where));
                    returnList.add(value.get(i));
                }
            }
            return new RuntimeListValue(returnList);
        }
        return null;
    }
}
