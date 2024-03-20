package task4_10_2;

public class Task4_10_2 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №10:\s
                    2. Напишите код, в котором будет выброшен нпе, при этом не используется throw\s

                Решение:\s""");

        System.out.println("Решение взято из предыдущего задания, т.к. оно соответствует условиям этой" +
                " задачи.");
        String str = null;

        try {
            if (str.equals("test")) {
                System.out.println("String is equal to 'test'");
            }
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught");
        }
    }
}