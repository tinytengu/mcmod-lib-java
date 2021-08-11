package com.tinytengu.mcmod.env;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;

/**
 * Mods environment class
 * @author tinytengu
 */
public class Environment {
    /** Environment storage */
    Storage storage;

    /** Environment folder path */
    public Path path;

    /**
     * Creates new object
     * @param path environment folder
     * @see Environment#Environment(String) 
     */
    public Environment(Path path) {
        this.path = path;
        this.storage = new Storage();
    }

    /**
     * Creates new object
     * @param path environment folder
     * @see Environment#Environment(Path)
     */
    public Environment(String path) {
        this.path = Paths.get(path).toAbsolutePath();
        this.storage = new Storage();
    }

    /**
     * Get mcmod.yaml file path from environment path
     * @return mcmod.yaml path
     */
    public Path getYMLPath() {
        return this.path.resolve("mcmod.yaml");
    }

    /**
     * Load environment data
     */
    public void load() {
        // Reading file
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(this.getYMLPath().toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Loading YAML schema
        Yaml yaml = new Yaml();
        HashMap<String, Object> yamlMap = yaml.load(inputStream);
        this.storage.load(yamlMap);
    }

    /**
     * Save environment storage data to mcmod.yaml
     */
    public void save() {
        Yaml yaml = new Yaml();
        String result = yaml.dumpAs(this.storage, Tag.MAP, null);

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(this.getYMLPath().toString()), StandardCharsets.UTF_8))) {
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
