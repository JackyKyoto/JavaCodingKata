package com.jacky.refactoringkata.chap1.step5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.jacky.refactoringkata.chap1.step5.Movie.NEW_RELEASE;
import static com.jacky.refactoringkata.chap1.step5.Movie.REGULAR;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    Movie movie1 = null;
    Rental rental1 = null;
    Customer customer1 = null;

    @BeforeEach
    void setUp() {
        movie1 = new Movie("大轰炸", NEW_RELEASE);
        rental1 = new Rental(movie1, 3);
        customer1 = new Customer("Jacky");
        customer1.addRental(rental1);
    }

    @Test
    void statement() {
        System.out.println(customer1.statement());
        String expectStr = "Rental Record for Jacky\n" +
                "\t大轰炸\t9.0\n" +
                "Amount owed is 9.0\n" +
                "You earned 2 frequent renter points";
        assertEquals(expectStr, customer1.statement());
        System.out.println("-----------------一周以后，不再是一个新上映的电影了----------------");
        movie1.setPriceCode(REGULAR);
        System.out.println(customer1.statement());
    }
}