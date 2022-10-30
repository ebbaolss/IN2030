package no.uio.ifi.asp.runtime;

public class RuntimeFloatValue extends RuntimeValue {
    double floatValue;

    public RuntimeFloatValue(double v) {
        floatValue = v;
    }

    @Override
    protected String typeName() {
        return "float";
    }
}
