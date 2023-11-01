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
    public boolean isInvertible(Matrix matrix) {
        return determinantMatrix(matrix) != 0;
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
        if (matrix.getRows() == 1 && matrix.getCols() == 1) return matrix.getPosition(0, 0);
        else {
            double determinant;
            if (matrix.getRows() == 2 && matrix.getCols() == 2) {
                determinant = (matrix.getPosition(0, 0) * matrix.getPosition(1, 1)) - (matrix.getPosition(0, 1) * matrix.getPosition(1, 0));
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
        Matrix organizedMatrix = organizeMatrix(matrix);
        int numRows = organizedMatrix.getRows();
        int numCols = organizedMatrix.getCols();
        System.out.println("-------------------- matriz organizada -------------------------------");
        printMatrix(organizedMatrix);
        for (int row = 0; row < numRows; row++) {
            double pivot = identifyPivot(organizedMatrix, row);
            for (int col = row; col < numCols; col++) {
                if (pivot != 0) {
                    organizedMatrix.setMatrix(row, col, organizedMatrix.getPosition(row, col) / pivot);
                }
            }
            System.out.println("------------------------------------"+(1)+"------------------------------------------");
            printMatrix(organizedMatrix);
            for (int i = 0; i < numRows; i++) {
                if (i != row) {
                    double factor = organizedMatrix.getPosition(i, row);
                    for (int j = row; j < numCols; j++)
                        organizedMatrix.setMatrix(i, j, organizedMatrix.getPosition(i, j) - factor * organizedMatrix.getPosition(row, j));
                    System.out.println("------------------------------------"+(2)+"------------------------------------------");
                    printMatrix(organizedMatrix);
                }

            }
        }
        printMatrix(organizedMatrix);
    }


    @Override
    public void invertMatrix(Matrix matrix) {
        if (matrix.getRows() != matrix.getCols())
            System.out.println("A matriz não é quadrada e não pode ser invertida.");

        else if (!isInvertible(matrix))
            System.out.println("A matriz não pode ser invertida, pois seu determinante é igual a 0");

        else {
            Matrix augmentedMatrix = augmentWithIdentity(matrix);

            scaleMatrix(augmentedMatrix);

            extractInverse(augmentedMatrix, matrix.getRows(), matrix.getCols());
        }
    }

    @Override
    public void extractInverse(Matrix augmentedMatrix, int row, int col) {
        Matrix inverseMatrix = new Matrix(row, col);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                inverseMatrix.setMatrix(i, j, augmentedMatrix.getPosition(i, col + j));
            }
        }

    }

    @Override
    public void transposedMatrix(Matrix matrix) {
        Matrix transposedMatrix = new Matrix(matrix.getCols(), matrix.getRows());

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                transposedMatrix.setMatrix(j, i, matrix.getPosition(i, j));
            }
        }

        printMatrix(transposedMatrix);
    }


    @Override
    public Matrix augmentWithIdentity(Matrix matrix) {
        Matrix augmentedMatrix = new Matrix(matrix.getRows(), matrix.getCols() * 2);

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++)
                augmentedMatrix.setMatrix(i, j, matrix.getPosition(i, j));
            augmentedMatrix.setMatrix(i, matrix.getCols() + i, 1.0);
        }

        return augmentedMatrix;
    }

    public double identifyPivot(Matrix matrix, int row){
        for (int cols = 0; cols < matrix.getCols(); cols++) {
            if (matrix.getPosition(row, cols) != 0){
                return matrix.getPosition(row,cols);
            }
        }
        return 0;
    }

    private int zeroCounter(double[] row) {
        int count = 0;
        for (double element : row) {
            if (element == 0.0) {
                count++;
            }
        }
        return count;
    }

    public Matrix organizeMatrix(Matrix matrix) {
        int numRows = matrix.getRows();
        int numCols = matrix.getCols();
        Matrix organizedMatrix = new Matrix(numRows, numCols);

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                organizedMatrix.setMatrix(i, j, matrix.getPosition(i, j));
            }
        }
        for (int i = 0; i < numRows - 1; i++) {
            for (int j = 0; j < numRows - i - 1; j++) {
                int zeroCountCurrent = zeroCounter(organizedMatrix.getEntireRow(j));
                int zeroCountNext = zeroCounter(organizedMatrix.getEntireRow(j + 1));

                if (zeroCountCurrent > zeroCountNext) {
                    for (int k = 0; k < numCols; k++) {
                        double temp = organizedMatrix.getPosition(j, k);
                        organizedMatrix.setMatrix(j, k, organizedMatrix.getPosition(j + 1, k));
                        organizedMatrix.setMatrix(j + 1, k, temp);
                    }
                }
            }
        }

        return organizedMatrix;
    }

    @Override
    public void printMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++)
                System.out.print("|" + matrix.getPosition(i, j) + "|");
            System.out.println();
        }
    }

}