package com.SearchAPI;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SearchMethod {

    public static void dosearch(String keyword) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        LuceneTester c = new LuceneTester();

        // Using getDeclareMethod() method
        Method m = LuceneTester.class.getDeclaredMethod("query", String.class);
        // Using setAccessible() method
        m.setAccessible(true);
        // Using invoke() method
        m.invoke(c, keyword);
    }
    public static void dolocalsearch(String keyword) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        LuceneTester c = new LuceneTester();

        // Using getDeclareMethod() method
        Method m = LuceneTester.class.getDeclaredMethod("query", String.class);
        // Using setAccessible() method
        m.setAccessible(true);
        // Using invoke() method
        m.invoke(c, keyword);
    }

}

