package task4_10_1;

public class Task4_10_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №10:\s
                    1. Выбросите NPE\s

                Решение:\s""");

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