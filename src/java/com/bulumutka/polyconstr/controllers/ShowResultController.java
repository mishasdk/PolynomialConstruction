package com.bulumutka.polyconstr.controllers;

import com.bulumutka.polyconstr.models.Repository;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowResultController implements Initializable {
    public TextField formulasField;
    public Button copyButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formulasField.setText(Repository.formulas);

        copyButton.setOnAction(e -> {
            System.out.println("Copy button clicked");
            var content = new ClipboardContent();
            content.putString(formulasField.getText());
            Clipboard.getSystemClipboard().setContent(content);
        });
    }
}
