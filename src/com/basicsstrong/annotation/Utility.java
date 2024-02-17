package com.basicsstrong.annotation;

public class Utility {

    void doStuff() {
        System.out.println("Doing something!");
    }

    @MostUsed(value = "Python")
    void doStuff(String arg) {
        System.out.println("Operating on: " + arg);
    }

    void doStuff(int value) {
        System.out.println("Operating with: " + value);
    }
}
