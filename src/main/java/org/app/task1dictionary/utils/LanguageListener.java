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

    @FXML
    private TextField textInputEngWord, textInputInaWord;
    private Stage primaryStage;

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

    public LanguageListener (ToggleGroup dictModeRBGroup, List<Node> componentLists, TextField textInputEngWord, TextField textInputInaWord) {
        super();
        this.dictModeRBGroup = dictModeRBGroup;
        this.componentLists = componentLists;
        this.textInputEngWord = textInputEngWord;
        this.textInputInaWord = textInputInaWord;
    }

    private Language currentLanguage = Language.ID;

    public void handleLanguageChange(List<Node> componentLists) {
        RadioButton selectedButton = (RadioButton) dictModeRBGroup.getSelectedToggle();
        if (selectedButton != null) {
            String selectedMode = selectedButton.getText();
            currentLanguage = selectedMode.equals(Language.EN.getFullMode()) ?
                    Language.EN : Language.ID;
        }
        primaryStage = (Stage) componentLists.getFirst().getScene().getWindow();

        updateUILanguageChange(currentLanguage, componentLists);
        updateControlChange();
    }

    public void defaultLanguageMode() {
        updateUILanguageChange(currentLanguage, componentLists);
        updateControlChange();
    }

    public void updateControlChange() {
        String dictionaryMode = ((RadioButton) dictModeRBGroup.getSelectedToggle()).getText();
        LanguageListener.Language langEn = LanguageListener.Language.EN;
        LanguageListener.Language langId = LanguageListener.Language.ID;
        if (dictionaryMode.equals(langEn.getFullMode())) {
            textInputInaWord.setDisable(true);
            textInputEngWord.setDisable(false);
        } else {
            textInputInaWord.setDisable(false);
            textInputEngWord.setDisable(true);
        }
    }

    private void updateUILanguageChange(Language currentLanguage, List<Node> componentLists) {
        setTextForAllComponent(componentLists, currentLanguage.getLocalizedString());
    }

    public void setTextForAllComponent(List<Node> components, ResourceBundle resourceBundle) {
        // set for title of window
        if (primaryStage != null) {
            primaryStage.setTitle(resourceBundle.getString("stage.title"));
        }

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
