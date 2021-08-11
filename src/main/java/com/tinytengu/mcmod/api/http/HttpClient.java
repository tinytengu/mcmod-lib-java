package com.tinytengu.mcmod.api.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.HashMap;

public class HttpClient {
    /**
     * Client headers for every request
     */
    private HashMap<String, String> headers;

    /**
     * HTTP Client
     */
    private org.apache.http.client.HttpClient client;

    /**
     * Creates new object
     */
    public HttpClient() {
        this.setClient(HttpClients.createDefault());
        this.setHeaders(new HashMap<>());
    }

    /**
     * Creates new object
     * @param headers http headers
     */
    public HttpClient(HashMap<String, String> headers) {
        this.setClient(HttpClients.createDefault());
        this.setHeaders(headers);
    }

    /**
     * Get headers
     * @return headers
     */
    public HashMap<String, String> getHeaders() {
        return headers;
    }

    /**
     * Set headers
     * @param headers headers
     */
    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    /**
     * Get Apache HTTP client
     * @return HTTP client
     */
    public org.apache.http.client.HttpClient getClient() {
        return client;
    }

    /**
     * Set Apache HTTP client
     * @param client HTTP client
     */
    public void setClient(org.apache.http.client.HttpClient client) {
        this.client = client;
    }

    /**
     * HTTP GET request method
     * @param url request URL
     * @return response as string
     * @throws IOException request exception
     */
    public HttpResponse get(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        this.getHeaders().forEach(request::addHeader);
        return this.getClient().execute(request);
    }
}
