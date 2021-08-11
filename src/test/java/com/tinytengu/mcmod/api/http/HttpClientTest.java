package com.tinytengu.mcmod.api.http;

import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HttpClientTest {
    HttpClient client;

    @BeforeEach
    void setUp() {
        client = new HttpClient();
    }

    @Test
    void constructorEmpty() {
        assertNotNull(client.getClient());
        assertNotNull(client.getHeaders());
    }

    @Test
    void constructorHeaders() {
        client = new HttpClient();
        HashMap<String, String> headers = client.getHeaders();

        headers.put("User-Agent", "Test User-Agent");
        headers.put("Content-Type", "Test Content-Type");

        assertEquals(headers.get("User-Agent"), "Test User-Agent");
        assertEquals(headers.get("Content-Type"), "Test Content-Type");
    }

    @Test
    void getHeaders() {
        assertNotNull(client.getHeaders());
    }

    @Test
    void setHeaders() {
        HashMap<String, String> headers = client.getHeaders();

        headers.put("User-Agent", "Test User-Agent");
        headers.put("Content-Type", "Test Content-Type");

        assertEquals(headers.get("User-Agent"), "Test User-Agent");
        assertEquals(headers.get("Content-Type"), "Test Content-Type");
    }

    @Test
    void getClient() {
        assertNotNull(client.getClient());
    }

    @Test
    void setClient() {
        org.apache.http.client.HttpClient httpClient = client.getClient();
        client.setClient(HttpClients.createDefault());

        assertNotEquals(httpClient, client.getClient());
    }

    @Test
    void get() {
        assertDoesNotThrow(() -> client.get("https://google.com"));
    }

    @Test
    void getThrowsException() {
        String uuid = UUID.randomUUID().toString();
        assertThrows(IOException.class, () -> client.get(uuid));
    }
}