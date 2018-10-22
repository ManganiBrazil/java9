package com.mangani.model;

import com.mangani.types.Category;

import java.util.List;

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