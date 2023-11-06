package org.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemImplementationTest {
    @Test
    public void scaleMatrixTest(){
        SystemImplementation system = new SystemImplementation();
        System.out.println("-----------------------------1------------------------------------");
        double[][] matrixTemp1 = {{1,3,7},{0,0,5},{0,0,0}};
        Matrix matrix = new Matrix(3,3);
        matrix.setMatrix(matrixTemp1);
        System.out.println("-------------------------Matriz sem escalonamento------------------");
        system.printMatrix(matrix);
        System.out.println("-------------------------Matriz escalonada-------------------------");
        system.scaleMatrix(matrix);
        System.out.println("------------------------------2------------------------------------");
        double[][] matrixTemp2 = {{1,3,7},{0,3,5},{4,0,0}};
        Matrix matrix2 = new Matrix(3,3);
        matrix2.setMatrix(matrixTemp2);
        System.out.println("-------------------------Matriz sem escalonamento------------------");
        system.printMatrix(matrix2);
        System.out.println("-------------------------Matriz escalonada-------------------------");
        system.scaleMatrix(matrix2);
        System.out.println("------------------------------3------------------------------------");
        double[][] matrixTemp3 = {{1,2,3},{2,4,6},{7,8,9}};
        Matrix matrix3 = new Matrix(3,3);
        matrix3.setMatrix(matrixTemp3);
        System.out.println("-------------------------Matriz sem escalonamento------------------");
        system.printMatrix(matrix3);
        System.out.println("-------------------------Matriz escalonada-------------------------");
        system.scaleMatrix(matrix3);
        System.out.println("------------------------------4------------------------------------");
        double[][] matrixTemp4 = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
        Matrix matrix4 = new Matrix(4,3);
        matrix4.setMatrix(matrixTemp4);
        System.out.println("-------------------------Matriz sem escalonamento------------------");
        system.printMatrix(matrix4);
        System.out.println("-------------------------Matriz escalonada-------------------------");
        system.scaleMatrix(matrix4);
        System.out.println("------------------------------5------------------------------------");
        double[][] matrixTemp5 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        Matrix matrix5 = new Matrix(4,4);
        matrix5.setMatrix(matrixTemp5);
        System.out.println("-------------------------Matriz sem escalonamento------------------");
        system.printMatrix(matrix5);
        System.out.println("-------------------------Matriz escalonada-------------------------");
        system.scaleMatrix(matrix5);
    }
}