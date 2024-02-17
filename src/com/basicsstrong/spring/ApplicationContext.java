package com.basicsstrong.spring;

import com.basicsstrong.annotation.Autowired;
import com.basicsstrong.annotation.Component;
import com.basicsstrong.annotation.ComponentScan;
import com.basicsstrong.annotation.Configuration;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;

@SuppressWarnings("all")
public class ApplicationContext {

    private static HashMap<Class<?>, Object> map = new HashMap<>();

    public ApplicationContext(Class<AppConfig> clazz) {
        Spring.initializeSpringContext(clazz);
    }


    private static class Spring {
        private static void initializeSpringContext(Class<?> clazz) {
            if (!clazz.isAnnotationPresent(Configuration.class)) {
                throw new RuntimeException("The file is not a Configuration file!");
            } else {
                ComponentScan annotation = clazz.getAnnotation(ComponentScan.class);
                String value = annotation.value();
                String packageStructure = "bin/" + value.replace(".", "/");

                File[] files = findClasses(new File(packageStructure));

                for (File file : files) {
                    String name = value + "." + file.getName().replace(".class", "");
                    try {
                        Class<?> loadingClass = Class.forName(name);
                        if (loadingClass.isAnnotationPresent(Component.class)) {
                            Constructor<?> constructor = loadingClass.getConstructor();
                            Object newInstance = constructor.newInstance();
                            map.put(loadingClass, newInstance);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private static File[] findClasses(File file) {
            if (!file.exists()) {
                throw new RuntimeException("Package " + file + " does not exists!");
            } else {
                return file.listFiles(e -> e.getName().endsWith(".class"));
            }
        }
    }

    public <T> T getBean(Class<T> clazz) {

        T object = (T)map.get(clazz);

        Field[] declaredFields = clazz.getDeclaredFields();

        injectBean(object, declaredFields);
        return object;
    }

    private <T> void injectBean(T object, Field[] declaredFields) {
        for (Field field : declaredFields) {
            if(field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                Object innerObject = map.get(type);
                try {
                    field.set(object, innerObject);

                    Field[] otherDeclaredFields = type.getDeclaredFields();
                    injectBean(innerObject, otherDeclaredFields);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
