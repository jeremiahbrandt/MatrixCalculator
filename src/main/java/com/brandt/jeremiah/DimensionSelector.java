package com.brandt.jeremiah;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class DimensionSelector extends HBox {
    private static int minCount = 2;
    private static int maxCount = 30;
    private static int countDisplaySize = 30;

    private int count;

    public DimensionSelector(String label) {
        super.setAlignment(Pos.CENTER);
        count = 2;

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

    private void update(UpdateType updateType) {
        if(updateType == UpdateType.DECREMENT) {
            if(count > minCount) {
                count--;
                setLabel(count);
            }
        } else {
            if(count < maxCount) {
                count++;
                setLabel(count);
            }
        }
    }

    private void setLabel(int newCount) {
        super.getChildren().forEach(node -> {
            if(node instanceof TextField) {
                ((TextField) node).setText(String.valueOf(newCount));
            }
        });
    }
}

enum UpdateType {
    DECREMENT,
    INCREMENT
}
