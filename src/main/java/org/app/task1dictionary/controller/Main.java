package org.app.task1dictionary.controller;

import javafx.event.ActionEvent;
import org.app.task1dictionary.utils.LanguageListener;
import org.app.task1dictionary.view.Components;

public class Main extends Components{

    private LanguageListener languageListener;

    public void initialize() {
        LanguageListener languageListener = new LanguageListener(dictModeRBGroup);
        dictModeRBGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> languageListener.handleLanguageChange());
    }

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
}
