package task6_5_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class Task6_5_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 6. Основные структуры данных. Задание №5:\s
                    1. Возьмите любую цитату, запишите её в файл и попробуйте прочитать её посчитав
                       количество уникальных слов в тексте.\s

                Решение:\s""");

        System.out.println("""
                ПРИМЕЧАНИЕ: У МЕНЯ К СОЖАЛЕНИЮ НЕ РАБОТАЮТ HASHSET И LINKEDHASHSET,
                ЕСЛИ ИСПОЛЬЗОВАТЬ ЦИТАТУ НА РУССКОМ ЯЗЫКЕ. ВОЗМОЖНО КАКИЕ-ТО ПРОБЛЕМЫ С НАЛИЧИЕМ
                ПАКЕТА РУССКОГО ЯЗЫКА В ПОСЛЕДНЕЙ ВЕРСИИ JDK. МНЕ ПОКА НЕ УДАЛОСЬ ЭТО ИСПРАВИТЬ.
                Я ИСПОЛЬЗОВАЛ ТАКЖЕ TREESET, ЧТОБЫ СЛОВА БЫЛИ ОТСОРТИРОВАНЫ ПО ВОЗРАСТАНИЮ.\s
                """);


        // Шаг 1: Чтение цитаты из файла
        String filePath = "src/task6_5_1/Quote.txt";
        String quote = readQuoteFromFile(filePath);
        if (quote == null) {
            System.out.println("Ошибка: не удалось прочитать цитату из файла " + filePath);
            return;
        }

        System.out.println("Цитата: \"" + quote + "\"");

        // Шаг 2: Нахождение уникальных слов с использованием HashSet и LinkedHashSet
        HashSet<String> uniqueWordsHashSet = new HashSet<>();
        LinkedHashSet<String> uniqueWordsLinkedHashSet = new LinkedHashSet<>();
        TreeSet<String> uniqueWordsTreeSet = new TreeSet<>();

        // Разделение цитаты на слова и добавление их в наборы
        String[] words = quote.split("\\W+");
        for (String word : words) {
            uniqueWordsHashSet.add(word.toLowerCase()); // Приводим к нижнему регистру для игнорирования регистра
            uniqueWordsLinkedHashSet.add(word.toLowerCase());
            uniqueWordsTreeSet.add(word.toLowerCase());
        }

        // Шаг 3: Подсчет количества уникальных слов и вывод результата на печать
        int countUniqueWords = uniqueWordsHashSet.size();
        System.out.println("\nКоличество уникальных слов: " + countUniqueWords + "\n");

        // Вывод уникальных слов на печать (в порядке добавления)
        System.out.println("Уникальные слова (HashSet): " + uniqueWordsHashSet);
        System.out.println("Уникальные слова (LinkedHashSet): " + uniqueWordsLinkedHashSet);
        System.out.println("Уникальные слова (TreeSet): " + uniqueWordsTreeSet);
    }

    // Метод для чтения цитаты из файла
    private static String readQuoteFromFile(String filePath) {
        StringBuilder quoteBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                quoteBuilder.append(line).append("\n");
            }
            return quoteBuilder.toString().trim(); // Удаляем лишние пробелы и переносы строк в начале и конце
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


//// ПРИМЕР 2
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.LinkedHashSet;
//import java.util.TreeSet;
//
//public class Task6_5_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 6. Основные структуры данных. Задание №5:\s
//                    1. Возьмите любую цитату, запишите её в файл и попробуйте прочитать её посчитав
//                       количество уникальных слов в тексте.\s
//
//                Решение:\s""");
//
//        System.out.println("""
//                ПРИМЕЧАНИЕ: У МЕНЯ К СОЖАЛЕНИЮ НЕ РАБОТАЮТ HASHSET И LINKEDHASHSET,
//                ЕСЛИ ИСПОЛЬЗОВАТЬ ЦИТАТУ НА РУССКОМ ЯЗЫКЕ. ВОЗМОЖНО КАКИЕ-ТО ПРОБЛЕМЫ С НАЛИЧИЕМ
//                ПАКЕТА РУССКОГО ЯЗЫКА В ПОСЛЕДНЕЙ ВЕРСИИ JDK. МНЕ ПОКА НЕ УДАЛОСЬ ЭТО ИСПРАВИТЬ.
//                Я ИСПОЛЬЗОВАЛ ТАКЖЕ TREESET, ЧТОБЫ СЛОВА БЫЛИ ОТСОРТИРОВАНЫ ПО ВОЗРАСТАНИЮ.\s
//                """);
//
//        // Шаг 1: Вывод цитаты на печать
//        String quote = "Java is a general-purpose programming language that is class-based, " +
//                "object-oriented, and designed to have as few implementation dependencies as possible.";
//        System.out.println("Цитата: \"" + quote + "\"");
//
//        // Шаг 2: Нахождение уникальных слов с использованием HashSet и LinkedHashSet
//        HashSet<String> uniqueWordsHashSet = new HashSet<>();
//        LinkedHashSet<String> uniqueWordsLinkedHashSet = new LinkedHashSet<>();
//        TreeSet<String> uniqueWordsTreeSet = new TreeSet<>();
//        // TreeSet<String> uniqueWordsTreeSet = new TreeSet<>();
//
//        // Разделение цитаты на слова и добавление их в наборы
//        String[] words = quote.split("\\W+");
//        for (String word : words) {
//            uniqueWordsHashSet.add(word.toLowerCase()); // Приводим к нижнему регистру для игнорирования регистра
//            uniqueWordsLinkedHashSet.add(word.toLowerCase());
//            uniqueWordsTreeSet.add(word.toLowerCase());
//        }
//
//        // Шаг 3: Подсчет количества уникальных слов и вывод результата на печать
//        int countUniqueWords = uniqueWordsHashSet.size();
//        System.out.println("\nКоличество уникальных слов: " + countUniqueWords + "\n");
//
//        // Вывод уникальных слов на печать (в порядке добавления)
//        System.out.println("Уникальные слова (HashSet): " + uniqueWordsHashSet);
//        // Вывод уникальных слов на печать (в порядке добавления)
//        System.out.println("Уникальные слова (LinkedHashSet): " + uniqueWordsLinkedHashSet);
//        // Вывод уникальных слов на печать (в порядке добавления)
//        System.out.println("Уникальные слова (TreeSet): " + uniqueWordsTreeSet);
//
//        // Путь к файлу Quote.txt
//        String filePath = "src/task6_5_1/Quote.txt";
//
//        try {
//            // Создаем объект FileWriter для записи в файл
//            FileWriter writer = new FileWriter(filePath);
//            // Записываем цитату в файл
//            writer.write(quote);
//            // Закрываем FileWriter
//            writer.close();
//            System.out.println("\nЦитата успешно записана в файл " + filePath);
//        } catch (IOException e) {
//            System.out.println("\nОшибка при записи цитаты в файл: " + e.getMessage());
//        }
//    }
//}
//// КОНЕЦ ПРИМЕРА 2