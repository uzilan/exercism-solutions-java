import java.util.List;
import java.util.Stack;

import static java.util.Arrays.asList;

public class BracketChecker {

    private boolean matchingBrackets;
    private static final List<Character> OPENING_BRACKETS = asList('(', '[', '{');
    private static final List<Character> CLOSING_BRACKETS = asList(')', ']', '}');

    public BracketChecker(String input) {
        matchingBrackets = helper(input, new Stack<>());
    }

    public boolean areBracketsMatchedAndNestedCorrectly() {
        return matchingBrackets;
    }

    private boolean helper(String input, Stack<Character> stack) {
        if (input.isEmpty()) {
            return stack.isEmpty();
        }

        final char firstChar = input.charAt(0);

        if (OPENING_BRACKETS.contains(firstChar)) {
            stack.push(firstChar);
            return helper(input.substring(1), stack);
        }

        if (CLOSING_BRACKETS.contains(firstChar) && stack.isEmpty()) {
            return false;
        }

        boolean ok;
        switch (firstChar) {
            case ')':
                ok = '(' == stack.pop();
                break;
            case ']':
                ok = '[' == stack.pop();
                break;
            case '}':
                ok = '{' == stack.pop();
                break;
            default:
                ok = true;
        }

        return ok && helper(input.substring(1), stack);
    }
}
