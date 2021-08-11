package com.tinytengu.mcmod.env;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SpellCheckingInspection")
class EnvironmentTest {
    /**
     * Current working directory path
     */
    Path userPath;

    /**
     * Test resources directory path
     */
    Path resPath;

    /**
     * Test environment
     */
    Environment env;

    /**
     * Test storage defaults
     */
    HashMap<String, String> storageDefaults;

    /**
     * Test storage mods
     */
    HashMap<String, HashMap<String, String>> storageMods;

    @BeforeEach
    void setUp() {
        // CWD
        userPath = Paths.get(System.getProperty("user.dir"));

        // Resources
        URL url = Thread.currentThread().getContextClassLoader().getResource("mcmod.yml");
        assert url != null;
        resPath = Paths.get(url.getPath().substring(1)).getParent();
    }

    @BeforeEach
    @AfterEach
    void setUpEnvironment() throws IOException {
        storageDefaults = new HashMap<String, String>() {{
            put("mcver", "1.12.2");
            put("type", "release");
        }};

        storageMods = new HashMap<String, HashMap<String, String>>() {{
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

        env = new Environment(resPath);
        env.getStorage().setDefaults(storageDefaults);
        env.getStorage().setMods(storageMods);

        env.save();
    }

    @Test
    void constructorString() {
        Environment env = new Environment("");

        assertEquals(env.getPath().toString(), userPath.toString());
        assertNotNull(env.getStorage());
    }

    @Test
    void constructorPath() {
        Environment env = new Environment(Paths.get("").toAbsolutePath());

        assertEquals(env.getPath(), userPath);
        assertNotNull(env.getStorage());
    }

    @Test
    void getStorage() {
        assertNotNull(env.getStorage());
    }

    @Test
    void setStorage() {
        Storage storage = env.getStorage();
        env.setStorage(new Storage());
        assertNotEquals(env.getStorage(), storage);
    }

    @Test
    void getPath() {
        assertEquals(env.getPath(), resPath);
    }

    @Test
    void setPath() {
        env.setPath(Paths.get("C:\\"));
        assertEquals(env.getPath().toString(), "C:\\");
    }

    @Test
    void getYml() {
        Environment env = new Environment("");

        assertEquals(env.getYml(), userPath.resolve("mcmod.yml"));
    }

    @Test
    void load() {
        assertDoesNotThrow(env::load);

        HashMap defaults = env.getStorage().getDefaults();
        HashMap mods = env.getStorage().getMods();

        storageDefaults.forEach((key, value) -> assertEquals(defaults.get(key), value));

        storageMods.forEach((name, value) -> {
            assertNotNull(mods.get(name));

            value.forEach((vname, vvalue) -> assertEquals(
                    ((HashMap) mods.get(name)).get(vname),
                    vvalue
            ));
        });
    }

    @Test
    void loadThrowsFileNotFoundException() {
        Environment env = new Environment("/");
        assertThrows(FileNotFoundException.class, env::load);
    }

    @Test
    void changeDefaults() {
        assertDoesNotThrow(env::load);
        HashMap<String, String> defaults = env.getStorage().getDefaults();

        defaults.put("mcver", "1.16.2");
        defaults.put("type", "alpha");

        assertEquals(defaults.get("mcver"), "1.16.2");
        assertEquals(defaults.get("type"), "alpha");
    }

    @Test
    void changeMods() {
        assertDoesNotThrow(env::load);

        HashMap<String, String> jei = env.getStorage().getMods().get("jei");
        jei.put("mcver", "1.5.2");

        assertEquals(jei.get("mcver"), "1.5.2");
    }

    @Test
    void save() {
        assertDoesNotThrow(env::load);

        HashMap<String, String> defaults = env.getStorage().getDefaults();

        defaults.put("mcver", "1.16.2");
        defaults.put("type", "alpha");
        assertDoesNotThrow(env::save);

        assertDoesNotThrow(env::load);
        defaults = env.getStorage().getDefaults();

        assertEquals(defaults.get("mcver"), "1.16.2");
        assertEquals(defaults.get("type"), "alpha");
    }

    @Test
    void saveThrowsIOException() {
        env.setPath(Paths.get("/no/such/place"));
        assertThrows(IOException.class, () -> env.save());
    }
}
