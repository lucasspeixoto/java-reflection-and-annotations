package com.basicsstrong.methodhandles;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;

public class MethodHandlesDemo {

    public static void main(String[] args) throws Throwable {

        Lookup lookup = MethodHandles.lookup();
        Class<?> clazz = lookup.findClass(Student.class.getName());

        Student student = new Student();
        student.setCourse("Java Reflection API");
        MethodType getCourseMethodType = MethodType.methodType(String.class);
        MethodHandle getCourseHandler = lookup.findVirtual(clazz, "getCourse", getCourseMethodType);
        System.out.println(getCourseHandler.invoke(student));

        MethodType nonArgsConstructorType = MethodType.methodType(void.class);
        MethodHandle nonArgsHandler =  lookup.findConstructor(clazz, nonArgsConstructorType);
        Student studentFromNoArgsHandler = (Student)nonArgsHandler.invoke();
        studentFromNoArgsHandler.setName("Lucas Peixoto");
        studentFromNoArgsHandler.setCourse("Java Reflection API");
        System.out.println(studentFromNoArgsHandler);

        MethodType withArgsConstructorType = MethodType.methodType(void.class, String.class, String.class);
        MethodHandle withArgsHandler =  lookup.findConstructor(clazz, withArgsConstructorType);
        Student studentFromWithArgsHandler = (Student)withArgsHandler.invoke("Lucas Peixoto Fernandes", "Advanced Spring Boot");
        System.out.println(studentFromWithArgsHandler);

        MethodType setNameMethodType = MethodType.methodType(void.class, String.class);
        MethodHandle setNameHandler = lookup.findVirtual(clazz, "setName", setNameMethodType);
        setNameHandler.invoke(studentFromNoArgsHandler, "Liana Fernandes");
        System.out.println(studentFromNoArgsHandler.getName());

        MethodType setNumOfStudentsMethodType = MethodType.methodType(void.class, int.class);
        MethodHandle setNumOfStudentsHandler = lookup.findStatic(clazz, "setNumOfStudents", setNumOfStudentsMethodType);
        setNumOfStudentsHandler.invoke(2);
        System.out.println(studentFromNoArgsHandler.getNumOfStudents());

        // Necessário para acessar membros privados
        Lookup privateLookupIn = MethodHandles.privateLookupIn(clazz, lookup);
        //MethodHandle getNameHandler = lookup.findGetter(clazz, "name", String.class);
        MethodHandle findNameGetterHandler = privateLookupIn.findGetter(clazz, "name", String.class);
        MethodHandle findNameSetterHandler = privateLookupIn.findSetter(clazz, "name", String.class);
        findNameSetterHandler.invoke(studentFromNoArgsHandler, "Liana Peixoto Fernandes");
        System.out.println(studentFromNoArgsHandler.getName());

        VarHandle courseVarHandler =  privateLookupIn.findVarHandle(clazz, "course", String.class);
        courseVarHandler.set(studentFromNoArgsHandler, "Advanced Next Js course");
        String value = (String)courseVarHandler.get(studentFromNoArgsHandler);
        System.out.println(value);
    }
}
