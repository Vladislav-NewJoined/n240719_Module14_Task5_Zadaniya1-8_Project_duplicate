package task6_7_1;

import java.util.HashMap;
import java.util.Scanner;

public class Task6_7_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 6. Основные структуры данных. Задание №7:\s
                    1. Вам необходимо используя HashMap написать базу данных студентов, реализовать
                       бесконечный цикл, пользователь вводит имя студента и оценку и в случае если
                       студент уже внесен в данную базу данных, вводимая оценка должна сохраняться в
                       списке и после каждого ввода необходимо осуществлять вывод на экран средней
                       оценки всех студентов.\s

                Решение:\s""");

        // Исходная база данных студентов
        HashMap<String, Integer> originalDatabase = new HashMap<>();
        originalDatabase.put("John", 4);
        originalDatabase.put("Petya", 3);
        originalDatabase.put("Michael", 5);
        originalDatabase.put("David", 2);
        originalDatabase.put("Frank", 1);
        System.out.println("Исходная база данных студентов:");
        System.out.println(originalDatabase);

        // Создаем HashMap для хранения базы данных студентов
        HashMap<String, HashMap<Integer, Integer>> studentDatabase = new HashMap<>();

        // Копируем исходную базу данных в studentDatabase
        for (String name : originalDatabase.keySet()) {
            HashMap<Integer, Integer> grades = new HashMap<>();
            grades.put(originalDatabase.get(name), 1);
            studentDatabase.put(name, grades);
        }

        // Бесконечный цикл для ввода данных пользователем
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите имя студента и его оценку через запятую (например, 'Petya,5'): ");
            String input = scanner.nextLine();

            // Парсим введенную строку на имя и оценку
            String[] parts = input.split(",");
            if (parts.length != 2) {
                System.out.println("Ошибка ввода. Попробуйте еще раз.");
                continue;
            }

            String studentName = parts[0].trim();
            int grade = Integer.parseInt(parts[1].trim());

            // Если студент уже есть в базе данных, добавляем новую оценку в список
            if (studentDatabase.containsKey(studentName)) {
                HashMap<Integer, Integer> gradesMap = studentDatabase.get(studentName);
                gradesMap.put(grade, gradesMap.getOrDefault(grade, 0) + 1);
            } else {
                // Если студента нет в базе данных, создаем новую запись
                HashMap<Integer, Integer> gradesMap = new HashMap<>();
                gradesMap.put(grade, 1);
                studentDatabase.put(studentName, gradesMap);
            }

            // Выводим список всех студентов и их средние оценки
            System.out.println("Список всех студентов и их средние оценки:");
            for (String name : studentDatabase.keySet()) {
                HashMap<Integer, Integer> gradesMap = studentDatabase.get(name);
                double totalGrade = 0.0;
                int totalStudents = 0;
                for (int g : gradesMap.keySet()) {
                    totalGrade += g * gradesMap.get(g);
                    totalStudents += gradesMap.get(g);
                }
                double averageGrade = totalGrade / totalStudents;
                System.out.println(name + ": " + averageGrade);
            }
        }
    }
}




//// ПРИМЕР 3
//import java.util.HashMap;
//import java.util.Scanner;
//
//public class Task6_7_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 6. Основные структуры данных. Задание №7:\s
//                    1. Вам необходимо используя HashMap написать базу данных студентов, реализовать
//                       бесконечный цикл, пользователь вводит имя студента и оценку и в случае если
//                       студент уже внесен в данную базу данных, вводимая оценка должна сохраняться в
//                       списке и после каждого ввода необходимо осуществлять вывод на экран средней
//                       оценки всех студентов.\s
//
//                Решение:\s""");
//
//        // Исходная база данных студентов
//        HashMap<String, Integer> originalDatabase = new HashMap<>();
//        originalDatabase.put("John", 4);
//        originalDatabase.put("Petya", 3);
//        originalDatabase.put("Michael", 5);
//        originalDatabase.put("David", 2);
//        originalDatabase.put("Frank", 1);
//        System.out.println("Исходная база данных студентов:");
//        System.out.println(originalDatabase);
//
//        // Создаем HashMap для хранения базы данных студентов
//        HashMap<String, HashMap<Integer, Integer>> studentDatabase = new HashMap<>();
//
//        // Копируем исходную базу данных в studentDatabase
//        for (String name : originalDatabase.keySet()) {
//            HashMap<Integer, Integer> grades = new HashMap<>();
//            grades.put(originalDatabase.get(name), 1);
//            studentDatabase.put(name, grades);
//        }
//
//        // Бесконечный цикл для ввода данных пользователем
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.print("Введите имя студента и его оценку через запятую (например, 'Petya,5'): ");
//            String input = scanner.nextLine();
//
//            // Парсим введенную строку на имя и оценку
//            String[] parts = input.split(",");
//            if (parts.length != 2) {
//                System.out.println("Ошибка ввода. Попробуйте еще раз.");
//                continue;
//            }
//
//            String studentName = parts[0].trim();
//            int grade = Integer.parseInt(parts[1].trim());
//
//            // Если студент уже есть в базе данных, добавляем новую оценку в список
//            if (studentDatabase.containsKey(studentName)) {
//                HashMap<Integer, Integer> grades = studentDatabase.get(studentName);
//                grades.put(grade, grades.getOrDefault(grade, 0) + 1);
//            } else {
//                // Если студента нет в базе данных, создаем новую запись
//                HashMap<Integer, Integer> grades = new HashMap<>();
//                grades.put(grade, 1);
//                studentDatabase.put(studentName, grades);
//            }
//
//            // Выводим список всех оценок студентов
//            System.out.println("Список всех оценок студентов:");
//            for (String name : studentDatabase.keySet()) {
//                System.out.println(name + ": " + studentDatabase.get(name));
//            }
//
//            // Вычисляем и выводим среднюю оценку студента
//            HashMap<Integer, Integer> grades = studentDatabase.get(studentName);
//            double totalGrade = 0.0;
//            int totalStudents = 0;
//            for (int g : grades.keySet()) {
//                totalGrade += g * grades.get(g);
//                totalStudents += grades.get(g);
//            }
//            double averageGrade = totalGrade / totalStudents;
//            System.out.println("Средняя оценка студента " + studentName + ": " + averageGrade);
//        }
//    }
//}
//// КОНЕЦ ПРИМЕРА 3




//// ПРИМЕР 2
//import java.util.HashMap;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Task6_7_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 6. Основные структуры данных. Задание №7:\s
//                    1. Вам необходимо используя HashMap написать базу данных студентов, реализовать
//                       бесконечный цикл, пользователь вводит имя студента и оценку и в случае если
//                       студент уже внесен в данную базу данных, вводимая оценка должна сохраняться в
//                       списке и после каждого ввода необходимо осуществлять вывод на экран средней
//                       оценки всех студентов.\s
//
//                Решение:\s""");
//
//        // Создаем HashMap для хранения базы данных студентов
//        HashMap<String, ArrayList<Integer>> studentDatabase = new HashMap<>();
//
//        // Бесконечный цикл для ввода данных пользователем
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.print("Введите имя студента и его оценку через запятую (например, 'Petya,5'): ");
//            String input = scanner.nextLine();
//
//            // Парсим введенную строку на имя и оценку
//            String[] parts = input.split(",");
//            if (parts.length != 2) {
//                System.out.println("Ошибка ввода. Попробуйте еще раз.");
//                continue;
//            }
//
//            String studentName = parts[0].trim();
//            int grade = Integer.parseInt(parts[1].trim());
//
//            // Если студент уже есть в базе данных, добавляем новую оценку в список
//            if (studentDatabase.containsKey(studentName)) {
//                studentDatabase.get(studentName).add(grade);
//            } else {
//                // Если студента нет в базе данных, создаем новую запись
//                ArrayList<Integer> grades = new ArrayList<>();
//                grades.add(grade);
//                studentDatabase.put(studentName, grades);
//            }
//
//            // Выводим список всех оценок студентов
//            System.out.println("Список всех оценок студентов:");
//            for (String name : studentDatabase.keySet()) {
//                System.out.println(name + ": " + studentDatabase.get(name));
//            }
//
//            // Вычисляем и выводим среднюю оценку всех студентов
//            double totalAverageGrade = 0.0;
//            int totalStudents = 0;
//            for (ArrayList<Integer> grades : studentDatabase.values()) {
//                for (int g : grades) {
//                    totalAverageGrade += g;
//                    totalStudents++;
//                }
//            }
//            double averageGrade = totalAverageGrade / totalStudents;
//            System.out.println("Средняя оценка всех студентов: " + averageGrade);
//        }
//    }
//}
//// КОНЕЦ ПРИМЕРА 2