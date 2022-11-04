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

    public String showInfo() {
        return "" + value;
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
            int valueV = (int) v.getIntValue("* operand", where);
            int length = value.size() * valueV;
            ArrayList<RuntimeValue> returnList = new ArrayList<>(length);
            for (int j = 0; j < length; j++) {
                for (int i = 0; i < value.size(); i++) {
                    returnList.add(value.get(i));
                }
            }
            return new RuntimeListValue(returnList);
        }
        return null;
    }

    public RuntimeValue evalSubscription(RuntimeValue v, AspSyntax where) {
        System.out.println("jeg er her ");
        RuntimeValue v1 = null;
        if (v instanceof RuntimeIntegerValue) {
            int i = (int) (v.getIntValue("[]", where));
            v1 = value.get(i);
        }
        return v1;
    }

}