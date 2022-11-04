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
        return toString();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public String getStringValue(String what, AspSyntax where) {
        //runtimeError("Type error: " + what + " is not a text string!", where);
        //return value.toString();
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
    
    /* 
    @Override
    public RuntimeValue evalEqual(RuntimeValue v, AspSyntax where) {
        if (v instanceof RuntimeIntegerValue) {
            return new RuntimeBoolValue(value == v.getIntValue("== operand", where));
        } else if (v instanceof RuntimeFloatValue) {
            return new RuntimeBoolValue(integerValue == v.getFloatValue("== operand", where));
        } else if (v instanceof RuntimeNoneValue) {
            return new RuntimeBoolValue(integerValue == v.getFloatValue("== operand", where));
        }
        runtimeError("Type error for '=='", where);
        return null;    
    }

    @Override
    public RuntimeValue evalNotEqual(RuntimeValue v, AspSyntax where) {
        if (v instanceof RuntimeIntegerValue) {
            return new RuntimeBoolValue(integerValue != v.getIntValue("!= operand", where));
        } else if (v instanceof RuntimeFloatValue) {
            return new RuntimeBoolValue(integerValue != v.getFloatValue("!= operand", where));
        } else if (v instanceof RuntimeNoneValue) {
            return new RuntimeBoolValue(integerValue == v.getFloatValue("!= operand", where));
        }
        runtimeError("Type error for '!='", where);
        return null;    
    }
    */

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
            int length = value.size()* valueV;
            ArrayList<RuntimeValue> returnList = new ArrayList<>(length);
            //v.getIntValue(v.toString(), where)
            for (int j = 0; j < length; j++) {
                for (int i = 0; i < value.size(); i++) {
                    //RuntimeValue a = value.get(i).toString().repeat((int) v.getIntValue("*", where));
                    returnList.add(value.get(i));
                }
            }
            return new RuntimeListValue(returnList);
        }
        return null;
    }

    public RuntimeValue evalSubscription(RuntimeValue v, AspSyntax where) {
        runtimeError("Subscription '[...]' undefined for " + typeName() + "!", where);
        return null;  // Required by the compiler!
    }

    public void evalAssignElem(RuntimeValue inx, RuntimeValue val, AspSyntax where) {
        if (inx instanceof RuntimeIntegerValue){
            int index = Integer.parseInt(inx.showInfo());
            //arrayList sin index = val
        }
        else{
            runtimeError("Type error for assignElem.", where);
        }
    }
}
