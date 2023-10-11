package org.matrix;

import org.matrix.exceptions.cantAddMatricesException;

public interface MatrixSystem {

    boolean isPossibleAddOrSubtract(Matrix matrixA, Matrix matrixB);
    boolean isPossibleMultiply(Matrix matrixA, Matrix matrixB);
    void addingMatrix(Matrix matrixA, Matrix matrixB) throws cantAddMatricesException;
    void subtractingMatrix(Matrix matrixA, Matrix matrixB);
    void multiplyingMatrix(Matrix matrixA, Matrix matrixB);
    Matrix scalarMultiplying(int scalar, Matrix matrix);
    Matrix transposingMatrix(Matrix matrix);
    double determinantMatrix(Matrix matrix);
    double cofactorMatrix(Matrix matrix, int row, int col);
    Matrix scaleMatrix(Matrix matrix);
    void swapRows(Matrix matrix, int row1, int row2);
    void scaleRow(Matrix matrix, int row, double scalar);
    void addRowToRow(Matrix matrix, int i, int pivotRow, double v);
    void printMatrix(Matrix matrix);

}