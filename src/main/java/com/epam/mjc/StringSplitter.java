package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> split = new ArrayList<>();
        split.add(source);

        for (String delimiter : delimiters) {
            List<String> intermediate = new ArrayList<>();
            for (String current : split) {
                intermediate.addAll(
                        Arrays.stream(current.split(delimiter))
                                .filter(it -> !it.isEmpty())
                                .collect(Collectors.toList())
                );
            }
            split = intermediate;
        }

        return split;
    }
}
