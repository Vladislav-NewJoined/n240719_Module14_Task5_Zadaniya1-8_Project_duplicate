package task7_1_1;

import java.util.Scanner;

public class Task7_1_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 7. Взаимодействие с API. Задание №1:\s
                    1. Задание в рамках интерфейса java нужно создать калькулятор выполняющий 4 основных
                       арифметических функций, сложение, вычитание, деление, умножение, а также осуществить
                       вывод на результатов на экран.\s

                Решение:\s""");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        double num1 = scanner.nextDouble();

        System.out.print("Введите второе число: ");
        double num2 = scanner.nextDouble();

        System.out.println("Выберите операцию:");
        System.out.println("1. Сложение (+)");
        System.out.println("2. Вычитание (-)");
        System.out.println("3. Умножение (*)");
        System.out.println("4. Деление (/)");

        System.out.print("Введите номер операции: ");
        int operation = scanner.nextInt();

        double result;

        switch (operation) {
            case 1:
                result = num1 + num2;
                System.out.println("Результат сложения: " + result);
                break;
            case 2:
                result = num1 - num2;
                System.out.println("Результат вычитания: " + result);
                break;
            case 3:
                result = num1 * num2;
                System.out.println("Результат умножения: " + result);
                break;
            case 4:
                if (num2 == 0) {
                    System.out.println("Ошибка: деление на ноль.");
                } else {
                    result = num1 / num2;
                    System.out.println("Результат деления: " + result);
                }
                break;
            default:
                System.out.println("Ошибка: неверно выбрана операция.");
        }
    }
}