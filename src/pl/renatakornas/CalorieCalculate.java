package pl.renatakornas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author rkornas
 */
public class CalorieCalculate {

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException, Exception {

//////////////////// Komponujemy swoj posilek
// pobieramy  z ProductRepository wybrane produkty        
        Product porrige = ProductRepository.getProduct("owsianka");
        Product banana = ProductRepository.getProduct("banan");
        Product aaa = ProductRepository.getProduct("aaa");

        Meal yourMeal = new Meal();

// dodawanie porcji produktow do posilku
        yourMeal.addProduct(porrige, 100);
        yourMeal.addProduct(banana, 150);
        yourMeal.addProduct(aaa, 100);

// kalkulacja kalorii
        yourMeal.calculateMeal();

// wyswietlenie wynikow
        yourMeal.showMealCalories();
    }
}
