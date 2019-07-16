package com.jacky.refactoringkata.chap1.step1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Movie movie1 = null;
    Rental rental1 = null;
    Customer customer1 = null;
    @BeforeEach
    void setUp() {
        movie1 = new Movie("Children movie", 2);
        rental1 = new Rental(movie1, 3);
        customer1 = new Customer("Jacky");
        customer1.addRental(rental1);
    }

    @Test
    void statement() {
        System.out.println(customer1.statement());
        String expectStr = "Rental Record for Jacky\n" +
                "\tChildren movie\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points";
        assertEquals(expectStr, customer1.statement());
    }
}