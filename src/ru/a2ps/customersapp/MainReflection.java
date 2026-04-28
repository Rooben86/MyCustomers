package ru.a2ps.customersapp;

import ru.a2ps.customersapp.model.Client;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Client c = new Client();
        Field field = c.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        field.get(c);
        field.set(c, "newUuid");
        //TODO: invoke c.toString via reflection;
        Method method = c.getClass().getMethod("toString");
        System.out.println(method.invoke(c));

        System.out.println(field.getName());
    }
}
