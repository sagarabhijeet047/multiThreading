package org.example;

import java.util.*;

public class StreamConcepts {

    static char findFirstNonRepeating(String str) {
        Map<Character, Integer> findFirst = new LinkedHashMap<>();
        for (char c : str.toCharArray()) {
            findFirst.put(c, findFirst.getOrDefault(c, 0) + 1);
        }

        // First character with count = 1
        for (Map.Entry<Character, Integer> entry : findFirst.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return '_';
    }

    public static void main(String[] args) {

//        Sum of all even numbers in a list using Stream API.
//                Input: [1,2,3,4,5,6] → Output: 12

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);
        int sum = nums.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
//        System.out.println(sum);

//        Count strings starting with a specific letter.
//                Input: ["apple", "banana", "apricot"], letter a → Output: 2

        List<String> fruits = Arrays.asList("apple", "banana", "apricot");
        int count = (int) fruits.stream()
                .filter(n -> n.startsWith("a"))
                .count();
//        System.out.println(count);

//        Find max and min from a list of integers using Streams.
//                Use max() and min() with Comparator.naturalOrder().
        List<Integer> numbers = Arrays.asList(42, 7, 15, 99, 3, 54, 21, 88);
        int max = numbers.stream()
                .max(Comparator.naturalOrder())
                .orElse(0);
//        System.out.println(max);

//        Convert list of strings to uppercase and collect back to a list.
        List<String> fruitsList = Arrays.asList("Banana", "Apple", "Elderberry", "Cherry", "Date");
        List<String> fruitsListToUpperCase = fruitsList.stream()
                .map(String::toUpperCase)
                .toList();
//        System.out.println(fruitsListToUpperCase);

//        Find average of numbers using mapToInt() and average().
        int average = (int) numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
//        System.out.println(average);

//        Remove duplicates from a list using distinct().
        List<String> cities = Arrays.asList("London", "Paris", "Tokyo", "London", "New York", "Tokyo", "Tokyo", "Berlin");
        List<String> distinctCities = cities.stream()
                .distinct()
                .toList();
//        System.out.println(distinctCities);

//        Concatenate all strings in a list into a single string using reduce().
        String allCities = cities.stream()
                .reduce("", String::concat);

//        Find the first element greater than 50 using findFirst().
        int first = numbers.stream()
                .filter(n -> n > 50)
                .findFirst()
                .orElse(0);
        System.out.println(first);

        List<Integer> arrayListNumbers = Arrays.asList(42, 7, 15, 99, 3, 54, 21, 88, 42, 7, 15, 99, 3, 54, 21, 88, 3, 21);
        List<Integer> removeDuplicates = arrayListNumbers.stream().distinct().toList();
        System.out.println("Distinct array element: " + removeDuplicates);

        Set<Integer> uniqueSet = new HashSet<>(arrayListNumbers);
        System.out.println("Distinct set element: " + uniqueSet);
        System.out.println(findFirstNonRepeating("aabcbcde"));


    }
}
