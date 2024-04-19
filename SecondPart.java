import java.util.HashMap;
import java.util.Map;

public class SecondPart {
    private String template;
    private int templateLength;
    private Map<Character, int[]> table;

    public SecondPart(String template, int countIterations) {
        if (template == null || template.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.template = template;
        this.templateLength = template.length();
        this.table = new HashMap<>();
        buildTable(countIterations);
    }

    public int getNumSkips(char c, int offset, int countIterations) {
        if (offset < 0 || offset > templateLength) {
            throw new IllegalArgumentException();
        }

        if (offset == templateLength) {
            return 0;
        }

        int index = templateLength - offset - 1;
        if (!table.containsKey(c)) {
            return index;
        }
        return table.get(c)[index];
    }

    private void buildTable(int countIterations) {
        for (int i=0; i < templateLength; i++) {
            countIterations++;
            char curr = template.charAt(i);
            if (!table.containsKey(curr)) {
                table.put(curr, new int[templateLength]);
            }
            table.get(curr)[i] = -1;
        }

        for (char c : table.keySet()) {
            int[] row = table.get(c);
            int count = 0;
            for (int i=0; i<row.length; i++) {
                countIterations++;
                if (row[i] == -1) {
                    row[i] = count;
                    count = 0;
                } else {
                    row[i] = count;
                    count++;
                }
            }
        }
    }
}