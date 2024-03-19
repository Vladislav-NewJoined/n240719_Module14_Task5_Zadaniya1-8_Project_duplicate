package task4_7_6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task4_7_6 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №7:\s
                    6. Пользователь вводит имя файла. Выведите содержимое этого файла, если не получилось
                       - выведите сообщение, что файл не найден.\s

                Решение:\s""");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите имя файла, например 'FileForTask6.txt':");
            String fileName = reader.readLine();

            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }
}