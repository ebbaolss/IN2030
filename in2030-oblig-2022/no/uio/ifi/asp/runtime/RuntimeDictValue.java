package no.uio.ifi.asp.runtime;

import java.util.HashMap;

public class RuntimeDictValue extends RuntimeValue {
    HashMap<String,RuntimeValue> dict;
    
    public RuntimeDictValue(HashMap<String,RuntimeValue> v) {
        dict = v;
    }

    @Override
    protected String typeName() {
        return "{}";
    }
    
}
