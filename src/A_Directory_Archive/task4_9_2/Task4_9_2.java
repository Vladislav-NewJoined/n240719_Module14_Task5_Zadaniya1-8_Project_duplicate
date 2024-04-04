package task4_9_2;

public class Task4_9_2 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №9:\s
                    2. Создайте проверяемое и непроверяемое исключение\s

                Решение:\s""");

        try {
            throw new MyCheckedException("Это мое проверяемое исключение");
        } catch (MyCheckedException e) {
            System.out.println("Поймано проверяемое исключение: " + e.getMessage());
        }

        // Можно выбросить непроверяемое исключение без try-catch блока
        throw new MyUncheckedException("Это мое непроверяемое исключение");
    }
}

// Проверяемое исключение
class MyCheckedException extends Exception {
    public MyCheckedException(String message) {
        super(message);
    }
}

// Непроверяемое исключение
class MyUncheckedException extends RuntimeException {
    public MyUncheckedException(String message) {
        super(message);
    }
}