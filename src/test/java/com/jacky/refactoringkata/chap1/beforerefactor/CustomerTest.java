package com.jacky.refactoringkata.chap1.beforerefactor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.jacky.refactoringkata.chap1.beforerefactor.Movie.NEW_RELEASE;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Movie movie1 = null;
    Rental rental1 = null;
    Customer customer1 = null;

    @BeforeEach
    void setUp() {
        movie1 = new Movie("New Released 1", NEW_RELEASE);
        rental1 = new Rental(movie1, 5);
        customer1 = new Customer("Jacky");
        customer1.addRental(rental1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addRental() {
    }

    @Test
    void getName() {
    }

    @Test
    void statement() {
        System.out.println(customer1.statement());
        String expectStr = "Rental Record for Jacky\n" +
                "\tNew Released 1\t15.0\n" +
                "Amount owed is 15.0\n" +
                "You earned 2 frequent renter points";
        assertEquals(expectStr, customer1.statement());
    }
}