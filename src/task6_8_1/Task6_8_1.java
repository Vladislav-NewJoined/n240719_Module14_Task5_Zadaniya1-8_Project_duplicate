package task6_8_1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task6_8_1 {

    // Класс, представляющий товар
    static class Product {
        private String name;
        private double price;
        private int quantity;

        public Product(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }
    }

    // Каталог товаров
    private static Map<String, Product> productsCatalog = new HashMap<>();

    // Инициализация каталога товаров
    static {
        productsCatalog.put("0001", new Product("Smartphone1", 25000, 8));
        productsCatalog.put("0002", new Product("Smartphone2", 30000, 11));
        productsCatalog.put("0003", new Product("Smartphone3", 40000, 17));
        productsCatalog.put("0004", new Product("Smartphone4", 15000, 5));
        productsCatalog.put("0005", new Product("Smartphone5", 20000, 3));
        productsCatalog.put("0006", new Product("Smartphone6", 45000, 20));
    }

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

        // Вывод каталога товаров
        System.out.println("Каталог товаров:");
        for (Map.Entry<String, Product> entry : productsCatalog.entrySet()) {
            Product product = entry.getValue();
            System.out.println("ID: " + entry.getKey() + ", Название: " + product.getName() + ", Цена: " + product.getPrice() + ", Количество: " + product.getQuantity());
        }

        // Добавление товаров в корзину и расчет суммы к оплате
        addItemsToCart();
    }

    // Метод для добавления товаров в корзину и расчета суммы к оплате
    private static void addItemsToCart() {
        Scanner scanner = new Scanner(System.in);
        double totalSum = 0;

        while (true) {
            System.out.print("Введите ID товара и количество через запятую (например, '0001,2'), или введите 'end' для завершения: ");
            String input = scanner.nextLine();

            // Проверка на завершение ввода
            if (input.equals("end")) {
                break;
            }

            // Разделение введенной строки на ID и количество
            String[] parts = input.split(",");

            // Проверка на корректность введенных данных
            if (parts.length != 2) {
                System.out.println("Ошибка ввода. Попробуйте еще раз.");
                continue;
            }

            // Извлечение ID и количества товара
            String productId = parts[0].trim();
            int quantity = Integer.parseInt(parts[1].trim());

            // Поиск товара по ID в каталоге
            if (!productsCatalog.containsKey(productId)) {
                System.out.println("Товар с указанным ID не найден.");
                continue;
            }

            // Проверка наличия товара в достаточном количестве
            int availableQuantity = productsCatalog.get(productId).getQuantity();
            if (quantity > availableQuantity) {
                System.out.println("Недостаточное количество товара.");
                continue;
            }

            // Добавление товара в корзину и расчет суммы к оплате
            double itemPrice = productsCatalog.get(productId).getPrice();
            totalSum += itemPrice * quantity;

            // Вывод сообщения о добавлении товара в корзину
            System.out.println("Товар добавлен в корзину.");
        }

        // Вывод итоговой суммы к оплате
        System.out.println("Сумма к оплате: " + totalSum);

        // Сохранение суммы к оплате в файл
        try {
            FileWriter writer = new FileWriter("src/task6_8_1/TotalSum.txt");
            writer.write("Сумма к оплате: " + totalSum);
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}




//// ПРИМЕР 2
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Task6_8_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 6. Основные структуры данных. Задание №7:\s
//                    1. Вам предстоит сделать онлайн магазин, без графического интерфейса, обладающего
//                       следующими функциями:
//                           Каталог товаров
//                           Авторизация почта и пароль (данные возможно хранить в простом незашифрованном виде)
//                           Реализовать вывод каталога
//                           Добавление товара по ID
//                           Возможность очистки корзины\s
//
//                Решение:\s""");
//
//        // Создаем список для хранения смартфонов
//        List<Smartphone> catalog = new ArrayList<>();
//
//        // Добавляем шесть случайных смартфонов в каталог
//        catalog.add(new Smartphone("Samsung Galaxy S21", 25000, 8));
//        catalog.add(new Smartphone("iPhone 12", 40000, 11));
//        catalog.add(new Smartphone("Google Pixel 5", 35000, 17));
//        catalog.add(new Smartphone("OnePlus 9 Pro", 42000, 5));
//        catalog.add(new Smartphone("Xiaomi Mi 11", 30000, 3));
//        catalog.add(new Smartphone("Huawei P40 Pro", 28000, 20));
//
//        // Выводим каталог в консоль
//        System.out.println("Каталог товаров:");
//        for (Smartphone phone : catalog) {
//            System.out.println(phone);
//        }
//
//        // Сохраняем каталог в файл
//        saveCatalogToFile(catalog, "src/task6_8_1/ProductsCatalog.txt");
//
//        // Запрашиваем у пользователя электронную почту и пароль
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Введите вашу электронную почту: ");
//        String email = scanner.nextLine();
//        System.out.print("Введите ваш пароль: ");
//        String password = scanner.nextLine();
//
//        // Сохраняем электронную почту и пароль в файл
//        saveCredentialsToFile(email, password, "src/task6_8_1/CustomersID.txt");
//    }
//
//    // Метод для сохранения каталога в файл
//    private static void saveCatalogToFile(List<Smartphone> catalog, String filePath) {
//        try (FileWriter writer = new FileWriter(filePath)) {
//            for (Smartphone phone : catalog) {
//                writer.write(phone.toString() + "\n");
//            }
//            System.out.println("Каталог товаров сохранен в файл: " + filePath);
//        } catch (IOException e) {
//            System.out.println("Ошибка при сохранении каталога в файл: " + e.getMessage());
//        }
//    }
//
//    // Метод для сохранения электронной почты и пароля в файл
//    private static void saveCredentialsToFile(String email, String password, String filePath) {
//        try (FileWriter writer = new FileWriter(filePath)) {
//            writer.write("Email: " + email + "\n");
//            writer.write("Password: " + password + "\n");
//            System.out.println("Электронная почта и пароль сохранены в файл: " + filePath);
//        } catch (IOException e) {
//            System.out.println("Ошибка при сохранении электронной почты и пароля в файл: " + e.getMessage());
//        }
//    }
//}
//
//// Класс представляющий смартфон
//class Smartphone {
//    private String name;
//    private int price;
//    private int quantity;
//    private String id;
//
//    public Smartphone(String name, int price, int quantity) {
//        this.name = name;
//        this.price = price;
//        this.quantity = quantity;
//        this.id = generateId();
//    }
//
//    // Метод для генерации уникального идентификатора
//    private String generateId() {
//        return String.format("%04d", (int) (Math.random() * 10000));
//    }
//
//    @Override
//    public String toString() {
//        return "ID: " + id + ", Название: " + name + ", Цена: " + price + " руб., Количество: " + quantity;
//
//    }
//}
//// КОНЕЦ ПРИМЕРА 2