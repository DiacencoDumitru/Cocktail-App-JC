package org.example;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        Cocktail cocktail1 = new Cocktail();
        Cocktail cocktail2 = new Cocktail(3, Arrays.asList(20.0, 30.0, 40.0), 50.0);
        Cocktail cocktail3 = new Cocktail(cocktail2);

        // Получаем значение из статического метода класса Cocktail
        int staticMethodCocktail = Cocktail.getCocktailCount();
        System.out.println("Количество коктейлей (из статического метода): " + staticMethodCocktail);

        // Создаём массив и добавляем реализацию значений класса по умолчанию для каждого из 6
        Cocktail[] cocktails = new Cocktail[6];
        for (int i = 0; i < cocktails.length; i++) {
            cocktails[i] = new Cocktail();
        }
        System.out.println("Массив коктейлей: " + Arrays.toString(cocktails));

        cocktail1.printRecipe();
        System.out.println("Среднее содержание алкоголя: " + cocktail1.calculateAverageAlcohol() + "\n");
        cocktail2.printRecipe();
        System.out.println("Среднее содержание алкоголя: " + cocktail2.calculateAverageAlcohol() + "\n");

        cocktail1.randomizeCocktail();
        cocktail1.printRecipe();
        System.out.println("\n");
        cocktail3.printRecipe();
        System.out.println("\n");

        System.out.println("Коктейль 1 и Коктейль 2 имеют одинаковое количество ингредиентов: " + cocktail1.compareIngredients(cocktail2));

        Cocktail.client(100, cocktail2);

        cocktail1.saveToFile("cocktail1.txt");
        cocktail2.saveToFile("cocktail2.txt");
        cocktail3.saveToFile("cocktail3.txt");

        System.out.println("Общее количество созданных коктейлей: " + Cocktail.getCocktailCount());
    }
}