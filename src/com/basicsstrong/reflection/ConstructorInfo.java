package com.basicsstrong.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorInfo {
    public static String className = "com.basicsstrong.reflection.Entity";

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName(className);
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        Constructor<?> publicConstructor = clazz.getConstructor(int.class, String.class);
        Entity entity = (Entity)publicConstructor.newInstance(10, "studentId");
        System.out.printf("Entity: " + entity.getVal() + " " + entity.getType() + "\n");

        Constructor<?> privateConstructor = clazz.getDeclaredConstructor();
        privateConstructor.setAccessible(true);
        Entity defaultE = (Entity)privateConstructor.newInstance();
        System.out.printf("Default Entity: " + defaultE.getVal() + " " + defaultE.getType() + "\n");

    }
}
