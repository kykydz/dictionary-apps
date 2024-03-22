package org.app.task1dictionary.utils;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import org.app.task1dictionary.Main;
import org.app.task1dictionary.view.Components;

import java.lang.reflect.Field;
import java.util.*;

public class LanguageListener extends Main {

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
            return ResourceBundle.getBundle(
                    "locale." + this.name().toLowerCase(),
                    Locale.forLanguageTag(this.name().toLowerCase())
            );
        }
    }

    public LanguageListener (ToggleGroup dictModeRBGroup) {
        this.dictModeRBGroup = dictModeRBGroup;
    }

    private Language currentLanguage;

    public void handleLanguageChange(List<Node> componentLists) {
        RadioButton selectedButton = (RadioButton) dictModeRBGroup.getSelectedToggle();
        if (selectedButton != null) {
            String selectedMode = selectedButton.getText();
            currentLanguage = selectedMode.equals(Language.EN.getFullMode()) ?
                    Language.EN : Language.ID;
        }

        updateUILanguageChange(currentLanguage, componentLists);
    }

    private void updateUILanguageChange(Language currentLanguage, List<Node> componentLists) {
        setTextForAllComponent(componentLists, currentLanguage.getLocalizedString());
    }

    public void setTextForAllComponent(List<Node> components, ResourceBundle resourceBundle) {
        for (Field field : components.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();

            try {
                System.out.println("Field Name: " + fieldName);
                String text = resourceBundle.getString(fieldName + ".text");

                Object control = field.get(components);
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

    public void setTextForAllComponents(Components components, ResourceBundle resourceBundle) {
        for (Field field : components.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();

            try {
                System.out.println("Field Name: " + fieldName);
                String text = resourceBundle.getString(fieldName + ".text");

                Object control = field.get(components);
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
