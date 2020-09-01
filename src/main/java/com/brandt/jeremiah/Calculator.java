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
        input1 = new MatrixGrid(3, 3);
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
        int[][] answer = new int[input1Values.length][input1Values[0].length];

        if(calculationType.equals(CalculationType.ADDITION)) {
            for(int i=0; i<input1Values.length; i++) {
                for(int j=0; j<input1Values[i].length; j++) {
                    answer[i][j] = input1Values[i][j] + input2Values[i][j];
                }
            }
        } else if(calculationType.equals(CalculationType.SUBSTRACTION)) {
            for(int i=0; i<input1Values.length; i++) {
                for(int j=0; j<input1Values[i].length; j++) {
                    answer[i][j] = input1Values[i][j] - input2Values[i][j];
                }
            }
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
