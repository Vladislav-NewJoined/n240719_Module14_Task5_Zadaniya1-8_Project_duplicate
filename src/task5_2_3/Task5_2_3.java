package task5_2_3;

import java.io.IOException;

public class Task5_2_3 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №2:\s
                    3. Поймайте в дебаггере метод toString у класса String\s

                Решение:\s""");

        CustomString customString = new CustomString();
        System.out.println(customString.toString());
        System.out.println();
        System.out.println("Метод ToString пойман в дебаггере на строке № 25");
    }
}

class CustomString {

    @Override
    public String toString() {
        return "Это тестовая строка.";
    }

    public static void main(String[] args) {
        CustomString customString = new CustomString();
        System.out.println(customString.toString());
    }
}


