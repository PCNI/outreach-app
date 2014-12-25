package com.innoppl.outreach.data.beans;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jerald Mejarla
 * @param <T>
 */
public class ValueAndCount<T extends Serializable> {
    private final List<T> data;
    private final long count;
    
    public ValueAndCount(final List<T> data, final long count) {
        this.data = data;
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public long getCount() {
        return count;
    }
}
