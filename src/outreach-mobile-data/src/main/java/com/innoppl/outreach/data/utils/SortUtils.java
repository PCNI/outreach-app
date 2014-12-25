package com.innoppl.outreach.data.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Jerald Mejarla
 */
public class SortUtils {

    public static <T extends Comparable<? super T>> List<T>
            asSortedList(Collection<T> c) {
        List<T> list = new ArrayList<T>(c);
        java.util.Collections.sort(list);
        return list;
    }
}
