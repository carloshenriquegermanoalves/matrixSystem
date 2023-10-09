package org.matrix;

public interface System {

    boolean isPossibleAddOrSubtractOrMultiply(Matrix matrixA, Matrix matrixB);
    Matrix addingMatrix(Matrix matrixA, Matrix matrixB);
    Matrix subtractingMatrix(Matrix matrixA, Matrix matrixB);
    Matrix scalarMultiplying(int scalar, Matrix matrix);
    Matrix multiplyingMatrix(Matrix matrixA, Matrix matrixB);
    Matrix transposingMatrix(Matrix matrix);
    double determinantMatrix(Matrix matrix);
    double cofactorMatrix(Matrix matrix, int row, int col);


}
