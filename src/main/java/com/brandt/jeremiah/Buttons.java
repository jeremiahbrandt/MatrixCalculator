package com.brandt.jeremiah;

import javafx.scene.Group;
import javafx.scene.control.Button;

public class Buttons extends Group {
    private static int BUTTON_WIDTH = 150;
    private static int BUTTON_HEIGHT = 70;
    private static int BUTTON_PADDING = 20;

    public Buttons(Calculator calculator) {
        Button addButton = new Button("Add");
        addButton.setOnMouseClicked(mouseEvent -> calculator.calculate(CalculationType.ADDITION));
        addButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        addButton.setLayoutX(0);

        Button subtractButton = new Button("Subtract");
        subtractButton.setOnMouseClicked(mouseEvent -> calculator.calculate(CalculationType.SUBSTRACTION));
        subtractButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        subtractButton.setLayoutX(BUTTON_WIDTH + BUTTON_PADDING);

        Button clearButton = new Button("Clear");
        clearButton.setOnMouseClicked(mouseEvent -> calculator.clearAllFields());
        clearButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        clearButton.setLayoutX(BUTTON_PADDING);
        clearButton.setLayoutY(BUTTON_HEIGHT + BUTTON_PADDING);

        super.getChildren().addAll(addButton, subtractButton, clearButton);
    }
}
