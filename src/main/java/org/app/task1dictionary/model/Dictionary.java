package org.app.task1dictionary.model;

import java.util.Hashtable;

public class Dictionary {
    private final Hashtable<String, String> dictionary;

    public Dictionary() {
        dictionary = new Hashtable<>();
        this.defaultValue();
    }

    public void addWord(String word, String meaning) {
        dictionary.put(word, meaning);
    }

    public String getMeaning(String word) {
        return dictionary.get(word);
    }

    public void removeWord(String word) {
        dictionary.remove(word);
    }

    public String findWord(String word) {
        return dictionary.containsKey(word) ? getMeaning(word) : null;
    }

    private void defaultValue() {
        dictionary.put("ONE", "SATU");
        dictionary.put("TWO", "DUA");
        dictionary.put("THREE", "TIGA");
    }
}
