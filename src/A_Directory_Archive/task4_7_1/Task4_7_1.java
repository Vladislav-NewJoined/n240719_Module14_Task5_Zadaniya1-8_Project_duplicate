package task4_7_1;

public class Task4_7_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №7:\s
                    1. В чем разница между throw и throws в Java?\s

                Решение:\s""");

        System.out.println("""
                Разница между throw и throws в Java заключается в их использовании:
                1. throw: оператор throw используется для генерации исключения внутри метода. Он
                   указывает на то, что внутри блока кода возникла ошибка или неожиданное условие,
                   и нужно сгенерировать исключение.

                Пример использования:
                throw new Exception("An error occurred");

                2. throws: ключевое слово throws используется в объявлении метода для указания на
                   то, что метод может выбросить определенное исключение. Когда метод объявлен с
                   ключевым словом throws, вызывающий код должен обработать это исключение или
                   передать его дальше.

                Пример использования:
                public void myMethod() throws IOException {
                    // код метода
                }

                Таким образом, throw используется для генерации исключений, а throws используется
                для указания на возможные исключения, которые метод может выбросить.""");
    }
}