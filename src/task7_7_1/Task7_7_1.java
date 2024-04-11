package task7_7_1;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Task7_7_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 7. Взаимодействие с API. Задание №7:\s
                    1. Вам нужно создать класс “Students” с полями “класс” и “оценка”, и попробовать вычислить средний балл у
                       студентов при помощи различных метода (reduce, min/max и т.д.).\s

                Решение:\s""");

        System.out.println("Информация о фамилии, классе, оценке: ");
        Students student1 = new Students();
        student1.name1 = "Иван";
        student1.theClass1 = "Java";
        student1.mark1 = 5;
        double dstudent1 = student1.mark1;
        student1.theClass2 = "Python";
        student1.mark2 = 5;
        double dstudent2 = student1.mark2;
        student1.theClass3 = "C++";
        student1.mark3 = 3;
        double dstudent3 = student1.mark3;
        student1.theClass4 = "Pascal";
        student1.mark4 = 4;
        double dstudent4 = student1.mark4;

        student1.name2 = "Петр";
        student1.theClass5 = "Java";
        student1.mark5 = 3;
        double dstudent5 = student1.mark5;
        student1.theClass6 = "Python";
        student1.mark6 = 5;
        double dstudent6 = student1.mark6;
        student1.theClass7 = "C++";
        student1.mark7 = 4;
        double dstudent7 = student1.mark7;
        student1.theClass8 = "Pascal";
        student1.mark8 = 3;
        double dstudent8 = student1.mark8;

        System.out.println(
                "name1=" + student1.name1 + ", theClass1=" + student1.theClass1 + ", mark1=" + student1.mark1 +
                        "\nname1=" + student1.name1 + ", theClass2=" + student1.theClass2 + ", mark2=" + student1.mark2 +
                        "\nname1=" + student1.name1 + ", theClass3=" + student1.theClass3 + ", mark3=" + student1.mark3 +
                        "\nname1=" + student1.name1 + ", theClass4=" + student1.theClass4 + ", mark4=" + student1.mark4 +
                        "\n\nname2=" + student1.name2 + ", theClass5=" + student1.theClass5 + ", mark5=" + student1.mark5 +
                        "\nname2=" + student1.name2 + ", theClass6=" + student1.theClass6 + ", mark6=" + student1.mark6 +
                        "\nname2=" + student1.name2 + ", theClass7=" + student1.theClass7 + ", mark7=" + student1.mark7 +
                        "\nname2=" + student1.name2 + ", theClass8=" + student1.theClass8 + ", mark8=" + student1.mark8);
        System.out.println(); // Перенос строки

        IntStream intStream1 = IntStream.of(student1.mark1, student1.mark2, student1.mark3, student1.mark4);
        IntStream intStream2 = IntStream.of(student1.mark5, student1.mark6, student1.mark7, student1.mark8);
        /*System.out.println("SUM: " + IntStream.of(student1.mark1, 30, 50, 70, 90, 100).sum());*/

        double avg1 = intStream1.average().getAsDouble();
        double avg2 = intStream2.average().getAsDouble();
        System.out.printf(
                "Пример с методом average: " +
                        "\nСредний балл Ивана: ");
        System.out.printf("%.2f", avg1);
        System.out.printf(
                "\nСредний балл Петра: ");
        System.out.printf("%.2f", avg2);
        System.out.println("\n"); // Перенос строки

        double minMax1 = IntStream.of((IntStream.of(student1.mark1, student1.mark2, student1.mark3, student1.mark4).min()).getAsInt(), (IntStream.of(student1.mark1, student1.mark2, student1.mark3, student1.mark4).max().getAsInt())).average().getAsDouble();
        double minMax2 = IntStream.of((IntStream.of(student1.mark5, student1.mark6, student1.mark7, student1.mark8).min()).getAsInt(), (IntStream.of(student1.mark5, student1.mark6, student1.mark7, student1.mark8).max().getAsInt())).average().getAsDouble();
        System.out.printf(
                "Пример с методами min/max (результат отличается, т.к. расчет по этим методам на совсем корректен): " +
                        "\nСредний балл Ивана: ");
        System.out.printf("%.2f", minMax1);
        System.out.printf(
                "\nСредний балл Петра: ");
        System.out.printf("%.2f", minMax2);
        System.out.println("\n"); // Перенос строки

        double reduce1 = (Stream.of(dstudent1, dstudent2, dstudent3, dstudent4).reduce((x, y) -> (x + y)).get()) / 4;
        double reduce2 = (Stream.of(dstudent5, dstudent6, dstudent7, dstudent8).reduce((x, y) -> (x + y)).get()) / 4;


        System.out.printf(
                "Пример с методом reduce: " +
                        "\nСредний балл Ивана: ");
        System.out.printf("%.2f", reduce1);
        System.out.printf(
                "\nСредний балл Петра: ");
        System.out.printf("%.2f", reduce2);
        System.out.println(""); // Перенос строки
    }
}