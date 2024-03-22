package org.app.task1dictionary.utils;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.app.task1dictionary.Main;
import org.app.task1dictionary.view.Components;

import java.lang.reflect.Field;
import java.util.*;

public class LanguageListener extends Main {

    private final ToggleGroup dictModeRBGroup;
    private final Stage primaryStage;

    List<Node> componentLists;

    public enum Language {
        EN("English - Indonesia"),
        ID("Indonesia - English");

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

    public LanguageListener (ToggleGroup dictModeRBGroup, List<Node> componentLists, Stage primaryStage) {
        this.dictModeRBGroup = dictModeRBGroup;
        this.componentLists = componentLists;
        this.primaryStage = primaryStage;
    }

    private Language currentLanguage = Language.ID;

    public void handleLanguageChange(List<Node> componentLists) {
        RadioButton selectedButton = (RadioButton) dictModeRBGroup.getSelectedToggle();
        if (selectedButton != null) {
            String selectedMode = selectedButton.getText();
            currentLanguage = selectedMode.equals(Language.EN.getFullMode()) ?
                    Language.EN : Language.ID;
        }

        updateUILanguageChange(currentLanguage, componentLists);
    }

    public void defaultLanguageMode() {
        updateUILanguageChange(currentLanguage, componentLists);
    }

    private void updateUILanguageChange(Language currentLanguage, List<Node> componentLists) {
        setTextForAllComponent(componentLists, currentLanguage.getLocalizedString());
    }

    public void setTextForAllComponent(List<Node> components, ResourceBundle resourceBundle) {
        // set for title of window
        primaryStage.setTitle(resourceBundle.getString("stage.title"));

        for (Node node : components) {
            try {
                String controlId = node.getId();
                String text = resourceBundle.getString(controlId + ".text");

                switch (node.getClass().getSimpleName()) {
                    case "Label" -> ((Label) node).setText(text);
                    case "Button" -> ((Button) node).setText(text);
                    case "RadioButton" -> ((RadioButton) node).setText(text);
                    case "TableView" -> {
                        for (TableColumn<?, ?> column : ((TableView<?>) node).getColumns()) {
                            String columnId = column.getId();
                            String textColumn = resourceBundle.getString(columnId + ".text");
                            column.setText(textColumn);
                        }
                    }
                    default -> {
                    }
                }
            } catch (MissingResourceException e) {
                System.out.println("Warning: Key '" + node + "' not found in resource bundle.");
            }
        }
    }
}
