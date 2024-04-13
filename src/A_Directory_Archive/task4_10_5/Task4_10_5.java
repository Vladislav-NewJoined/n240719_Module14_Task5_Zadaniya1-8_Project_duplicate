package task4_10_5;

public class Task4_10_5 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №10:\s
                    5. Создайте массив nullов\s

                Решение:\s""");

        System.out.println("""
                Создаем массив array из пяти элементов типа Integer. Затем в цикле присваиваем каждому
                элементу массива значение null. Далее мы выводим каждый элемент массива на экран. Таким
                образом, мы создаем массив из пяти элементов, где каждый элемент содержит значение null,
                демонстрируя обработку значений null.\s
                """);

        Integer[] array = new Integer[5]; // Создаем массив из пяти элементов

        for (int i = 0; i < array.length; i++) {
            array[i] = null; // Присваиваем каждому элементу массива значение null
        }

        // Выводим элементы массива на экран
        for (int i = 0; i < array.length; i++) {
            System.out.println("Элемент " + i + ": " + array[i]);
        }
    }
}
