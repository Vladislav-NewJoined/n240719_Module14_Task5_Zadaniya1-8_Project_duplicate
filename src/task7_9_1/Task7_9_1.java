package task7_9_1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task7_9_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 7. Взаимодействие с API. Задание №9:\s
                    1. Создайте поток каждым из предложенных в данном уроке способов.\s

                Решение:\s""");

        ArrayList<StudentsAge> studentsAge = new ArrayList<>();
        studentsAge.add(new StudentsAge("Саша", 19, 180));
        studentsAge.add(new StudentsAge("Женя", 20, 170));
        studentsAge.add(new StudentsAge("Ваня", 18, 180));
        studentsAge.add(new StudentsAge("Миша", 19, 190));
        studentsAge.add(new StudentsAge("Петя", 20, 170));
        studentsAge.add(new StudentsAge("Оля", 20, 160));

// Способ 1
        System.out.println("Способ 1");
        Map<Integer, List<StudentsAge>> groupedStudents = studentsAge.stream().collect(Collectors.groupingBy(StudentsAge::getAge));
        for (int age : groupedStudents.keySet()) {
            System.out.println("Имеют возраст " + age + " лет следующие студенты:");
            for (StudentsAge studentsAge2 : groupedStudents.get(age)) {
                System.out.println(studentsAge2.name);
            }
        }
        System.out.println(); // перенос строки
// Способ 1 конец

// Способ 2
        System.out.println("Способ 2");
        Stream<StudentsAge> studentStream = studentsAge.stream();
//        Map<Integer, List<Student>> groupedStudents = studentStream.collect(Collectors.counting());
        System.out.println("Количество студентов: " + studentStream.collect(Collectors.counting()));
        System.out.println(); // перенос строки
// Способ 2 конец

// Способ 3
        System.out.println("Способ 3");
        Stream<StudentsAge> studentStream2 = studentsAge.stream();
        System.out.println("Количество студентов, имеющих соответствующий возраст: " + studentStream2.collect(Collectors.groupingBy(StudentsAge::getAge, Collectors.counting())));
        System.out.println(); // перенос строки
// Способ 3 конец

// Способ 4
        System.out.println("Способ 4");
        Stream<StudentsAge> studentStream4 = studentsAge.stream();
        System.out.println("Просуммированный рост студентов, имеющих соответствующий возраст: " + studentStream4.collect(Collectors.groupingBy(StudentsAge::getAge, Collectors.summingInt(StudentsAge::getHeight))));
        System.out.println(); // перенос строки
// Способ 4 конец

// Способ 5
        System.out.println("Способ 5");
        Stream<StudentsAge> studentStream5 = studentsAge.stream();
        System.out.println("Средний рост студентов, имеющих соответствующий возраст: " + studentStream5.collect(Collectors.averagingInt(StudentsAge::getHeight)));
        System.out.println(); // перенос строки
// Способ 5 конец

// Способ 6
        System.out.println("Способ 6");
        Stream<StudentsAge> studentStream6 = studentsAge.stream();
        System.out.println("Результат, касательно максимального роста: " + studentStream6.collect(Collectors.groupingBy(StudentsAge::getAge, Collectors.maxBy(Comparator.comparingInt(StudentsAge::getHeight)))));
        System.out.println(); // перенос строки
// Способ 6 конец

// Способ 7
        System.out.println("Способ 7");
        Stream<StudentsAge> studentStream7 = studentsAge.stream();
        System.out.println("Результат группировки при помощи mapping (рост, соответствующий возрасту): " + studentStream7.collect(Collectors.groupingBy(StudentsAge::getAge, Collectors.mapping(StudentsAge::getHeight, Collectors.toList()))));
        System.out.println(); // перенос строки
// Способ 7 конец

// Способ 8
        System.out.println("Способ 8");
        Stream<StudentsAge> studentStream8 = studentsAge.stream();
        System.out.println("То же, что и способ 7, только в группы собираем имена: " + studentStream8.collect(Collectors.groupingBy(StudentsAge::getAge, Collectors.mapping(StudentsAge::getName, Collectors.toList()))));
        System.out.println(); // перенос строки
// Способ 8 конец

// Способ 9
        System.out.println("Способ 9");
        Stream<StudentsAge> studentStream9 = studentsAge.stream();
        System.out.println("Сортируем по возрасту (больше 19 лет), ищем true напротив значений (итого 3 человека): " + studentStream9.collect(Collectors.partitioningBy((s) -> s.age > 19)));
        System.out.println(); // перенос строки
// Способ 9 конец

// Способ 10
        System.out.println("Способ 10");
        Stream<StudentsAge> studentStream10 = studentsAge.stream();
        System.out.println("То же, но в более читаемом виде: " + studentStream10.collect(Collectors.partitioningBy((s) -> s.age > 19, Collectors.mapping(StudentsAge::getName, Collectors.toList()))));
// Способ 10 конец
    }
}

class StudentsAge {
    String name;
    int age;
    int height; // Рост

    public StudentsAge(String name, int age, int height) {
        this.name = name;
        this.age = age;
        this.height = height; // Рост
    }

    int getAge() {
        return this.age;
    }

    int getHeight() {
        return this.height; // Рост
    }

    String getName() {
        return this.name;
    }
}