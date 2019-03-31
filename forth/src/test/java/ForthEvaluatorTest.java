import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class ForthEvaluatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private ForthEvaluator forthEvaluator;

    @Before
    public void setUp() {
        forthEvaluator = new ForthEvaluator();
    }

    @Test
    public void testNumbersAreJustPushedOntoTheStack() {
        assertEquals(
                Arrays.asList(1, 2, 3, 4, 5),
                forthEvaluator.evaluateProgram(Collections.singletonList("1 2 3 4 5")));
    }

    @Test
    public void testTwoNumbersCanBeAdded() {
        assertEquals(
                Collections.singletonList(3),
                forthEvaluator.evaluateProgram(Collections.singletonList("1 2 +")));
    }

    @Test
    public void testErrorIfAdditionAttemptedWithNothingOnTheStack() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Addition requires that the stack contain at least 2 values");

        forthEvaluator.evaluateProgram(Collections.singletonList("+"));
    }

    @Test
    public void testErrorIfAdditionAttemptedWithOneNumberOnTheStack() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Addition requires that the stack contain at least 2 values");

        forthEvaluator.evaluateProgram(Collections.singletonList("1 +"));
    }

    @Test
    public void testTwoNumbersCanBeSubtracted() {
        assertEquals(
                Collections.singletonList(-1),
                forthEvaluator.evaluateProgram(Collections.singletonList("3 4 -")));
    }

    @Test
    public void testErrorIfSubtractionAttemptedWithNothingOnTheStack() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Subtraction requires that the stack contain at least 2 values");

        forthEvaluator.evaluateProgram(Collections.singletonList("-"));
    }

    @Test
    public void testErrorIfSubtractionAttemptedWithOneNumberOnTheStack() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Subtraction requires that the stack contain at least 2 values");

        forthEvaluator.evaluateProgram(Collections.singletonList("1 -"));
    }

    @Test
    public void testTwoNumbersCanBeMultiplied() {
        assertEquals(
                Collections.singletonList(8),
                forthEvaluator.evaluateProgram(Collections.singletonList("2 4 *")));
    }

    @Test
    public void testErrorIfMultiplicationAttemptedWithNothingOnTheStack() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Multiplication requires that the stack contain at least 2 values");

        forthEvaluator.evaluateProgram(Collections.singletonList("*"));
    }

    @Test
    public void testErrorIfMultiplicationAttemptedWithOneNumberOnTheStack() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Multiplication requires that the stack contain at least 2 values");

        forthEvaluator.evaluateProgram(Collections.singletonList("1 *"));
    }

    @Test
    public void testTwoNumbersCanBeDivided() {
        assertEquals(
                Collections.singletonList(4),
                forthEvaluator.evaluateProgram(Collections.singletonList("12 3 /")));
    }

    @Test
    public void testThatIntegerDivisionIsUsed() {
        assertEquals(
                Collections.singletonList(2),
                forthEvaluator.evaluateProgram(Collections.singletonList("8 3 /")));
    }

    @Test
    public void testErrorIfDividingByZero() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Division by 0 is not allowed");

        forthEvaluator.evaluateProgram(Collections.singletonList("4 0 /"));
    }

    @Test
    public void testErrorIfDivisionAttemptedWithNothingOnTheStack() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Division requires that the stack contain at least 2 values");

        forthEvaluator.evaluateProgram(Collections.singletonList("/"));
    }

    @Test
    public void testErrorIfDivisionAttemptedWithOneNumberOnTheStack() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Division requires that the stack contain at least 2 values");

        forthEvaluator.evaluateProgram(Collections.singletonList("1 /"));
    }

    @Test
    public void testCombinedAdditionAndSubtraction() {
        assertEquals(
                Collections.singletonList(-1),
                forthEvaluator.evaluateProgram(Collections.singletonList("1 2 + 4 -")));
    }

    @Test
    public void testCombinedMultiplicationAndDivision() {
        assertEquals(
                Collections.singletonList(2),
                forthEvaluator.evaluateProgram(Collections.singletonList("2 4 * 3 /")));
    }

    @Test
    public void testDupCopiesAValueOnTheStack() {
        assertEquals(
                Arrays.asList(1, 1),
                forthEvaluator.evaluateProgram(Collections.singletonList("1 dup")));
    }

    @Test
    public void testDupCopiesTopValueOnTheStack() {
        assertEquals(
                Arrays.asList(1, 2, 2),
                forthEvaluator.evaluateProgram(Collections.singletonList("1 2 dup")));
    }

    @Test
    public void testErrorIfDuplicatingAttemptedWithNothingOnTheStack() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Duplicating requires that the stack contain at least 1 value");

        forthEvaluator.evaluateProgram(Collections.singletonList("dup"));
    }

    @Test
    public void testDropRemovesTheTopValueOnTheStackIfItIsTheOnlyOne() {
        assertEquals(
                Collections.emptyList(),
                forthEvaluator.evaluateProgram(Collections.singletonList("1 drop")));
    }

    @Test
    public void testDropRemovesTheTopValueOnTheStackIfItIsNotTheOnlyOne() {
        assertEquals(
                Collections.singletonList(1),
                forthEvaluator.evaluateProgram(Collections.singletonList("1 2 drop")));
    }

    @Test
    public void testErrorIfDroppingAttemptedWithNothingOnTheStack() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Dropping requires that the stack contain at least 1 value");

        forthEvaluator.evaluateProgram(Collections.singletonList("drop"));
    }

    @Test
    public void testSwapSwapsTheTopTwosValueOnTheStackIfTheyAreTheOnlyOnes() {
        assertEquals(
                Arrays.asList(2, 1),
                forthEvaluator.evaluateProgram(Collections.singletonList("1 2 swap")));
    }

    @Test
    public void testSwapSwapsTheTopTwosValueOnTheStackIfTheyAreNotTheOnlyOnes() {
        assertEquals(
                Arrays.asList(1, 3, 2),
                forthEvaluator.evaluateProgram(Collections.singletonList("1 2 3 swap")));
    }

    @Test
    public void testErrorIfSwappingAttemptedWithNothingOnTheStack() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Swapping requires that the stack contain at least 2 values");

        forthEvaluator.evaluateProgram(Collections.singletonList("swap"));
    }

    @Test
    public void testErrorIfSwappingAttemptedWithOneNumberOnTheStack() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Swapping requires that the stack contain at least 2 values");

        forthEvaluator.evaluateProgram(Collections.singletonList("1 swap"));
    }

    @Test
    public void testOverCopiesTheSecondElementIfThereAreOnlyTwo() {
        assertEquals(
                Arrays.asList(1, 2, 1),
                forthEvaluator.evaluateProgram(Collections.singletonList("1 2 over")));
    }

    @Test
    public void testOverCopiesTheSecondElementIfThereAreMoreThanTwo() {
        assertEquals(
                Arrays.asList(1, 2, 3, 2),
                forthEvaluator.evaluateProgram(Collections.singletonList("1 2 3 over")));
    }

    @Test
    public void testErrorIfOveringAttemptedWithNothingOnTheStack() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Overing requires that the stack contain at least 2 values");

        forthEvaluator.evaluateProgram(Collections.singletonList("over"));
    }

    @Test
    public void testErrorIfOveringAttemptedWithOneNumberOnTheStack() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Overing requires that the stack contain at least 2 values");

        forthEvaluator.evaluateProgram(Collections.singletonList("1 over"));
    }

    @Test
    public void testUserDefinedOperatorsCanConsistOfBuiltInOperators() {
        assertEquals(
                Arrays.asList(1, 1, 1),
                forthEvaluator.evaluateProgram(Arrays.asList(": dup-twice dup dup ;", "1 dup-twice")));
    }

    @Test
    public void testUserDefinedOperatorsAreEvaluatedInTheCorrectOrder() {
        assertEquals(
                Arrays.asList(1, 2, 3),
                forthEvaluator.evaluateProgram(Arrays.asList(": countup 1 2 3 ;", "countup")));
    }

    @Test
    public void testCanRedefineAUserDefinedOperator() {
        assertEquals(
                Arrays.asList(1, 1, 1),
                forthEvaluator.evaluateProgram(Arrays.asList(": foo dup ;", ": foo dup dup ;", "1 foo")));
    }

    @Test
    public void testCanOverrideBuiltInWordOperators() {
        assertEquals(
                Arrays.asList(1, 1),
                forthEvaluator.evaluateProgram(Arrays.asList(": swap dup ;", "1 swap")));
    }

    @Test
    public void testCanOverrideBuiltInArithmeticOperators() {
        assertEquals(
                Collections.singletonList(12),
                forthEvaluator.evaluateProgram(Arrays.asList(": + * ;", "3 4 +")));
    }

    @Test
    public void testCannotRedefineNumbers() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Cannot redefine numbers");

        forthEvaluator.evaluateProgram(Collections.singletonList(": 1 2 ;"));
    }

    @Test
    public void testErrorIfEvaluatingAnUndefinedOperator() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("No definition available for operator \"foo\"");

        forthEvaluator.evaluateProgram(Collections.singletonList("foo"));
    }

    @Test
    public void testDupIsCaseInsensitive() {
        assertEquals(
                Arrays.asList(1, 1, 1, 1),
                forthEvaluator.evaluateProgram(Collections.singletonList("1 DUP Dup dup")));
    }

    @Test
    public void testDropIsCaseInsensitive() {
        assertEquals(
                Arrays.asList(1),
                forthEvaluator.evaluateProgram(Collections.singletonList("1 2 3 4 DROP Drop drop")));
    }

    @Test
    public void testSwapIsCaseInsensitive() {
        assertEquals(
                Arrays.asList(2, 3, 4, 1),
                forthEvaluator.evaluateProgram(Collections.singletonList("1 2 SWAP 3 Swap 4 swap")));
    }

    @Test
    public void testOverIsCaseInsensitive() {
        assertEquals(
                Arrays.asList(1, 2, 1, 2, 1),
                forthEvaluator.evaluateProgram(Collections.singletonList("1 2 OVER Over over")));
    }

    @Test
    public void testUserDefinedWordsAreCaseInsensitive() {
        assertEquals(
                Arrays.asList(1, 1, 1, 1),
                forthEvaluator.evaluateProgram(Arrays.asList(": foo dup ;", "1 FOO Foo foo")));
    }

    @Test
    public void testDefinitionsAreCaseInsensitive() {
        assertEquals(
                Arrays.asList(1, 1, 1, 1),
                forthEvaluator.evaluateProgram(Arrays.asList(": SWAP DUP Dup dup ;", "1 swap")));
    }

}
