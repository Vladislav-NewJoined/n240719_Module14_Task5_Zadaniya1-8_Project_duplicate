package a_shablony;

import java.util.Scanner;

public class Krestiki_Noliki_prostoi {
    public static void main(String[] args) {
        System.out.println("""
            Задание:\s
            Модуль 3. Основы ООП. Задание №10:\s
                1. Напишите сами крестики-нолики, не подглядывая в наш код.\s

            Решение:\s""");

        System.out.println("Пишем калькулятор с обработкой исключений: ");

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите первое число: ");
            double num1 = scanner.nextDouble();

            System.out.println("Введите знак операции (+, -, /, *): ");
            char operation = scanner.next().charAt(0);

            System.out.println("Введите второе число: ");
            double num2 = scanner.nextDouble();

            double result = 0;

            switch(operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '/':
                    if(num2 != 0) {
                        result = num1 / num2;
                    } else {
                        throw new ArithmeticException("Ошибка: деление на ноль!");
                    }
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                default:
                    throw new IllegalArgumentException("Неверная операция!");
            }

            System.out.println("Результат: " + result);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}