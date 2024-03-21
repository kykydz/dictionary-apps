package org.app.task1dictionary.utils;

import javafx.scene.control.*;
import org.app.task1dictionary.view.Components;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LanguageListener {
    public RadioButton rbDictModeInaEng, rbDictModeEngIna;
    private final ToggleGroup dictModeRBGroup;

    public enum Language {
        EN("English - Indonesia"),
        ID("Indonesia - Inggris");

        private final String fullMode;

        Language(String fullMode) {
            this.fullMode = fullMode;
        }

        public String getFullMode() {
            return fullMode;
        }

        public ResourceBundle getLocalizedString() {
            ResourceBundle resources = ResourceBundle.getBundle(
                    "resources",
                    Locale.forLanguageTag(this.name().toLowerCase())
            );
            return resources;
        }
    }

    public LanguageListener (ToggleGroup dictModeRBGroup) {
        this.dictModeRBGroup = dictModeRBGroup;
    }

    private Language currentLanguage = Language.EN;

    public void handleLanguageChange() {
        RadioButton selectedButton = (RadioButton) dictModeRBGroup.getSelectedToggle();
        if (selectedButton != null) {
            String selectedMode = selectedButton.getText();
            currentLanguage = selectedMode.equals(Language.EN.getFullMode()) ?
                    Language.EN : Language.ID;
        }

        updateUILanguageChange(currentLanguage);
    }

    private void updateUILanguageChange(Language currentLanguage) {
        Components components = new Components();
        setTextForAllComponents(components, currentLanguage.getLocalizedString());
    }

    public void setTextForAllComponents(Components components, ResourceBundle resourceBundle) {
        for (Field field : components.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();

            try {
                String text = resourceBundle.getString(fieldName);

                if (field.getType() == Label.class) {
                    ((Label) field.get(components)).setText(text);
                } else if (field.getType() == Button.class) {
                    ((Button) field.get(components)).setText(text);
                } else if (field.getType() == RadioButton.class) {
                    ((RadioButton) field.get(components)).setText(text);
                } else if (field.getType() == TextField.class) {
                    ((TextField) field.get(components)).setPromptText(text);
                }
            } catch (MissingResourceException e) {
                System.out.println("Warning: Key '" + fieldName + "' not found in resource bundle.");
            } catch (IllegalAccessException e) {
                System.out.println("Warning: Could not access field: " + fieldName);
            }
        }
    }

}
