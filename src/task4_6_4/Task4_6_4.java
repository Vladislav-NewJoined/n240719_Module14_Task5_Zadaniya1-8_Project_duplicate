package task4_6_4;

import java.util.Scanner;

class CustomException1 extends Exception {
    public CustomException1() {
        super("Custom exception 1");
    }
}

class CustomException2 extends Exception {
    public CustomException2() {
        super("Custom exception 2");
    }
}

class CustomException3 extends Exception {
    public CustomException3() {
        super("Custom exception 3");
    }
}

class CustomException4 extends Exception {
    public CustomException4() {
        super("Custom exception 4");
    }
}

class CustomException5 extends Exception {
    public CustomException5() {
        super("Custom exception 5");
    }
}

class CustomException6 extends Exception {
    public CustomException6() {
        super("Custom exception 6");
    }
}

class CustomException7 extends Exception {
    public CustomException7() {
        super("Custom exception 7");
    }
}

class CustomException8 extends Exception {
    public CustomException8() {
        super("Custom exception 8");
    }
}

class CustomException9 extends Exception {
    public CustomException9() {
        super("Custom exception 9");
    }
}

class CustomException10 extends Exception {
    public CustomException10() {
        super("Custom exception 10");
    }
}

public class Task4_6_4 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №6:\s
                    4. Создайте 10 классов-исключений. Соберите их в массив. Пусть пользователь выбирает,
                       какое по счету исключение выбросить.\s

                Решение:\s""");

        Exception[] exceptions = new Exception[]{
                new CustomException1(),
                new CustomException2(),
                new CustomException3(),
                new CustomException4(),
                new CustomException5(),
                new CustomException6(),
                new CustomException7(),
                new CustomException8(),
                new CustomException9(),
                new CustomException10()
        };

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер исключения, которое Вы хотите выбросить (1-10): ");
        int choice = scanner.nextInt();

        try {
            throw exceptions[choice - 1];
        } catch (Exception e) {
            System.out.println("Выбрасывается следующее исключение: " + e.getMessage());
        }
    }
}