package a_shablony_A_Exercises;

public class Exercises {
    public static void main(String[] args) {
        System.out.println("""
                Решение:\s""");

        Helper helper = new Helper(); // Создаем объект класса Helper
        String greeting = helper.getHello(); // Вызываем метод getHello() и сохраняем результат в переменную
        System.out.println(greeting); // Выводим результат на экран

        Helper2 helper2 = new Helper2(); // Создаем объект класса Helper2
        helper2.method2("", "PARAMETER2"); // Вызываем метод method2()
        System.out.println(helper2.getHello); // Выводим результат на экран

        Helper3 helper3 = new Helper3(); // Создаем объект класса Helper2
        helper3.parameter3("PARAMETER4", "PARAMETER5"); // Вызываем метод method2()
        System.out.println(helper3.parameter3);
    }
}

// Класс, содержащий метод, возвращающий строку
class Helper {
    public String getHello() {
        return "Привет из класса Helper!";
    }
}

class Helper2 {
    String getHello;

    public void method2(String parameter1, String parameter2) {
        getHello = "Привет из класса Helper2! Значение parameter2 = " + parameter2; // Инициализация переменной getHello в методе

    }
}

class Helper3 {
    String parameter3;

    public String parameter3(String parameter4, String parameter5) {
        parameter3 = "Привет из класса Helper3! Значение parameter3 = PARAMETER3" +
                ", Значение parameter4 = " + parameter4 +
                ", Значение parameter5 = " + parameter5; // Инициализация переменной parameter3 в методе

        return parameter3; // Возвращаем значение parameter3
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
