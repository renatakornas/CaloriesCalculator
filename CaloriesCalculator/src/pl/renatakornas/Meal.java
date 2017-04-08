package pl.renatakornas;

import java.util.ArrayList;

/**
 *
 * @author rkornas
 */
public class Meal { // klasa reprezentujaca caly posilek

    public class MealItem { // klasa reprezentujaca porcje produktu - czesc posilku

        Product product;
        double weight;

        public MealItem(Product product, double weight) { // konstruktor 
            this.product = product;
            this.weight = weight;
        }
    }

    ArrayList<MealItem> itemsTable = new ArrayList<MealItem>();

    public void addProduct(Product product, double weight) {
        MealItem mealPart = new MealItem(product, weight);
        itemsTable.add(mealPart);
    }

    private double calcMealPart(MealItem mealPart) { // liczy kalorie pojedynczej porcji produktu
        double calories = mealPart.weight * mealPart.product.getCalorific() / 100;
        return calories;
    }

    public double calculateMeal() {
        double calories = 0;
        for (MealItem mealPart : itemsTable) {
            calories += calcMealPart(mealPart);
        }
        return calories;
    }

    public void showMealCalories() {

        double allKcal = 0;
        for (MealItem mealPart : itemsTable) {
            double kcal = 0;
            kcal += calcMealPart(mealPart);
            allKcal += kcal;
            String name = mealPart.product.getName();
            if (mealPart.product.getCalorific() == 0) {
                System.out.println(mealPart.weight + " g. produktu \"" + name + "\" - " + kcal + " kcal, lub brak w bazie produktu o nazwie " + name);
            } else {
                System.out.println(mealPart.weight + " g. produktu \"" + name + "\" - " + kcal + " kcal");
            }
        }
        System.out.println("_________________________");
        System.out.println("Razem: " + allKcal);
    }

}
