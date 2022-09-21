import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(new Float[]{1.078f, 0.1f, 4.0f, 0.9f}, 2, 2, 0.0f);
        Matrix matrix2 = new Matrix(new Float[]{1.078f, 0.1f, 4.0f, 0.9f}, 2, 2, null);
        printOperation(matrix1, matrix2.VisualString(), matrix1.add(matrix2), "+");
        printOperation(matrix1, matrix2.VisualString(), matrix1.sub(matrix2), "-");
        printOperation(matrix1, " 2", matrix1.mulNumber(2), "*");

        Matrix matrix4 = new Matrix(new Float[]{5f, 3f, 7f, 0f, 7f, 1f, 9f, 2f, 3f, 4f, 7f, 6f}, 3, 4, null);
        Matrix matrix5 = new Matrix(new Float[]{20.30f, 1.60f, 18.60f, 1.30f, 12.10f, 1.00f, 23.00f, 1.80f}, 4, 2, null);
        printOperation(matrix4, matrix5.VisualString(), matrix4.mulMatrix(matrix5), "*");
    }

    private static void printOperation(Matrix arg1, String arg2, Matrix result, String operation){
        System.out.println(arg1.VisualString() + operation + arg2 + " = " + result.VisualString());
    }
}