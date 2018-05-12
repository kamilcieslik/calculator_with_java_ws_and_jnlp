package com.github.kamilcieslik.academic.calculator_with_java_ws_and_jnlp.javafx.controller;

import com.github.kamilcieslik.academic.calculator_with_java_ws_and_jnlp.javafx.ButtonSoundPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {
    private Double resultOfThePreviousOperation;
    private Boolean isSoundOn;
    private Boolean afterCalculations;
    private Image imageSoundOn;
    private Image imageSoundOff;
    private ButtonSoundPlayer buttonSoundPlayer;

    @FXML
    private ImageView imageViewSound;

    @FXML
    private VBox vBoxCalculator;

    @FXML
    private TextField textFieldCalculations;

    public void setMoveControl(BorderlessScene borderlessScene) {
        borderlessScene.setMoveControl(vBoxCalculator);
    }

    public void initialize(URL location, ResourceBundle resources) {
        resultOfThePreviousOperation = 0D;
        isSoundOn = false;
        afterCalculations = false;
        imageSoundOn = new Image("/img/sound_on.png");
        imageSoundOff = new Image("/img/sound_off.png");
    }

    @FXML
    void buttonOperationSign_onAction(ActionEvent event) {
        Button button = (Button) event.getSource();

        playButtonAudio();

        if (afterCalculations) {
            textFieldCalculations.clear();
            afterCalculations = false;
        }

        if ((textFieldCalculations.getText()
                + " " + button.getText()).matches("^-?[0-9]+(\\.[0-9]+)?\\s[+\\-/*]$"))
            textFieldCalculations.setText(textFieldCalculations.getText() + " " + button.getText());
        else if (textFieldCalculations.getText().equals(""))
            textFieldCalculations.setText(textFieldCalculations.getText() + button.getText());
    }

    @FXML
    void buttonNumber_onAction(ActionEvent event) {
        Button button = (Button) event.getSource();

        playButtonAudio();

        if (afterCalculations) {
            textFieldCalculations.clear();
            afterCalculations = false;
        }

        if (button.getText().equals(".")) {
            if ((textFieldCalculations.getText() + button.getText())
                    .matches("^-?[0-9]+\\.$|^[+\\-/*]\\s-?[0-9]+\\.$" +
                            "|^-?[0-9]+(\\.[0-9]+)?\\s[+\\-/*]\\s-?[0-9]+\\.$"))
                textFieldCalculations.setText(textFieldCalculations.getText() + button.getText());
        } else if (!(textFieldCalculations.getText() + button.getText())
                .matches("^-?[0][0-9]$|^[+\\-/*]\\s-?[0][0-9]$|^$" +
                        "|^-?[0-9]+(\\.[0-9]+)?\\s[+\\-/*]\\s-?[0][0-9]$")) {
            if (textFieldCalculations.getText().equals("") ||
                    (textFieldCalculations.getText() + button.getText())
                            .matches("^[0-9]+(\\.[0-9]+)?$"))
                textFieldCalculations.setText(textFieldCalculations.getText() + button.getText());
            else if ((textFieldCalculations.getText()
                    + button.getText())
                    .matches("^-?[0-9]+(\\.[0-9]+)?\\s[+\\-/*]?(\\s-?[0-9]+(\\.[0-9]+)?)?$"))
                textFieldCalculations.setText(textFieldCalculations.getText() + button.getText());
            else if ((textFieldCalculations.getText()
                    + " " + button.getText())
                    .matches("^-?[0-9]+(\\.[0-9]+)?\\s[+\\-/*]?(\\s-?[0-9]+(\\.[0-9]+)?)?$"))
                textFieldCalculations.setText(textFieldCalculations.getText() + " " + button.getText());
            else if ((textFieldCalculations.getText()
                    + button.getText())
                    .matches("^[+\\-/*](\\s-?[0-9]+(\\.[0-9]+)?)?$"))
                textFieldCalculations.setText(textFieldCalculations.getText() + button.getText());
            else if ((textFieldCalculations.getText()
                    + " " + button.getText())
                    .matches("^[+\\-/*](\\s-?[0-9]+(\\.[0-9]+)?)?$"))
                textFieldCalculations.setText(textFieldCalculations.getText() + " " + button.getText());
        }
    }

    @FXML
    void buttonClearMemory_onAction() {
        playButtonAudio();
        resultOfThePreviousOperation = 0D;
        textFieldCalculations.clear();
    }

    @FXML
    void buttonClear_onAction() {
        playButtonAudio();
        textFieldCalculations.clear();
    }

    @FXML
    void buttonEqualSign_onAction() {
        playButtonAudio();

        String operationSign;
        String[] stringOperationParts = textFieldCalculations.getText().split(" ");
        Double[] doubleOperationParts = new Double[2];

        if (textFieldCalculations.getText().matches("|^-?[0-9]+(\\.[0-9]+)?\\s[+\\-/*]\\s-?[0-9]+(\\.[0-9]+)?$")) {
            operationSign = stringOperationParts[1];
            doubleOperationParts[0] = Double.valueOf(stringOperationParts[0]);
            doubleOperationParts[1] = Double.valueOf(stringOperationParts[2]);
        } else if (textFieldCalculations.getText().matches("^[+\\-/*]\\s-?[0-9]+(\\.[0-9]+)?$")) {
            operationSign = stringOperationParts[0];
            doubleOperationParts[0] = resultOfThePreviousOperation;
            doubleOperationParts[1] = Double.valueOf(stringOperationParts[1]);
        } else
            return;

        resultOfThePreviousOperation = calculate(operationSign, doubleOperationParts);
        textFieldCalculations.setText(String.valueOf(resultOfThePreviousOperation));
        afterCalculations = true;
    }

    private void playButtonAudio() {
        if (isSoundOn)
            new Thread(() -> {
                buttonSoundPlayer = new ButtonSoundPlayer(getClass().getClassLoader()
                        .getResourceAsStream("mp3/button_sound.mp3"));
                buttonSoundPlayer.play();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    buttonSoundPlayer.close();
                }
                buttonSoundPlayer.close();
            }).start();
    }

    private Double calculate(String operationSign, Double[] operationParts) {
        switch (operationSign) {
            case "+":
                return operationParts[0] + operationParts[1];
            case "-":
                return operationParts[0] - operationParts[1];
            case "*":
                return operationParts[0] * operationParts[1];
            default:
                return operationParts[0] / operationParts[1];
        }
    }

    @FXML
    void buttonOff_onAction() {
        Stage stage = (Stage) textFieldCalculations.getScene().getWindow();
        stage.close();
    }

    @FXML
    void buttonSound_onAction() {
        if (isSoundOn) {
            imageViewSound.setImage(imageSoundOff);
            isSoundOn = false;
        } else {
            imageViewSound.setImage(imageSoundOn);
            isSoundOn = true;
        }
    }
}
