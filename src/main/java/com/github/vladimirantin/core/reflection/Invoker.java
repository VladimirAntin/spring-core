package com.github.vladimirantin.core.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 27.11.2019
 * Time: 21:38
 */
public interface Invoker {

    /**
     * Invoke getter supper class
     * @param <T> - type of value
     * @param propertyName - name of property of class
     * @return value by name of property
     * @throws InvocationTargetException - target exception
     * @throws IllegalAccessException - illegal access (security)
     */
    default <T>Object getReflectValue(String propertyName) throws InvocationTargetException, IllegalAccessException {
        Method getter = Arrays.stream(this.getClass().getMethods()).filter(m -> m.getName().toLowerCase().contains(String.format("get%s", propertyName))).findFirst().get();
        return (T) getter.invoke(this);
    }

    /**
     * invoke setter supper class
     * @param propertyName - name of property of class
     * @param newValue - new value for property of class
     * @throws InvocationTargetException - target exception
     * @throws IllegalAccessException - illegal access (security)
     * @return current object
     */
    default Invoker setReflectValue(String propertyName, Object newValue) throws InvocationTargetException, IllegalAccessException {
        Method setter = Arrays.stream(this.getClass().getMethods()).filter(m -> m.getName().toLowerCase().contains(String.format("set%s", propertyName))).findFirst().get();
        if (setter.getName().toLowerCase().endsWith("version")) {
            throw new IllegalAccessException();
        }
        setter.invoke(this,newValue);
        return this;
    }

}
