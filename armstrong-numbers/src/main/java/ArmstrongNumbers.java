import java.util.Arrays;

class ArmstrongNumbers {

    boolean isArmstrongNumber(int numberToCheck) {
        final int[] split = Integer.toString(numberToCheck).chars().map(c -> c - '0').toArray();
        final double sum = Arrays.stream(split).mapToDouble(i -> Math.pow(i, split.length)).sum();
        return sum == numberToCheck;
    }
}
