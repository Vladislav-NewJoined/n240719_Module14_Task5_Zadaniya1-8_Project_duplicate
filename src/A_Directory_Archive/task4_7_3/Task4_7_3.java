package task4_7_3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task4_7_3 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №7:\s
                    3. Пользователь вводит число. Если произошла ошибка ввода, дайте пользователю
                       ввести еще раз: так пока он не введёт нормально (подсказка: while)\s

                Решение:\s""");

        Scanner scanner = new Scanner(System.in);
        int number = 0;
        boolean inputCorrect = false;

        while (!inputCorrect) {
            try {
                System.out.print("Введите число: ");
                number = scanner.nextInt();
                inputCorrect = true;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Пожалуйста, введите число заново.");
                scanner.next(); // Очистить ввод
            }
        }

        System.out.println("Введено число: " + number);
    }
}