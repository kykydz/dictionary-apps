package org.app.task1dictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.app.task1dictionary.utils.WindowLoader;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/app/task1dictionary/view/Main.fxml"));

        WindowLoader windowLoader = new WindowLoader();
        windowLoader.load(fxmlLoader, primaryStage);
    }
}
