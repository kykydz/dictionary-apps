package org.app.task1dictionary.model;

import java.util.Hashtable;

public class Dictionary {
    private static final Hashtable<String, String> dictionary;

    // Initialize the dictionary with default values
    static {
        dictionary = new Hashtable<>();
        dictionary.put("ONE", "SATU");
        dictionary.put("TWO", "DUA");
        dictionary.put("THREE", "TIGA");
    }

    // Method to add a word to the dictionary
    private static void addWord(String enWord, String idWord) {
        dictionary.put(enWord, idWord);
    }

    // Method to remove a word from the dictionary
    private void removeWord(String keyWord) {
        dictionary.remove(keyWord);
    }

    // Method to find the Indonesian word corresponding to an English word
    private String getIDWordFromEn(String enWord) {
        return dictionary.get(enWord);
    }

    // Method to find the English word corresponding to an Indonesian word
    private String getEnWordFromId(String idWord) {
        for (String englishWord : dictionary.keySet()) {
            if (dictionary.get(englishWord).equals(idWord)) {
                return englishWord;
            }
        }
        return null;
    }

    // Method to retrieve the entire dictionary
    public Hashtable<String, String> getDictionary() {
        return dictionary;
    }
}
