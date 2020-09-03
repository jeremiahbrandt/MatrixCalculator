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

    public Matrix(String identifier) {
        super.setAlignment(Pos.CENTER);
        super.setSpacing(30);

        Labeled title = new Label("Matrix " + identifier.toUpperCase());
        title.setFont(titleFont);

        HBox dimensionSelectors = new HBox();
        dimensionSelectors.setSpacing(15);
        DimensionSelector rowSelector = new DimensionSelector("Rows");
        DimensionSelector colSelector = new DimensionSelector("Columns");
        dimensionSelectors.getChildren().addAll(rowSelector, colSelector);

        Grid grid = new Grid();
        super.getChildren().addAll(title, dimensionSelectors, grid);
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
