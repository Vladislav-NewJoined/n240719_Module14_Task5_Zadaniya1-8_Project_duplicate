package a_shablony_A_Exercises;

import java.io.IOException;

public class Exercises {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 5. ...:\s
                    1. ...\s

                Решение:\s""");

        System.out.println("""
                \nSome text.\s""");

        Helper helper = new Helper(); // Создаем объект класса Helper
        String greeting = helper.getHello(); // Вызываем метод getHello() и сохраняем результат в переменную
        System.out.println(greeting); // Выводим результат на экран
    }
}

// Класс, содержащий метод, возвращающий строку
class Helper {
    public String getHello() {
        return "Hello, World!";
    }
}





//// ПРИМЕР 2
//import java.io.IOException;
//
//// Класс, содержащий метод, без возвращаемого значения
//public class Exercises {
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                \n...если в методе getHello() отсутствует строка возвращения значения (return
//                "Hello, World!";), то переменную с этим значением будет невозможно использовать
//                в других методах или классах, так как метод не возвращает никакого значения, а
//                значит переменной не будет присвоено никакого значения.\s
//
//                Ниже привожу пример измененного кода, где отсутствует строка return в методе
//                getHello():\s""");
//
//        Helper helper = new Helper(); // Создаем объект класса Helper
//        helper.getHello(); // Вызываем метод getHello(), который не возвращает значение
//        // Попытка использовать переменную greeting приведет к ошибке, так как переменная определена внутри
//        // метода и не видна вне его
//    }
//}
//
//// Класс, содержащий метод, без возвращаемого значения
//class Helper {
//    public void getHello() {
//        String greeting = "Hello, World!"; // Присвоение значения переменной greeting
//    }
//}
//// КОНЕЦ ПРИМЕРА 2
