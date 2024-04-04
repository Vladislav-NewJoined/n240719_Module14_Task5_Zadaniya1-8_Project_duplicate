package task6_3_1;

import java.util.Arrays;
import java.util.Comparator;

public class Task6_3_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 6. Основные структуры данных. Задание №3:\s
                    1. Нужно отсортировать случайный список чисел.
                       Числа нужно отсортировать следующим образом:
                       Четные идут в начале по возрастанию, нечетные по убыванию в конце списка.\s

                Решение:\s""");

        // Предположим, что массив уже создан и заполнен случайными числами
        Integer[] array = {-5, 10, 3, -2, 7, -8, 6, 1, -4, 9, -1, 2, -6, 4, -3, 8, -10, 5, -9, 0};

        // Определяем компаратор для сортировки четных чисел по возрастанию
        Comparator<Integer> ascendingComparator = Comparator.comparingInt(Integer::intValue);

        // Определяем компаратор для сортировки нечетных чисел по убыванию
        Comparator<Integer> descendingComparator = (a, b) -> Integer.compare(b.intValue(), a.intValue());

        // Сортируем массив
        Arrays.sort(array, (a, b) -> {
            if (a % 2 == 0 && b % 2 == 0) {
                return ascendingComparator.compare(a, b);
            } else if (a % 2 == 0) {
                return -1;
            } else if (b % 2 == 0) {
                return 1;
            } else {
                return descendingComparator.compare(a, b);
            }
        });

        // Выводим отсортированный массив
        System.out.println("Отсортированный массив:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}




//// ПРИМЕР 2 _сделано, но не отсортировано
//import java.util.ArrayList;
//import java.util.Random;
//import java.util.Comparator;
//
//public class Task6_3_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 6. Основные структуры данных. Задание №3:\s
//                    1. Нужно отсортировать случайный список чисел.
//                       Числа нужно отсортировать следующим образом:
//                       Четные идут в начале по возрастанию, нечетные по убыванию в конце списка.\s
//
//                Решение:\s""");
//
//        System.out.println("""
//                Some text""");
//
//        // Создаем список для хранения случайных чисел
//        ArrayList<Integer> randomList = new ArrayList<>();
//
//        // Генерируем случайные числа и добавляем их в список
//        Random random = new Random();
//        for (int i = 0; i < 20; i++) {
//            // Генерируем случайное число от -100 до 100
//            int randomNumber = random.nextInt(201) - 100;
//            randomList.add(randomNumber);
//        }
//
//        // Преобразуем список в массив типа int
//        int[] array = new int[randomList.size()];
//        for (int i = 0; i < randomList.size(); i++) {
//            array[i] = randomList.get(i);
//        }
//
//        // Выводим полученный массив
//        System.out.println("Случайный массив чисел:");
//        for (int num : array) {
//            System.out.print(num + " ");
//        }
//    }
//}
//// КОНЕЦ ПРИМЕРА 2
