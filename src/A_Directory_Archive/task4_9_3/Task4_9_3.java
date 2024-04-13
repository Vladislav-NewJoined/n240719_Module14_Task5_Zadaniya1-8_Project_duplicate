package task4_9_3;

public class Task4_9_3 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №9:\s
                    3. Создайте 10 checked и 10 unchecked исключений. Сделайте два массива с ними\s

                Решение:\s""");

        // Создание массива проверяемых исключений
        Exception[] checkedExceptions = new Exception[10];

        // Заполнение массива проверяемых исключений
        for (int i = 0; i < checkedExceptions.length; i++) {
            checkedExceptions[i] = new Exception("Checked Exception " + i);
        }

        // Создание массива непроверяемых исключений
        RuntimeException[] uncheckedExceptions = new RuntimeException[10];

        // Заполнение массива непроверяемых исключений
        for (int i = 0; i < uncheckedExceptions.length; i++) {
            uncheckedExceptions[i] = new RuntimeException("Unchecked Exception " + i);
        }

        // Вывод информации об исключениях
        for (Exception e : checkedExceptions) {
            System.out.println("Checked Exception: " + e.getMessage());
        }

        for (RuntimeException e : uncheckedExceptions) {
            System.out.println("Unchecked Exception: " + e.getMessage());
        }
    }
}
