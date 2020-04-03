package com.jacky.tddkata;

public class GameNumber {
    int value;

    public GameNumber(int i) {
        this.value = i;
    }

    @Override
    public String toString() {
        if(value%3==0){
            return "fizz";
        }
        return String.valueOf(value);
    }
}
