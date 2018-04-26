import java.util.Arrays;

public class RotateMatrix {

    private static void fillMatrix(int[][] matrix) {

        int n = matrix.length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = i*5 + j;
    }

    private static void printMatrix(int[][] matrix) {
        System.out.print("[");
        for (int i = 0; i < matrix.length - 1; i++)
            System.out.println(Arrays.toString(matrix[i]));

        System.out.println(Arrays.toString(matrix[matrix.length - 1]) + "]");
    }

    public static void rotateMatrix(int[][] img) {
    }



    public static void main(String[] args) {

        int img[][] = new int[5][5];
        fillMatrix(img);
        System.out.println("Original matrix:");
        printMatrix(img);

        rotateMatrix(img);

        System.out.println("Rotated matrix:");
        printMatrix(img);
    }
}
