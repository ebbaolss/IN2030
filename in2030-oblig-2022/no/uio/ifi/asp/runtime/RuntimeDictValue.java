package no.uio.ifi.asp.runtime;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;
import java.util.HashMap;

public class RuntimeDictValue extends RuntimeValue {
    HashMap<String, RuntimeValue> dict;

    public RuntimeDictValue(HashMap<String, RuntimeValue> v) {
        dict = v;
    }

    @Override
    protected String typeName() {
        return "dictionary";
    }

    public String showInfo() {
        return toString();
    }

    @Override
    public String toString() {
        return String.valueOf(dict);
    }

    @Override
    public boolean getBoolValue(String what, AspSyntax where) {
        if (dict.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String getStringValue(String what, AspSyntax where) {
        return what;
    }

    @Override
    public RuntimeValue evalNot(AspSyntax where) {
        if (dict.size() == 0) {
            return new RuntimeBoolValue(true);
        }
        return new RuntimeBoolValue(false);
    }

    public RuntimeValue evalSubscription(RuntimeValue v, AspSyntax where) {
        /*RuntimeValue value = null;
        if (v instanceof RuntimeStringValue) {
            value = dict.get(v.showInfo());
        }
        return value;*/
        return null;
    }

}