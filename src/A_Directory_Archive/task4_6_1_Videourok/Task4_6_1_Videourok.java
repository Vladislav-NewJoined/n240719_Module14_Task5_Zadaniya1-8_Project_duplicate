package task4_6_1_Videourok;

import java.io.IOException;
import java.util.Scanner;

public class Task4_6_1_Videourok {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №6:\s
                    1. Доработайте крестики-нолики; создайте исключение, которое будете бросать при       
                       вводе пользователя\s

                Решение:\s""");

        // Пишем программу, которая делит одно число на другое
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите два слова:");
        String line = scanner.nextLine();
        String[] words = line.split(" ");

        if (words.length == 2) {
            System.out.println(words[1] + " " + words[0]);
        } else {
            throw new RuntimeException("нужно ввести два слова!");
        }

//        int x = scanner.nextInt();
//        int y = scanner.nextInt();

//        if (y == 0) {
//            // Если нажать Ctrl + P , на Mac Command + P
//            // Класс можно посмотреть нажав Ctrl и кликнув на него
//            RuntimeException e = new RuntimeException("неверные входные данные, нельзя поделить на ноль");
//            // throw e;
//            throw e;
////            System.out.println(e);
//        }

//        System.out.println(x/y);
    }
}
