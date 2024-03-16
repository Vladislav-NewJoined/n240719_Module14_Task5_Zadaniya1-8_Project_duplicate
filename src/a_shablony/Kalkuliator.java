package a_shablony;

import java.util.Scanner;

public class Kalkuliator {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Задание для Урока 1. Анонимные классы:\s
                    2. Задание в рамках интерфейса java нужно создать калькулятор выполняющий 4
                       основных арифметических функций, сложение, вычитание, деление,
                       умножение, а также осуществить вывод на результатов на экран\s

                Решение:\s""");

        Scanner scanner = new Scanner(System.in);

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
                    System.out.println("Ошибка: деление на ноль!");
                    return;
                }
                break;
            case '*':
                result = num1 * num2;
                break;
            default:
                System.out.println("Неверная операция!");
                return;
        }

        System.out.println("Результат: " + result);
    }
}