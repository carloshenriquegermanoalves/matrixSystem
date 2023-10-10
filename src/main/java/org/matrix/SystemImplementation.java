package org.matrix;

public class SystemImplementation implements MatrixSystem {
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
        if (matrixA.getCols() != matrixB.getRows()) {
            throw new IllegalArgumentException("As matrizes não podem ser multiplicadas, pois possuem dimensões incompatíveis!");
        }

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

    @Override
    public Matrix scaleMatrix(Matrix matrix) {
        int pivotRow = 0;
        for (int col = 0; col < matrix.getCols(); col++) {
            //Encontra uma linha não nula na coluna atual
            int noZeroRow = pivotRow;
            while (noZeroRow < matrix.getRows() && matrix.getPosition(noZeroRow, col) == 0)
                noZeroRow++;

            //Se não encontrou uma linha não nula, troque as linhas, se necessário
            if (noZeroRow == matrix.getRows())
                continue;

            //Se encontrou uma linha não nula, troque as linhas, se necessário
            if (noZeroRow != pivotRow)
                this.swapRows(matrix, noZeroRow, pivotRow);

            //Faz o elemento da diagonal (pivô) igual a 1
            double pivotElement = matrix.getPosition(pivotRow, col);
            if (pivotElement != 1)
                scaleRow(matrix, pivotRow, 1.0 / pivotElement);

            //Zera todos os elementos abaixo do pivô da mesma coluna
            for (int i = pivotRow + 1; i < matrix.getRows(); i++) {
                double factor = matrix.getPosition(i, col);
                addRowToRow(i, pivotRow, -factor);
            }

            //Avança para a próxima linha pivô
            pivotRow++;
        }
        return matrix;
    }


    @Override
    public void swapRows(Matrix matrix, int row1, int row2) {
        if (row1 >= 0 && row1 < matrix.getRows() && row2 >= 0 && row2 < matrix.getRows()) {
            double[] tempRow = new double[matrix.getCols()];
            for (int col = 0; col < matrix.getCols(); col++) {
                tempRow[col] = matrix.getPosition(row1, col);
                matrix.setMatrix(row1, col, matrix.getPosition(row2, col));
                matrix.setMatrix(row2, col, tempRow[col]);
            }
        } else {
            throw new IllegalArgumentException("Índices de linha inválidos.");
        }
    }

    @Override
    public void scaleRow(Matrix matrix, int row, double scalar) {
        if (row >= 0 && row < matrix.getRows()) {
            for (int col = 0; col < matrix.getCols(); col++) {
                double value = matrix.getPosition(row, col);
                matrix.setMatrix(row, col, value * scalar);
            }
        } else {
            throw new IllegalArgumentException("Índice de linha inválido.");
        }
    }

    @Override
    public void addRowToRow(int i, int pivotRow, double v) {

    }

    @Override
    public void printMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                System.out.print(matrix.getPosition(i, j) + " ");
            }
            System.out.println(); // Avança para a próxima linha após imprimir uma linha completa
        }
    }


}
