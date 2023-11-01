package org.matrix;

public class Matrix {

    private final int rows;
    private final int cols;
    private double[][] matrix;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[rows][cols];
    }

    public void setMatrix(int rows, int cols, double value) {
        matrix[rows][cols] = value;
    }
    public void setMatrix(double[][] matrix){
        this.matrix = matrix;
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public double getPosition(int rowSquare, int colSquare) {
        return this.matrix[rowSquare][colSquare];
    }
    public double[] getEntireRow(int row){
        return this.matrix[row];
    }

}