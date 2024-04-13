package task6_2_2;

import java.util.LinkedList;

public class Task6_2_2 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 6. Основные структуры данных. Задание №2:\s
                    2. Как реализуется Linked List?\s

                Решение:\s""");

        System.out.println("""
                В языке программирования Java связанный список (Linked List) реализуется с
                использованием класса LinkedList из стандартной библиотеки Java (java.util.LinkedList).
                Связанный список состоит из узлов, каждый из которых содержит данные и ссылку на
                следующий узел в списке.
                
                Ниже пример использования Linked Lis в виде кода на языке Java. Этот код создает
                связанный список целых чисел, добавляет в него несколько элементов в начало и
                конец списка, затем удаляет первый и последний элементы и выводит оставшиеся элементы
                списка.\s
                """);

// Создание объекта LinkedList с элементами типа Integer
        LinkedList<Integer> linkedList = new LinkedList<>();

        // Добавление элементов в начало списка
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);

        // Добавление элемента в конец списка
        linkedList.addLast(4);

        // Вывод всех элементов списка
        System.out.println("Элементы списка: " + linkedList);

        // Получение и удаление первого элемента списка
        int firstElement = linkedList.removeFirst();
        System.out.println("Удаленный первый элемент: " + firstElement);

        // Получение и удаление последнего элемента списка
        int lastElement = linkedList.removeLast();
        System.out.println("Удаленный последний элемент: " + lastElement);

        // Вывод всех элементов списка после удалений
        System.out.println("Элементы списка после удалений: " + linkedList);
    }
}