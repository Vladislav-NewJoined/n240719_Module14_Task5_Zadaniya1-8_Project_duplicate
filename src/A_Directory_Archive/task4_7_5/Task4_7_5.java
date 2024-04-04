package task4_7_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task4_7_5 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №7:\s
                    5. Пользователь вводит адрес ссылки. Если удаётся скачать страничку по адресу,
                       сохранить ее в html файл; иначе вывести, что страница не найдена.\s

                Решение:\s""");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Введите адрес ссылки, например 'https://ya.ru/':");
//                System.out.println("Введите адрес ссылки, например 'https://www.google.com/':");
            String url = reader.readLine();

            URL website = new URL(url);
            BufferedReader webReader = new BufferedReader(new InputStreamReader(website.openStream()));

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = webReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            webReader.close();

            Path file = Paths.get("src/task4_7_5/webpage.html");
            Files.write(file, content.toString().getBytes());

            System.out.println("Страница успешно сохранена в файл webpage.html");
        } catch (IOException e) {
            System.out.println("Страница не найдена" + e.getMessage());
        }
    }
}
