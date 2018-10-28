package com.mangani.example;

import com.mangani.model.Book;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;

public class HttpClientExample {

    public static void main(String[] args) throws InterruptedException {
        simpleRequest();
        requestBooks();
        asyncRequest();
        csvCreate();

        TimeUnit.SECONDS.sleep(10);
    }

    public static final void simpleRequest() {

        try {
            URI uri = new URI("http://www.google.com");
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).GET().build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

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


    public static final List<Book> requestBooks() {

        List<Book> books = Collections.emptyList();

        try {
            String csv = HttpClient.newHttpClient()
                    .send(HttpRequest.newBuilder()
                            .uri(new URI("https://turini.github.io/livro-java-9/books.csv"))
                            .GET()
                            .build(), HttpResponse.BodyHandlers.ofString()).body();

            books = Stream.of(csv.split("\n"))
                    .map(Book::toBook)
                    .collect(Collectors.toList());

            books.stream()
                    .map(Book::getCategories)
                    .flatMap(Collection::stream)
                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return books;
    }

    public static void asyncRequest() {

        try {

            URI uri = new URI("https://turini.github.io/livro-java-9/books.csv");
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).GET().build();

            CompletableFuture<HttpResponse<String>> response =
                    HttpClient.newHttpClient()
                            .sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                            .whenComplete((r, t) ->
                                ofNullable(t).ifPresentOrElse(ex -> ex.printStackTrace(), () -> System.out.println(r.body())));

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void csvCreate() {
        try {
            URI uri = new URI("https://turini.github.io/livro-java-9/books.csv");
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).GET().build();
            HttpClient.newHttpClient()
                    .sendAsync(httpRequest, HttpResponse.BodyHandlers.ofFile(Paths.get("books.csv")))
                    .whenComplete((r, t) -> {
                        ofNullable(t).ifPresentOrElse(ex -> ex.printStackTrace(), () -> System.out.println(r.body().toAbsolutePath()));
                    });

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
