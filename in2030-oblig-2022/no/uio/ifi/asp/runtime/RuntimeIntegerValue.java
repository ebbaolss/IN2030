package no.uio.ifi.asp.runtime;
import java.util.ArrayList;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeIntegerValue extends RuntimeValue {
    long integerValue;

    public RuntimeIntegerValue(long v) {
        integerValue = v;
    }
    
    @Override
    String typeName() {
        return "integer";
    }

    /*@Override 
    public String toString() {
        return String.valueOf(integerValue);
    }*/

    @Override
    public String getStringValue(String what, AspSyntax where) {
        return Long.toString(integerValue);
    }

    @Override
    public boolean getBoolValue(String what, AspSyntax where) {
        if(integerValue == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public double getFloatValue(String what, AspSyntax where) {
        return (double)integerValue;
    }

    @Override
    public long getIntValue(String what, AspSyntax where) {
        return integerValue;
    }

    public RuntimeValue evalNegate(RuntimeValue v, AspSyntax where) {
        return new RuntimeIntegerValue(-1 * integerValue);
    }

    @Override
    public RuntimeValue evalNot(AspSyntax where) {
        if (integerValue == 0) {
            return new RuntimeBoolValue(true);
        }
        return new RuntimeBoolValue(false);
    }
    
    //Basert på koden utdelt fra forelesningen
    @Override
    public RuntimeValue evalAdd(RuntimeValue v, AspSyntax where) {
        if (v instanceof RuntimeIntegerValue) {
            return new RuntimeIntegerValue(integerValue + v.getIntValue("+", where));
        }
        else if (v instanceof RuntimeFloatValue) {
            return new RuntimeFloatValue(integerValue + v.getFloatValue("+", where));
        } 
        runtimeError("Type error for '+'" + typeName() + "!", where);
        return null;
    }

    @Override
    public RuntimeValue evalSubtract(RuntimeValue v, AspSyntax where) {
        if (v instanceof RuntimeIntegerValue) {
            return new RuntimeIntegerValue(integerValue - v.getIntValue("- operand", where));
        }
        else if (v instanceof RuntimeFloatValue) {
            return new RuntimeFloatValue(integerValue - v.getFloatValue("- operand", where));
        }
        runtimeError("Type error for '-'", where);
        return null;
    }



    @Override
    public RuntimeValue evalEqual(RuntimeValue v, AspSyntax where) {
        if (v instanceof RuntimeIntegerValue) {
            //If equal, sets to true
            return new RuntimeBoolValue(integerValue == v.getIntValue("== operand", where));
        } 
        else if (v instanceof RuntimeFloatValue) {
            //If equal, sets to false
            return new RuntimeBoolValue(integerValue == v.getFloatValue("== operand", where));
        }
        runtimeError("Type error for '=='", where);
        return null;    
    }


    @Override
    public RuntimeValue evalNotEqual(RuntimeValue v, AspSyntax where) {
        if (v instanceof RuntimeIntegerValue) {
            return new RuntimeBoolValue(integerValue != v.getIntValue("!= operand", where));
        } 
        else if (v instanceof RuntimeFloatValue) {
            return new RuntimeBoolValue(integerValue != v.getFloatValue("!= operand", where));
        }
        runtimeError("Type error for '!='", where);
        return null;    
    }


    @Override 
    public RuntimeValue evalIntDivide(RuntimeValue v, AspSyntax where) {
        if (v instanceof RuntimeIntegerValue) { 
            return new RuntimeIntegerValue(Math.floorDiv(integerValue, v.getIntValue("// operand", where)));
        } 
        else if (v instanceof RuntimeFloatValue) {
            return new RuntimeFloatValue(Math.floor(integerValue / v.getFloatValue("// operand", where)));
        }
        runtimeError("Type error for '//'", where);
        return null;
    }

    @Override 
    public RuntimeValue evalDivide(RuntimeValue v, AspSyntax where) {
        if (v instanceof RuntimeIntegerValue) { 
            return new RuntimeFloatValue(integerValue / v.getIntValue("/ operand", where));
        } 
        else if (v instanceof RuntimeFloatValue) {
            return new RuntimeFloatValue(integerValue / v.getFloatValue("/ operand", where));
        }   
        runtimeError("Type error for '/'", where);
        return null;
    }
    

    public RuntimeValue  evalMultiply(RuntimeValue v, AspSyntax where) {
        if (v instanceof RuntimeIntegerValue) {
            return new RuntimeIntegerValue(integerValue * v.getIntValue("* operand", where));
        } 
        else if (v instanceof RuntimeFloatValue) {
            return new RuntimeFloatValue(integerValue * v.getFloatValue("* operand", where));
        }
        runtimeError("Type error for '*'", where);
        return null;    
    }

    @Override
    public RuntimeValue evalModulo(RuntimeValue v, AspSyntax where) {
        if (v instanceof RuntimeIntegerValue) {
            return new RuntimeIntegerValue(integerValue % v.getIntValue("% operand", where));
        } 
        else if (v instanceof RuntimeFloatValue) {
            return new RuntimeFloatValue(integerValue % v.getFloatValue("% operand", where));
        }
        runtimeError("Type error for '%'", where);
        return null;
    }


    //Return positiv verdi (dersom får negativ)
    @Override
    public RuntimeValue evalPositive(AspSyntax where) {
        if (integerValue < 0) {
            return new RuntimeIntegerValue(-1 * integerValue);
        }
        return new RuntimeIntegerValue(integerValue);    
    }

    //Return negativ verdi
    @Override
    public RuntimeValue evalNegate(AspSyntax where) {
        return new RuntimeIntegerValue(-1 * integerValue);    
    }

    @Override 
    public RuntimeValue evalLess(RuntimeValue v, AspSyntax where) {
        if (v instanceof RuntimeIntegerValue) { 
            return new RuntimeBoolValue(integerValue < v.getIntValue("< operand", where));
        } 
        else if (v instanceof RuntimeFloatValue) {
            return new RuntimeBoolValue(integerValue < v.getFloatValue("< operand", where));
        }
        runtimeError("Type error for '<'", where);
        return null;
    }

    @Override 
    public RuntimeValue evalLessEqual(RuntimeValue v, AspSyntax where) {
        if (v instanceof RuntimeIntegerValue) { 
            return new RuntimeBoolValue(integerValue <= v.getIntValue("<= operand", where));
        } 
        else if (v instanceof RuntimeFloatValue) {
            return new RuntimeBoolValue(integerValue <= v.getFloatValue("<= operand", where));
        }
        runtimeError("Type error for '<='", where);
        return null;
    }

    @Override 
    public RuntimeValue evalGreater(RuntimeValue v, AspSyntax where) {
        if (v instanceof RuntimeIntegerValue) { 
            return new RuntimeBoolValue(integerValue > v.getIntValue("> operand", where));
        } 
        else if (v instanceof RuntimeFloatValue) {
            return new RuntimeBoolValue(integerValue > v.getFloatValue("> operand", where));
        }
        runtimeError("Type error for '>'", where);
        return null;
    }

    @Override 
    public RuntimeValue evalGreaterEqual(RuntimeValue v, AspSyntax where) {
        if (v instanceof RuntimeIntegerValue) { 
            return new RuntimeBoolValue(integerValue >= v.getIntValue(">= operand", where));
        } 
        else if (v instanceof RuntimeFloatValue) {
            return new RuntimeBoolValue(integerValue >= v.getFloatValue(">= operand", where));
        }
        runtimeError("Type error for '>='", where);
        return null;
    }
}
