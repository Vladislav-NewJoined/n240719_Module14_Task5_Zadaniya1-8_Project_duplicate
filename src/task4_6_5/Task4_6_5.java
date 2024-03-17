package task4_6_5;

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

public class Task4_6_5 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №6:\s
                    5. Соберите их в матрицу 3x3. Пусть пользователь выбирает, номер строки и столбца,
                       какое исключение выбросить. Если ввод неверный - выбросить 10-е исключение.\s

                Решение:\s""");

        Exception[][] exceptionsMatrix = new Exception[][]{
                {new CustomException1(), new CustomException2(), new CustomException3()},
                {new CustomException4(), new CustomException5(), new CustomException6()},
                {new CustomException7(), new CustomException8(), new CustomException9()}
        };

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер строки (1-3): ");
        int row = scanner.nextInt();
        System.out.print("Введите номер столбца (1-3): ");
        int col = scanner.nextInt();

        try {
            throw exceptionsMatrix[row - 1][col - 1];
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Введены неверные номер строки и столбца, выбрасывается исключение 10: " + new CustomException10().getMessage());
        } catch (Exception e) {
            System.out.println("Выбрасывается следующее исключение: " + e.getMessage());
        }
    }
}