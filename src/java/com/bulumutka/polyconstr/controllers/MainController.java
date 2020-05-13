package com.bulumutka.polyconstr.controllers;

import com.bulumutka.polyconstr.models.graphlib.EditMode;
import com.bulumutka.polyconstr.models.graphlib.GraphEditor;
import com.bulumutka.polyconstr.ui.GraphCanvas;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public StackPane drawArea;
    public GraphCanvas canvas;
    public GridPane mainPane;
    public Button vertexButton;
    public Button edgeButton;
    public Button startVertexButton;
    public Button removeButton;
    public Button clearButton;
    private GraphEditor editor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editor = new GraphEditor(canvas);

        vertexButton.setOnAction(event -> {
            System.out.println("Vertex button clicked.");
            editor.setEditMode(EditMode.ADD_VERTEX);
        });

        edgeButton.setOnAction(event -> {
            System.out.println("Edge button clicked.");
            editor.setEditMode(EditMode.ADD_EDGE);
        });

        removeButton.setOnAction(event -> {
            System.out.println("Remove button clicked.");
            editor.setEditMode(EditMode.REMOVE);
        });

        clearButton.setOnAction(event -> {
            editor.reset();
        });

        startVertexButton.setOnAction(event -> {
            System.out.println("Start button clicked.");
            editor.setEditMode(EditMode.START_VERTEX);
        });
    }
}
