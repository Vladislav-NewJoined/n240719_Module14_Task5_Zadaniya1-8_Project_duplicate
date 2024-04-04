package task4_9_4;

import java.util.Random;

public class Task4_9_4 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №9:\s
                    4. Сделайте метод, который возвращает из этих массивов случайный элемент; иногда
                       из первого, иногда из второго. Какой тип данных будет возвращать этот метод?\s

                Решение:\s""");

        // Создаём массив проверяемых исключений
        Exception[] checkedExceptions = new Exception[10];

        // Заполняем массив проверяемых исключений
        for (int i = 0; i < checkedExceptions.length; i++) {
            checkedExceptions[i] = new Exception("Checked Exception " + i);
        }

        // Создаём массив непроверяемых исключений
        RuntimeException[] uncheckedExceptions = new RuntimeException[10];

        // Заполняем массив непроверяемых исключений
        for (int i = 0; i < uncheckedExceptions.length; i++) {
            uncheckedExceptions[i] = new RuntimeException("Unchecked Exception " + i);
        }

        // Выводим информацию об исключениях
        for (Exception e : checkedExceptions) {
            System.out.println("Checked Exception: " + e.getMessage());
        }

        for (RuntimeException e : uncheckedExceptions) {
            System.out.println("Unchecked Exception: " + e.getMessage());
        }

        // Получаем случайный элемент из массивов
        System.out.println("Случайный элемент: " + getRandomElement(checkedExceptions, uncheckedExceptions));
    }

    public static Object getRandomElement(Exception[] checkedExceptions, RuntimeException[] uncheckedExceptions) {
        Random random = new Random();
        int choice = random.nextInt(2); // Генерируем случайное число 0 или 1

        if (choice == 0) {
            int index = random.nextInt(checkedExceptions.length);
            return checkedExceptions[index];
        } else {
            int index = random.nextInt(uncheckedExceptions.length);
            return uncheckedExceptions[index];
        }
    }
}