// © 2021 Dag Langmyhr, Institutt for informatikk, Universitetet i Oslo

package no.uio.ifi.asp.runtime;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeLibrary extends RuntimeScope {
    private Scanner keyboard = new Scanner(System.in);

    public RuntimeLibrary() {
        
        assign("len", new RuntimeFunc("len") {
            @Override
            public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
                checkNumParams(actualParams, 1, "len", where);
                return actualParams.get(0).evalLen(where);
            }
        });

        assign("print", new RuntimeFunc("print") {
            @Override
            public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
                for (int i = 0; i < actualParams.size(); ++i) {
                    if (i > 0)
                        System.out.print(" ");
                    System.out.print(actualParams.get(i).toString());
                }
                System.out.println();
                return new RuntimeNoneValue();
            }
        });

        assign("float", new RuntimeFunc("float") {
            @Override
            public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
                System.out.println("float");
                return new RuntimeFloatValue(actualParams.get(0).getFloatValue("float", where));
            }
        });

        assign("input", new RuntimeFunc("input") {
            @Override
            public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
                checkNumParams(actualParams, 1, "input", where);
                System.out.println(actualParams.get(0).getStringValue("input", where));
                return new RuntimeStringValue(keyboard.next());
            }
        });

        assign("int", new RuntimeFunc("int") {
            @Override
            public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
                System.out.println("int");
                return new RuntimeIntegerValue(actualParams.get(0).getIntValue("int", where));
            }
        });

        assign("range", new RuntimeFunc("range") {
            @Override
            public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
                checkNumParams(actualParams, 2, "range", where);
                System.out.println("range");
                ArrayList<RuntimeValue> rangeList = new ArrayList<>();

                long v1 = actualParams.get(0).getIntValue("int", where);
                long v2 = actualParams.get(1).getIntValue("int", where);

                if (v1 < v2) {
                    for (long i = v1; i < v2; i++) {
                        rangeList.add(new RuntimeIntegerValue(i));
                    }
                    return new RuntimeListValue(rangeList);
                } 
                else if (v1 == v2) {
                    rangeList.add(new RuntimeIntegerValue(v1));
                    return new RuntimeListValue(rangeList);
                }
                return new RuntimeNoneValue();
            }
        });
        
        assign("str", new RuntimeFunc("str") {
            @Override
            public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
                RuntimeValue v = null;
                for (int i = 0; i < actualParams.size(); i++) {
                    v = actualParams.get(i);
                }
                return new RuntimeStringValue(v.toString());
            }
        });
    }

    private void checkNumParams(ArrayList<RuntimeValue> actArgs, int nCorrect, String id, AspSyntax where) {
        if (actArgs.size() != nCorrect) {
            RuntimeValue.runtimeError("Wrong number of parameters to " + id + "!",where);
        }
    }
}
