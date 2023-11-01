package org.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemImplementationTest {
    @Test
    public void scaleMatrixTest(){
        SystemImplementation system = new SystemImplementation();
        double[][] matrix1 = {{1,2,3},{4,5,6},{7,8,9}};
        Matrix matrix = new Matrix(3,3);
        matrix.setMatrix(matrix1);
        system.scaleMatrix(matrix);
        system.organizeMatrix(matrix);
        System.out.println("------------------------------------------------------------------6");
        system.printMatrix(matrix);
    }
}