package com.brandt.jeremiah;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Matrix extends VBox {
    private static Font titleFont = new Font(30);

    private DimensionSelector rowSelector;
    private DimensionSelector colSelector;
    private Grid grid;

    public Matrix(String identifier) {
        super.setAlignment(Pos.CENTER);
        super.setSpacing(30);

        Labeled title = new Label("Matrix " + identifier.toUpperCase());
        title.setFont(titleFont);

        HBox dimensionSelectors = new HBox();
        dimensionSelectors.setSpacing(15);
        rowSelector = new DimensionSelector("Rows",this);
        colSelector = new DimensionSelector("Columns", this);
        dimensionSelectors.getChildren().addAll(rowSelector, colSelector);

        grid = new Grid();
        super.getChildren().addAll(title, dimensionSelectors, grid);
    }

    protected void resize() {
        int newRowCount = rowSelector.getCount();
        int newColCount = colSelector.getCount();

        int[][] originalValues = grid.getValues();
        int originalRowCount = originalValues.length;
        int originalColCount = originalValues[0].length;

        int[][] copyOfOrginalValues = new int[newRowCount][newColCount];
        for(int i=0; i<copyOfOrginalValues.length; i++) {
            if(i >= originalRowCount) {
                break;
            }

            for(int j=0; j<copyOfOrginalValues[0].length; j++) {
                if(j >= originalColCount) {
                   break;
                }

                copyOfOrginalValues[i][j] = originalValues[i][j];
            }
        }

        grid.setValues(copyOfOrginalValues);
        Grid.resizeInputs();
    }

    public Grid getGrid() {
        for(Node node: super.getChildren()) {
            if(node instanceof Grid) {
                return (Grid) node;
            }
        }
        throw new Error("Grid not found in Matrix: " + this.toString() + "!");
    }
}
