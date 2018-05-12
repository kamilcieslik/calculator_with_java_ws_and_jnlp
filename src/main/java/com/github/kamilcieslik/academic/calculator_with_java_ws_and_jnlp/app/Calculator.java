package com.github.kamilcieslik.academic.calculator_with_java_ws_and_jnlp.app;

import com.github.kamilcieslik.academic.calculator_with_java_ws_and_jnlp.javafx.controller.CalculatorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculator extends Application {
    public static void main(String args[]) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getClassLoader().getResource("fxml/calculator.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            primaryStage.setTitle("Calculator");
            primaryStage.getIcons().add(new Image("/img/app_icon.png"));
            primaryStage.setWidth(345);
            primaryStage.setHeight(465);
            BorderlessScene borderlessScene = new BorderlessScene(primaryStage, StageStyle.UNDECORATED, root,
                    345, 465);
            borderlessScene.removeDefaultCSS();
            borderlessScene.setResizable(false);
            primaryStage.setScene(borderlessScene);
            CalculatorController calculatorController = loader.getController();
            calculatorController.setMoveControl(borderlessScene);
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (IOException ioEcx) {
            Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
    }

    public void stop() {
        System.exit(0);
    }
}
