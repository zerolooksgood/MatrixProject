import java.util.List;
import java.util.Map;

public class Tile {
    public static Map<Integer, List<int[]>> highestPath;
    public static Map<Integer, List<int[]>> lowestPath;

    public Tile(Map<Integer, List<int[]>> HighestPath, Map<Integer, List<int[]>> LowestPath) {
        this.highestPath = HighestPath;
        this.lowestPath = LowestPath;
    }
}
