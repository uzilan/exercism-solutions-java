import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.util.Arrays.asList;

public class ForthEvaluator {

    private final Stack<Integer> stack = new Stack<>();
    private final Map<String, List<String>> userDefinedOperators = new HashMap<>();

    public List<Integer> evaluateProgram(List<String> inputList) {
        inputList.forEach(input -> {
            if (input.startsWith(":")) {
                addUserDefinedOperator(input);
            } else {
                applyOperators(asList(input.split(" ")));
            }
        });
        return new ArrayList<>(stack);
    }

    private void applyOperators(List<String> operators) {
        operators.forEach(operator -> {
            if (isNumeric(operator)) {
                stack.push(parseInt(operator));
            } else {
                applyOperator(operator);
            }
        });
    }

    private void applyOperator(String operator) {
        final String lowercaseOperator = operator.toLowerCase();
        if (userDefinedOperators.containsKey(lowercaseOperator)) {
            applyOperators(userDefinedOperators.get(lowercaseOperator));
            return;
        }

        switch (lowercaseOperator) {
            case "+":
                add();
                break;
            case "-":
                subtract();
                break;
            case "*":
                multiply();
                break;
            case "/":
                divide();
                break;
            case "dup":
                duplicate();
                break;
            case "drop":
                drop();
                break;
            case "swap":
                swap();
                break;
            case "over":
                over();
                break;
            default:
                throw new IllegalArgumentException(
                        format("No definition available for operator \"%s\"", lowercaseOperator));
        }
    }

    private void add() {
        expectNumberOfValuesInTheStack(2, "Addition");
        stack.push(stack.pop() + stack.pop());
    }

    private void subtract() {
        expectNumberOfValuesInTheStack(2, "Subtraction");
        Integer subtrahend = stack.pop();
        Integer minuend = stack.pop();
        stack.push(minuend - subtrahend);
    }

    private void multiply() {
        expectNumberOfValuesInTheStack(2, "Multiplication");
        stack.push(stack.pop() * stack.pop());
    }

    private void divide() {
        expectNumberOfValuesInTheStack(2, "Division");
        final Integer denominator = stack.pop();
        expectNoZeroDenominator(denominator);
        final Integer numerator = stack.pop();
        stack.push(numerator / denominator);
    }

    private void duplicate() {
        expectNumberOfValuesInTheStack(1, "Duplicating");
        stack.push(stack.peek());
    }

    private void drop() {
        expectNumberOfValuesInTheStack(1, "Dropping");
        stack.pop();
    }

    private void swap() {
        expectNumberOfValuesInTheStack(2, "Swapping");
        final Integer last = stack.pop();
        final Integer lastButOne = stack.pop();
        stack.push(last);
        stack.push(lastButOne);
    }

    private void over() {
        expectNumberOfValuesInTheStack(2, "Overing");
        final Integer last = stack.pop();
        final Integer lastButOne = stack.peek();
        stack.push(last);
        stack.push(lastButOne);
    }

    private void addUserDefinedOperator(String input) {
        final String[] split = input.split(" ");
        expectNoNumberRedefinition(split[1]);
        userDefinedOperators.put(
                split[1].toLowerCase(),
                asList(split).subList(2, split.length - 1));
    }

    private boolean isNumeric(String operator) {
        return operator.chars().allMatch(Character::isDigit);
    }

    private void expectNumberOfValuesInTheStack(int number, String operation) {
        if (stack.size() < number) {
            throw new IllegalArgumentException(
                    format(
                            "%s requires that the stack contain at least %d values",
                            operation,
                            number));
        }
    }

    private void expectNoZeroDenominator(int number) {
        if (number == 0) {
            throw new IllegalArgumentException("Division by 0 is not allowed");
        }
    }

    private void expectNoNumberRedefinition(String operator) {
        if (isNumeric(operator)) {
            throw new IllegalArgumentException("Cannot redefine numbers");
        }
    }
}