package com.tinytengu.mcmod.env;

import java.util.HashMap;

/**
 * Environment storage class
 * @author tinytengu
 */
public class Storage {
    /** Storage default values */
    public HashMap<String, String> defaults;

    /** Storage mods list */
    public HashMap<String, HashMap<String, String>> mods;

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
        this.defaults = (HashMap<String, String>)yamlMap.get("defaults");
        this.mods = (HashMap<String, HashMap<String, String>>)yamlMap.get("mods");
    }
}
