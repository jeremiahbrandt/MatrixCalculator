package com.brandt.jeremiah;

import javafx.scene.control.TextField;

public class InputField extends TextField {
    public int row, col;
    public InputField(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
