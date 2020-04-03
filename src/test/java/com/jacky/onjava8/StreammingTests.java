package com.jacky.onjava8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.time.Clock;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class StreammingTests {

    @Test
    public void should_equal_to_2_when_calculator_add_1_and_1() {
        //题目2：给定两个数字列表，如何返回所有的数对呢？例如给定列表【1，2，3】 和【3，4】，  1，3    1，4  2，3  2，4  3，4  3，4

        // GIVEN
        List<Integer> quiz2numberlist1 = Arrays.asList(1, 2, 3);
        List<Integer> quiz2numberlist2 = Arrays.asList(3, 4);
        // WHEN


        List<int[]> expectOutput = Lists.newArrayList(new int[]{1, 3}, new int[]{1, 4}, new int[]{2, 3},
                new int[]{2, 4}, new int[]{3, 3}, new int[]{3, 4});
        Stream<int[]> stream = quiz2numberlist1.stream().
                flatMap(integer -> quiz2numberlist2.stream().map(integer1 -> new int[]{integer,integer1*integer}));
        List<int[]> output = (List<int[]>) stream.collect(toList());
        // THEN
        assertThat(output, equalTo(expectOutput));

    }
}
