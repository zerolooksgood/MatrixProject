import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        int[][] matrix = {
                {1, 1, 1},
                {4, 5, 1},
                {7, 8, 9}
        };

        Tile[][] tileMatrix = new Tile[matrix.length][matrix[0].length];

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                checkTile(new int[] {row, column}, tileMatrix, matrix);
            }
        }

        tileMatrix[matrix.length - 1][matrix[0].length -1].printHighestLowestPath();

        for (Tile[] row : tileMatrix) {
            for (Tile column : row) {
                //System.out.println(column);
            }
        }

    }

    public static void checkTile(int[] pos, Tile[][] tileMatrix, int[][] matrix) {

        int maxSum = 0;
        List<int[]> maxPath = new ArrayList<>();

        int minSum = 0;
        List<int[]> minPath = new ArrayList<>();

        Tile tileAbove = null;
        try {
            tileAbove = tileMatrix[pos[0] - 1][pos[1]];
        } catch (Exception e) {}

        Tile tileLeft = null;
        try {
            tileLeft = tileMatrix[pos[0]][pos[1] - 1];
        } catch (Exception e) {}

        if (tileAbove != null && tileLeft != null) {

            int maxSumAbove = tileAbove.getMaxSum();
            int minSumAbove = tileAbove.getMinSum();

            int maxSumLeft = tileLeft.getMaxSum();
            int minSumLeft = tileLeft.getMinSum();

            if (maxSumAbove > maxSumLeft) {
                maxSum = maxSumAbove;
                maxPath = tileAbove.getMaxPath();
            } else {
                maxSum = maxSumLeft;
                maxPath = tileLeft.getMaxPath();
            }

            if (minSumAbove < minSumLeft) {
                minSum = minSumAbove;
                minPath = tileAbove.getMinPath();
            } else {
                minSum = minSumLeft;
                minPath = tileLeft.getMinPath();
            }

        } else if (tileAbove == null && tileLeft != null) {

            maxSum = tileLeft.getMaxSum();
            maxPath = tileLeft.getMaxPath();
            minSum = tileLeft.getMinSum();
            minPath = tileLeft.getMinPath();

        } else if (tileLeft == null && tileAbove != null) {

            maxSum = tileAbove.getMaxSum();
            maxPath = tileAbove.getMaxPath();
            minSum = tileAbove.getMinSum();
            minPath = tileAbove.getMinPath();

        }

        maxSum += matrix[pos[0]][pos[1]];
        maxPath.add(new int[] {pos[0], pos[1]});
        minSum += matrix[pos[0]][pos[1]];
        minPath.add(new int[] {pos[0], pos[1]});

        tileMatrix[pos[0]][pos[1]] = new Tile(maxSum, maxPath, minSum, minPath);
    }
}