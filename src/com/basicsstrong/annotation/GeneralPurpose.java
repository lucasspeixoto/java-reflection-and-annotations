package com.basicsstrong.annotation;

import java.util.ArrayList;

class Parent {

    public void m1() {
        System.out.println(" m1 Parent Implementation\n");
    }

    @Deprecated(since = "2")
    public void m2(int a) {
        System.out.printf(" m2 Parent Implementation, a is " + a + "\n" );
    }

}


public class GeneralPurpose extends Parent {

    @Override
    public void m1() {
        System.out.println("m1 Child Implementation\n");
    }

    //@SuppressWarnings("all")
    public static void main(String[] args) {

        //@SuppressWarnings("unused")
        int a = 10;

        //@SuppressWarnings({"rawtypes", "unused"})
        ArrayList aList = new ArrayList();

        GeneralPurpose generalPurpose = new GeneralPurpose();
        generalPurpose.m2(a);

         Integer i = new Integer(0);

        MyFunctionalInterface impl = () -> System.out.println("Method invoked");

        impl.method();
    }
}


@FunctionalInterface
interface MyFunctionalInterface {
    public void method();
}