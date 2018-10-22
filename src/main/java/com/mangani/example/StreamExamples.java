package com.mangani.example;

import com.mangani.model.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExamples {

    public static final void streamMap() {
        Map<Integer, String> weekDays = new HashMap<>();
        weekDays.put(1,"Monday") ;
        weekDays.put(2,"Tuesday");
        weekDays.put(3,"Wednesday");
        weekDays.put(4,"Thursday") ;
        weekDays.put(5,"Friday");
        weekDays.put(6,"Saturday");
        weekDays.put(7,"Sunday");

        // transforming in list of weekdays containing only the first tree letters of the day.
        List dayNames = weekDays
                .entrySet()
                .stream()
                .flatMap(e -> Stream.ofNullable(e.getValue()))
                .map(s -> s.substring(0, 3))
                .collect(Collectors.toList());

        System.out.println(dayNames);
    }

    /**
     * Skips the first 5 values of a stream and print the others.
     */
    public static final void skip() {
        System.out.println("skip: ");
        IntStream.range(0, 10).skip(5).forEach(System.out::println);
    }

    /**
     *  Even there is a range of values, the limit method accept only tree elements.
     */
    public static final void limit() {
        System.out.println("limit: ");
        IntStream.range(0, 10).limit(3).forEach(System.out::println);
    }

    public static final void dropWhile() {
        System.out.println("dropWhile: ");
        // number of elements less or equal 5.
        IntStream.range(0, 10).dropWhile(e -> e <= 5).forEach(System.out::println);
    }

    public static final void takeWhile() {
        System.out.println("takeWhile:");
        IntStream.range(0, 10).takeWhile(e -> e <= 5).forEach(System.out::println);
    }

    public static final void dropWhileNotNo() {
        Stream.of("Paulo", "Sérgio", "No", "Bruno").dropWhile(str -> !str.equals("No")).forEach(System.out::println);
    }

    public static final void iteratePersons() {
        List<Person> persons = List.of(
            new Person("Paulo", Byte.valueOf("40")),
            new Person("Sérgio", Byte.valueOf("40")),
            new Person("Bruno", Byte.valueOf("40")));

        Stream.iterate(1, n -> n <= persons.size(), n -> n + 1).forEach(System.out::println);
    }

    public static void main(String[] args) {
        streamMap();
        skip();
        limit();
        dropWhile();
        takeWhile();
        dropWhileNotNo();
        iteratePersons();
    }
}
