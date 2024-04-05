package task6_6_1;

import java.util.Set;
import java.util.TreeSet;

public class Task6_6_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 6. Основные структуры данных. Задание №6:\s
                    1. Вам нужно реализовать класс, создать сет студентов и попробуйте его поместить
                       в TreeSet.\s

                Решение:\s""");

        // Создаем сет студентов
        Set<Student> studentSet = new TreeSet<>();

        // Добавляем студентов в сет
        studentSet.add(new Student("Alice", 7));
        studentSet.add(new Student("Bob", 4));
        studentSet.add(new Student("Charlie", 3));
        studentSet.add(new Student("David", 1));
        studentSet.add(new Student("Emma", 9));
        studentSet.add(new Student("Frank", 2));
        studentSet.add(new Student("Grace", 9));
        studentSet.add(new Student("Henry", 6));
        studentSet.add(new Student("Ivy", 10));
        studentSet.add(new Student("Jack", 5));

        // Выводим результаты в консоль
        System.out.println("Список студентов в TreeSet:");
        for (Student student : studentSet) {
            System.out.println(student);
        }
    }
}

class Student implements Comparable<Student> {
    String name;
    int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }

    @Override
    public int compareTo(Student other) {
        // Сравниваем студентов по оценке
        return Integer.compare(this.grade, other.grade);
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