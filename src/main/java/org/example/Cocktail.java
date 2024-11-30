package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cocktail {
    private int numIngredients;
    private List<Double> alcoholContent;
    private double price;
    private static int cocktailCount = 0;

    private double mass;
    private String color;

    public Cocktail() {
        this.numIngredients = 0;
        this.alcoholContent = new ArrayList<>();
        this.alcoholContent = new ArrayList<>();
        this.price = 0.0;
        cocktailCount++;
    }

    public Cocktail(int numIngredients, List<Double> alcoholContent, double price) {
        this.numIngredients = numIngredients;
        this.alcoholContent = alcoholContent;
        this.price = price;
    }

    public Cocktail(Cocktail other) {
        this.numIngredients = other.numIngredients;
        this.alcoholContent = new ArrayList<>(other.getAlcoholContent());
        this.price = other.price;
        this.mass = other.mass;
        this.color = other.color;
        cocktailCount++;
    }

    public Cocktail(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        this.numIngredients = Integer.parseInt(reader.readLine());
        this.alcoholContent = new ArrayList<>();
        for (int i = 0; i < this.numIngredients; i++) {
            this.alcoholContent.add(Double.parseDouble(reader.readLine()));
        }
        this.price = Double.parseDouble(reader.readLine());
        this.mass = Double.parseDouble(reader.readLine());
        this.color = reader.readLine();
        cocktailCount++;
        reader.close();
    }

    public int getNumIngredients() {
        return numIngredients;
    }

    public void setNumIngredients(int numIngredients) {
        this.numIngredients = numIngredients;
    }

    public List<Double> getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(List<Double> alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static int getCocktailCount() {
        return cocktailCount;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void printRecipe() {
        System.out.println("Number of ingredients: " + numIngredients);
        System.out.println("Alcohol content: " + alcoholContent);
        System.out.println("Price: " + price + " MDL");
        System.out.println("Mass: " + mass);
        System.out.println("Color: " + color);
    }

    public void inputCocktail() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of ingredients");
        this.numIngredients = scanner.nextInt();
        this.alcoholContent = new ArrayList<>();
        for (int i = 0; i < numIngredients; i++) {
            System.out.println("Enter alcohol content of ingredient " + (i + 1) + ": ");
            this.alcoholContent.add(scanner.nextDouble());

        }
        System.out.print("Enter price: ");
        this.price = scanner.nextDouble();
        System.out.print("Enter mass: ");
        this.mass = scanner.nextDouble();
        System.out.print("Enter color: ");
        this.color = scanner.next();
    }

    public void randomizeCocktail() {
        Random random = new Random();
        this.numIngredients = random.nextInt(5) + 1; // от 1 до 5
        this.alcoholContent = new ArrayList<>();
        for (int i = 0; i < numIngredients; i++) {
            this.alcoholContent.add(random.nextDouble() * 50); // процент алкоголя
        }
        this.price = random.nextDouble() * 100;
        this.mass = random.nextDouble() * 500;
        this.color = "Color" + random.nextInt(5);
    }

    public boolean compareIngredients(Cocktail other) {
        return this.numIngredients == other.numIngredients;
    }

    public double calculateAverageAlcohol() {
        return alcoholContent.stream().mapToDouble(a -> a).average().orElse(0.0);
    }

    public static void client(double wallet, Cocktail cocktail) {
        int canBuy = (int) (wallet / cocktail.price);
        System.out.println("Client can buy " + canBuy + " cocktails.");
        if (cocktail.calculateAverageAlcohol() > 40) {
            System.out.println("That's strong! I can handle only" + canBuy);
        } else {
            System.out.println("Delicious! I'll have " + canBuy);
        }
    }

    public void saveToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(numIngredients + "\n");
        for (Double alcohol : alcoholContent) {
            writer.write(alcohol + "\n");
        }
        writer.write(price + "\n");
        writer.write(mass + "\n");
        writer.write(color + "\n");
        writer.close();
    }
}
