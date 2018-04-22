import java.util.List;
import java.util.LinkedList;

public class NQueens {

    public static List<List<Pos>> nQueens(int n) {
        return nQueens(n, 0, new LinkedList<Pos>());
    }

    private static List<List<Pos>> nQueens(int n, int row, LinkedList<Pos> placed) {

        List<List<Pos>> result = new LinkedList<List<Pos>>();
        if (row == n)
            result.add(new LinkedList<Pos>(placed)); // found a complete solution
        else {
            List<Integer> possibleCols = possibleColumns(n, row, placed);
            for (int c : possibleCols) {
                placed.addLast(new Pos(row, c));
                result.addAll(nQueens(n, row + 1, placed));
                placed.removeLast();
            }
        }

        return result;
    }

    private static List<Integer> possibleColumns(int n, int row, List<Pos> placed) {

        List<Integer> result = new LinkedList<Integer>();
        for (int c = 0; c < n; c++) {
            if (!sharesColumn(c, placed) && !sharesDiag(c, row, placed))
                result.add(c);
        }

        return result;
    }

    private static boolean sharesColumn(int col, List<Pos> placed) {
        for (Pos p: placed)
            if (p.col == col)
                return true;

        return false;
    }

    private static boolean sharesDiag(int col, int row, List<Pos> placed) {
        for (Pos p: placed)
            if (Math.abs(p.col - col) == Math.abs(p.row - row))
                return true;

        return false;
    }

    public static void main(String[] args) {

        int[] sizes = {4, 8};
        for (int s : sizes)
            System.out.println("Solution for size " + s + ": " + nQueens(s));
    }
}
