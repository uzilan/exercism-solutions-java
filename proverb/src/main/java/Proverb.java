import java.util.Arrays;

class Proverb {

    private String[] words;

    Proverb(final String[] words) {
        this.words = words;
    }

    String recite() {
        if (words.length == 0) {
            return "";
        }

        final StringBuilder sb = new StringBuilder();
        helper(sb, words);
        sb.append(String.format("And all for the want of a %s.", words[0]));
        return sb.toString();
    }

    private void helper(final StringBuilder sb, final String[] words) {
        if (words.length < 2) {
            return;
        }
        sb.append(String.format("For want of a %s the %s was lost.\n", words[0], words[1]));
        helper(sb, Arrays.copyOfRange(words, 1, words.length));
    }
}
