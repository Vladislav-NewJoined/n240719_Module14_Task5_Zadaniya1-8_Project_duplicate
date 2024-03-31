package task5_3_5;

import java.io.IOException;

public class Task5_3_5 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
                    5. Всегда ли сравнение через equals сравнивает значения элементов?\s

                Решение:\s""");

        System.out.println("""
                Нет, не всегда. В Java метод equals() для сравнения объектов по умолчанию сравнивает
                ссылки на объекты, а не их значения. Для сравнения значений элементов необходимо
                переопределить метод equals() в соответствующем классе.\s""");
    }
}