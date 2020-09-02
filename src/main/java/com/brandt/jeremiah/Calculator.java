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
        input1 = new MatrixGrid(3, 2);
        BorderPane.setAlignment(input1, Pos.CENTER);
        super.setLeft(input1);

        input2 = new MatrixGrid(3, 3);
        super.setCenter(input2);

        output = new MatrixGrid(3, 3);
        output.disableEdits();
        super.setRight(output);
        BorderPane.setAlignment(output, Pos.CENTER);

        MatrixGrid.resizeInputs();


        Group buttons = new Buttons(this);
        BorderPane.setAlignment(buttons, Pos.CENTER);
        super.setBottom(buttons);
    }

    protected void calculate(CalculationType calculationType) {
        int[][] input1Values = input1.getValues();
        int[][] input2Values = input2.getValues();
        int[][] answer = null;

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
            answer = new int[input1Values[0].length][input2Values.length];
            for(int i=0; i<answer.length; i++) {
                for(int j=0; j<answer[0].length; j++) {
                    int sum = 0;
                    for(int k=0; k<input1Values[0].length; k++) {
                        for(int m=0; m<input2Values.length; m++) {
                            sum += input1Values[i][k] * input2Values[j][m];
                        }
                    }
                    answer[i][j] = sum;
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

}
