package com.mangani.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionExamples {

    /**
     * All these collections are immutable.
     */
    public static final void mapOf() {
        Map<Integer, String> weekDays = Map.of(
                1, "Monday",
                2, "Tuesday",
                3, "Wednesday",
                4, "Thursday",
                5, "Friday",
                6, "Saturday",
                7, "Sunday"
        );
    }

    /**
     * All these collections are immutable.
     */
    public static final void mapOfEntries() {
        Map.ofEntries(
                Map.entry(1, "Monday"),
                Map.entry(2, "Tuesday"),
                Map.entry(3, "Wednesday"),
                Map.entry(4, "Thursday"),
                Map.entry(5, "Friday"),
                Map.entry(6, "Saturday"),
                Map.entry(7, "Sunday")
        );
    }

    public static final void listOf() {
        List<String> names = List.of("Paulo", "Sérgio", "Bruno");
    }

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

    public static void main(String[] args) {
        streamMap();
    }
}
