import org.junit.jupiter.api.Test;
import org.matrix.Matrix;
import org.matrix.SystemImplementation;

public class MatrixTest {

    SystemImplementation systemImplementation = new SystemImplementation();
    @Test
    public void testAddingMatrix() {
        Matrix matrixA = new Matrix(2, 2);
        Matrix matrixB = new Matrix(2, 2);

        systemImplementation.addingMatrix(matrixA, matrixB);
    }

    @Test
    public void testSubtractingMatrix() {
        Matrix matrixA = new Matrix(2, 2);
        Matrix matrixB = new Matrix(2, 2);

        systemImplementation.subtractingMatrix(matrixA, matrixB);
    }

    @Test
    public void testMultiplyingMatrix() {
        Matrix matrixA = new Matrix(2, 3);
        Matrix matrixB = new Matrix(3, 2);

        systemImplementation.multiplyingMatrix(matrixA, matrixB);
    }

    @Test
    public void testScalarMultiplying() {
        Matrix matrix = new Matrix(2, 2);
        double scalar = 2.0;

        systemImplementation.scalarMultiplying(scalar, matrix);
    }

}
