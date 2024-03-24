package org.app.task1dictionary.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.app.task1dictionary.model.Dictionary;
import org.app.task1dictionary.utils.ClassExtractor;
import org.app.task1dictionary.utils.LanguageListener;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Main implements Initializable {
    private Stage primaryStage;
    @FXML
    public TableColumn<Dictionary, String> tableColEnglish, tableColIndonesia;

    @FXML
    protected Parent root;
    // Declare component fields
    @FXML
    public TextField textInputEngWord, textInputInaWord;
    @FXML
    public Button btnFindWord, btnSaveWord, btnShowDict, btnDeleteWord, btnExit;
    @FXML
    public RadioButton rbDictModeInaEng, rbDictModeEngIna;
    @FXML
    public TableView<Dictionary> tableViewDictionary;
    @FXML
    public ToggleGroup dictModeRBGroup;
    @FXML
    public Label labelDictionaryMode, labelInputEngWord, labelInputInaWord;

    public List<Node> componentLists;

    private LanguageListener languageListener;

    public void onBtnFindWordClick(ActionEvent actionEvent) {
        String langMode = this.GetSelectedDictionaryMode();
        String word;
        Dictionary currentDictionary = (Dictionary) Dictionary.getDictionary();
        Dictionary foundRecord;
        if (langMode.equals("en")) {
            word = GetInputEnWord();
            foundRecord = currentDictionary.getRecord(word);
        } else {
            word = GetInputEnWord();
            currentDictionary.getEnWord(word);
            foundRecord = currentDictionary.getRecord(word);
        }
        ObservableList<Dictionary> currentDictionaries = FXCollections.observableArrayList();
        tableViewDictionary.setItems(currentDictionaries);
    }

    public void onBtnSaveWordClick(ActionEvent actionEvent) {
        String enWordInput = this.GetInputEnWord();
        String idWordInput = this.GetInputEnWord();
        Dictionary currentDictionary = (Dictionary) Dictionary.getDictionary();
        currentDictionary.addWord(enWordInput, idWordInput);
        ObservableList<Dictionary> newDictionaries = FXCollections.observableArrayList();
        tableViewDictionary.setItems(newDictionaries);
    }

    public void onBtnDeleteWordClick(ActionEvent actionEvent) {
        String langMode = GetSelectedDictionaryMode();
        String word;
        String foundWord;
        Dictionary currentDictionary = (Dictionary) Dictionary.getDictionary();
        if (langMode.equals("en")) {
            word = GetInputEnWord();
            foundWord = currentDictionary.getIDWord(word);
        } else {
            word = GetInputEnWord();
            foundWord = currentDictionary.getEnWord(word);
        }
        ObservableList<Dictionary> currentDictionaries = FXCollections.observableArrayList();
        tableViewDictionary.setItems(currentDictionaries);
    }

    public void onBtnExitClick(ActionEvent actionEvent) {
    }

    public void onBtnShowDictClick(ActionEvent actionEvent) {
    }

    public void toggleChangeListener(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
        languageListener.handleLanguageChange(componentLists);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        componentLists = initializeControls(root);

        languageListener = new LanguageListener(dictModeRBGroup, componentLists);
        languageListener.defaultLanguageMode();
        dictModeRBGroup.selectedToggleProperty().addListener(this::toggleChangeListener);

        initializeTable();
    }

    public List<Node> initializeControls(Parent root) {
        ClassExtractor classExtractor = new ClassExtractor();
        return classExtractor.extractControls(new ArrayList<>(root.getChildrenUnmodifiable()));
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private ObservableList<Dictionary> dictionaries;
    private void initializeTable() {
        dictionaries = FXCollections.observableArrayList();
        tableViewDictionary.setItems(dictionaries);

        tableColEnglish.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEnglish()));
        tableColIndonesia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIndonesian()));

        // populate initial dictionary and show
        tableViewDictionary.setItems(Dictionary.getDictionary());
    }

    private String GetInputEnWord() {
        return textInputEngWord.getText();
    }

    private String GetInputIdWord() {
        return textInputInaWord.getText();
    }

    private String GetSelectedDictionaryMode() {
        String dictionaryMode = ((RadioButton) dictModeRBGroup.getSelectedToggle()).getText();
        LanguageListener.Language langEn = LanguageListener.Language.EN;
        LanguageListener.Language langId = LanguageListener.Language.ID;
        if (dictionaryMode.equals(langEn.getFullMode())) {
            return LanguageListener.Language.EN.name();
        } else {
            return LanguageListener.Language.ID.name();
        }
    }
}
