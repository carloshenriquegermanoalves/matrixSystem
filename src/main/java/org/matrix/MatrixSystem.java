package org.matrix;

public interface MatrixSystem {

    boolean isPossibleAddOrSubtractOrMultiply(Matrix matrixA, Matrix matrixB);
    Matrix addingMatrix(Matrix matrixA, Matrix matrixB);
    Matrix subtractingMatrix(Matrix matrixA, Matrix matrixB);
    Matrix scalarMultiplying(int scalar, Matrix matrix);
    Matrix multiplyingMatrix(Matrix matrixA, Matrix matrixB);
    Matrix transposingMatrix(Matrix matrix);
    double determinantMatrix(Matrix matrix);
    double cofactorMatrix(Matrix matrix, int row, int col);
    Matrix scaleMatrix(Matrix matrix);
    void swapRows(Matrix matrix, int row1, int row2);
    void printMatrix(Matrix matrix);
}
