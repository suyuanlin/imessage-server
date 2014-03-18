package com.imessage.util.lang;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.Assert;

@SuppressWarnings("rawtypes")
public class ObjectUtils extends org.springframework.util.ObjectUtils {

	public static void extend(Object object1, Object object2) {
		//
		Assert.isAssignable(object2.getClass(), object1.getClass());
		//
		BeanWrapper object1Wrapper = PropertyAccessorFactory.forBeanPropertyAccess(object1);
		BeanWrapper object2Wrapper = PropertyAccessorFactory.forBeanPropertyAccess(object2);
		//
		for (PropertyDescriptor pd : object2Wrapper.getPropertyDescriptors()) {
			if (pd.getPropertyType().isPrimitive()) {
				continue;
			}
			//
			Object value = object2Wrapper.getPropertyValue(pd.getName());
			if (value != null && object1Wrapper.isWritableProperty(pd.getName())) {
				object1Wrapper.setPropertyValue(pd.getName(), value);
			}
		}
	}

	/**
	 * 
	 * 
	 * @param clazz
	 * @return
	 * @author Tkk
	 */
	public static Class getGenericClass(Class clazz) {
		return getGenericClass(clazz, 0);
	}

	/**
	 * 
	 * 
	 * @param paramType
	 * @param i
	 * @return
	 * @author Tkk
	 */
	public static Class<?> getGenericClass(Class<?> clazz, int i) {
		return (Class) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[i];
	}

	/**
	 * 
	 * 
	 * @param resultMapping
	 * @param string
	 * @param columnAliasName
	 * @author Tkk
	 */
	public static void setValue(Object target, String name, Object value) {
		try {
			Field field = target.getClass().getDeclaredField(name);
			field.setAccessible(true);
			field.set(target, value);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
