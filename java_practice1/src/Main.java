public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(new Float[]{1.078f, 0.1f, 4.0f, 0.9f}, 2, 2, 0.0f);
        Matrix matrix2 = new Matrix(new Float[]{1.078f, 0.1f, 4.0f, 0.9f}, 2, 2, null);
        Matrix matrix3 = matrix1.add(matrix2);
        System.out.println(matrix1.VisualString());
        System.out.println(matrix2.VisualString());
        System.out.println(matrix3.VisualString());
    }
}