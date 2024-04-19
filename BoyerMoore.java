public class BoyerMoore {
    private String template;
    private int templateLength;
    private FirstPart first;
    private SecondPart second;
    private int countIterations;

    public BoyerMoore(String template) {
        if (template == null || template.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.countIterations = 0;
        this.template = template;
        this.templateLength = template.length();
        this.first = new FirstPart(template, countIterations);
        this.second = new SecondPart(template, countIterations);
    }

    public int run(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }

        final int textLength = text.length();
        if (textLength == 0 || text.length() < templateLength) {
            return 0;
        }

        int matches = 0;
        int index = templateLength - 1;
        while (index < textLength) {
            int offset = 0;
            while (offset < templateLength) {
                countIterations++;
                if (template.charAt(templateLength - offset - 1) != text.charAt(index - offset)) {
                    int FirstSkips = second.getNumSkips(text.charAt(index - offset), offset, countIterations);
                    int SecondSkips = first.getNumSkips(offset,countIterations);
                    index += Math.max(FirstSkips, SecondSkips);
                    break;
                }
                offset++;
            }

            if (offset == templateLength) {
                matches++;
                // Правило правильного суффикса по-прежнему применяется и может привести к пропускам
                //Правило плохого символа не применяется, поскольку все символы совпадают (т.е. плохих символов нет).
                index += first.getNumSkips(offset, countIterations);
            }
            index++;
        }
        return matches;
    }

    public String getPattern() {
        return template;
    }
    public int getCountIterations() {
        return countIterations;
    }
}