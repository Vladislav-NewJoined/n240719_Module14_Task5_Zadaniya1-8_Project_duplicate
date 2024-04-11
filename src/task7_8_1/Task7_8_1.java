package task7_8_1;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Task7_8_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 7. Взаимодействие с API. Задание №8:\s
                    1. Вам необходимо из массива “-10 – 10” взять числа меньше 10, которые получатся в результате
                       функции x2 – 5x – 10 и вывести квадрат максимального.\s

                Решение:\s""");

        // Шаг 1: Получаем массив чисел, которые получатся в результате функции x^2 – 5x – 10
        int[] array1 = IntStream.rangeClosed(-10, 10)
                .map(x -> x * x - 5 * x - 10)
                .toArray();

        // Выводим массив array1 в консоль
        System.out.println("Шаг 1: Массив чисел, полученных из функции x^2 – 5x – 10, " +
                "\nгде х - текущий элемент массива от -10 до 10:");
        System.out.println(Arrays.toString(array1));

        // Шаг 2: Фильтруем числа из array1, которые меньше 10
        int[] array2 = Arrays.stream(array1)
                .filter(x -> x < 10)
                .toArray();

        // Выводим массив array2 в консоль
        System.out.println("\nШаг 2: Массив чисел array2, сформированный из чисел из массива array1, меньших 10:");
        System.out.println(Arrays.toString(array2));

        // Шаг 3: Находим максимальное число в массиве array2 и выводим квадрат этого числа
        int maxNumber = Arrays.stream(array2)
                .max()
                .orElse(0);
        int squareOfMax = maxNumber * maxNumber;

        // Выводим квадрат максимального числа в консоль
        System.out.println("\nШаг 3: Квадрат максимального числа из массива array2: " + squareOfMax);
    }
}