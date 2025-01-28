import java.sql.Array;
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

        calculateMatrix(matrix, tileMatrix);
        printHighestAndLowest(tileMatrix);

    }

    public static void calculateMatrix(int[][] matrix, Tile[][] tileMatrix) {


        for (int row = 0; row < matrix.length; row++) {

            for (int column = 0; column < matrix[0].length; row++) {

                calculateTile(new int[] {row, column}, tileMatrix, matrix);

            }

        }

    }

    public static void calculateTile(int[] position, Tile[][] tileMatrix, int[][] matrix) {
        if (position[0] == 0 && position[1] == 0) {

            int sum = matrix[0][0];
            List<int[]> path = new ArrayList<>();
            path.add(new int[] {0, 0});
            Map<Integer, List<int[]>> highestAndLowestPath = new HashMap<>();
            highestAndLowestPath.put(sum, new ArrayList<>(path));

            tileMatrix[0][0] = new Tile(new HashMap<>(highestAndLowestPath), new HashMap<>(highestAndLowestPath));

        } else if (position[0] == 0 && position[1] > 0) {

            Map<Integer, List<int[]>> highestAndLowestPath = new HashMap<>(tileMatrix[0][position[1] - 1].highestPath);
            Map.Entry<Integer, List<int[]>> entry = highestAndLowestPath.entrySet().iterator().next();

            int sum = entry.getKey() + matrix[0][position[1]];
            List<int[]> path = new ArrayList<>(entry.getValue());
            path.add(new int[] {0, position[1]});

            highestAndLowestPath = new HashMap<>();
            highestAndLowestPath.put(sum, new ArrayList<>(path));

            tileMatrix[0][position[1]] = new Tile(new HashMap<>(highestAndLowestPath), new HashMap<>(highestAndLowestPath));

        } else if (position[1] == 0 && position[0] > 0) {

            Map<Integer, List<int[]>> highestAndLowestPath = new HashMap<>(tileMatrix[position[0] - 1][0].highestPath);
            Map.Entry<Integer, List<int[]>> entry = highestAndLowestPath.entrySet().iterator().next();

            int sum = entry.getKey() +  matrix[position[0]][0];
            List<int[]> path = new ArrayList<>(entry.getValue());
            path.add(new int[] {position[0], 0});

            highestAndLowestPath = new HashMap<>();
            highestAndLowestPath.put(sum, new ArrayList<>(path));

            tileMatrix[position[0]][0] = new Tile(new HashMap<>(highestAndLowestPath), new HashMap<>(highestAndLowestPath));

        } else {

            Map<Integer, List<int[]>> highestPathAbove = new HashMap<>(tileMatrix[position[0] - 1][position[1]].highestPath);
            Map<Integer, List<int[]>> highestPathLeft = new HashMap<>(tileMatrix[position[0]][position[1] - 1].highestPath);
            Map.Entry<Integer, List<int[]>> highestPathAboveEntry = highestPathAbove.entrySet().iterator().next();
            Map.Entry<Integer, List<int[]>> highestPathLeftEntry = highestPathLeft.entrySet().iterator().next();

            Map<Integer, List<int[]>> lowestPathAbove = new HashMap<>(tileMatrix[position[0] - 1][position[1]].lowestPath);
            Map<Integer, List<int[]>> lowestPathLeft = new HashMap<>(tileMatrix[position[0]][position[1] - 1].lowestPath);
            Map.Entry<Integer, List<int[]>> lowestPathAboveEntry = lowestPathAbove.entrySet().iterator().next();
            Map.Entry<Integer, List<int[]>> lowestPathLeftEntry = lowestPathLeft.entrySet().iterator().next();

            Map<Integer, List<int[]>> highestPath = new HashMap<>();
            Map<Integer, List<int[]>> lowestPath = new HashMap<>();

            if (highestPathLeftEntry.getKey() > highestPathAboveEntry.getKey()) {

                int sum = highestPathLeftEntry.getKey() + matrix[position[0]][position[1]];
                List<int[]> path = new ArrayList<>(highestPathLeftEntry.getValue());
                path.add(new int[] {position[0], position[1]});

                highestPath.put(sum, new ArrayList<>(path));

            } else {

                int sum = highestPathAboveEntry.getKey() + matrix[position[0]][position[1]];
                List<int[]> path = new ArrayList<>(highestPathAboveEntry.getValue());
                path.add(new int[] {position[0], position[1]});

                highestPath.put(sum, new ArrayList<>(path));

            }

            if (lowestPathLeftEntry.getKey() > lowestPathAboveEntry.getKey()) {

                int sum = lowestPathLeftEntry.getKey() + matrix[position[0]][position[1]];
                List<int[]> path = new ArrayList<>(lowestPathLeftEntry.getValue());
                path.add(new int[] {position[0], position[1]});

                lowestPath.put(sum, new ArrayList<>(path));

            } else {

                int sum = lowestPathAboveEntry.getKey() + matrix[position[0]][position[1]];
                List<int[]> path = new ArrayList<>(lowestPathAboveEntry.getValue());
                path.add(new int[] {position[0], position[1]});

                lowestPath.put(sum, new ArrayList<>(path));

            }

            tileMatrix[position[0]][position[1]] = new Tile(new HashMap<>(highestPath), new HashMap<>(lowestPath));
        }
    }

    public static void printHighestAndLowest(Tile[][] tileMatrix) {

        Tile tile = tileMatrix[tileMatrix.length - 1][tileMatrix[0].length - 1];
        Map.Entry<Integer, List<int[]>> highestPath = tile.highestPath.entrySet().iterator().next();
        Map.Entry<Integer, List<int[]>> lowestPath = tile.lowestPath.entrySet().iterator().next();

        System.out.println("\nPath with the highest sum:");
        System.out.println("Sum: " + highestPath.getKey());
        System.out.println("Path: " + printList(highestPath.getValue()));

        System.out.println("\nPath with the lowest sum:");
        System.out.println("Sum: " + lowestPath.getKey());
        System.out.println("Path: " + printList(lowestPath.getValue()));

    }

    public static String printList(List<int[]> path) {

        String asString = "";

        for (int[] i : path) {

            asString += "{" + i[0] + ", " + i[1] + "}, ";

        }

        return  asString;
    }
}