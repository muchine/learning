package com.muchine.chapter2_4.ui.gridview;

/**
 * Created by sejoonlim on 8/22/16.
 */
public class ArrayTable {

    private final int rowCount;
    private final int columnCount;

    private final String[][] table;

    public ArrayTable(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.table = new String[rowCount][columnCount];
    }

    public void setCell(int row, int column, String value) {
        table[row][column] = value;
    }

    public String getCell(int row, int column) {
        return table[row][column];
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }
}
