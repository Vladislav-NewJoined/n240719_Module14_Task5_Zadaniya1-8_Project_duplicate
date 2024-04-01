package task5_3_3;

import java.io.IOException;

public class Task5_3_3 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
                    3. Поймайте в дебаггере метод Object.equals\s

                Решение:\s""");

        Object obj1 = new Object();
        Object obj2 = new Object();

        Object obj3 = new Object();
        Object obj4 = obj3;

        System.out.println("Результат сравнения объектов obj1 и obj2: " + obj1.equals(obj2));
        System.out.println("Метод Object.equals пойман в дебаггере на строке №20.");

        System.out.println("\nРезультат сравнения объектов obj3 и obj4: " + obj3.equals(obj4));
        System.out.println("Метод Object.equals пойман в дебаггере на строке №23.");
    }
}