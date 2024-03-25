package org.app.task1dictionary.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

public class Dictionary {
    private static Hashtable<String, String> dictionary;
    private String english;
    private String indonesian;

    public Dictionary(String english, String indonesian) {
        this.english = english;
        this.indonesian = indonesian;
        defaultValue();
    }

    public String getEnglish() {
        return english;
    }

    public String getIndonesian() {
        return indonesian;
    }

    public void addWord(String enWord, String idWord) {
        dictionary.put(enWord, idWord);
    }

    public String getIDWordFromEn(String enWord) {
        return dictionary.get(enWord);
    }

    public String getEnWordFromId(String idWord) {
        for (String englishWord : dictionary.keySet()) {
            if (dictionary.get(englishWord).equals(idWord)) {
                return englishWord;
            }
        }
        return null;
    }

    public Dictionary getRecord(String keyWord) {
        Dictionary foundDictRecord;
        String key = null;
        String value = null;
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            key = entry.getKey();
            if (Objects.equals(key, keyWord)) {
                value = entry.getValue();
                break;
            }
        }
        if (key != null && value != null) {
            foundDictRecord = new Dictionary(key, value);
            return foundDictRecord;
        }
        return null;
    }

    public void removeWord(String keyWord) {
        dictionary.remove(keyWord);
    }

    public String findWord(String enWord) {
        return dictionary.containsKey(enWord) ? getIDWordFromEn(enWord) : null;
    }

    public static void defaultValue() {
        if (dictionary == null) {
            dictionary = new Hashtable<>();
            dictionary.put("ONE", "SATU");
            dictionary.put("TWO", "DUA");
            dictionary.put("THREE", "TIGA");
        }
    }

    public static ObservableList<Dictionary> getDictionary() {
        Hashtable<String, String> dictionary = getInitializedDictionary();

        // Populate the ObservableList with data from the Hashtable
        ObservableList<Dictionary> data = FXCollections.observableArrayList();
        for (String english : dictionary.keySet()) {
            String indonesian = dictionary.get(english);
            data.add(new Dictionary(english, indonesian));
        }
        return data;
    }

    public ObservableList<Dictionary> showDictionary(ObservableList<Dictionary> currentDictionaries) {
        dictionary.forEach((key, value) -> currentDictionaries.add(new Dictionary(key, value)));
        return currentDictionaries;
    }

    private static Hashtable<String, String> getInitializedDictionary() {
        Dictionary instance = new Dictionary("", ""); // Dummy instance to ensure initialization
        instance.defaultValue();
        return dictionary;
    }
}
