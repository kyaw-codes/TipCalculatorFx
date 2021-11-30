package com.yci.tipcalculator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TipController implements Initializable {

    @FXML
    private Label lblTipPerPerson;
    @FXML
    private Label lblTotal;
    @FXML
    private Label lblOriginal;
    @FXML
    private Label lblTotalTip;
    @FXML
    private Label lblTipPercentage;
    @FXML
    private Slider sliderPercentage;
    @FXML
    private TextField tfTotalAmount;
    @FXML
    private TextField tfNoOfPeople;

    private double tipPercentage = 10.0;
    private double originalAmount = 0.0;
    private int noOfPeople = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sliderPercentage.valueProperty().addListener((obj, oldValue, newValue) -> {
            this.tipPercentage = (double) newValue;
            lblTipPercentage.setText(String.format("%.1f %%", newValue));
            calculateTip();
        });

        tfTotalAmount.textProperty().addListener((obj, oldValue, newValue) -> {
            this.originalAmount = Double.parseDouble(newValue);
            if (originalAmount == 0) {
                reset();
            } else {
                calculateTip();
            }
        });

        tfNoOfPeople.textProperty().addListener((obj, oldValue, newValue) -> {
            this.noOfPeople = Integer.parseInt(newValue);
            calculateTip();
        });

        reset();
    }

    void calculateTip() {
        double tipAmount = originalAmount *  tipPercentage / 100;

        lblOriginal.setText(String.format("$ %.1f", originalAmount));
        lblTotal.setText(String.format("$ %.1f", tipAmount + originalAmount));
        lblTotalTip.setText(String.format("$ %.1f", tipAmount));
        lblTipPerPerson.setText(String.format("$ %.1f", tipAmount / noOfPeople));
    }

    void reset() {
        tfNoOfPeople.setText("1");
        tfTotalAmount.setText("0.0");

        lblTotal.setText("$ 0.0");
        lblTotalTip.setText("$ 0.0");
        lblTipPerPerson.setText("$ 0.0");
    }
}
