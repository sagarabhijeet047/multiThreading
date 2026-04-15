package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamAdv {
    public static void main(String[] args) {
        String input = "swiss";

        Character result = input.chars()           // IntStream of characters
                .mapToObj(c -> (char) c)               // Convert to Stream<Character>
                .collect(Collectors.groupingBy(        // Group and count
                        Function.identity(),
                        LinkedHashMap::new,                // Maintains insertion order
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1) // Filter non-repeating
                .map(Map.Entry::getKey)
                .findFirst()                            // Get the first one
                .orElse(null);

        System.out.println("First non-repeating character: " + result);

        List<Integer> numbers = Arrays.asList(12,1,454,512,12,584,4,3,53,58748,57,44);
        int secondLargest = numbers.stream()
                            .distinct()
                            .sorted(Comparator.reverseOrder())
                            .skip(1)
                            .findFirst()
                            .orElse(0);
        System.out.println("Second largest: "+secondLargest);
    }
}