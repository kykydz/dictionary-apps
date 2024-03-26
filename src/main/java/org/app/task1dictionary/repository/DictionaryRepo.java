package org.app.task1dictionary.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.app.task1dictionary.model.Dictionary;

import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

public class DictionaryRepo {
    private final Dictionary dictionaryModel;

    public DictionaryRepo() {
        dictionaryModel = new Dictionary();
    }

    public Dictionary getRecord(String keyWord) {
        Dictionary foundDictRecord;
        String key = null;
        String value = null;
        Hashtable<String, String> dictionary = dictionaryModel.getDictionary();

        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            key = entry.getKey();
            if (Objects.equals(key, keyWord)) {
                value = entry.getValue();
                break;
            }
        }
        if (key != null && value != null) {
            foundDictRecord = new Dictionary();
            return foundDictRecord;
        }
        return null;
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
        Dictionary instance = new Dictionary("", "");
        instance.defaultValue();
        return dictionary;
    }
}
