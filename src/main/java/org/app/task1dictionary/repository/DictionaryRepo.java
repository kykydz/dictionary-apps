package org.app.task1dictionary.repository;

import java.util.Hashtable;

public class DictionaryRepo {
    private static Hashtable<String, String> dictionary;

    static {
        dictionary = new Hashtable<>();
        dictionary.put("ONE", "SATU");
        dictionary.put("TWO", "DUA");
        dictionary.put("THREE", "TIGA");
    }
}
