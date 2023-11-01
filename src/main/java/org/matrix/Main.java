package org.matrix;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SystemImplementation systemImplementation = new SystemImplementation();
        boolean userContinue = true;
        Scanner input = new Scanner(System.in);

        while (userContinue) {
            System.out.println("""
                    Escolha uma opção:\s
                    1. Criar matriz
                    2. Soma de matrizes
                    3. Subtração de matrizes
                    4. Multiplicação de matrizes
                    5. Multiplicação de matriz por escalar
                    6. Determinante de uma matriz
                    7. Escalonamento de uma matriz
                    8. Inversa de uma matriz
                    9. Matriz transposta
                    10. Sair do sistema
                    11. Organizar matriz
                    """);
            String option = input.nextLine();
            switch (option) {
                case "1", "5", "6", "7", "8", "9","11" -> {
                    Matrix matrix = createMatrix(input);
                    System.out.println("Matriz criada com sucesso!");
                    switch (option) {
                        case "1" -> systemImplementation.printMatrix(matrix);
                        case "5" -> {
                            System.out.print("Digite um valor escalar para multiplicar pela matriz criada: ");
                            double scalar = Double.parseDouble(input.nextLine());
                            systemImplementation.scalarMultiplying(scalar, matrix);
                        }
                        case "6" -> System.out.println(systemImplementation.determinantMatrix(matrix));
                        case "7" -> systemImplementation.scaleMatrix(matrix);
                        case "8" -> systemImplementation.invertMatrix(matrix);
                        case "9" -> {
                            systemImplementation.printMatrix(matrix);
                            systemImplementation.transposedMatrix(matrix);
                        }
                        case "11" -> systemImplementation.organizeMatrix(matrix);
                    }
                }
                case "2", "3", "4" -> {
                    Matrix matrix1 = createMatrix(input);
                    Matrix matrix2 = createMatrix(input);
                    switch (option) {
                        case "2" -> systemImplementation.addingMatrix(matrix1, matrix2);
                        case "3" -> systemImplementation.subtractingMatrix(matrix1, matrix2);
                        default -> systemImplementation.multiplyingMatrix(matrix1, matrix2);
                    }
                }
                case "10" -> userContinue = false;
                default -> System.out.println("Opção inválida! Tente novamente");
            }
        }
        input.close();
    }

    public static Matrix createMatrix(Scanner input) {
        System.out.print("Digite a quantidade de linhas da matriz: ");
        int rows = Integer.parseInt(input.nextLine());
        System.out.print("Digite a quantidade de colunas da matriz: ");
        int cols = Integer.parseInt(input.nextLine());

        Matrix matrix = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Digite o valor para a posição: [" + (i + 1) + "][" + (j + 1) + "]");
                double number = Double.parseDouble(input.nextLine());
                matrix.setMatrix(i, j, number);
            }
        }
        return matrix;
    }

}
