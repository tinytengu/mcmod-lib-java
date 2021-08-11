package com.tinytengu.mcmod.env;

import java.util.HashMap;

/**
 * Environment storage class
 * @author tinytengu
 */
public class Storage {
    /** Storage default values */
    private HashMap<String, String> defaults;

    /** Storage mods list */
    private HashMap<String, HashMap<String, String>> mods;

    /**
     * Creates new object
     */
    public Storage() {
        this.defaults = new HashMap<>();
        this.mods = new HashMap<>();
    }

    /**
     * Get storage defaults
     * @return defaults
     */
    public HashMap<String, String> getDefaults() {
        return defaults;
    }

    /**
     * Set storage defaults
     * @param defaults defaults
     */
    public void setDefaults(HashMap<String, String> defaults) {
        this.defaults = defaults;
    }

    /**
     * Get storage mods
     * @return mods
     */
    public HashMap<String, HashMap<String, String>> getMods() {
        return mods;
    }

    /**
     * Set storage mods
     * @param mods mods
     */
    public void setMods(HashMap<String, HashMap<String, String>> mods) {
        this.mods = mods;
    }
}
