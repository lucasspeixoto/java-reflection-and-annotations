package com.basicsstrong.reflection;

import java.lang.reflect.Field;

public class FieldInfo {

    public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        Entity entity = new Entity(10, "id");
        Class<? extends Entity> clazz = entity.getClass();
        //! getFields: Apenas campos não privado da classe e da super classe
        //! getDeclaredFields: Todos os campos da classe e da super classe
        for (Field field : clazz.getDeclaredFields()) {

            System.out.printf("Field Name: " + field.getName() + "\n");
            System.out.printf("Field Type: " + field.getType());
            System.out.println("\n\n");

        }

        Field typeField = clazz.getField("type");
        typeField.set(entity, "rollNo.");
        System.out.println(entity.getType());

        Field valField = clazz.getDeclaredField("val");
        valField.setAccessible(true);
        valField.set(entity, 4);
        System.out.println(entity.getVal());

    }
}
