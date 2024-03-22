module org.app.task1dictionary {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.app.task1dictionary.controller to javafx.fxml;
    exports org.app.task1dictionary;
    exports org.app.task1dictionary.model;
    exports org.app.task1dictionary.controller;
    exports org.app.task1dictionary.view;
}