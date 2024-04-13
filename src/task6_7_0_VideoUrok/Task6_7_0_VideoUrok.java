package task6_7_0_VideoUrok;

import java.util.HashMap;

public class Task6_7_0_VideoUrok {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 6. Основные структуры данных. Задание №7:\s
                    1. Вам необходимо используя HushMap написать базу данных студентов, реализовать
                       бесконечный цикл, пользователь вводит имя студента и оценку и в случае если
                       студент уже внесен в данную базу данных, вводимая оценка должна сохраняться в
                       списке и после каждого ввода необходимо осуществлять вывод на экран средней
                       оценки всех студентов.\s

                Решение:\s""");

        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("John", 4);
        hashMap.put("Petya", 3);
        hashMap.put("Michael", 5);
        System.out.println(hashMap);
        System.out.println(hashMap.get("John"));
    }
}