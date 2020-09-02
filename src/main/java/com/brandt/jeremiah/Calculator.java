package com.brandt.jeremiah;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class Calculator extends BorderPane {
    Group center;
    private MatrixGrid input1;
    private MatrixGrid input2;
    private MatrixGrid output;

    public Calculator() {
        input1 = new MatrixGrid(3, 5);
        BorderPane.setAlignment(input1, Pos.CENTER);
        super.setLeft(input1);

        input2 = new MatrixGrid(7, 3);
        super.setCenter(input2);

        output = new MatrixGrid(3, 3);
        output.disableEdits();
        super.setRight(output);
        BorderPane.setAlignment(output, Pos.CENTER);

        MatrixGrid.resizeInputs();


        Group buttons = new Buttons(this);
        BorderPane.setAlignment(buttons, Pos.CENTER);
        super.setBottom(buttons);

        insertTestValues();
    }

    protected void calculate(CalculationType calculationType) {
        int[][] input1Values = input1.getValues();
        int[][] input2Values = input2.getValues();
        int[][] answer = new int[0][0];

        if(calculationType.equals(CalculationType.ADDITION)) {
            answer = new int[input1Values.length][input1Values[0].length];
            for(int i=0; i<input1Values.length; i++) {
                for(int j=0; j<input1Values[i].length; j++) {
                    answer[i][j] = input1Values[i][j] + input2Values[i][j];
                }
            }
        } else if(calculationType.equals(CalculationType.SUBSTRACTION)) {
            answer = new int[input1Values.length][input1Values[0].length];
            for(int i=0; i<input1Values.length; i++) {
                for(int j=0; j<input1Values[i].length; j++) {
                    answer[i][j] = input1Values[i][j] - input2Values[i][j];
                }
            }

        } else if(calculationType.equals(CalculationType.MULTIPLICATION)) {
            answer = new int[input1Values.length][input2Values[0].length];
            for(int row=0; row<answer.length; row++) {
                for(int col=0; col<answer[0].length; col++) {
                    int sum = 0;
                    for(int i=0; i<input1Values[0].length; i++) {
                        int valFromInput1 = input1Values[row][i];
                        int valFromInput2 = input2Values[i][col];
                        sum += valFromInput1 * valFromInput2;
                    }
                    answer[row][col] = sum;
                }
            }
        } else if(calculationType.equals(CalculationType.DIVISION)) {
            answer = new int[input1Values.length][input2Values[0].length];
        }

        input1.disableEdits();
        input2.disableEdits();
        output.setValues(answer);
    }

    protected void clearAllFields() {
        for(Node node: super.getChildren()) {
            if(node instanceof MatrixGrid) {
                MatrixGrid matrixGrid = (MatrixGrid) node;
                matrixGrid.enableEdits();
            }
        }
    }

    // TOOD: Remove test method
    private void insertTestValues() {
//        int[][] input1TestValues = new int[][]{{1, 2, 3}, {4, 5, 6}};
//        int[][] input2TestValues = new int[][]{{1, 2}, {3, 4}, {5, 6}};
        int[][] input1TestValues = new int[][]{{1, 6, 11}, {2, 7, 12}, {3, 8, 13}, {4, 9, 14}, {5, 10, 15}};
        int[][] input2TestValues = new int[][]{{1, 4, 7, 10, 13, 16, 19}, {2, 5, 8, 11, 14, 17, 20}, {3, 6, 9, 12, 15, 18, 21}};

        input1.setValues(input1TestValues);
        input2.setValues(input2TestValues);
    }

}
