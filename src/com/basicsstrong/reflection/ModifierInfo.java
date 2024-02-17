package com.basicsstrong.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ModifierInfo {

    public static void main(String[] args) throws NoSuchMethodException {
        Entity entity = new Entity(10, "id");
        Class<? extends Entity> clazz = entity.getClass();
        int modifiersInt = clazz.getModifiers();
        //int i  = modifiersInt & Modifier.PUBLIC;
        //System.out.println(i); //1 if Entity is public
        boolean isPublicClass = Modifier.isPublic(modifiersInt);
        System.out.println(isPublicClass);

        Method method = clazz.getMethod("getVal");
        int methodModifiers = method.getModifiers();
        //int j  = methodModifiers & Modifier.PRIVATE;
        //System.out.println(j);
        boolean isPublicMethod = Modifier.isPublic(methodModifiers);
        System.out.println(isPublicMethod);
    }
}
