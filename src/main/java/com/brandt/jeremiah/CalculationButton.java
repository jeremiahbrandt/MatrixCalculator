package com.brandt.jeremiah;

import javafx.scene.control.Button;

public class CalculationButton extends Button {
    private static final int WIDTH = 75;
    private static final double HEIGHT = 75;

    public CalculationButton(Calculator calculator) {
        super.setText("=");
        super.setMinSize(WIDTH, HEIGHT);
        super.setOnMouseClicked(mouseEvent -> calculator.calculate());
    }
}
