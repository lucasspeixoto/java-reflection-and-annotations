package com.basicsstrong.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInfo {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Entity entity = new Entity(10, "id");
        Class<? extends Entity> clazz = entity.getClass();

        Method[] methods = clazz.getMethods();
        for(Method method : methods) {
            System.out.printf("Method: " + method.getName() + "\n");
        }
        System.out.println("------------------------------------");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for(Method method : declaredMethods) {
            System.out.printf("Declared Method: " + method.getName() + "\n");
        }

        Method method1 = clazz.getDeclaredMethod("setVal", int.class);
        method1.setAccessible(true
        );
        method1.invoke(entity, 15);

        Method method2 = clazz.getMethod("getVal", null);
        System.out.println(method2.invoke(entity, null));;

    }
}
