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
    public String showInfo() {
        return value.toString();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public String getStringValue(String what, AspSyntax where) {
        return what;
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
    public RuntimeValue evalNot(AspSyntax where) {
        if (value == null) {
            return new RuntimeBoolValue(true);
        }
        return new RuntimeBoolValue(false);
    }

    public RuntimeValue evalMultiply(RuntimeValue v, AspSyntax where) {
        if (v instanceof RuntimeIntegerValue) {
            long valueV = v.getIntValue("* Operator", where);
            RuntimeListValue newList = new RuntimeListValue(new ArrayList<RuntimeValue>());
            for (int i = 0; i < valueV; i++) {
                newList.value.addAll(value);
            }
            return newList;
        }
        return null;
    }

    public ArrayList<RuntimeValue> getListValue(String what, AspSyntax where) {
        if (value.isEmpty()){
            return null;
        } else {
            return value;
        }
    }

    public RuntimeValue evalSubscription(RuntimeValue v, AspSyntax where) {
        RuntimeValue v1 = null;
        
        if (v instanceof RuntimeIntegerValue) {
            int i = (int) (v.getIntValue("[]", where));
            v1 = value.get(i);
        }
        return v1;
    }

}