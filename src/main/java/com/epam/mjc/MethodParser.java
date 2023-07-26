package com.epam.mjc;

import com.epam.mjc.MethodSignature.Argument;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] entities = signatureString.split("\\(");

        String[] notArguments = entities[0].split(" ");
        List<Argument> arguments = operateArguments(entities[1]);

        MethodSignature methodSignature = new MethodSignature(notArguments[notArguments.length - 1], arguments);

        methodSignature.setReturnType(notArguments[notArguments.length - 2]);

        if (notArguments.length > 2) {
            methodSignature.setAccessModifier(notArguments[notArguments.length - 3]);
        }

        return methodSignature;
    }

    private List<Argument> operateArguments(String arguments) {
        arguments = arguments.substring(0, arguments.indexOf(")"));

        List<Argument> list = new ArrayList<>();
        String[] typedArgs = arguments.split(",");
        for (String typedArg : typedArgs) {
            typedArg = typedArg.trim();
            String[] arg = typedArg.split(" ");
            if (arg.length > 1) {
                list.add(new Argument(arg[0], arg[1]));
            }
        }

        return list;
    }
}
