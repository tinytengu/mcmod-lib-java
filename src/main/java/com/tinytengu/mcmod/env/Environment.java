package com.tinytengu.mcmod.env;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Mods environment class
 * @author tinytengu
 */
public class Environment {
    /** Environment storage */
    private Storage storage;

    /** Environment folder path */
    private Path path;

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
     * Get environment storage
     * @return storage
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Set environment storage
     * @param storage storage
     */
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * Get environment path
     * @return path
     */
    public Path getPath() {
        return path;
    }

    /**
     * Set environment path
     * @param path path
     */
    public void setPath(Path path) {
        this.path = path;
    }

    /**
     * Get environment .yml file path
     * @return file path
     */
    public Path getYml() {
        return this.path.resolve("mcmod.yml");
    }

    /**
     * Load environment data
     */
    public void load() throws FileNotFoundException {
        // Reading file
        InputStream inputStream = new FileInputStream(this.getYml().toString());

        // Loading YAML schema
        Yaml yaml = new Yaml(new Constructor(Storage.class));
        this.setStorage(yaml.load(inputStream));
    }

    /**
     * Save environment storage data to mcmod.yaml
     */
    public void save() throws IOException {
        Yaml yaml = new Yaml();
        String result = yaml.dumpAs(this.getStorage(), Tag.MAP, null);

        FileOutputStream fileOutputStream = new FileOutputStream(this.getYml().toString());
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);

        Writer writer = new BufferedWriter(outputStreamWriter);
        writer.write(result);
        writer.close();
    }
}
