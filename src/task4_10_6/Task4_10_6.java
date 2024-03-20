package task4_10_6;

public class Task4_10_6 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №10:\s
                    5. Создайте массив nullов\s

                Решение:\s""");

        Integer[] array = new Integer[5]; // Создаем массив из пяти элементов

        int sumOfNulls = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null; // Присваиваем каждому элементу массива значение null
            sumOfNulls++; // Увеличиваем сумму null'ов на 1
        }

        // Выводим сумму null'ов на экран
        System.out.println("Сумма null'ов в массиве: " + sumOfNulls);
    }
}