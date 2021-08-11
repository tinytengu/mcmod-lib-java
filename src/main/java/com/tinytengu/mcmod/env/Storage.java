package com.tinytengu.mcmod.env;

import java.util.HashMap;

/**
 * Environment storage class
 * @author tinytengu
 */
public class Storage {
    /** Storage default values */
    public HashMap defaults;

    /** Storage mods list */
    public HashMap mods;

    /**
     * Creates new object
     */
    public Storage() {

        this.defaults = new HashMap<>();
        this.mods = new HashMap<>();
    }

    /***
     * Load yamlMap structure to Storage fields
     * @param yamlMap YAML map
     */
    public void load(HashMap yamlMap) {
        this.defaults = (HashMap)yamlMap.get("defaults");
        this.mods = (HashMap)yamlMap.get("mods");
    }
}
