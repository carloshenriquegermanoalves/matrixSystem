package org.matrix;

public interface System {

    boolean isPossibleAddOrSubtract(Matrix matrixA, Matrix matrixB);
    Matrix addingMatrix(Matrix matrixA, Matrix matrixB);
    Matrix subtractingMatrix(Matrix matrixA, Matrix matrixB);
    Matrix scalarMultiplying(int scalar, Matrix matrix);
    Matrix multiplyingMatrix(Matrix matrixA, Matrix matrixB);
    Matrix transposingMatrix(Matrix matrix);
    Matrix determinantMatrix(Matrix matrix);


}
