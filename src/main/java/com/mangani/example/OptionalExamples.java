package com.mangani.example;

import com.mangani.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OptionalExamples {

    public static final void ifPresentOrElse() {
        Book book = null;

        Optional.ofNullable(book).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Book does not exist!"));
    }

    public static final void findSimilarByMap() {
        List<Book> books = Book.all();
        List<Optional<Book>> similars =   books.stream().map(Book::findSimilar).collect(Collectors.toList());
        System.out.println(similars);
    }

    public static final void findSimilarByFlatMap() {
        List<Book> books = Book.all();
        List<Book> similars = books.stream().flatMap(b -> Book.findSimilar(b).stream()).collect(Collectors.toList());
        System.out.println(similars);
    }

    public static void main(String[] args) {
        ifPresentOrElse();
        findSimilarByMap();
        findSimilarByFlatMap();
    }
}
