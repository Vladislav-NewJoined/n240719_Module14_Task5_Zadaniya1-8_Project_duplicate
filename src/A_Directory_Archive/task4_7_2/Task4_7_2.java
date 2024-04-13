package task4_7_2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task4_7_2 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №7:\s
                    2. Пользователь вводит число. Если произошла ошибка ввода, выведите пользователю
                       сообщение о том, что ввод некорректный\s

                Решение:\s""");

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите число: ");
            int number = scanner.nextInt();
            System.out.println("Вы ввели число: " + number);
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("Некорректный ввод. Введите целое число.");
        } finally {
            scanner.close();
        }
    }
}