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
                8. Sair do sistema
                """);
            String option = input.nextLine();
            switch (option) {
                case "1" -> {
                    Matrix matrix = createMatrix(input, systemImplementation);
                    System.out.println("Matriz criada com sucesso!");
                    systemImplementation.printMatrix(matrix);
                }
                case "2", "3", "4" -> {
                    Matrix matrix1 = createMatrix(input, systemImplementation);
                    Matrix matrix2 = createMatrix(input, systemImplementation);
                    if ("2".equals(option))
                        systemImplementation.addingMatrix(matrix1, matrix2);
                    else if ("3".equals(option))
                        systemImplementation.subtractingMatrix(matrix1, matrix2);
                    else
                        systemImplementation.multiplyingMatrix(matrix1, matrix2);
                }
                case "5" -> {

                }
                case "6" -> {

                }
                case "7" -> {

                }
                case "8" -> {
                    userContinue = false;
                }
                default -> {
                }
            }
        }
        input.close();
    }

    private static Matrix createMatrix(Scanner input, SystemImplementation systemImplementation) {
        System.out.println("Digite a quantidade de linhas da matriz: ");
        int rows = Integer.parseInt(input.nextLine());
        System.out.println("Digite a quantidade de colunas da matriz: ");
        int cols = Integer.parseInt(input.nextLine());

        Matrix matrix = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println("Digite o valor para a posição: [" + (i + 1) + "][" + (j + 1) + "]");
                double number = Double.parseDouble(input.nextLine());
                matrix.setMatrix(i, j, number);
            }
        }
        return matrix;
    }

}
