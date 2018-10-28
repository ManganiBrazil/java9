package com.mangani;

import com.mangani.model.Book;
import com.mangani.types.Category;

import java.lang.reflect.Field;
import java.util.stream.Stream;

public class ReflectionExample {

    public static void main(String[] args) {
        Book book = new Book("Mangani Reflection", "Mangani", Category.PROGRAMMING);
        reflectionTraing(book);


    }

    private static final void reflectionTraing(Object o) {
        Stream<Field> fieldStream = Stream.of(o.getClass().getDeclaredFields());
        fieldStream.forEach(field -> {

            try {
                field.setAccessible(true);
                System.out.println(String.format("Name: %s value: %s", field.getName(), field.get(o)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

    }
}
