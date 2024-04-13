package task5_3_2;

import java.io.IOException;

public class Task5_3_2 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
                    2. Поймайте в дебаггере метод StringBuilder.equals\s

                Решение:\s""");

        String str1 = "Hello";
        String str2 = new String("Hello");
        StringBuilder sb1 = new StringBuilder("Hello");
        StringBuilder sb2 = new StringBuilder("Hello");

        System.out.println("Результат сравнения строк (этот метод здесь представлен просто для сравнения): " + str1.equals(str2));
        System.out.println("Результат сравнения StringBuilder: " + sb1.equals(sb2));
        System.out.println("Метод StringBuilder.equals пойман в дебаггере на строке № 20.");
    }
}