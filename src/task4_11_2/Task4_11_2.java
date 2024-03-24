package task4_11_2;

public class Task4_11_2 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №11:\s
                    2. Выведите stacktrace\s

                Решение:\s""");

        System.out.println("""
                Этот код генерирует NullPointerException (исключение, возникающее при обращении к
                методам объекта, у которого значение null) и затем выводит stacktrace с помощью
                метода printStackTrace() объекта исключения e. В результате выполнения программы
                будет выведен стек-трейс с информацией о месте возникновения исключения и
                последовательности вызовов методов до этого момента\s""");

        try {
            // Генерируем NullPointerException
            String str = null;
            System.out.println(str.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}