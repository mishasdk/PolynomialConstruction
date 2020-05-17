package com.bulumutka.polyconstr.controllers;

import com.bulumutka.polyconstr.models.Repository;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowResultController implements Initializable {
    public TextField formulasField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formulasField.setText(Repository.formulas);
    }
}
