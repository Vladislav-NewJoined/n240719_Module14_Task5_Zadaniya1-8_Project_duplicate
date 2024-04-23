package task5_3_4;

import java.io.IOException;

public class Task5_3_4 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
                    4. В чем разница сравнения через == и через equals?\s

                Решение:\s""");

        System.out.println("""
                Оператор == в Java сравнивает две переменные или объекты на их идентичность, то есть
                проверяет, указывают ли они на один и тот же объект в памяти.

                Метод equals() в Java сравнивает содержимое двух объектов и возвращает результат в виде
                true (если объекты равны) или false (если объекты не равны). Метод equals() обычно
                переопределяется в классе, чтобы сравнивать поля объектов вместо ссылок.

                Важно помнить, что для большинства классов стандартная реализация метода equals()
                сравнивает объекты на идентичность, как и оператор ==. Поэтому, если требуется сравнение
                содержимого объектов, метод equals() следует переопределить.\s""");
    }
}


//// ПРИМЕР 2
//import java.io.IOException;
//
//public class Task5_3_4 {
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
//                    4. В чем разница сравнения через == и через equals?\s
//
//                Решение:\s""");
//
//        System.out.println("""
//                Оператор == в Java сравнивает две переменные или объекты на их идентичность, то есть
//                проверяет, указывают ли они на один и тот же объект в памяти.
//
//                Метод equals() в Java сравнивает содержимое двух объектов и возвращает результат в виде
//                true (если объекты равны) или false (если объекты не равны). Метод equals() обычно
//                переопределяется в классе, чтобы сравнивать поля объектов вместо ссылок.
//
//                Важно помнить, что для большинства классов стандартная реализация метода equals()
//                сравнивает объекты на идентичность, как и оператор ==. Поэтому, если требуется сравнение
//                содержимого объектов, метод equals() следует переопределить.\s""");
//    }
//}
//// КОНЕЦ ПРИМЕРА 2