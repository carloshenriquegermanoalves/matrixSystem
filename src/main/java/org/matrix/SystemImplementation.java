package org.matrix;

public class SystemImplementation implements System {
    @Override
    public boolean isPossibleAddOrSubtractOrMultiply(Matrix matrixA, Matrix matrixB) {
        return matrixA.getCols() != matrixB.getRows();
    }

    public Matrix addingMatrix(Matrix matrixA, Matrix matrixB) {
        if (isPossibleAddOrSubtractOrMultiply(matrixA, matrixB))
            throw new IllegalArgumentException("As matrizes não podem ser somadas, por terem dimensões incompatíveis!");

        Matrix result = new Matrix(matrixA.getRows(), matrixA.getCols());
        for(int i = 0; i < matrixA.getRows(); i++) {
            for (int j = 0; j < matrixA.getCols(); j++) {
                double sum = matrixA.getPosition(i, j) + matrixB.getPosition(i, j);
                result.setMatrix(i, j, sum);
            }
        }
        return result;
    }


    @Override
    public Matrix subtractingMatrix(Matrix matrixA, Matrix matrixB) {
        if (isPossibleAddOrSubtractOrMultiply(matrixA, matrixB))
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
                double scal = scalar * matrix.getPosition(i, j);
                result.setMatrix(i, j, scal);
            }
        }
        return result;
    }


    @Override
    public Matrix multiplyingMatrix(Matrix matrixA, Matrix matrixB) {
        if (isPossibleAddOrSubtractOrMultiply(matrixA, matrixB))
            throw new IllegalArgumentException("As matrizes não podem ser multiplicadas, pois possuem dimensões diferentes!");

        Matrix result = new Matrix(matrixA.getRows(), matrixA.getCols());
        for (int i = 0; i < matrixA.getRows(); i++) {
            for (int j = 0; j < matrixA.getCols(); j++) {
                double mult = matrixA.getPosition(i, j) * matrixB.getPosition(i, j);
                result.setMatrix(i, j, mult);
            }
        }
        return result;
    }


    @Override
    public Matrix transposingMatrix(Matrix matrix) {
        Matrix transposed = new Matrix(matrix.getCols(), matrix.getRows());
        for (int i = 0; i < transposed.getRows(); i++) {
            for (int j = 0; j < transposed.getCols(); j++) {
                double elementPosition = matrix.getPosition(i, j);
                transposed.setMatrix(j, i, elementPosition);
            }
        }
        return transposed;
    }

    @Override
    public double determinantMatrix(Matrix matrix) {
        if (matrix.getRows() == 1 && matrix.getCols() == 1)
            return matrix.getPosition(0, 0);
        else {
            double determinant;
            if (matrix.getRows() == 2 && matrix.getCols() == 2) {
                determinant = (matrix.getPosition(1, 1) * matrix.getPosition(2, 2)) -
                        (matrix.getPosition(1, 2) * matrix.getPosition(2, 1));
            } else {
                determinant = 0.0;
                for (int col = 0; col < matrix.getCols(); col++) {
                    double cofactor = matrix.getPosition(0, col) * cofactorMatrix(matrix, 0, col);
                    determinant += (col % 2 == 0 ? 1 : -1) * cofactor;
                }
            }
            return determinant;
        }
    }

    @Override
    public double cofactorMatrix(Matrix matrix, int row, int col) {
        Matrix subMatrix = new Matrix(matrix.getRows() - 1, matrix.getCols() - 1);

        for (int i = 0, subRow = 0; i < matrix.getRows(); i++) {
            if (i == row) {
                continue;
            }
            for (int j = 0, subCol = 0; j < matrix.getCols(); j++) {
                if (j == col) {
                    continue;
                }
                subMatrix.setMatrix(subRow, subCol, matrix.getPosition(i, j));
                subCol++;
            }
            subRow++;
        }
        return determinantMatrix(subMatrix);
    }

}
