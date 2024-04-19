public class FirstPart{
    private String template;
    private int templateLength;
    private int[] table;

    public FirstPart(String template, int countIterations) {
        if (template == null || template.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.template = template;
        this.templateLength = template.length();
        this.table = new int[templateLength + 1];
        buildTable(countIterations);
    }


    public int getNumSkips(int offset,int countIterations) {
        if (offset < 0 || offset > templateLength) {
            throw new IllegalArgumentException();
        }
        if (offset == 0) {
            return 0;
        }int index = templateLength - offset - 1;
        return table[index+1];
    }

    private void buildTable(int countIterations) {
        int[] tmp = new int[templateLength + 1];
        int i = templateLength;
        int j = templateLength + 1;
        tmp[i] = j;

        while (i > 0) {
            while (j <= templateLength && template.charAt(i-1) != template.charAt(j-1)) {
                countIterations++;
                if (table[j] == 0) {
                    table[j] = j - i;
                }
                j = tmp[j];
            }
            i--;
            j--;
            tmp[i] = j;
        }

        j = tmp[0];
        for (i=0; i <= templateLength; i++) {
            countIterations++;
            if (table[i] == 0) {
                table[i] = j;
            }
            if (i == j) {
                j = tmp[j];
            }
        }

        for (i=0; i < table.length; i++) {
            countIterations++;
            table[i]--;
        }
    }
}
