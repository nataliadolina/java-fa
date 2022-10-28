import Matrix.Matrix;
import Vector.Vector3;

import java.lang.reflect.Array;

public class Main {

    public static void main(String[] args) {
        TestMatrix();
        TestVector3();
    }

    private static void TestVector3(){
        Vector3 vector3 = new Vector3(2f, 1f);
        Vector3 otherVector3 = new Vector3(10f, 8f);
        System.out.println(String.format("Длина: %s = %s", vector3, vector3.getVectorLength()));
        System.out.println(String.format("Сумма %s и %s = %s", vector3, otherVector3, vector3.addVector3(otherVector3)));
        System.out.println(String.format("Разность %s и %s = %s", vector3, otherVector3, vector3.subVector3(otherVector3)));
        System.out.println(String.format("Угол между векторами %s и %s = %s", vector3, otherVector3, vector3.getAngleInRandiansBetweenVectors(otherVector3)));
        System.out.println(String.format("Скалярное произведение %s и %s = %s", vector3, otherVector3, vector3.getDotProduct(otherVector3)));
        System.out.println(String.format("Векторное произведение %s и %s = %s", vector3, otherVector3, vector3.getVectorProduct(otherVector3)));
        Vector3[] randomVectors = Vector3.generateVectors(8);
        System.out.println(String.join(", ", Vector3.toString(randomVectors)));
    }

    private static void TestMatrix(){
        Matrix matrix1 = new Matrix(new Float[]{1.078f, 0.1f, 4.0f, 0.9f}, 2, 2, 0.0f);
        Matrix matrix2 = new Matrix(new Float[]{1.078f, 0.1f, 4.0f, 0.9f}, 2, 2, null);
        printOperation(matrix1, matrix2.toString(), matrix1.add(matrix2), "+");
        printOperation(matrix1, matrix2.toString(), matrix1.sub(matrix2), "-");
        printOperation(matrix1, " 2", matrix1.mulNumber(2), "*");

        Matrix matrix4 = new Matrix(new Float[]{5f, 3f, 7f, 0f, 7f, 1f, 9f, 2f, 3f, 4f, 7f, 6f}, 3, 4, null);
        Matrix matrix5 = new Matrix(new Float[]{20.30f, 1.60f, 18.60f, 1.30f, 12.10f, 1.00f, 23.00f, 1.80f}, 4, 2, null);
        printOperation(matrix4, matrix5.toString(), matrix4.mulMatrix(matrix5), "*");

        Matrix matrixToTranspose = new Matrix(new Float[]{7f, 9f,10f, 7.4f, 1.2f, 8.4f, 12.5f}, 3, 2, null);
        Matrix transposedMatrix = matrixToTranspose.transpose();
        printTransposeOperation(matrixToTranspose, transposedMatrix);

        Matrix matrixToPow = new Matrix(new Float[]{3f, 5f, 2f, 1f}, 2, 2, null);
        Matrix poweredMatrix3 = matrixToPow.powMatrix(3);
        Matrix poweredMatrix0 = matrixToPow.powMatrix(0);
        printPowOperation(matrixToPow, poweredMatrix3, 3);
        printPowOperation(matrixToPow, poweredMatrix0, 0);
    }

    private static void printOperation(Matrix arg1, String arg2, Matrix result, String operation){
        System.out.println(arg1 + operation + arg2 + " = " + result);
    }

    private static void printTransposeOperation(Matrix matrix, Matrix transposedMatrix){
        System.out.println(String.format("Transposed %s = %s", matrix, transposedMatrix));
    }

    private static void printPowOperation(Matrix matrix, Matrix poweredMatrix, int power){
        System.out.println(String.format("%s raised to power of " + String.valueOf(power) + " = %s", matrix, poweredMatrix));
    }
}