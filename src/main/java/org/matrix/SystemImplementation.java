package org.matrix;

public class SystemImplementation implements MatrixSystem {

    @Override
    public boolean isPossibleAddOrSubtract(Matrix matrixA, Matrix matrixB) {
        return matrixA.getRows() != matrixB.getRows() || matrixA.getCols() != matrixB.getCols();
    }

    @Override
    public boolean isPossibleMultiply(Matrix matrixA, Matrix matrixB) {
        return matrixA.getCols() == matrixB.getRows();
    }

    @Override
    public void addingMatrix(Matrix matrixA, Matrix matrixB) {
        if (isPossibleAddOrSubtract(matrixA, matrixB))
            System.out.println("As matrizes não podem ser somadas, por terem dimensões incompatíveis!");

        else {
            Matrix result = new Matrix(matrixA.getRows(), matrixA.getCols());
            for (int i = 0; i < matrixA.getRows(); i++) {
                for (int j = 0; j < matrixA.getCols(); j++) {
                    double sum = matrixA.getPosition(i, j) + matrixB.getPosition(i, j);
                    result.setMatrix(i, j, sum);
                }
            }
            printMatrix(result);
        }
    }


    @Override
    public void subtractingMatrix(Matrix matrixA, Matrix matrixB) {
        if (isPossibleAddOrSubtract(matrixA, matrixB))
            System.out.println("As matrizes não podem ser subtraídas, por terem dimensões incompatíveis!");

        else {
            Matrix result = new Matrix(matrixA.getRows(), matrixA.getCols());
            for (int i = 0; i < matrixA.getRows(); i++) {
                for (int j = 0; j < matrixA.getCols(); j++) {
                    double sub = matrixA.getPosition(i, j) - matrixB.getPosition(i, j);
                    result.setMatrix(i, j, sub);
                }
            }
            printMatrix(result);
        }
    }

    @Override
    public void multiplyingMatrix(Matrix matrixA, Matrix matrixB) {
        if (!isPossibleMultiply(matrixA, matrixB))
            System.out.println("As matrizes não podem ser multiplicadas, por terem dimensões incompatíveis!");

        else {
            Matrix result = new Matrix(matrixA.getRows(), matrixB.getCols());
            for (int i = 0; i < matrixA.getRows(); i++) {
                for (int j = 0; j < matrixB.getCols(); j++) {
                    double sum = 0.0;
                    for (int k = 0; k < matrixA.getCols(); k++) {
                        sum += matrixA.getPosition(i, k) * matrixB.getPosition(k, j);
                    }
                    result.setMatrix(i, j, sum);
                }
            }
            printMatrix(result);
        }
    }

    @Override
    public void scalarMultiplying(double scalar, Matrix matrix) {
        Matrix result = new Matrix(matrix.getRows(), matrix.getCols());
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                double scal = scalar * matrix.getPosition(i, j);
                result.setMatrix(i, j, scal);
            }
        }
        printMatrix(result);
    }

    @Override
    public double determinantMatrix(Matrix matrix) {
        if (matrix.getRows() == 1 && matrix.getCols() == 1)
            return matrix.getPosition(0, 0);
        else {
            double determinant;
            if (matrix.getRows() == 2 && matrix.getCols() == 2) {
                determinant = (matrix.getPosition(0, 0) * matrix.getPosition(1, 1)) -
                        (matrix.getPosition(0, 1) * matrix.getPosition(1, 0));
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

    @Override
    public void scaleMatrix(Matrix matrix) {
        int numRows = matrix.getRows();
        int numCols = matrix.getCols();

        for (int row = 0; row < numRows; row++) {
            if (matrix.getPosition(row, row) != 0) {
                double pivot = matrix.getPosition(row, row);
                for (int col = row; col < numCols; col++) {
                    matrix.setMatrix(row, col, matrix.getPosition(row, col) / pivot);
                }
                for (int i = 0; i < numRows; i++) {
                    if (i != row) {
                        double factor = matrix.getPosition(i, row);
                        for (int j = row; j < numCols; j++)
                            matrix.setMatrix(i, j, matrix.getPosition(i, j) - factor * matrix.getPosition(row, j));
                    }
                }
            }
        }
        printMatrix(matrix);
    }

    @Override
    public Matrix extractInverse(Matrix augmentedMatrix, int row, int col) {
        Matrix inverseMatrix = new Matrix(row, col);

        // Extraia a parte direita da matriz aumentada como a matriz inversa
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                inverseMatrix.setMatrix(i, j, augmentedMatrix.getPosition(i, col + j));
            }
        }

        return inverseMatrix;
    }

    @Override
    public Matrix invertMatrix(Matrix matrix) {

        if (matrix.getRows() != matrix.getCols()) {
            throw new IllegalArgumentException("A matriz não é quadrada e não pode ser invertida.");
        }

        // Concatene a matriz original com a matriz identidade
        Matrix augmentedMatrix = augmentWithIdentity(matrix);

        // Escalone a matriz aumentada usando Gauss-Jordan
        scaleMatrix(augmentedMatrix);

        // Extraia a parte direita da matriz aumentada como a matriz inversa
        Matrix inverseMatrix = extractInverse(augmentedMatrix, matrix.getRows(), matrix.getCols());

        return inverseMatrix;
    }

    @Override
    public Matrix augmentWithIdentity(Matrix matrix) {
        int numRows = matrix.getRows();
        int numCols = matrix.getCols();
        Matrix augmentedMatrix = new Matrix(matrix.getRows(), matrix.getCols() * 2);

        // Preencha a matriz aumentada com a matriz original e a matriz identidade
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++)
                augmentedMatrix.setMatrix(i, j, matrix.getPosition(i, j));
            augmentedMatrix.setMatrix(i, numCols + i, 1.0);
        }

        return augmentedMatrix;
    }


    @Override
    public void printMatrix(Matrix matrix) {
        System.out.println("Operação realizada com sucesso!");
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++)
                System.out.print("|" + matrix.getPosition(i, j) + "|");
            System.out.println();
        }
    }

}