package org.app.task1dictionary.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.app.task1dictionary.model.Dictionary;

public class Components {

    @FXML
    public TextField textInputEngWord;
    @FXML
    public TextField textInputInaWord;
    @FXML
    public Button btnFindWord, btnSaveWord, btnShowDict;
    @FXML
    public RadioButton rbDictModeInaEng, rbDictModeEngIna;
    @FXML
    public TableView<Dictionary> tableViewDictionary;
    @FXML
    public ToggleGroup dictModeRBGroup;
    @FXML
    public Button btnDeleteWord;
    @FXML
    public Button btnExit;
    @FXML
    public Label labelDictionaryMode;
    @FXML
    public Label labelInputEngWord;
    @FXML
    public Label labelInputInaWord;
}
