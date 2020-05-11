package com.bulumutka.polyconstr.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class DialogWindow {
    static public Optional<Double> edgeWeightDialog() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Edge parameters");
        dialog.setHeaderText("Enter edge weight:");
        dialog.setContentText("Weight:");
        Optional<String> result = dialog.showAndWait();
        if (result.isEmpty()) {
            return Optional.empty();
        }
        try {
            Double weight = Double.parseDouble(result.get());
            return Optional.of(weight);
        } catch (NumberFormatException ex) {
            errorDialog("Wrong edge weight parameter. It should be real number.");
        }
        return Optional.empty();
    }

    static public void errorDialog(String msg) {
        var dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setTitle("Error");
        dialog.setHeaderText(msg);

        dialog.show();
    }
}
