package no.uio.ifi.asp.runtime;

public class RuntimeListValue extends RuntimeValue {
    RuntimeValue[] value;

    public RuntimeListValue(RuntimeValue[] v) {
        value = v;
    }

    @Override
    protected String typeName() {
        return "[]";
    }
    
}
