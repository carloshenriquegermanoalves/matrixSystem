package org.matrix;

public class SystemImplementation implements System {

    public boolean isPossibleAddOrSubtract(Matrix matrixA, Matrix matrixB) {
        return matrixA.getCols() == matrixB.getRows();
    }


    public Matrix addingMatrix(Matrix matrixA, Matrix matrixB) {
        if (!isPossibleAddOrSubtract(matrixA, matrixB))
            throw new IllegalArgumentException("As matrizes não podem ser somadas, por terem dimensões incompatíveis!");

        int rows = matrixA.getRows();
        int cols = matrixA.getCols();
        Matrix result = new Matrix(rows, cols);

        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double sum = matrixA.getPosition(i, j) + matrixB.getPosition(i, j);
                result.setMatrix(i, j, sum);
            }
        }
        return result;
    }


    @Override
    public Matrix subtractingMatrix(Matrix matrixA, Matrix matrixB) {
        if (!isPossibleAddOrSubtract(matrixA, matrixB))
            throw new IllegalArgumentException("As matrizes não podem ser subtraídas, por terem dimensões incompatíveis");

        Matrix result = new Matrix(matrixA.getRows(), matrixA.getCols());

        for (int i = 0; i < matrixA.getRows(); i++) {
            for(int j = 0; j < matrixA.getCols(); j++) {
               double sub = matrixA.getPosition(i, j) - matrixB.getPosition(i, j);
               result.setMatrix(i, j, sub);
            }
        }
        return result;
    }


    @Override
    public Matrix scalarMultiplying(int scalar, Matrix matrix) {
        Matrix result = new Matrix(matrix.getRows(), matrix.getCols());

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                double mult = scalar * matrix.getPosition(i, j);
                result.setMatrix(i, j, mult);
            }
        }
        return result;
    }



    @Override
    public Matrix multiplyingMatrix(Matrix matrixA, Matrix matrixB) {
        return null;
    }


    @Override
    public Matrix transposingMatrix(Matrix matrix) {
        return null;
    }

    @Override
    public Matrix determinantMatrix(Matrix matrix) {
        return null;
    }

}
