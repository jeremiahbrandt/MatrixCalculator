package com.brandt.jeremiah;

import com.brandt.jeremiah.Exceptions.UnknownOperationException;
import javafx.scene.control.ComboBox;

public class OperationSelector extends ComboBox {
    private static int WIDTH = 75;
    private static int HEIGHT = 75;

    public OperationSelector() {
        super.getItems().addAll("+", "-", "*");
        super.getSelectionModel().selectFirst();
        super.setMinSize(WIDTH, HEIGHT);
        super.setStyle("-fx-font: 20px \"Serif\";");
    }

    public Operation getSelectedOperation() throws UnknownOperationException {
        if(super.getValue() == "+") {
            return Operation.ADDITION;
        } else if (super.getValue() == "-") {
            return Operation.SUBSTRACTION;
        } else if (super.getValue() == "*") {
            return Operation.MULTIPLICATION;
        }

        throw new UnknownOperationException((String) super.getValue());
    }
}

