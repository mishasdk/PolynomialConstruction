package com.bulumutka.polyconstr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects
                .requireNonNull(getClass().getClassLoader().getResource("views/MainView.fxml")));
        primaryStage.setTitle("Polynomial construction");
        primaryStage.setScene(new Scene(root, 960, 540));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
