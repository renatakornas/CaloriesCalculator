/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.renatakornas;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rkornas
 */
public class MealTest {

    public MealTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calculate method, of class Meal.
     */
    @Test
    public void shouldBeZeroKcal() {
        Product p1 = new Product("p", 0);
        Meal m1 = new Meal();
        m1.addProduct(p1, 100);
        double m = m1.calculateMeal();
        assertEquals(0d, m, 0.001);
    }

}
