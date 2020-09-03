package com.brandt.jeremiah;

import javafx.scene.control.ComboBox;

public class OperationSelector extends ComboBox {
    private static int WIDTH = 75;
    private static int HEIGHT = 75;

    public OperationSelector() {
        super.getItems().addAll("+", "-", "*");
        super.setMinSize(WIDTH, HEIGHT);
        super.setStyle("-fx-font: 20px \"Serif\";");
    }
}
