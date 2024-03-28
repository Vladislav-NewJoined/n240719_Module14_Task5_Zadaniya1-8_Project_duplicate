package task5_2_5;

import java.io.IOException;

public class Task5_2_5 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №2:\s
                    5. Поймайте в дебаггере метод toString у класса StringBuilder\s

                Решение:\s""");

        StringBuilder sb = new StringBuilder();
        System.out.println(sb.toString());
        System.out.println();
        System.out.println("Метод toString пойман в дебаггере на строке № 25.");
    }
}

class StringBuilder {

    @Override
    public String toString() {
        return "Это тестовый класс StringBuilder.";
    }
}