package task7_5_1;

import java.util.ArrayList;

public class Task7_5_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 7. Взаимодействие с API. Задание №5:\s
                    1. У вас есть список моделей смартфонов, iPhone 6 S", "Lumia 950", "Samsung Galaxy S 6", "LG G 4",
                       "Nexus 7", вам нужно вывести в консоль названия двух моделей пропустив при этом первый бренд
                       при помощи  метода skip и limit.\s

                Решение:\s""");

        ArrayList<String> catalog = new ArrayList<>();
        catalog.add("iPhone 6 S");
        catalog.add("Lumia 950");
        catalog.add("Samsung Galaxy S 6");
        catalog.add("LG G 4");
        catalog.add("Nexus 7");

        System.out.println("Исходный массив: ");
        for (int i = 0; i < catalog.size(); i++) {
            System.out.println(catalog.get(i));
        }

        System.out.println(); // Это перенос строки
        System.out.println("Полученный массив: ");
        catalog.stream().skip(1).limit(2).forEach(System.out::println);
    }
}