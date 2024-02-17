package com.basicsstrong.reflection;

public class GettingClassObject {
    public static void main(String[] args) throws Exception {

        //! forName()
        Class<?> clazz1 = Class.forName("java.lang.String");
        Class<?> clazz2 = Class.forName("java.lang.String");

        System.out.println(clazz1 == clazz2); //! True. All class objects created for the same class share the same object

        //! ClassName.class
        Class<?> clazz3 = int.class;
        Class<?> clazz4 = String.class;

        //! obj.getClass()
        MyClass2 myClass = new MyClass2();
        Class<? extends MyClass2> clazz5 = myClass.getClass();
        //! super class
        Class<?> superClass = clazz5.getSuperclass();
        //! interfaces
        Class<?>[] interfaces = clazz5.getInterfaces();
        //! getName()
        System.out.printf("Class name: " + clazz5.getName());


    }
}
