package task4_8_1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task4_8_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №8:\s
                    1. Пользователь вводит 5 названий файлов. Они могут повторяться. Сохраните в каждый
                       из файл названия ВСЕХ 5 файлов, используйте try with resources\s

                Решение:\s""");

        try (Scanner scanner = new Scanner(System.in)) {
            String[] filenames = new String[5];
            for (int i = 0; i < 5; i++) {
                System.out.print("Введите название файла " + (i + 1) + ": ");
                filenames[i] = scanner.nextLine();
            }

            for (String filename : filenames) {
                try (FileWriter writer = new FileWriter("src/task4_8_1/" + filename + ".txt")) {
                    for (String name : filenames) {
                        writer.write(name + "\n");
                    }
                    System.out.println("Файл " + filename + ".txt успешно создан.");
                } catch (IOException e) {
                    System.out.println("Ошибка при записи в файл " + filename + ".txt: " + e.getMessage());
                }
            }
        }
    }
}
