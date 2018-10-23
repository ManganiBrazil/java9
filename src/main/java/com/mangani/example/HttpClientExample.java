package com.mangani.example;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpClientExample {

    public static void main(String[] args) {

        try {
            URI uri = new URI("http://www.google.com");
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).GET().build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandler.asString());

            System.out.println("version = [" + httpResponse.version() + "]");
            System.out.println("statusCode = [" + httpResponse.statusCode() + "]");
            System.out.println("body = [" + httpResponse.body() + "]");

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
