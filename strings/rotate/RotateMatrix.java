import java.util.Arrays;

public class RotateMatrix {

    private static void fillMatrix(int[][] matrix) {

        int n = matrix.length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = i*n + j;
    }

    private static void printMatrix(int[][] matrix) {
        System.out.print("[");
        for (int i = 0; i < matrix.length - 1; i++)
            System.out.println(Arrays.toString(matrix[i]));

        System.out.println(Arrays.toString(matrix[matrix.length - 1]) + "]");
    }

    public static void rotateMatrix(int[][] img) {

        int n = img.length;
        for (int i = 0; i < n - 1 - i; i++)
            rotateLevel(img, i);
    }

    private static void rotateLevel(int[][] img, int level) {

        int n = img.length;
        for (int i = level; i < n - level - 1; i++) {

            int topLeft = img[level][i];
            img[level][i] = img[n-1-i][level];
            img[n-1-i][level] = img[n-1-level][n-1-i];
            img[n-1-level][n-1-i] = img[i][n-1-level];
            img[i][n-1-level] = topLeft;
        }
    }



    public static void main(String[] args) {

        int img[][] = new int[10][10];
        fillMatrix(img);
        System.out.println("Original matrix:");
        printMatrix(img);

        for (int i = 1; i <= 4; i++) {
            rotateMatrix(img);
            System.out.println("Rotated matrix after " + i + " rotations:");
            printMatrix(img);
        }
    }
}
