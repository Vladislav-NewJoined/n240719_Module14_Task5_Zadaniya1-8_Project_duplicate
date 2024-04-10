package task7_3_1;

import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Task7_3_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 7. Взаимодействие с API. Задание №3:\s
                    1. Используя Stream вам необходимо объединить данные три строки “I”, “LOVE”, “JAVA”
                       вы создаете новый поток из простых строк, а затем собираете их в одну при помощи
                       метода collect().\s

                Решение:\s""");

        // Создаем три отдельные строки
        String str1 = "I";
        String str2 = "LOVE";
        String str3 = "JAVA";

        // Создаем поток из трех отдельных строк
        Stream<String> stringStream = Stream.of(str1, str2, str3);

        // Собираем строки в одну при помощи метода collect
        String result = stringStream.collect(Collectors.joining(" "));

        // Выводим результат
        System.out.println("Результат объединения: " + result);
    }
}