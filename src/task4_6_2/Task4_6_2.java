package task4_6_2;

//public class Task4_6_2 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 4. Наследование. Задание №6:\s
//                    2. Доработайте калькулятор: при неверном вводе выбрасывайте исключение\s
//
//                Решение:\s""");
//
//        final String input = "10/5";
//        final String[] numbers = input.split("[+-/*]");
//        final int a = Integer.parseInt(numbers[0]);
//        final int b = Integer.parseInt(numbers[1]);
//        final int lastNumberIndex = input.indexOf(numbers[1]);
//        final String operationString = input.substring(lastNumberIndex-1, lastNumberIndex);
//        Operation sum = new Operation() {
//            @Override
//            public int calculate(int a, int b) {
//                return a+b;
//            }
//        };
//        Operation minus = new Operation() {
//            @Override
//            public int calculate(int a, int b) {
//                return a-b;
//            }
//        };
//        Operation multiply = new Operation() {
//            @Override
//            public int calculate(int a, int b) {
//                return a*b;
//            }
//        };
//        Operation divide = new Operation() {
//            @Override
//            public int calculate(int a, int b) {
//                return a/b;
//            }
//        };
//
//        Operation operation = null;
//        switch (operationString) {
//            case "+":
//                operation = sum;
//                break;
//
//            case "-":
//                operation = minus;
//                break;
//
//            case "*":
//                operation = multiply;
//                break;
//
//            case "/":
//                operation = divide;
//                break;
//
//        }
//        System.out.println(operation.calculate(a, b));
//    }
//}
//
//interface Operation {
//    int calculate(int a, int b);
//
//}




//// ПРИМЕР 3
import java.util.Scanner;

public class Task4_6_2 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №6:\s
                    2. Доработайте калькулятор: при неверном вводе выбрасывайте исключение\s

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
//// КОНЕЦ ПРИМЕРА 3




//// ПРИМЕР 2
//import java.util.Scanner;
//
//public class Task4_6_2 {
//    public static void main(String[] args) {
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
//        double result;
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
