package org.example.twosums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TwoSumsTest {

    private final TwoSums twoSums = new TwoSums();

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, 5, new int[]{1, 2}),
                Arguments.of(new int[]{55, 56, 57, 58, 59}, 99, null)
        );
    }

    @ParameterizedTest
    @MethodSource("generateData")
    public void verifyTwoSums(int[] input, int desiredValue, int[] output) {
        int[] calculatedSums = twoSums.calculate(input, desiredValue);

        Assertions.assertArrayEquals(calculatedSums,output);
    }

}
