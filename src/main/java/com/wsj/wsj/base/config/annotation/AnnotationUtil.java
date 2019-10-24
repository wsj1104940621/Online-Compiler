package com.wsj.wsj.base.config.annotation;

import java.lang.annotation.Annotation;

public class AnnotationUtil {
    public static boolean isAssigned(Class anno, Class cla) {
        if (anno == null || cla == null)
            return false;

        boolean flag = false;
        Annotation[]  annotations = cla.getAnnotations();
        for (Annotation annotation : annotations) {
            if (!flag && annotation.annotationType() == anno) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
