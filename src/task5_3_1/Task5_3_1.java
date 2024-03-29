package task5_3_1;

import java.io.IOException;

public class Task5_3_1 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
                    1. Поймайте в дебаггере метод String.equals\s

                Решение:\s""");

        String str1 = "Hello";
        String str2 = "World";

        System.out.println("Результат сравнения строк: " + str1.equals(str2));
        System.out.println("Метод String.equals пойман в дебаггере на строке № 19.");
    }
}