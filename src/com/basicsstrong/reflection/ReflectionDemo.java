package com.basicsstrong.reflection;

import java.lang.reflect.Constructor;

class MyClass {
    private MyClass() {
        System.out.println("My Class object created!");
    }
}

class MyClass2 {
    public MyClass2() {
        System.out.println("My Class 2 object created!");
    }
}

public class ReflectionDemo {
    public static void main(String[] args) throws Exception {
        //MyClass myClass = new MyClass();

        Class<?> clazz = Class.forName("com.basicsstrong.reflection.MyClass");
        Constructor<?> constructor  = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        MyClass newInstance = (MyClass)constructor.newInstance();

    }
}
