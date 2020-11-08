package com.bulumutka.polyconstr;

import com.bulumutka.polyconstr.ui.WindowStarter;
import com.bulumutka.polyconstr.ui.WindowType;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        new WindowStarter().startWindow(WindowType.START);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
