package task6_2_3;

import java.util.ArrayList;
import java.util.LinkedList;

public class Task6_2_3 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 6. Основные структуры данных. Задание №2:\s
                    3. Сравните их реализации.\s

                Решение:\s""");

        System.out.println("""
                Приводим примеры сравнение основных характеристик и особенностей реализаций
                ArrayList и LinkedList в Java:
                
                - Доступ по индексу:
                ArrayList: быстрый доступ к элементам по индексу, так как основан на массиве.
                LinkedList: доступ к элементам по индексу медленный из-за необходимости прохода по
                всему списку от начала или конца до нужного элемента.
                
                - Вставка и удаление элементов:
                ArrayList: медленные операции вставки и удаления элементов в середине списка, так как
                требуется перемещение элементов в массиве.
                LinkedList: быстрые операции вставки и удаления элементов в любом месте списка, так как
                требуется только изменение ссылок на соседние элементы.
                
                - Использование памяти:
                ArrayList: использует непрерывный блок памяти для хранения элементов, поэтому занимает
                меньше памяти, чем LinkedList.
                LinkedList: использует дополнительную память для хранения ссылок на следующий и
                предыдущий элементы, поэтому может занимать больше памяти, чем ArrayList.
                
                - Итерация:
                ArrayList: быстрая итерация при обходе списка, так как доступ к элементам по индексу
                быстрый.
                LinkedList: медленная итерация при обходе списка, так как требуется перемещение по
                ссылкам на следующий элемент.
                
                Ниже пример сравнения использования ArrayList и LinkedList в виде кода на языке Java.
                Этот код сравнивает время добавления элементов в начало ArrayList и LinkedList.\s
                """);

// Создание ArrayList и LinkedList
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        // Добавление элементов в начало списка
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            arrayList.add(0, i);
        }
        long endTime = System.nanoTime();
        long arrayListTime = endTime - startTime;
        System.out.println("Время добавления элементов в начало ArrayList: " + arrayListTime +
                " наносекунд");

        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            linkedList.addFirst(i);
        }
        endTime = System.nanoTime();
        long linkedListTime = endTime - startTime;
        System.out.println("Время добавления элементов в начало LinkedList: " + linkedListTime +
                " наносекунд");

        // Сравнение времени добавления элементов в начало списка
        if (arrayListTime < linkedListTime) {
            System.out.println("ArrayList быстрее чем LinkedList при добавлении элементов в" +
                    " начало списка");
        } else if (arrayListTime > linkedListTime) {
            System.out.println("LinkedList быстрее чем ArrayList при добавлении элементов в" +
                    " начало списка");
        } else {
            System.out.println("ArrayList и LinkedList добавляют элементы в начало списка за" +
                    " одинаковое время");
        }
    }
}