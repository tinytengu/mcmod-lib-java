package com.tinytengu.mcmod.api;

import static org.junit.jupiter.api.Assertions.*;
import com.tinytengu.mcmod.api.http.APIException;
import com.tinytengu.mcmod.api.http.HttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void getModThrowsException() {
        String uuid = UUID.randomUUID().toString();
        assertThrows(APIException.class, () -> api.getMod(uuid));
    }
}
