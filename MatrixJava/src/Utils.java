import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<int[]> deepCopyList(List<int[]> orgList) {

        List<int[]> newList = new ArrayList<>();

        for (int[] i : orgList) {
            newList.add(new int[] {i[0], i[1]});
        }

        return newList;
    }

    public static String printList(List<int[]> list) {
        String result = "";
        for (int[] i : list) {
            result += "{" + i[0] + ", " + i[1] + "}, ";
        }
        return result;
    }
}
