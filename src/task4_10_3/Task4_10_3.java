package task4_10_3;

public class Task4_10_3 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №10:\s
                    3. Как уберечься от\s

                Решение:\s""");

        System.out.println("Существует несколько способов избежать проблем с null:");
        System.out.println();
        System.out.println("1. Проверка на null перед использованием переменной:");
        System.out.println("""
                String str = null;
                if (str != null) {
                // Используем переменную str безопасно
                } else {
                    System.out.println("Переменная str содержит значение null");
                }
                """);

        System.out.println("2. Использование оператора условного null (Java 8 и выше):");
        System.out.println("""
                String str = null;
                String result = (str != null) ? str : "default";
                System.out.println(result); // Если str не равно null, то будет выведено его значение, иначе "default"
                """);

        System.out.println("3. Обработка NullPointerException с помощью оператора try-catch:");
        System.out.println("""
                String str = null;
                try {
                    int length = str.length();
                } catch (NullPointerException e) {
                    System.out.println("NullPointerException caught");
                }
                """);

        System.out.println("и другие.");
    }
}