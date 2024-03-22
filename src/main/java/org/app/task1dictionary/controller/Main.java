package org.app.task1dictionary.controller;

import javafx.beans.value.ObservableValue;
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
    public TableColumn<String, String> tableColEnglish, tableColIndonesia;
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
    }

    public void onBtnSaveWordClick(ActionEvent actionEvent) {
    }

    public void onBtnDeleteWordClick(ActionEvent actionEvent) {
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

//        primaryStage = (Stage) componentLists.getFirst().getScene().getWindow();

        languageListener = new LanguageListener(dictModeRBGroup, componentLists,primaryStage);
        languageListener.defaultLanguageMode();
        dictModeRBGroup.selectedToggleProperty().addListener(this::toggleChangeListener);
    }

    public List<Node> initializeControls(Parent root) {
        ClassExtractor classExtractor = new ClassExtractor();
        return classExtractor.extractControls(new ArrayList<>(root.getChildrenUnmodifiable()));
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
