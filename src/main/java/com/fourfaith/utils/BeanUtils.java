//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

public class BeanUtils extends org.springframework.beans.BeanUtils {
    public BeanUtils() {
    }

    public static Object getPrivateProperty(Object object, String propertyName) throws IllegalAccessException, NoSuchFieldException {
        Assert.notNull(object);
        Assert.hasText(propertyName);
        Field field = object.getClass().getDeclaredField(propertyName);
        field.setAccessible(true);
        return field.get(object);
    }

    public static void setPrivateProperty(Object object, String propertyName, Object newValue) throws IllegalAccessException, NoSuchFieldException {
        Assert.notNull(object);
        Assert.hasText(propertyName);
        Field field = object.getClass().getDeclaredField(propertyName);
        field.setAccessible(true);
        field.set(object, newValue);
    }

    public static Object invokePrivateMethod(Object object, String methodName, Object... params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Assert.notNull(object);
        Assert.hasText(methodName);
        Class[] types = new Class[params.length];

        for(int i = 0; i < params.length; ++i) {
            types[i] = params[i].getClass();
        }

        Method method = object.getClass().getDeclaredMethod(methodName, types);
        method.setAccessible(true);
        return method.invoke(object, params);
    }

    public static Object invokeSuperPrivateMethod(Object object, String methodName, Object... params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Assert.notNull(object);
        Assert.hasText(methodName);
        Class[] types = new Class[params.length];

        for(int i = 0; i < params.length; ++i) {
            types[i] = params[i].getClass();
        }

        Method method = object.getClass().getSuperclass().getDeclaredMethod(methodName, types);
        method.setAccessible(true);
        return method.invoke(object, params);
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    public static boolean isNotEmpty(Long o) {
        return !isEmpty(o);
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        } else {
            if (o instanceof String) {
                if (((String)o).trim().length() == 0) {
                    return true;
                }
            } else if (o instanceof Collection) {
                if (((Collection)o).isEmpty()) {
                    return true;
                }
            } else if (o.getClass().isArray()) {
                if (((Object[])o).length == 0) {
                    return true;
                }
            } else if (o instanceof Map) {
                if (((Map)o).isEmpty()) {
                    return true;
                }
            } else if (o instanceof Long) {
                if ((Long)o == null) {
                    return true;
                }
            } else {
                if (!(o instanceof Short)) {
                    return false;
                }

                if ((Short)o == null) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isInherit(Class cls, Class parentClass) {
        return parentClass.isAssignableFrom(cls);
    }

    public static void copyProperties(Object dest, Object orig) {
        try {
            org.springframework.beans.BeanUtils.copyProperties(dest, orig);
        } catch (Exception var3) {
            handleReflectionException(var3);
        }

    }

    private static void handleReflectionException(Exception e) {
        ReflectionUtils.handleReflectionException(e);
    }

    public static void copyPropertiesExcludeNull(Object source, Object target) throws IllegalAccessException, InvocationTargetException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        PropertyDescriptor[] var7 = targetPds;
        int var6 = targetPds.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            PropertyDescriptor targetPd = var7[var5];
            if (targetPd.getWriteMethod() != null) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null && sourcePd.getReadMethod() != null) {
                    try {
                        Method readMethod = sourcePd.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }

                        Object value = readMethod.invoke(source);
                        if (value != null) {
                            Method writeMethod = targetPd.getWriteMethod();
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }

                            writeMethod.invoke(target, value);
                        }
                    } catch (Throwable var12) {
                        throw new FatalBeanException("Could not copy properties from source to target", var12);
                    }
                }
            }
        }

    }

    public static Map<String, Object> ConvertObjToMap(Object obj, List<String> filterProperty) {
        Map<String, Object> reMap = new LinkedHashMap();
        if (obj == null) {
            return null;
        } else {
            Field[] fields = obj.getClass().getDeclaredFields();

            try {
                for(int i = 0; i < fields.length; ++i) {
                    try {
                        if (!filterProperty.contains(fields[i].getName())) {
                            Field f = obj.getClass().getDeclaredField(fields[i].getName());
                            f.setAccessible(true);
                            Object o = f.get(obj);
                            reMap.put(fields[i].getName(), o);
                        }
                    } catch (NoSuchFieldException var7) {
                        var7.printStackTrace();
                    } catch (IllegalArgumentException var8) {
                        var8.printStackTrace();
                    } catch (IllegalAccessException var9) {
                        var9.printStackTrace();
                    }
                }
            } catch (SecurityException var10) {
                var10.printStackTrace();
            }

            return reMap;
        }
    }
}
