package com.mangani.model;

import com.mangani.types.Category;

import java.util.List;
import java.util.Optional;

public class Book {

    private String name;
    private String author;
    private List<Category> categories;

    public Book(String name, String author, Category ... categories) {
        this.name = name;
        this.author = author;
        this.categories = List.of(categories);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", categories=" + categories +
                '}';
    }

    public static final Book toBook (String line) {
        String[] values = line.split(",");
        return new Book(values[0], values[1], Category.valueOf(values[3]));
    }

    public static final List<Book> all() {
        return List.of(
                new Book(
                        "Desbravando Java",
                        "Rodrigo Turini",
                        Category.PROGRAMMING
                ),
                new Book(
                        "APIs Java",
                        "Rodrigo Turini",
                        Category.PROGRAMMING
                ),

                new Book(
                        "Certificação Java",
                        "Guilherme Silveira",
                        Category.PROGRAMMING, Category.CERTIFICATION
                ),
                new Book(
                        "TDD",
                        "Mauricio Aniche",
                        Category.PROGRAMMING, Category.AGILE
                ),

                new Book(
                        "SOLID",
                        "Mauricio Aniche",
                        Category.PROGRAMMING
                ),
                new Book(
                        "Guia da Startup",
                        "Joaquim Torres",
                        Category.BUSINESS
                )
        );
    }


    public static Optional<Book> findSimilar(Book book) {

        return Book.all().stream()
                .filter(b -> b.getCategories().equals(book.getCategories()))
                .filter(b -> !b.getAuthor().equals(book.getAuthor()))
                .findAny();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}