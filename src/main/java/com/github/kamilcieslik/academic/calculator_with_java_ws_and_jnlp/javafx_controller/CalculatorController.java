package com.github.kamilcieslik.academic.calculator_with_java_ws_and_jnlp.javafx_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {
    private Double resultOfThePreviousOperation;

    @FXML
    VBox vBoxCalculator;

    @FXML
    private TextField textFieldCalculations;

    public void setMoveControl(BorderlessScene borderlessScene){
        borderlessScene.setMoveControl(vBoxCalculator);
    }

    public void initialize(URL location, ResourceBundle resources) {
        resultOfThePreviousOperation = null;
    }

    @FXML
    void buttonOperationSign_onAction(ActionEvent event) {

    }

    @FXML
    void buttonNumber_onAction(ActionEvent event) {

    }

    @FXML
    void buttonClearMemory_onAction() {
        resultOfThePreviousOperation = null;
        textFieldCalculations.clear();
    }

    @FXML
    void buttonClear_onAction() {
        textFieldCalculations.clear();
    }

    @FXML
    void buttonDot_onAction() {

    }

    @FXML
    void buttonEqualSign_onAction() {

    }

    @FXML
    void buttonOff_onAction() {
        Stage stage = (Stage) textFieldCalculations.getScene().getWindow();
        stage.close();
    }

    @FXML
    void buttonSound_onAction() {

    }
}
