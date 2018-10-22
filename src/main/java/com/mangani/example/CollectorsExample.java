package com.mangani.example;

import com.mangani.model.Book;
import com.mangani.types.Category;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectorsExample {

    /**
     * With java 8, the author mangani will be exclude from the result list.
     */
    public static final void groupByAuthorJava8() {
        List<Book> books = List.of(
                new Book("Java 9 how to program", "Paul Harvey Deitel", Category.PROGRAMMING),
                new Book("Pro Angular", "Adam Free", Category.PROGRAMMING),
                new Book("TDD", "Maurício Aniche", Category.PROGRAMMING, Category.AGILE),
                new Book("Cooking crack", "Mangani", Category.BUSINESS)
        );

        Map<String, List<Book>> booksCatalog = books.stream()
                .filter(b -> b.getCategories().contains(Category.PROGRAMMING))
                .collect(Collectors.groupingBy(Book::getAuthor));

        System.out.println(booksCatalog);
    }

    /**
     * With java 8, the author mangani will be include to the result list, but just his name representing an empty object.
     */
    public static final void groupByAuthorJava9() {
        List<Book> books = List.of(
                new Book("Java 9 how to program", "Paul Harvey Deitel", Category.PROGRAMMING),
                new Book("Pro Angular", "Adam Free", Category.PROGRAMMING),
                new Book("TDD", "Maurício Aniche", Category.PROGRAMMING, Category.AGILE),
                new Book("Cooking crack", "Mangani", Category.BUSINESS),
                new Book("How to make a good job", "Mangani", Category.BUSINESS)
        );

        Map<String, List<Book>> booksCatalog = books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.filtering(b -> b.getCategories().contains(Category.PROGRAMMING), Collectors.toList())));

        System.out.println(booksCatalog);
    }

    public static void main(String[] args) {
        groupByAuthorJava8();
        groupByAuthorJava9();
        collectorsWithflatMap();
    }

    public static final void collectorsWithflatMap() {

        // aggregates a list of books grouped by author containing a category that they've written books.

        List<Book> books = List.of(
                new Book("Java 9 how to program", "Paul Harvey Deitel", Category.PROGRAMMING),
                new Book("Pro Angular", "Adam Free", Category.PROGRAMMING),
                new Book("TDD", "Maurício Aniche", Category.PROGRAMMING, Category.AGILE),
                new Book("Cooking crack", "Mangani", Category.BUSINESS),
                new Book("How to make a good job", "Mangani", Category.BUSINESS)
        );

        Map<String, Set<Category>> map =  books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor,
                        Collectors.flatMapping(b -> b.getCategories().stream(),
                        Collectors.toSet())));

        System.out.println(map);
    }

}
