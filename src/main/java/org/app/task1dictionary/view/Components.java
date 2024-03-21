package org.app.task1dictionary.view;

import javafx.scene.control.*;
import org.app.task1dictionary.model.Dictionary;

public class Components {

    public TextField textInputEngWord;
    public TextField textInputInaWord;
    public Button btnFindWord, btnSaveWord, btnShowDict;
    public RadioButton rbDictModeInaEng, rbDictModeEngIna;

    public TableView<Dictionary> tableViewDictionary;
    public ToggleGroup dictModeRBGroup;
    public Button btnDeleteWord;
    public Button btnExit;
    public Label labelDictionaryMode;
    public Label labelInputEngWord;
    public Label labelInputInaWord;
}
