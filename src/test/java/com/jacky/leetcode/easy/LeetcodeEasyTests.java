package com.jacky.leetcode.easy;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LeetcodeEasyTests {
    @Test
    public void should_two_sum() {
        TwoSumSolution twoSumSolution = new TwoSumSolution();
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
//        int [] result = twoSumSolution.twoSum(nums,target);
//        assertArrayEquals(new int[]{0,1},result);
        int[] result2 = twoSumSolution.twoSumBetterSolution(nums, target);
        assertArrayEquals(new int[]{0, 1}, result2);
        int[] result3 = twoSumSolution.twoSumBestSolution(nums, target);
        assertArrayEquals(new int[]{0, 1}, result3);
    }



}
