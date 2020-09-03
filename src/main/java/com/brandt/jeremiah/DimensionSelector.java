package com.brandt.jeremiah;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class DimensionSelector extends HBox {
    private static int minCount = 2;
    private static int maxCount = 10;
    private static int countDisplaySize = 30;

    private int count;
    private Matrix matrix;

    public DimensionSelector(String label, Matrix matrix) {
        super.setAlignment(Pos.CENTER);
        count = 2;
        this.matrix = matrix;

        Labeled title = new Label(label + ": ");

        TextField countDisplay = new TextField();
        countDisplay.setText(String.valueOf(count));
        countDisplay.setEditable(false);
        countDisplay.setPrefSize(countDisplaySize, countDisplaySize);

        Button decrementButton = new Button("-");
        decrementButton.setOnMouseClicked(mouseEvent -> update(UpdateType.DECREMENT));
        decrementButton.setPrefSize(countDisplaySize, countDisplaySize);

        Button incrementButton = new Button("+");
        incrementButton.setOnMouseClicked(mouseEvent -> update(UpdateType.INCREMENT));
        incrementButton.setPrefSize(countDisplaySize, countDisplaySize);

        super.getChildren().addAll(title, countDisplay, decrementButton, incrementButton);
    }

    public int getCount() {
        return count;
    }

    private void update(UpdateType updateType) {
        boolean validChange = false;
        if(updateType == UpdateType.DECREMENT) {
            if(count > minCount) {
                count--;
                validChange = true;
            }
        } else {
            if(count < maxCount) {
                count++;
                validChange = true;
            }
        }

        if(validChange) {
            updateLabel();
            matrix.resize();
        }
    }

    private void updateLabel() {
        super.getChildren().forEach(node -> {
            if(node instanceof TextField) {
                ((TextField) node).setText(String.valueOf(count));
            }
        });
    }
}

enum UpdateType {
    DECREMENT,
    INCREMENT
}
