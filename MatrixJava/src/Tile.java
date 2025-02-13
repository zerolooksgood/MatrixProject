import jdk.jshell.execution.Util;

import java.util.List;

public class Tile {
    private int maxSum;
    private List<int[]> maxPath;

    private int minSum;
    private List<int[]> minPath;

    public Tile(int maxSum, List<int[]> maxPath, int minSum, List<int[]> minPath) {
        this.maxSum = maxSum;
        this.maxPath = Utils.deepCopyList(maxPath);
        this.minSum = minSum;
        this.minPath = Utils.deepCopyList(minPath);
    }

    public int getMaxSum() {
        return maxSum;
    }

    public List<int[]> getMaxPath() {
        return Utils.deepCopyList(maxPath);
    }

    public int getMinSum() {
        return minSum;
    }

    public List<int[]> getMinPath() {
        return Utils.deepCopyList(minPath);
    }

    public void printHighestLowestPath () {
        System.out.println("\nPath with the highest sum:");
        System.out.println("Sum: " + maxSum);
        System.out.println("Path: " + Utils.printList(maxPath));
        System.out.println("\nPath with the lowest sum:");
        System.out.println("Sum: " + minSum);
        System.out.println("Path: " + Utils.printList(minPath));
    }
}
