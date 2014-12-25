package com.innoppl.outreach.data.utils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Jerald Mejarla
 */
public final class EntityUtils {

    private static Map<String, List<AccessibleObject>> cache =
            new HashMap<String, List<AccessibleObject>>(0);

    private EntityUtils() {
    }

    /**
     * @param obj1
     * @param obj2
     * @return true if obj1 is equal to obj2 else false
     */
    public static boolean equals(final Object obj1, final Object obj2) {
        if (obj1 == obj2) {
            return true;
        }

        if (obj2 == null || obj2.getClass() != obj1.getClass()) {
            return false;
        }

        final EqualsBuilder equalsBuilder = new EqualsBuilder();

        for (AccessibleObject ao : getAccessibleObjects(obj1, 1)) {
            try {
                if (ao instanceof Field) {
                    equalsBuilder.append(((Field) ao).get(obj1),
                            ((Field) ao).get(obj2));
                } else {
                    equalsBuilder.append(((java.lang.reflect.Method) ao).invoke(obj1,
                            (Object[]) null), ((java.lang.reflect.Method) ao).invoke(obj2,
                            (Object[]) null));
                }
            } catch (Exception e) {
                //Ignore Exception
            }
        }

        return equalsBuilder.isEquals();
    }

    /**
     * @param obj
     * @return Hash code of the object
     */
    public static int hashCode(final Object obj) {

        final HashCodeBuilder builder = new HashCodeBuilder();
        for (AccessibleObject ao : getAccessibleObjects(obj, 2)) {
            try {
                if (ao instanceof Field) {
                    builder.append(((Field) ao).get(obj));
                } else {
                    builder.append(((java.lang.reflect.Method) ao).invoke(obj,
                            (Object[]) null));
                }
            } catch (Exception e) {
                //Ignore Exception
            }
        }

        return builder.toHashCode();
    }

    /**
     *
     * @param obj
     * @return String representation of the object
     */
    public static String toString(final Object obj) {
        final ToStringBuilder builder = new ToStringBuilder(obj,
                ToStringStyle.SHORT_PREFIX_STYLE);

        for (AccessibleObject ao : getAccessibleObjects(obj, 4)) {
            try {
                if (ao instanceof Field) {
                    builder.append(((Field) ao).getName(), ((Field) ao).get(obj));
                } else {
                    builder.append(((java.lang.reflect.Method) ao).getName(),
                            ((java.lang.reflect.Method) ao).invoke(obj,
                            (Object[]) null));
                }
            } catch (Exception e) {
                //Ignore Exception
            }
        }

        return builder.toString();
    }

    /**
     *
     * @param obj
     * @param filter
     * @return {@link List} of {@link AccessibleObject}
     */
    private static List<AccessibleObject> getAccessibleObjects(final Object obj,
            final int filter) {
        Class<?> clazz = obj.getClass();

        final String name = clazz.getName() + filter;

        if (!cache.containsKey(name)) {
            final List<AccessibleObject> aos = new ArrayList<AccessibleObject>();

            do {
                final Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    final InjectMethods bizKey = 
                            field.getAnnotation(InjectMethods.class);
                    if (bizKey != null && (filter(bizKey) & filter) == filter) {
                        field.setAccessible(true);
                        aos.add(field);
                    }
                }

                final java.lang.reflect.Method[] methods = 
                        clazz.getDeclaredMethods();

                for (java.lang.reflect.Method method : methods) {
                    final InjectMethods bizKey = 
                            method.getAnnotation(InjectMethods.class);
                    if (bizKey != null && (filter(bizKey) & filter) == filter) {
                        method.setAccessible(true);
                        aos.add(method);
                    }
                }

                clazz = clazz.getSuperclass();
            } while (clazz != null);

            Collections.sort(aos, new AccessibleObjectComparator());

            cache.put(name, aos);
        }

        return cache.get(name);
    }

    private static int filter(final InjectMethods bizKey) {
        int filter = 0;

        for (Method method : bizKey.include()) {
            switch (method) {
                case ALL:
                    filter |= 7;
                    break;
                case EQUALS:
                    filter |= 1;
                    break;
                case HASH_CODE:
                    filter |= 2;
                    break;
                case TO_STRING:
                    filter |= 4;
                    break;
            }
        }

        for (Method method : bizKey.exclude()) {
            switch (method) {
                case ALL:
                    filter -= filter & 7;
                    break;
                case EQUALS:
                    filter -= filter & 1;
                    break;
                case HASH_CODE:
                    filter -= filter & 2;
                    break;
                case TO_STRING:
                    filter -= filter & 4;
                    break;
            }
        }
        return filter;
    }

    /**
     * Helper class for object compare
     */
    private static class AccessibleObjectComparator implements
            Comparator<AccessibleObject> {

        @Override
        public int compare(final AccessibleObject accessObj1,
                final AccessibleObject accessObj2) {
            final boolean o1IsField = accessObj1 instanceof Field;
            final boolean o2IsField = accessObj2 instanceof Field;

            if (!o1IsField && o2IsField) {
                return 1;
            } else if (o1IsField && !o2IsField) {
                return -1;
            }

            if (o1IsField) {
                return ((Field) accessObj1).getName().compareTo(
                        ((Field) accessObj2).getName());
            } else {
                return ((java.lang.reflect.Method) accessObj1).getName().compareTo(
                        ((java.lang.reflect.Method) accessObj2).getName());
            }
        }
    }
}
