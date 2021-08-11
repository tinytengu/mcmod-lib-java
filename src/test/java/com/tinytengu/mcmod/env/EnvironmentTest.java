package com.tinytengu.mcmod.env;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class EnvironmentTest {
    /**
     * Current working directory path
     */
    Path userPath;

    /**
     * Test resources directory path
     */
    Path resPath;

    @BeforeEach
    void setUp() {
        // CWD
        userPath = Paths.get(System.getProperty("user.dir"));

        // Resources
        URL url = Thread.currentThread().getContextClassLoader().getResource("mcmod.yaml");
        assert url != null;
        resPath = Paths.get(url.getPath().substring(1)).getParent();
    }

    @Test
    void constructorString() {
        Environment env = new Environment("");

        Assertions.assertEquals(env.path.toString(), userPath.toString());
        Assertions.assertNotNull(env.storage);
    }

    @Test
    void constructorPath() {
        Environment env = new Environment(Paths.get("").toAbsolutePath());

        Assertions.assertEquals(env.path, userPath);
        Assertions.assertNotNull(env.storage);
    }

    @Test
    void getYMLPath() {
        Environment env = new Environment("");

        Assertions.assertEquals(env.getYMLPath(), userPath.resolve("mcmod.yaml"));
    }

    @Test
    void load() {
        Environment env = new Environment(resPath);
        env.load();

        HashMap defaults = env.storage.defaults;
        HashMap mods = env.storage.mods;

        Assertions.assertEquals(defaults.get("mcver"), "1.12.2");
        Assertions.assertEquals(defaults.get("type"), "release");

        HashMap<String, HashMap> storageMods = new HashMap<String, HashMap>() {{
            put("jei", new HashMap<String, String>(){{
                put("mcver", "1.16.2");
                put("type", "beta");
                put("filename", "jei1.16.2-beta.jar");
            }});
            put("autoreglib", new HashMap<String, String>(){{
                put("mcver", "1.15.5");
                put("type", "alpha");
                put("filename", "autoreglib1.15.5-alpha.jar");
            }});
        }};

        for (Map.Entry<String, HashMap> entry : storageMods.entrySet()) {
            String name = entry.getKey();
            HashMap values = entry.getValue();
            Assertions.assertNotNull(mods.get(name));

            values.forEach((vname, vvalue) -> {
                Assertions.assertEquals(
                        ((HashMap) mods.get(name)).get(vname),
                        vvalue
                );
            });
        }
    }

    @Test
    void changeDefaults() {
        Environment env = new Environment(resPath);
        env.load();

        env.storage.defaults.put("mcver", "1.16.2");
        env.storage.defaults.put("type", "alpha");

        Assertions.assertEquals(env.storage.defaults.get("mcver"), "1.16.2");
        Assertions.assertEquals(env.storage.defaults.get("type"), "alpha");
    }

    @Test
    void changeMods() {
        Environment env = new Environment(resPath);
        env.load();

        env.storage.mods.get("jei").put("mcver", "1.5.2");

        Assertions.assertEquals(env.storage.mods.get("jei").get("mcver"), "1.5.2");
    }

    @Test
    void save() {
        Environment env = new Environment(resPath);
        env.load();

        env.storage.defaults.put("mcver", "1.16.2");
        env.storage.defaults.put("type", "alpha");

        env.save();

        env.load();

        Assertions.assertEquals(env.storage.defaults.get("mcver"), "1.16.2");
        Assertions.assertEquals(env.storage.defaults.get("type"), "alpha");
    }
}
