package no.uio.ifi.asp.runtime;

public class RuntimeStringValue extends RuntimeValue {
    String stringValue;

    public RuntimeStringValue(String v) {
        stringValue = v;
    }

    @Override
    protected String typeName() {
        return "string";
    }
}
