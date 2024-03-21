package org.app.task1dictionary.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowLoader {

    public void load (FXMLLoader fxmlLoader, Stage stage) throws IOException {
        Scene scene = new Scene(fxmlLoader.load());

        // Get the scene's root node
        Parent root = scene.getRoot();

        // Calculate preferred size based on layout bounds (same as before)
        double prefWidth = root.prefWidth(-1);
        double prefHeight = root.prefHeight(-1);

        // Adjust for window decorations (optional)
        prefWidth += 16;
        prefHeight += 39;

        stage.setWidth(prefWidth);
        stage.setHeight(prefHeight);
        stage.setScene(scene);
        stage.show();
    }
}
