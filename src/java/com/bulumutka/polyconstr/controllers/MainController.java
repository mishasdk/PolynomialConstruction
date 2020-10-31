package com.bulumutka.polyconstr.controllers;

import com.bulumutka.polyconstr.models.*;
import com.bulumutka.polyconstr.models.graphlib.CompressedGraph;
import com.bulumutka.polyconstr.models.graphlib.MetricGraph;
import com.bulumutka.polyconstr.ui.DialogWindow;
import com.bulumutka.polyconstr.ui.GraphCanvas;
import com.bulumutka.polyconstr.ui.WindowStarter;
import com.bulumutka.polyconstr.ui.WindowType;
import com.bulumutka.polyconstr.utils.SafeWriter;
import com.bulumutka.polyconstr.utils.ScriptExecutorKt;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

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
        var data = c.getVector();
        var result = new ArrayList<String>();
        for (var elem : data) {
            result.add(elem.toString());
        }
        Repository.formulas = ScriptExecutorKt.getFormulasPy(result);
        showCountResult();
    }

    private void showCountResult() {
        new WindowStarter().startWindow(WindowType.FORMULAS);
    }
}
