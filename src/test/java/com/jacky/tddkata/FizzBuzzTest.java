package com.jacky.tddkata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzTest {
    @ParameterizedTest(name = "输入是{0} ，返回值是 {1} ")
    @CsvSource({
            "1,    1",
            "2,    2",
            "3,  fizz",
            "4,  4"
    })
    void add(int first, int expectedResult) {
//        Calculator calculator = new Calculator();
//        assertEquals(expectedResult, calculator.add(first, second),
//                () -> first + " + " + second + " should equal " + expectedResult);
    }
}
