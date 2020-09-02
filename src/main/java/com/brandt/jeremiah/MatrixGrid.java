package com.brandt.jeremiah;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class MatrixGrid extends GridPane {
    private static List<MatrixGrid> matrices = new ArrayList<MatrixGrid>();
    private static int width = 300;
    private static int height = 300;
    private static int inputWidth = 50;
    private static int inputHeight = 50;
    private static Font inputFont = new Font(30);

    private int numRows;
    private int numCols;

    public MatrixGrid() {
        this(2, 2);
    }

    public MatrixGrid(int rows, int cols) {
        matrices.add(this);
        super.setAlignment(Pos.CENTER);
        super.setPrefSize(400, 400);

        numRows = rows;
        numCols = cols;
        setInputDimensions();

        for(int row=0; row<numRows; row++) {
            for(int col=0; col<numCols; col++) {
                TextField input = new InputField(row, col);
                input.setPrefSize(inputWidth, inputHeight);
                input.setAlignment(Pos.CENTER);
                input.setFont(inputFont);
                super.add(input, inputWidth*row, inputHeight*col);
            }
        }

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
        for(MatrixGrid matrix: matrices) {
            for(Node input: matrix.getChildren()) {
                if(input instanceof TextField) {
                    ((TextField) input).setPrefSize(inputWidth, inputHeight);
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
                    values[row][col] = Integer.valueOf(((InputField) input).getText());
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
                input.setPrefSize(inputWidth, inputHeight);
                input.setAlignment(Pos.CENTER);
                input.setFont(inputFont);
                super.add(input, inputHeight*col, inputWidth*row);
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

    private void setInputDimensions() {
        inputWidth = width/numRows;
        inputHeight = height/numCols;

        if(inputWidth > inputHeight) {
            inputWidth = inputHeight;
        } else {
            inputHeight = inputWidth;
        }
    }
}
