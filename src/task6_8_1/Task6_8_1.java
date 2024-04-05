package task6_8_1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task6_8_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 6. Основные структуры данных. Задание №7:\s
                    1. Вам предстоит сделать онлайн магазин, без графического интерфейса, обладающего
                       следующими функциями:
                           Каталог товаров
                           Авторизация почта и пароль (данные возможно хранить в простом незашифрованном виде)
                           Реализовать вывод каталога
                           Добавление товара по ID
                           Возможность очистки корзины\s

                Решение:\s""");

        // Создаем список для хранения смартфонов
        List<Smartphone> catalog = new ArrayList<>();

        // Добавляем шесть случайных смартфонов в каталог
        catalog.add(new Smartphone("Samsung Galaxy S21", 25000, 8));
        catalog.add(new Smartphone("iPhone 12", 40000, 11));
        catalog.add(new Smartphone("Google Pixel 5", 35000, 17));
        catalog.add(new Smartphone("OnePlus 9 Pro", 42000, 5));
        catalog.add(new Smartphone("Xiaomi Mi 11", 30000, 3));
        catalog.add(new Smartphone("Huawei P40 Pro", 28000, 20));

        // Выводим каталог в консоль
        System.out.println("Каталог товаров:");
        for (Smartphone phone : catalog) {
            System.out.println(phone);
        }

        // Сохраняем каталог в файл
        saveCatalogToFile(catalog, "src/task6_8_1/ProductsCatalog.txt");

        // Запрашиваем у пользователя электронную почту и пароль
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите вашу электронную почту: ");
        String email = scanner.nextLine();
        System.out.print("Введите ваш пароль: ");
        String password = scanner.nextLine();

        // Сохраняем электронную почту и пароль в файл
        saveCredentialsToFile(email, password, "src/task6_8_1/CustomersID.txt");
    }

    // Метод для сохранения каталога в файл
    private static void saveCatalogToFile(List<Smartphone> catalog, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Smartphone phone : catalog) {
                writer.write(phone.toString() + "\n");
            }
            System.out.println("Каталог товаров сохранен в файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении каталога в файл: " + e.getMessage());
        }
    }

    // Метод для сохранения электронной почты и пароля в файл
    private static void saveCredentialsToFile(String email, String password, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Email: " + email + "\n");
            writer.write("Password: " + password + "\n");
            System.out.println("Электронная почта и пароль сохранены в файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении электронной почты и пароля в файл: " + e.getMessage());
        }
    }
}

// Класс представляющий смартфон
class Smartphone {
    private String name;
    private int price;
    private int quantity;
    private String id;

    public Smartphone(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.id = generateId();
    }

    // Метод для генерации уникального идентификатора
    private String generateId() {
        return String.format("%04d", (int) (Math.random() * 10000));
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Название: " + name + ", Цена: " + price + " руб., Количество: " + quantity;

    }
}
