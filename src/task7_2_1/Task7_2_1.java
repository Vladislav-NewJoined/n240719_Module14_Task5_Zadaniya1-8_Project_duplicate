package task7_2_1;

import java.util.function.Function;

public class Task7_2_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 7. Взаимодействие с API. Задание №2:\s
                    1. Написать лямбда выражение, которое принимает на вход число и возвращает значение “Положительное
                       число”, “Отрицательное число” или  “Ноль”. Используем функциональный интерфейс Function.\s

                Решение:\s""");

        Function<Integer, String> function = i -> {
            String result = "Ноль";
            if (i > 0) {
                result = "Положительное число";
            } else if (i < 0) {
                result = "Отрицательное число";
            }
            return result;
        };

        System.out.println(function.apply(-9));
        System.out.println(function.apply(0));
        System.out.println(function.apply(99));
    }
}