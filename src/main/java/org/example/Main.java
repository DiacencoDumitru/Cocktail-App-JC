package org.example;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        Cocktail cocktail1 = new Cocktail();
        Cocktail cocktail2 = new Cocktail(3, Arrays.asList(20.0, 30.0, 40.0), 50.0);
        Cocktail cocktail3 = new Cocktail(cocktail2);

        cocktail1.printRecipe();
        System.out.println("Average alcohol: " + cocktail1.calculateAverageAlcohol() + "\n");
        cocktail2.printRecipe();
        System.out.println("Average alcohol: " + cocktail2.calculateAverageAlcohol() + "\n");

        cocktail1.randomizeCocktail();
        cocktail1.printRecipe();
        System.out.println("\n");
        cocktail3.printRecipe();
        System.out.println("\n");

        System.out.println("Cocktail1 and Cocktail2 have the same number of ingredients: " + cocktail1.compareIngredients(cocktail2));

        Cocktail.client(100, cocktail2);

        cocktail1.saveToFile("cocktail1.txt");
        cocktail2.saveToFile("cocktail2.txt");
        cocktail3.saveToFile("cocktail3.txt");

        System.out.println("Total cocktails created: " + Cocktail.getCocktailCount());
    }
}