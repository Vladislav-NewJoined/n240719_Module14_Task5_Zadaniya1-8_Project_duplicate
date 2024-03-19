package task4_8_1_Videourok;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task4_8_1_Videourok {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №8:\s
                    1. Пользователь вводит 5 названий файлов. Они могут повторяться. Сохраните в каждый
                       из файл названия ВСЕХ 5 файлов, используйте try with resources\s

                Решение:\s""");

        Scanner scanner = new Scanner(System.in);
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/task4_8_1_Videourok/out.txt"));
        try {
            System.out.print("x: ");
            int x = scanner.nextInt();
            System.out.print("y: ");
            int y = scanner.nextInt();
            writer.write(x + "/" + y + "=" + x / y);
//            writer.close();
        } catch (Exception e) {
            writer.write("There is error: " + e.getMessage());
//            writer.close();
            throw e;
        } finally {
            writer.close();
            System.out.println("written to out.txt");
        }
    }
}