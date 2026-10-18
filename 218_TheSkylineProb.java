
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> events = new ArrayList<>();

        for (int[] building : buildings) {
            events.add(new int[]{building[0], building[2]});
            events.add(new int[]{building[1], -building[2]});
        }

        events.sort((a, b) -> Integer.compare(a[0], b[0]));

        TreeMap<Integer, Integer> heights = new TreeMap<>();
        heights.put(0, 1);

        List<List<Integer>> result = new ArrayList<>();
        int previousMax = 0;
        int i = 0;

        while (i < events.size()) {
            int x = events.get(i)[0];

            while (i < events.size() && events.get(i)[0] == x) {
                int height = events.get(i)[1];

                if (height > 0) {
                    heights.put(
                        height,
                        heights.getOrDefault(height, 0) + 1
                    );
                } else {
                    int h = -height;
                    int count = heights.get(h);

                    if (count == 1) {
                        heights.remove(h);
                    } else {
                        heights.put(h, count - 1);
                    }
                }

                i++;
            }

            int currentMax = heights.lastKey();

            if (currentMax != previousMax) {
                result.add(Arrays.asList(x, currentMax));
                previousMax = currentMax;
            }
        }

        return result;
    }
}
