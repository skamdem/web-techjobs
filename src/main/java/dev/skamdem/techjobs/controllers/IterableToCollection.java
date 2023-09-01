package dev.skamdem.techjobs.controllers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamdem
 */
public class IterableToCollection {
    // function to convert Iterable into Collection
    public static <T> List<T>
    getListFromIterable(Iterable<T> itr){
        // Create an empty Collection to hold the result
        List<T> cltn = new ArrayList<T>();

        // Iterate through the iterable to
        // add each element into the collection
        for (T t : itr) cltn.add(t);

        // Return the converted collection
        return cltn;
    }
}
