package com.basicsstrong.practice;

import com.basicsstrong.annotation.Column;
import com.basicsstrong.annotation.PrimaryKey;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hibernate<T> {

    private final Connection connection;

    private AtomicLong id = new AtomicLong(0L);

    public static <T> Hibernate<T> getConnection() throws SQLException {
        return new Hibernate<T>();
    }

    private Hibernate() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:h2:C:/Users/User/Documents/projects/java/java-reflection-and-annotations/database/practice1", "sa", "");
    }

    @SuppressWarnings("all")
    public void write(T t) throws IllegalAccessException, SQLException {
        Class<? extends Object> clazz = t.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        Field pkey = null;
        ArrayList<Field> columns = new ArrayList();
        StringJoiner joiner = new StringJoiner(",");

        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(PrimaryKey.class)) {
                pkey = field;
            } else if (field.isAnnotationPresent(Column.class)) {
                joiner.add(field.getName());
                columns.add(field);
            }
        }

        int number = columns.size() + 1;

        String qMarks = IntStream.range(0, number)
                .mapToObj(e -> "?")
                .collect(Collectors.joining(","));

        String sqlString = "insert into " +
                clazz.getSimpleName() +
                "( " + pkey.getName() + "," + joiner.toString() + ") " +
                "values (" + qMarks + ")";

        PreparedStatement statement = connection.prepareStatement(sqlString);

        if (pkey.getType() == long.class) {
            statement.setLong(1, id.incrementAndGet());
        }

        int index = 2;
        for (Field field : columns) {
            field.setAccessible(true);
            if (field.getType() == int.class) {
                statement.setInt(index++, (int) field.get(t));
            } else if (field.getType() == String.class) {
                statement.setString(index++, (String) field.get(t));
            }
        }

        statement.executeUpdate();

    }

    public T read(Class<T> clazz, long l) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Field[] declaredFields = clazz.getDeclaredFields();
        Field pkey = null;
        for (Field field : declaredFields) {
            if(field.isAnnotationPresent(PrimaryKey.class)) {
                pkey = field;
                break;
            }
        }

        assert pkey != null;

        String sqlString = "SELECT * FROM " + clazz.getSimpleName() + " WHERE " + pkey.getName() + " = " + l;

        PreparedStatement statement = connection.prepareStatement(sqlString);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        T t = clazz.getConstructor().newInstance();

        long transactionId = resultSet.getInt(pkey.getName());

        pkey.setAccessible(true);

        pkey.set(t, transactionId);

        for (Field field: declaredFields) {
            if(field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);

                if(field.getType() == int.class) {
                    field.set(t, resultSet.getInt(field.getName()));
                } else if (field.getType() == String.class) {
                    field.set(t, resultSet.getString(field.getName()));
                }
            }
        }

        return t;
    }
}
