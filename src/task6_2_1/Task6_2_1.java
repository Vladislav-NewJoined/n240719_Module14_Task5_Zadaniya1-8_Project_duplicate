package task6_2_1;

import java.util.ArrayList;

public class Task6_2_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 6. Основные структуры данных. Задание №2:\s
                    1. Как реализуется Array List?\s

                Решение:\s""");

        System.out.println("""
                ArrayList в языке программирования Java реализуется как класс из стандартной библиотеки
                Java (java.util.ArrayList). Он представляет собой динамический массив, который может
                изменять свой размер по мере необходимости. Внутренне ArrayList основан на массиве,
                но автоматически управляет его размером и обеспечивает удобные методы для добавления,
                удаления и доступа к элементам.
                
                Ниже пример использования ArrayList в виде кода на языке Java. Этот код создает
                ArrayList строк и добавляет в него несколько элементов. Затем он демонстрирует
                различные операции, такие как получение размера, доступ к элементам по индексу,
                удаление элемента по значению и проверку наличия элемента.\s
                """);


        // Создание объекта ArrayList с элементами типа String
        ArrayList<String> arrayList = new ArrayList<>();

        // Добавление элементов в ArrayList
        arrayList.add("apple");
        arrayList.add("banana");
        arrayList.add("orange");

        // Вывод размера ArrayList
        System.out.println("Размер ArrayList: " + arrayList.size());

        // Доступ к элементу по индексу
        System.out.println("Элемент по индексу 1: " + arrayList.get(1));

        // Удаление элемента по значению
        arrayList.remove("banana");

        // Проверка наличия элемента
        System.out.println("Содержит ли ArrayList элемент 'banana': " + arrayList.contains("banana"));

        // Вывод всех элементов ArrayList
        System.out.println("Элементы ArrayList: " + arrayList);
    }
}