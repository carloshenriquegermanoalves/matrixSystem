package org.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemImplementationTest {
    @Test
    public void scaleMatrixTest(){
        SystemImplementation system = new SystemImplementation();
        double[][] matrix1 = {{1,2,3},{0,0,0},{0,2,1}};
        Matrix matrix = new Matrix(3,3);
        matrix.setMatrix(matrix1);
        system.scaleMatrix(matrix);
        System.out.println("------------------------------------------------------------------");
    }
}