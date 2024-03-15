package a_Shablony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// источник: https://ru.hexlet.io/qna/java/questions/kak-ispolzovat-metod-iz-drugogo-klassa-java?ysclid=lriys3yarr14846829
//public class CallMethodFromAnotherClass {
//    public static void main(String[] args) {
//        // Создаем объект класса
//        Greetings greetings = new Greetings();
//        greetings.printHello(); // => Hello
//        // Вызываем метод
//        Greetings.printHexlet(); // => Hexlet
//        // Вызываем статический метод
//    }
//}
//
//class Greetings {
//    public void printHello() {
//        System.out.println("Hello");
//    }
//
//    public static void printHexlet() {
//        System.out.println("Hexlet");
//    }
//}




//// ПРИМЕР 5
//// источник: https://ru.hexlet.io/qna/java/questions/kak-ispolzovat-metod-iz-drugogo-klassa-java?ysclid=lriys3yarr14846829
//public class CallMethodFromAnotherClass {
//    public static void main(String[] args) {
//        // Создаем объект класса
//        Greetings greetings = new Greetings();
//        greetings.printHello(); // => Hello
//        // Вызываем метод
//        Greetings.printHexlet(); // => Hexlet
//        // Вызываем статический метод
//    }
//}
//
//class Greetings {
//    public void printHello() {
//        System.out.println("Hello");
//    }
//
//    public static void printHexlet() {
//        System.out.println("Hexlet");
//    }
//}
//// КОНЕЦ ПРИМЕРА 5


//// ПРИМЕР 4_2
//public class CallMethodFromAnotherClass {
//    public static void main(String[] args) throws IOException {
//        String myLocalVar;
//        String myLocalVar2;
//
//        MyClass myClass = new MyClass();
//        myClass.myClass();
//        myLocalVar = myClass.myVarVal1;
//        System.out.println(myLocalVar);
//
//        MyClass myClass2 = new MyClass();
////        myClass2.myClass();
//        myLocalVar2 = myClass2.myVarVal2;
//        System.out.println("Вызываем вторую переменную myVarVal2 из класса MyKlass, она тоже " +
//                "не в методе, она в шапке класса " + myLocalVar2);
//
//
//
//
//
//
//
//
////        String myLocalVar;
////        String myLocalVar2;
////
////        MyClass myClass = new MyClass();
////        myLocalVar = myClass.myVarVal1;
////        System.out.println(myLocalVar);
////
////        MyClass myClass2 = new MyClass();
////        myClass2.myClass();
////        myLocalVar2 = myClass2.myVarVal2;
////        System.out.println("Call second variable from MyClass: " + myLocalVar2);
//    }
//}
//
//class MyClass{
//    String myVarVal1 = "Вызываем первую переменную myVarVal1 из класса MyClass, она не в методе, она в шапке класса";
//    String myVarVal2;
//
//    void myClass() throws IOException {
//        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Введите в следующей строке исходную дату с разделителем '/' " +
//                "и нажмите Enter, пример: 14/02/2020");
//        myVarVal2 = buffered.readLine();  // Start date
//    }
//}
//// КОНЕЦ ПРИМЕРА  4_2


//// ПРИМЕР 4
public class CallMethodFromAnotherClass {
    public static void main(String[] args) throws IOException {
        String myLocalVar1;
        String myLocalVar2;

        MyClass myClass = new MyClass();
        myLocalVar1 = myClass.myVarVal1;
        System.out.println(myLocalVar1);

        MyClass myClass2 = new MyClass();
        myClass2.myClass();
        myLocalVar2 = myClass2.myVarVal2;
        System.out.println("Вызываем вторую переменную myVarVal2 из класса MyKlass, она тоже " +
                "не в методе, она в шапке класса: " + myLocalVar2);
    }
}

class MyClass{
    static String myVarVal1 = "Вызываем первую переменную myVarVal1 из класса MyClass, она не в методе, она под заголовком самого класса";
    static String myVarVal2;

    void myClass() throws IOException {
        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите в следующей строке исходную дату с разделителем '/' " +
                "и нажмите Enter, пример: 14/02/2020");
        myVarVal2 = buffered.readLine();  // Start date
    }
}
//// КОНЕЦ ПРИМЕРА  4


//// ПРИМЕР 3
//// +java как вызвать метод из другого класса
//// +java how to call method from another class
//// CallMethodFromAnotherClass
//// Проект, package и класс вот эти:
//// Draft230928 / task1_4_2_2 / Task1_4_2_2.java
//public class CallMethodFromAnotherClass {
//    public static void main(String[] args) {
//        PrintSomeText ps = new PrintSomeText();
//        ps.printSome(); // => Some text printed!
//
//        PrintSomeText ps2 = new PrintSomeText();
//        ps2.printSomeAnother(); // => Some ANOTHER text printed!
//    }
//}
//
//class PrintSomeText {
//    public void printSome() {
//        System.out.println("Some text printed!");
//    }
//
//    public void printSomeAnother() {
//        System.out.println("Some ANOTHER text printed!");
//    }
//}
//// КОНЕЦ ПРИМЕРА 3




//// ПРИМЕР 2
//// источник: https://ru.hexlet.io/qna/java/questions/kak-ispolzovat-metod-iz-drugogo-klassa-java?ysclid=lriys3yarr14846829
//public class CallMethodFromAnotherClass {
//    public static void main(String[] args) {
//        // Создаем объект класса
//        Greetings greetings = new Greetings();
//        greetings.printHello(); // => Hello
//        // Вызываем метод
//        Greetings.printHexlet(); // => Hexlet
//        // Вызываем статический метод
//    }
//}
//
//class Greetings {
//    public void printHello() {
//        System.out.println("Hello");
//    }
//
//    public static void printHexlet() {
//        System.out.println("Hexlet");
//    }
//}
//// КОНЕЦ ПРИМЕРА 2




//// ПРИМЕР 2_2
//// источник: https://ru.hexlet.io/qna/java/questions/kak-ispolzovat-metod-iz-drugogo-klassa-java?ysclid=lriys3yarr14846829
//public class CallMethodFromAnotherClass {
//    public static void main(String[] args) {
//        // Создаем объект класса
//        Greetings greetings = new Greetings();
//        // Вызываем метод
//        greetings.printHello(); // => Hello
//        // Вызываем статический метод
//        Greetings.printHexlet(); // => Hexlet
//    }
//}
//
//class Greetings {
//    public void printHello() {
//        System.out.println("Hello");
//    }
//
//    public static void printHexlet() {
//        System.out.println("Hexlet");
//    }
//}
//// КОНЕЦ ПРИМЕРА 2_2


