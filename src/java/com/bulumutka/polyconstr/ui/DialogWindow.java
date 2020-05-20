package com.bulumutka.polyconstr.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;
import java.util.OptionalLong;

public class DialogWindow {
    static public Optional<Double> edgeWeightDialog() {
        Optional<String> result = textInputDialogShow();
        if (result.isEmpty()) {
            return Optional.empty();
        }
        try {
            Double weight = Double.parseDouble(result.get());
            if (weight <= 0) {
                throw new NumberFormatException();
            }
            return Optional.of(weight);
        } catch (NumberFormatException ex) {
            errorDialog("Wrong edge weight parameter. It should be real number > 0.");
        }
        return Optional.empty();
    }

    static public Optional<String> edgeWeightSymbDialog() {
        var result = textInputDialogShow();
        if (result.isEmpty()) {
            return Optional.empty();
        }
        return result;
    }

    static public void errorDialog(String msg) {
        var dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setTitle("Error");
        dialog.setHeaderText(msg);

        dialog.show();
    }

    static private Optional<String> textInputDialogShow() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Edge parameters");
        dialog.setHeaderText("Enter edge weight:");
        dialog.setContentText("Weight:");
        return dialog.showAndWait();
    }
}
