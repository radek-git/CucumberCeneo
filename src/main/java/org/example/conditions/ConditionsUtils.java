package org.example.conditions;

public class ConditionsUtils {

    public static HasClass containingClass(String htmlClass) {
        return new HasClass(htmlClass);
    }
}
