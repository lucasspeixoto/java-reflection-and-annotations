package com.basicsstrong.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReadingAnnotation {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
     Class<?> clazz = Class.forName("com.basicsstrong.annotation.Utility");
     Constructor<?> constructor = clazz.getConstructor();

     Utility utility = (Utility)constructor.newInstance();

     Method[] methods = clazz.getDeclaredMethods();

     for (Method method: methods) {
         if(method.isAnnotationPresent(MostUsed.class)) {
             MostUsed annotation = method.getAnnotation(MostUsed.class);
             String value = annotation.value();
             method.invoke(utility, value);
         }
     }
    }
}
