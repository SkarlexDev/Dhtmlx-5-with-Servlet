package com.util;

import jakarta.servlet.http.HttpServletRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Logger;

public class BeanUtils {

    private static final Logger log = Logger.getLogger(BeanUtils.class.getName());

    /*
     * This will iterate over HttpServletRequest map and will try to call setters
     * from provided object to bind parameters value to class fields
     */
    public static void populate(Object bean, HttpServletRequest req) {
        log.info("Populating: " + bean.getClass());
        Map<String, String[]> map = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            try {
                setAttribute(bean, entry.getKey(), entry.getValue()[0]);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * From provided bean we call all methods from class and find setters based on
     * string key from HttpServletRequest map, If a setter is found the value from
     * HttpServletRequest map will be parsed to corresponding parameter type
     */
    public static void setAttribute(Object bean, String name, String value)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
        log.info("Parsing attributes");
        Method[] methods = bean.getClass().getDeclaredMethods();
        for (Method item : methods) {
            if (item.getName().equalsIgnoreCase("set" + name)) {
                Class<?> paramType = item.getParameterTypes()[0];
                if (paramType.equals(byte.class) || paramType.equals(Byte.class)) {
                    item.invoke(bean, Byte.parseByte(value));
                } else if (paramType.equals(short.class) || paramType.equals(Short.class)) {
                    item.invoke(bean, Short.parseShort(value));
                } else if (paramType.equals(int.class) || paramType.equals(Integer.class)) {
                    item.invoke(bean, Integer.parseInt(value));
                } else if (paramType.equals(long.class) || paramType.equals(Long.class)) {
                    item.invoke(bean, Long.parseLong(value));
                } else if (paramType.equals(float.class) || paramType.equals(Float.class)) {
                    item.invoke(bean, Float.parseFloat(value));
                } else if (paramType.equals(double.class) || paramType.equals(Double.class)) {
                    item.invoke(bean, Double.parseDouble(value));
                } else if (paramType.equals(char.class) || paramType.equals(Character.class)) {
                    item.invoke(bean, value.charAt(0));
                } else if (paramType.equals(boolean.class) || paramType.equals(Boolean.class)) {
                    item.invoke(bean, Boolean.parseBoolean(value));
                } else if (paramType.equals(String.class)) {
                    item.invoke(bean, String.valueOf(value));
                }
                break;
            }
        }
    }
}
