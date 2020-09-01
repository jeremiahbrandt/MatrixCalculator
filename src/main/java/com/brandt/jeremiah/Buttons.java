package com.brandt.jeremiah;

import javafx.scene.Group;
import javafx.scene.control.Button;

public class Buttons extends Group {
    private static int BUTTON_WIDTH = 120;
    private static int BUTTON_HEIGHT = 50;
    private static int BUTTON_PADDING = 15;

    public Buttons(Calculator calculator) {
        Button additionButton = new Button("Add");
        additionButton.setOnMouseClicked(mouseEvent -> calculator.calculate(CalculationType.ADDITION));
        additionButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        additionButton.setLayoutX(0);

        Button subtractionButton = new Button("Subtract");
        subtractionButton.setOnMouseClicked(mouseEvent -> calculator.calculate(CalculationType.SUBSTRACTION));
        subtractionButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        subtractionButton.setLayoutX(BUTTON_WIDTH + BUTTON_PADDING);

        Button multiplicationButton = new Button("Multiply");
        multiplicationButton.setOnMouseClicked(mouseEvent -> calculator.calculate(CalculationType.MULTIPLICATION));
        multiplicationButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        multiplicationButton.setLayoutX(0);
        multiplicationButton.setLayoutY(BUTTON_HEIGHT + BUTTON_PADDING);


        Button divisionButton = new Button("Divide");
        divisionButton.setOnMouseClicked(mouseEvent -> calculator.calculate(CalculationType.DIVISION));
        divisionButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        divisionButton.setLayoutX(2 * (BUTTON_WIDTH + BUTTON_PADDING));
        divisionButton.setLayoutX(BUTTON_WIDTH + BUTTON_PADDING);
        divisionButton.setLayoutY(BUTTON_HEIGHT + BUTTON_PADDING);

        Button clearButton = new Button("Clear");
        clearButton.setOnMouseClicked(mouseEvent -> calculator.clearAllFields());
        clearButton.setPrefSize(BUTTON_WIDTH/2, (BUTTON_HEIGHT*2)+BUTTON_PADDING);
        clearButton.setLayoutX(2 * (BUTTON_WIDTH + BUTTON_PADDING));
        clearButton.setLayoutY(0);

        super.getChildren().addAll(additionButton, subtractionButton, multiplicationButton, divisionButton, clearButton);
    }
}
