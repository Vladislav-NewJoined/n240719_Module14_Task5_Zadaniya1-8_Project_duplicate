package task5_2_4;

public class Task5_2_4 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №2:\s
                    4. Поймайте в дебаггере метод toString у класса Object\s

                Решение:\s""");

        Object obj = new Object();
        System.out.println(obj.toString());
        System.out.println();
        System.out.println("Метод toString пойман в дебаггере на строке № 23.");
    }
}

class Object {

    @Override
    public String toString() {
        return "Это тестовый класс Object.";
    }
}



