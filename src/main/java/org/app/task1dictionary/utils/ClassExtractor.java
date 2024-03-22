package org.app.task1dictionary.utils;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ClassExtractor {
    private final List<Node> fields = new ArrayList<>();

    public List<Node> extractControls(List<Node> nodes) {
        for (Node node : nodes) {
            if (node instanceof Pane) {
                extractControls(((Pane) node).getChildren());
            } else {
                fields.add(node);
            }
        }
        return fields;
    }
}
