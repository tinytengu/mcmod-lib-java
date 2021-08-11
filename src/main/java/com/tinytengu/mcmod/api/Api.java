package com.tinytengu.mcmod.api;

import com.tinytengu.mcmod.api.http.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class Api {
    /**
     * Requests User-Agent
     */
    private final String defaultUserAgent =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) " +
            "Chrome/91.0.4472.164 Safari/537.36 OPR/77.0.4054.298";

    /**
     * HTTP Client
     */
    private HttpClient client;

    /**
     * Creates new object
     * @see Api#Api(HttpClient)
     */
    public Api() {
        this.setClient(new HttpClient(new HashMap<String, String>() {{
            put("User-Agent", defaultUserAgent);
            put("Content-Type", "application/json");
        }}));
    }

    /**
     * Creates new object
     * @param client HTTP Client
     * @see Api#Api()
     */
    public Api(HttpClient client) {
        this.setClient(client);
    }

    /**
     * Get default User-Agent
     * @return default User-Agent
     */
    public String getDefaultUserAgent() {
        return defaultUserAgent;
    }

    /**
     * Get HTTP client
     * @return HTTP client
     */
    public HttpClient getClient() {
        return client;
    }

    /**
     * Set HTTP client
     * @param client HTTP client
     */
    public void setClient(HttpClient client) {
        this.client = client;
    }

    /**
     * Get mod by its id
     * @param id mod id
     * @throws IOException HTTP Client exception
     * @throws APIException CurseForge API Exception
     */
    public JSONObject getMod(String id) throws IOException, APIException {
        /**
         * Base CurseForge API URL
         */
        // Make GET request
        String baseUrl = "https://api.cfwidget.com/minecraft/mc-mods/";

        HttpResponse response = this.getClient().get(baseUrl + id);
        HttpEntity entity = response.getEntity();

        if(entity == null) {
            return new JSONObject();
        }

        // Convert response to JSON object
        String result = EntityUtils.toString(entity);
        JSONObject json = new JSONObject(result);

        // Handle status codes and throw exceptions
        int statusCode = response.getStatusLine().getStatusCode();
        String errorTitle = json.get("title").toString();

        switch (statusCode) {
            case 202: throw new QueuedException(errorTitle);
            case 301:
            case 500:
                throw new APIException(errorTitle);
            case 400: throw new InvalidPathException(errorTitle);
            case 404: throw new DoesNotExistsException(errorTitle);
        }
        return json;
    }
}
