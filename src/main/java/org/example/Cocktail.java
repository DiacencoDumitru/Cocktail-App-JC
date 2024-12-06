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

    // Конструктор по умолчанию (устанавливает стандартные значения при создании нового объекта данного класса)
    public Cocktail() {
        this.numIngredients = 0;
        this.alcoholContent = new ArrayList<>();
        this.price = 0.0;
        cocktailCount++;
    }

    public Cocktail(int numIngredients, List<Double> alcoholContent, double price) {
        this.setNumIngredients(numIngredients);
        this.setAlcoholContent(alcoholContent);
        this.setPrice(price);
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
        if (numIngredients <= 0) {
            throw new IllegalArgumentException("Количество ингредиентов должно быть больше 0");
        }
        this.numIngredients = numIngredients;
    }

    public List<Double> getAlcoholContent() {
        return alcoholContent;
    }

    // Во всех сеттерах выполняется проверка корректности данных, чтобы избежать некорректной работы приложения
    public void setAlcoholContent(List<Double> alcoholContent) {
        if (alcoholContent == null || alcoholContent.isEmpty()) {
            throw new IllegalArgumentException("Содержание алкоголя не должно быть пустым");
        }
        for (Double alcohol : alcoholContent) {
            if (alcohol < 0 || alcohol > 100) {
                throw new IllegalArgumentException("Содержание алкоголя должно быть в диапазоне от 0 до 100");
            }
        }
        this.alcoholContent = alcoholContent;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0");
        }
        this.price = price;
    }

    public static int getCocktailCount() {
        return cocktailCount;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        if (mass <= 0) {
            throw new IllegalArgumentException("Масса должна быть больше 0");
        }
        this.mass = mass;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Цвет не должен быть пустым");
        }
        this.color = color;
    }

    public void printRecipe() {
        System.out.println("Количество ингредиентов: " + numIngredients);
        System.out.println("Содержание алкоголя: " + alcoholContent);
        System.out.println("Цена: " + price + " MDL");
        System.out.println("Масса: " + mass);
        System.out.println("Цвет: " + color);
    }

    public void inputCocktail() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество ингредиентов:");
        this.setNumIngredients(scanner.nextInt());
        this.alcoholContent = new ArrayList<>();
        for (int i = 0; i < numIngredients; i++) {
            System.out.println("Введите содержание алкоголя для ингредиента " + (i + 1) + ": ");
            this.alcoholContent.add(scanner.nextDouble());
        }
        System.out.print("Введите цену: ");
        this.setPrice(scanner.nextDouble());
        System.out.print("Введите массу: ");
        this.setMass(scanner.nextDouble());
        System.out.print("Введите цвет: ");
        this.setColor(scanner.next());
    }

    public void randomizeCocktail() {
        Random random = new Random();
        this.setNumIngredients(random.nextInt(5) + 1);
        this.alcoholContent = new ArrayList<>();
        for (int i = 0; i < numIngredients; i++) {
            this.alcoholContent.add(random.nextDouble() * 50);
        }
        this.setPrice(random.nextDouble() * 100);
        this.setMass(random.nextDouble() * 500);
        this.setColor("Цвет" + random.nextInt(5));
    }

    public boolean compareIngredients(Cocktail other) {
        return this.numIngredients == other.numIngredients;
    }

    public double calculateAverageAlcohol() {
        return alcoholContent.stream().mapToDouble(a -> a).average().orElse(0.0);
    }

    public static void client(double wallet, Cocktail cocktail) {
        int canBuy = (int) (wallet / cocktail.price);
        System.out.println("Клиент может купить " + canBuy + " коктейлей.");
        if (cocktail.calculateAverageAlcohol() > 40) {
            System.out.println("Это крепко! Я справлюсь только с " + canBuy);
        } else {
            System.out.println("Вкусно! Я возьму " + canBuy);
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
