package com.bulumutka.polyconstr.controllers;

import com.bulumutka.polyconstr.models.*;
import com.bulumutka.polyconstr.models.graphlib.CompressedGraph;
import com.bulumutka.polyconstr.models.graphlib.MetricGraph;
import com.bulumutka.polyconstr.ui.DialogWindow;
import com.bulumutka.polyconstr.ui.GraphCanvas;
import com.sun.javafx.fxml.builder.JavaFXSceneBuilder;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.ResponseCache;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Stack;

public class MainController implements Initializable {
    public StackPane drawArea;
    public GraphCanvas canvas;
    public GridPane mainPane;
    public Button vertexButton;
    public Button edgeButton;
    public Button startVertexButton;
    public Button clearButton;
    public Button countButton;
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

        clearButton.setOnAction(event -> editor.reset());

        startVertexButton.setOnAction(event -> {
            System.out.println("Start button clicked.");
            editor.setEditMode(EditMode.START_VERTEX);
        });

        countButton.setOnAction(event -> {
            if (!editor.hasGraph()) {
                DialogWindow.errorDialog("Draw your graph first");
            } else if (!editor.hasStartVertex()) {
                DialogWindow.errorDialog("Choose start vertex");
            } else {
                try {
                    startCalculations(editor.getGraph());
                } catch (Exception ex) {
                    DialogWindow.errorDialog(ex.getMessage());
                }
            }
        });
    }

    private void startCalculations(MetricGraph graph) {
        var c = new CompressedGraph(graph);
        var vector = c.getVector();
        SafeWriter.writeVector(vector, "data.txt");
        Repository.formulas = PythonScript
                .start("out/production/PolynomialConstruction/com/bulumutka/polyconstr" +
                        "/get_formulas.py");
        showCountResult();
    }

    private void showCountResult() {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(
                    getClass().getClassLoader().getResource("views" + "/ShowResultView.fxml")));
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.centerOnScreen();
            stage.setTitle("Function's formulas");
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException("No resources found " + ex.getMessage());
        }
    }
}
