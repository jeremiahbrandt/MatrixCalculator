package com.brandt.jeremiah;

import com.brandt.jeremiah.Exceptions.UnknownOperationException;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class Calculator extends HBox {
    private Matrix input1;
    private OperationSelector operationSelector;
    private Matrix input2;
    private Matrix output;

    public Calculator() {
        super.setSpacing(20);

        input1 = new Matrix("A");

        operationSelector = new OperationSelector();
        super.setAlignment(Pos.CENTER);

        input2 = new Matrix("B");

        CalculationButton calculationButton = new CalculationButton(this);

        output = new Matrix("C");
        output.getGrid().disableEdits();


        super.getChildren().addAll(input1, operationSelector, input2, calculationButton, output);

        insertTestValues();
        Grid.resizeInputs();
    }

    protected void calculate() {
        Operation operation;
        try {
            operation = operationSelector.getSelectedOperation();
        } catch (UnknownOperationException e) {
            operation = Operation.ADDITION;
            System.out.println("Unable to perform operation \"" + e.getOperationString() + "\"");
            System.out.println("Defaulting operation to " + operation + "!");
        }

        int[][] input1Values = input1.getGrid().getValues();
        int[][] input2Values = input2.getGrid().getValues();
        int[][] answer = new int[0][0];

        if(operation.equals(Operation.ADDITION)) {
            answer = new int[input1Values.length][input1Values[0].length];
            for(int i=0; i<input1Values.length; i++) {
                for(int j=0; j<input1Values[i].length; j++) {
                    answer[i][j] = input1Values[i][j] + input2Values[i][j];
                }
            }
        } else if(operation.equals(Operation.SUBSTRACTION)) {
            answer = new int[input1Values.length][input1Values[0].length];
            for(int i=0; i<input1Values.length; i++) {
                for(int j=0; j<input1Values[i].length; j++) {
                    answer[i][j] = input1Values[i][j] - input2Values[i][j];
                }
            }

        } else if(operation.equals(Operation.MULTIPLICATION)) {
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
        } else if(operation.equals(Operation.DIVISION)) {
            answer = new int[input1Values.length][input2Values[0].length];
        }

        input1.getGrid().disableEdits();
        input2.getGrid().disableEdits();
        output.getGrid().setValues(answer);
        Grid.resizeInputs();
    }

    protected void clearAllFields() {
        for(Node node: super.getChildren()) {
            if(node instanceof Grid) {
                Grid grid = (Grid) node;
                grid.enableEdits();
            }
        }
    }

    // TOOD: Remove test method
    private void insertTestValues() {
//        int[][] input1TestValues = new int[][]{{1, 2, 3}, {4, 5, 6}};
//        int[][] input2TestValues = new int[][]{{1, 2}, {3, 4}, {5, 6}};
        int[][] input1TestValues = new int[][]{{1, 6, 11}, {2, 7, 12}, {3, 8, 13}, {4, 9, 14}, {5, 10, 15}};
        int[][] input2TestValues = new int[][]{{1, 4, 7, 10, 13, 16, 19}, {2, 5, 8, 11, 14, 17, 20}, {3, 6, 9, 12, 15, 18, 21}};

        input1.getGrid().setValues(input1TestValues);
        input2.getGrid().setValues(input2TestValues);
    }

}
