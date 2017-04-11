/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.renatakornas;

/**
 *
 * @author R
 */
public class Product {

    private final String name;
    private final double calorific;
    
    
    public Product(String name, double calorific) {
        this.name = name;
        this.calorific = calorific;
        }

    public String getName() {
        return name;
    }

    public double getCalorific() {
        return calorific;
    }
}
