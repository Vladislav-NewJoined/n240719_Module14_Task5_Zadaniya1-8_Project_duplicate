package task4_7_4;

import java.util.Scanner;

public class Task4_7_4 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №7:\s
                    4. Доработайте калькулятор. Если пользователь ввел неверное значение, просто дайте ему
                       ввести еще раз. (Пока он не введёт корректно)\s

                Решение:\s""");

        Scanner scanner = new Scanner(System.in);
        double num1;
        char operation;
        double num2;
        double result;

        try {
            System.out.println("Введите первое число: ");
            num1 = scanner.nextDouble();

            System.out.println("Введите знак операции (+, -, /, *): ");
            operation = scanner.next().charAt(0);

            System.out.println("Введите второе число: ");
            num2 = scanner.nextDouble();

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
            System.out.println(e.getMessage());
            System.out.println("Попробуйте еще раз.");
            main(args); // Рекурсивный вызов метода main для повторного ввода значений.
        }
    }
}




//// ПРИМЕР 2
//import java.util.Scanner;
//
//public class Task4_7_4 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 4. Наследование. Задание №7:\s
//                    4. Доработайте калькулятор. Если пользователь ввел неверное значение, просто дайте ему
//                       ввести еще раз. (Пока он не введёт корректно)\s
//
//                Решение:\s""");
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Введите первое число: ");
//        double num1 = scanner.nextDouble();
//
//        System.out.println("Введите знак операции (+, -, /, *): ");
//        char operation = scanner.next().charAt(0);
//
//        System.out.println("Введите второе число: ");
//        double num2 = scanner.nextDouble();
//
//        double result = 0;
//
//        switch(operation) {
//            case '+':
//                result = num1 + num2;
//                break;
//            case '-':
//                result = num1 - num2;
//                break;
//            case '/':
//                if(num2 != 0) {
//                    result = num1 / num2;
//                } else {
//                    System.out.println("Ошибка: деление на ноль!");
//                    return;
//                }
//                break;
//            case '*':
//                result = num1 * num2;
//                break;
//            default:
//                System.out.println("Неверная операция!");
//                return;
//        }
//
//        System.out.println("Результат: " + result);
//    }
//}
//// КОНЕЦ ПРИМЕРА 2