package com.jacky.tddkata;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FizzbuzzTest02 {
    @Test
    public void should_shownumber(){
        checkGameNumberTest(1, "1");
        checkGameNumberTest(2, "2");
    }

    @Test
    public void should_show_fizz_if_it_is3(){
        checkGameNumberTest(3,"fizz");
    }

    private void checkGameNumberTest(int i, String s) {
        assertThat(new GameNumber(i).toString(), is(s));
    }


}
