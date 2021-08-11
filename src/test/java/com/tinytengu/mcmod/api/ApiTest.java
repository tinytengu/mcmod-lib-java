package com.tinytengu.mcmod.api;

import static org.junit.jupiter.api.Assertions.*;

import com.tinytengu.mcmod.api.http.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

class ApiTest {
    Api api;

    @BeforeEach
    void setUp() {
        api = new Api();
    }

    @Test
    void constructorEmpty() {
        HttpClient client = api.getClient();
        HashMap<String, String> headers = client.getHeaders();

        assertEquals(headers.get("User-Agent"), api.getDefaultUserAgent());
        assertEquals(headers.get("Content-Type"), "application/json");
    }

    @Test
    void constructorClient() {
        HttpClient client = new HttpClient();
        client.getHeaders().put("User-Agent", "Test User-Agent");
        client.getHeaders().put("Content-Type", "Test Content-Type");

        Api api = new Api(client);

        HashMap<String, String> headers = api.getClient().getHeaders();

        assertEquals(headers.get("User-Agent"), "Test User-Agent");
        assertEquals(headers.get("Content-Type"), "Test Content-Type");
    }

    @Test
    void getDefaultUserAgent() {
        assertEquals(api.getDefaultUserAgent(), new Api().getDefaultUserAgent());
    }

    @Test
    void getClient() {
        assertNotNull(api.getClient());
    }

    @Test
    void setClient() {
        HttpClient client = api.getClient();
        api.setClient(new HttpClient());

        assertNotEquals(client, api.getClient());
    }

    @Test
    void getMod() {
        assertDoesNotThrow(() -> api.getMod("jei"));
    }

    @Test
    void getModThrowsQueuedException() {
        String uuid = UUID.randomUUID().toString();
        assertThrows(QueuedException.class, () -> api.getMod(uuid));
    }

    @Test
    void getModThrowsNonCanonicalPathException() {
        String uuid = UUID.randomUUID().toString();
        assertThrows(QueuedException.class, () -> api.getMod(uuid));
    }

    @Test
    void getModInvalidPathException() {
        assertThrows(InvalidPathException.class, () -> api.getMod("jei1.2"));
    }

    @Test
    void getModDoesNotExistsException() throws IOException, InterruptedException {
        String uuid = UUID.randomUUID().toString();
        try {
            api.getMod(uuid);
        } catch (APIException e) {
            Thread.sleep(5000);
        }
        assertThrows(DoesNotExistsException.class, () -> api.getMod(uuid));
    }

    @Test
    void getModThrowsAPIException() {
        String uuid = UUID.randomUUID().toString();
        assertThrows(APIException.class, () -> api.getMod(uuid));
    }
}
