package org.matrix;

public interface MatrixSystem {

    boolean isPossibleAddOrSubtract(Matrix matrixA, Matrix matrixB);
    boolean isPossibleMultiply(Matrix matrixA, Matrix matrixB);
    void addingMatrix(Matrix matrixA, Matrix matrixB);
    void subtractingMatrix(Matrix matrixA, Matrix matrixB);
    void multiplyingMatrix(Matrix matrixA, Matrix matrixB);
    void scalarMultiplying(double scalar, Matrix matrix);
    double determinantMatrix(Matrix matrix);
    double cofactorMatrix(Matrix matrix, int row, int col);
    void scaleMatrix(Matrix matrix);
    Matrix extractInverse(Matrix augmentedMatrix, int row, int col);
    Matrix invertMatrix(Matrix matrix);
    Matrix augmentWithIdentity(Matrix matrix);

    void printMatrix(Matrix matrix);

}