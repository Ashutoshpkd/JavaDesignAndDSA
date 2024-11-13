package com.interview.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = List.of(1,2,3,4,5, 11, 14,6,7,8,8,9,10);
        List<String> stringList = List.of("Ashutosh", "Manjiri", "Yash", "Charul");
        List<List<String>> nested = List.of(stringList, stringList);
        List<Person> people = Arrays.asList(
                new Person("Alice", 25, "New York"),
                new Person("Bob", 30, "London"),
                new Person("Charlie", 35, "New York"),
                new Person("David", 40, "London"),
                new Person("Eve", 45, "Paris")
        );
        System.out.println((list.stream().skip(3).limit(5).toList()));
        System.out.println(list.stream().mapToDouble(i -> i).average().orElse(-1.0));
        System.out.println(filterEven(list));
        System.out.println(upperCaseConvertor(stringList));
        System.out.println(sortList(stringList));
        System.out.println(findMax(list));
        System.out.println(countOccurrence(list, 8));
        System.out.println(groupByCity(people));
        System.out.println(findSum(list));
        System.out.println(getDistinct(list));
        System.out.println(flatList(nested));
    }

    private static Map<String, List<Person>> groupByCity(List<Person> people) {

        Function<Person, String> groupPeople = (person -> person.getCity());

        return people.stream().collect(Collectors.groupingBy(groupPeople));
    }

    public static List<Integer> filterEven(List<Integer> list) {
        Predicate<Integer> evenFilter = (number) -> number % 2 == 0;
        return list.stream().filter(evenFilter).toList();
    }

    public static List<String> upperCaseConvertor(List<String> list) {
        Function<String, String> toUpperCase = (original) -> original.toUpperCase();
        return list.stream().map(toUpperCase).toList();
    }

    public static List<String> sortList(List<String> list) {
        return list.stream().sorted(Comparator.comparing(String::toUpperCase)).toList();
    }

    public static Integer findMax(List<Integer> list) {
        return list.stream().max(Comparator.comparingInt(i -> i)).orElse(-1);
    }

    public static Long countOccurrence(List<Integer> list, int num) {
        return list.stream()
                .filter(element -> element == num)
                .count();
    }

    public static Integer findSum(List<Integer> list) {
        BinaryOperator<Integer> reducer = (number, acc) -> acc + number;

        return list.stream().reduce(0, (a, b) -> a + b);
    }

    public static List<Integer> getDistinct(List<Integer> list) {
        return list.stream().distinct().toList();
    }

    public static List<String> flatList(List<List<String>> nested) {
        return nested.stream()
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());
    }
}
