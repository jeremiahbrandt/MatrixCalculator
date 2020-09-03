package com.brandt.jeremiah;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class Grid extends GridPane {
    private static List<Grid> matrices = new ArrayList<>();
    private static int smallestCountRowsOrColumns = 999;
    private static int gridSize = 100;
    private static Font inputFont = new Font(15);

    private int numRows;
    private int numCols;

    public Grid() {
        this(2, 2);
    }

    public Grid(int rows, int cols) {
        matrices.add(this);
        super.setAlignment(Pos.TOP_CENTER);
        super.setPrefSize(300, 300);

        numRows = rows;
        numCols = cols;

        for(int row=0; row<numRows; row++) {
            for(int col=0; col<numCols; col++) {
                TextField input = new InputField(row, col);
                input.setAlignment(Pos.CENTER);
                input.setFont(inputFont);
                super.add(input, row, col);
            }
        }

        smallestCountRowsOrColumns = numRows < smallestCountRowsOrColumns ? numRows : smallestCountRowsOrColumns;
        smallestCountRowsOrColumns = numCols < smallestCountRowsOrColumns ? numCols : smallestCountRowsOrColumns;
    }

    public void enableEdits() {
        for(Node input: super.getChildren()) {
            if(input instanceof InputField) {
                TextField textField = (TextField) input;
                textField.setText("");
                textField.setEditable(true);
            }
        }
    }

    public void disableEdits() {
        for(Node input: super.getChildren()) {
            if(input instanceof InputField) {
                ((InputField) input).setEditable(false);
            }
        }
    }

    public static void resizeInputs() {
        int inputSize = gridSize / smallestCountRowsOrColumns;

        for(Grid matrix: matrices) {
            for(Node input: matrix.getChildren()) {
                if(input instanceof InputField) {
                    ((TextField) input).setPrefSize(inputSize, inputSize);
                }
            }
        }
    }

    public int[][] getValues() {
        int[][] values = new int[numRows][numCols];

        for(Node input: super.getChildren()) {
            if(input instanceof InputField) {
                int row = ((InputField) input).row;
                int col = ((InputField) input).col;
                try {
                    values[row][col] = Integer.parseInt(((InputField) input).getText());
                } catch (NumberFormatException e) {
//                    TODO: Throw custom exception
                    values[row][col] = 0;
                }
            }
        }

        return values;
    }

    public void setValues(int[][] values) {
        super.getChildren().clear();

        numRows = values.length;
        numCols = values[0].length;

        for(int row=0; row<numRows; row++) {
            for(int col=0; col<numCols; col++) {
                TextField input = new InputField(row, col);
                input.setAlignment(Pos.CENTER);
                input.setFont(inputFont);
                super.add(input, col, row);
            }
        }

        for(Node input: super.getChildren()) {
            if(input instanceof InputField) {
                InputField inputField = (InputField) input;
                int row = inputField.row;
                int col = inputField.col;
                inputField.setText(String.valueOf(values[row][col]));
            }
        }
    }
}
