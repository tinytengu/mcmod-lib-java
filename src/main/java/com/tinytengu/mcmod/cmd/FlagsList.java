package com.tinytengu.mcmod.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Flags list class
 * @author tinytengu
 */
public class FlagsList implements Iterable<Flag> {
    /** Flags list */
    ArrayList<Flag> list;

    /**
     * Creates new object
     *
     * @see FlagsList#FlagsList(Flag[])
     */
    public FlagsList() {
        this.list = new ArrayList<>();
    }

    /**
     * Creates new object
     * @param list Flags list
     *
     * @see FlagsList#FlagsList()
     */
    public FlagsList(Flag[] list) {
        this.list = new ArrayList<>();
        Collections.addAll(this.list, list);
    }

    /**
     * Add flag to flag list
     * @param flag - Flag
     * @return add result
     */
    public boolean add(Flag flag) {
        return this.list.add(flag);
    }

    /**
     * Remove flag from flag list
     * @param flag Flag
     * @return remove result
     */
    public boolean remove(Flag flag) {
        return this.list.remove(flag);
    }

    /**
     * Get flag from flag list by its index
     * @param idx Index
     * @return flag
     */
    public Flag get(int idx) {
        return this.list.get(idx);
    }

    /**
     * Find flag in flag list by its name
     * @param name Flag name
     * @return Flag or null
     */
    public Flag find(String name) {
        for (Flag flag : this.list) {
            if(flag.name.equalsIgnoreCase(name))
                return flag;
        }
        return null;
    }

    @Override
    public String toString() {
        return "FlagsList{" + list + '}';
    }

    @Override
    public Iterator<Flag> iterator() {
        return this.list.iterator();
    }

    @Override
    public void forEach(Consumer<? super Flag> action) {
        this.list.forEach(action);
    }

    @Override
    public Spliterator<Flag> spliterator() {
        return this.list.spliterator();
    }
}
