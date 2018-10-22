package com.mangani.example;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MapAndFlatMapExample {

    public static final void toUpperCaseListByMap() {
        List<String> names = List.of("Paulo", "Sérgio", "Bruno");

        List<String> upperCaseNames = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        print("Uppercase names by map", upperCaseNames);
    }

    public static final void toUpperCaseListByFlatMap() {

        List<String> programmers = List.of("Mangani", "Decard", "Darth Vader");
        List<String> designers = List.of("Michael Jordan", "Magic Johnson", "Kareem Abdul Jabbar");
        List<List<String>> workers = List.of(programmers, designers);

        List<String> workersToUppercase = workers.stream()
                .flatMap(Collection::stream)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        print("Uppercase names by flatMap", workersToUppercase);
    }

    private static final void print(String subject, List<String> names) {
        System.out.print(String.format("%s: ", subject));
        names.forEach(System.out::println);
    }

    public static void main(String[] args) {
        toUpperCaseListByMap();
        toUpperCaseListByFlatMap();
    }


}
