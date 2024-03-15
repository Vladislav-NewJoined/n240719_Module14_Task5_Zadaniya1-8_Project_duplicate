package a_Shablony;

//// источник: https://www.youtube.com/watch?v=7FsDj1Otco8
//// все уроки: https://www.youtube.com/playlist?list=PLVfMKQXDAhGVWEKi2wKx4y-yNLk7QKam3
//// о канале: https://www.youtube.com/channel/UCe_H8hzx9WV7Ca7Ps5gt72Q/about,
//// канал: www.youtube.com/@SuprunAlexey
//public class CallMethodFromMethod {
//    public static void main(String[] args) {
//        System.out.println("Hello world");
//
//        hello();
//        welcome();
//
//
//        int a = 7;
//        int b = 8;
//        sum(a, b);
////        sum(10, 11);
//
//        sum("Hello", 9);
//
//        int z = sum(1, 3, 1000);
//        System.out.println(z);
//        sum(2, 5, 8888);
//
//        someStr1("OneTwoThree");
//        System.out.println(someStr1("Here it is the someStr1"));
//    }
//
//
//    // Тестовые методы начались:
//    static String someStr1(String someStr1) {
//        String sS = "OneTwoThree";
////        System.out.println(sS);
//        return sS;
//    }
//
//    static void hello() {
//        System.out.println("Hello ");
//    }
//
//    static void welcome() {
//        System.out.println("welcome world ");
//    }
//
//    static void sum(int x, int y) {
//        int z = x + y;
//        System.out.println(z);
//    }
//
//    static void sum(String x, int y) {
//        System.out.println(x);
//        System.out.println(y);
//    }
//
//    static int sum(int x, int y, int z) {
//        System.out.println(z);
//        CallMethodFromMethod cmf = new CallMethodFromMethod(); // это необязательно
//        cmf.sum("можно так, но это необязательно", 333); // так тоже необязательно
//        sum("String x + int y: вот так короче", 1111);
//        return x + y + z;
//    }
//}


//// ПРИМЕР 20
//// источник: Перегрузка методов https://youtu.be/z1dnIG5ZrKo
//public class CallMethodFromMethod {
//
//    static void method() {
//        method("String", 34, 23.7);
//    }
//
//    static void method(String str, int num, double num2) {
//        System.out.println(str + ", " + num + ", " + num2);
//    }
//
//    static void method(String str, int num, double num2, int num3) {
//        System.out.println(str + ", " + num + ", " + num2);
//    }
//
//    static void method(String str) {
//        method(str, 44, 33.3);
//    }
//
//    static void method(String str, int num) {
//        method(str, num, 777.7);
//    }
//
//    public static void main(String[] args) throws Exception {
//        method();
//        method("String2");  // String2 берет из метода String str, а числа берет из метода String str, int num, double num2
//        method("String3", 99);
//        method("String4", 34, 56.3);
//    }
//}
//// КОНЕЦ ПРИМЕРА 20



//// ПРИМЕР 19
//// источник: Читаю переменные из других методов. Перегрузка методов https://youtu.be/z1dnIG5ZrKo
//public class CallMethodFromMethod {
//
//    static void way() {
//        way("Прочитана переменная № ", 1);
//    }
//    static void way(String str, int num) {
//        System.out.println("Ещё ничего не прочитано. Хи-хи.");
//        System.out.println(str + num);
//    }
//
//    static void way(String str) {
//        way(str, "2");
//    }
//    static void way(String str, String str2) {
//        System.out.println(str + str2);
//    }
//
//    static void way(String str, String str2, int num) {
//        System.out.println(str + " " + str2 + " " + num);
//    }
//
//    static void way(int num) {
//        way("Прочитана", "переменная", "№ ");
//        System.out.println(num);
//    }
//
//    static void way(String str, String str2, String str3) {
//        System.out.print(str + " " + str2 + " " + str3);
//    }
//
//    static void way(String str8, String str9, String str10, String str11, String str12) {
//        way("Прочитано ", "м", "н", "о", "г", "о ");
//        System.out.println(str8 + str9 + str10 + str11 + str12);
//    }
//
//    static void way(String str, String str2, String str3, String str4, String str5, String str6) {
//        System.out.print(str + str2 + str3 + str4 + str5 + str6);
//    }
//
//    public static void main(String[] args) throws Exception {
//        way();
//        way("Прочитана переменная № ");
//        way("Прочитана", "переменная №", 3);
//        way(4);
//        way("пере", "м", "е", "н", "ных");
//    }
//}
//// КОНЕЦ ПРИМЕРА 19



//// ПРИМЕР 18
// Переменная должна быть static
// В классах, из которых вызывается должны быть методы void , void методы
// В классах, из которых извлекается должны быть методы void , void методы
// источник: https://www.youtube.com/watch?v=7FsDj1Otco8
// все уроки: https://www.youtube.com/playlist?list=PLVfMKQXDAhGVWEKi2wKx4y-yNLk7QKam3
// о канале: https://www.youtube.com/channel/UCe_H8hzx9WV7Ca7Ps5gt72Q/about,
// канал: www.youtube.com/@SuprunAlexey
public class CallMethodFromMethod {
    public static void main(String[] args) {
//        someStr1();
//        System.out.println(someStr());
        System.out.println(someStr1());
        someStr2("");
//        someStr2("Вторая переменная str прочитана!");
        someStr3();
        System.out.println(someStr5("Четвёртая переменная str из static String someStr5(String str4) прочитана!"));
        someStr6("");
        System.out.println(someStr7("Ничего нет"));
    }



    // Тестовые методы начались:
    public static String someStr() {
        String str = "Ха-ха! Пока ещё ничего из static String someStr() не прочитано";
//        System.out.println("Ха-ха! Пока ещё ничего не прочитано");
        return str;
    }

    public static String someStr7(String str7) {
        str7 = "Много переменных str из static String someStr7(String str7) прочитано!";
        return str7;
    }

    public static String someStr6(String str6) {
        str6 = "Пятая переменная str из static String someStr6(String str6) прочитана!";
        System.out.println(str6);
//        String someStr6 = "Всё закончилось. Тут теперь ничего нет";
//        System.out.println(someStr6);
        return str6;
    }

    public static String someStr5(String str4) {
        String str5 = str4;
        return str5;
    }

    public static void someStr4() {
        String str3 = "Третья переменная str из void someStr4() прочитана!";
        System.out.println(str3);
    }

    public static void someStr3() {
        someStr4();
    }

    public static void someStr2(String someS) {
        String str2 = "Вторая переменная str из void someStr2 (String someS) прочитана!";
        System.out.println(str2);
    }

    public static String someStr1() {
        System.out.println(someStr());
        return "Первая переменная str из static String someStr1() прочитана!";
    }
}
//// КОНЕЦ ПРИМЕРА 18


//// ПРИМЕР 18_2
//// Переменная должна быть static
//// В классах, из которых вызывается должны быть методы void , void методы
//// В классах, из которых извлекается должны быть методы void , void методы
//// источник: https://www.youtube.com/watch?v=7FsDj1Otco8
//// все уроки: https://www.youtube.com/playlist?list=PLVfMKQXDAhGVWEKi2wKx4y-yNLk7QKam3
//// о канале: https://www.youtube.com/channel/UCe_H8hzx9WV7Ca7Ps5gt72Q/about,
//// канал: www.youtube.com/@SuprunAlexey
//public class CallMethodFromMethod {
//    public static void main(String[] args) {
////        someStr1();
////        System.out.println(someStr());
//        System.out.println(someStr1());
//        someStr2("");
////        someStr2("Вторая переменная str прочитана!");
//        someStr3();
//        System.out.println(someStr5("Четвёртая переменная str прочитана!"));
//        someStr6("");
//        System.out.println(someStr7("Ничего нет"));
//    }
//
//
//
//    // Тестовые методы начались:
//    public static String someStr() {
//        String str = "Ха-ха! Пока ещё ничего не прочитано";
////        System.out.println("Ха-ха! Пока ещё ничего не прочитано");
//        return str;
//    }
//
//    public static String someStr7(String str7) {
//        str7 = "Много переменных str прочитано!";
//        return str7;
//    }
//
//    public static String someStr6(String str6) {
//        str6 = "Пятая переменная str прочитана!";
//        System.out.println(str6);
////        String someStr6 = "Всё закончилось. Тут теперь ничего нет";
////        System.out.println(someStr6);
//        return str6;
//    }
//
//    public static String someStr5(String str4) {
//        String str5 = str4;
//        return str5;
//    }
//
//    public static void someStr4() {
//        String str3 = "Третья переменная str прочитана!";
//        System.out.println(str3);
//    }
//
//    public static void someStr3() {
//        someStr4();
//    }
//
//    public static void someStr2(String someS) {
//        String str2 = "Вторая переменная str прочитана!";
//        System.out.println(str2);
//    }
//
//    public static String someStr1() {
//        System.out.println(someStr());
//        return "Первая переменная str прочитана!";
//    }
//}
//// КОНЕЦ ПРИМЕРА  18_2


//// ПРИМЕР 17
//// Переменная должна быть static, переменная должна быть объявлена в шапке класса с модификатором static
//// В классах, из которых вызывается должны быть методы void , void методы
//// В классах, из которых извлекается должны быть методы void , void методы
//// источник: Извлечь переменную static сверху за пределами main метода Return не предлагать он уже занят и его менять нельзя , https://qna.habr.com/q/341470
//// В том числе из другого класса
//// "Я - ничто" - "Я -всё!"
//public class CallMethodFromMethod {
//    // Это решение, а задание ниже
//    // Переменная должна быть static!
//    static int a = 0;
//    static String str = "Я - ничто";
//    static String str2 = "Переменная должна быть НЕ STATIC";
//    static int x = 1;
//    static int y = 1;
//    static int z = x + y;
//    static int number = 100;
//
//    public static void main(String[] args) {
//
//        TestClassC.c();
//
//        ReadFromNumber rfn = new ReadFromNumber();
//        rfn.bNumber(NumberClass.number);
//
//        ReadFromNumber rfn2 = new ReadFromNumber();
//        rfn2.bNumber(NumberClass.number);
//
//    }
//}
//
//class TestClassAB {
//    static int a = CallMethodFromMethod.a;
//    static String str = CallMethodFromMethod.str;
//    static String str2 = CallMethodFromMethod.str2;
//    static int x = CallMethodFromMethod.x;
//
//    static void a() {
//        a = 88;
//        str = "Я - всё!";
//        str2 = "Переменная должна быть static!";
//    }
//
//    static void b() {
//        System.out.println("Привет из static void b()");
//        a();
//        a = 99;
//        System.out.println("В static void b() переменная a равна " + a);
//        System.out.println("В static void b() переменная x равна " + TestClassAB.x);
//
//    }
//}
//
//class TestClassC {
//    static int x = 150;
//    static int y = 350;
//    static int z = x + y;
//
//    static void c() {
//     TestClassAB.b();
//
//     int x = 35;
//     int y = 15;
//     z = x + y;
//     System.out.println("Из самого метода c(): Сумма x и y равна: " + z);
//    }
//}
//
//    // Это задание
//class NumberClass {
//    static int number = 4;
//
//    void aNumber() {
////        number = 5;
//        number = NumberClass.number + 1;
//    }
//}
//
//
//class ReadFromNumber {
//
//    public void bNumber(int number) {
//        // Как считать переменную number?
//        // return не предлагать он уже занят и его менять нельзя
//        NumberClass number1 = new NumberClass();
//        number1.aNumber();
//        int number2 = NumberClass.number;
//        System.out.println("Считана переменная number из метода bNumber, её значение: " + number2);
//    }
//}
//// КОНЕЦ ПРИМЕРА 17




//// ПРИМЕР 17_2
//// Переменная должна быть static
//// В классах, из которых вызывается должны быть методы void , void методы
//// В классах, из которых извлекается должны быть методы void , void методы
//// источник: Извлечь переменную static сверху за пределами main метода Return не предлагать он уже занят и его менять нельзя , https://qna.habr.com/q/341470
//// В том числе из другого класса
//// "Я - ничто" - "Я -всё!"
//public class CallMethodFromMethod {
//    // Это решение, а задание ниже
//    // Переменная должна быть static!
//    static int a = 0;
//    static String str = "Я - ничто";
//    static String str2 = "Переменная должна быть НЕ STATIC";
//    static int x = 1;
//    static int y = 1;
//
//    public static void main(String[] args) {
//        TestClass.c();
//    }
//}
//
//class TestClass {
//    static int a = CallMethodFromMethod.a;
//    static String str = CallMethodFromMethod.str;
//    static String str2 = CallMethodFromMethod.str2;
//    static int x = CallMethodFromMethod.x;
//    static int y = CallMethodFromMethod.y;
//
//    static void a() {
//        a = 88;
//        str = "Я - всё!";
//        str2 = "Переменная должна быть static!";
//    }
//
//    static void b() {
//        a();
//        System.out.println("Переменная a равна: " + a);
//        System.out.println("Переменная str равна: " + str);
//        System.out.println(str2);
//    }
//
//    static void c() {
//        b();
//
//        x = 35;
//        y = 15;
//        int z = x + y;
//        System.out.println("Сумма x и y равна: " + z);
//    }
//}
//
////    // Это задание
////    private void a() {
////        int number = 5;
////    }
////
////    private void b() {
////        // Как считать переменную number?
////        // return не предлагать он уже занят и его менять нельзя
////    }
//// КОНЕЦ ПРИМЕРА 17_2



//// ПРИМЕР 16
//// источник: Return не предлагать он уже занят и его менять нельзя , https://qna.habr.com/q/341470
//public class CallMethodFromMethod {
//    static int a = 0;
//    static String str = "Я - ничто";
//    static int x = 1;
//    static int y = 1;
//
//    public static void main(String[] args) {
//        c();
//    }
//
//    public static void a() {
//        a = 88;
//        str = "Я - всё!";
//    }
//
//    public static void b() {
//        a();
//        System.out.println("Переменная a равна: " + a);
//        System.out.println("Переменная str равна: " + str);
//    }
//
//    public static void c() {
//        b();
//
//        x = 35;
//        y = 15;
//        int z = x + y;
//        System.out.println("Сумма x и y равна: " + z);
//    }
//}
//
////    // Это задание
////    private void a() {
////        int number = 5;
////    }
////
////    private void b() {
////        // Как считать переменную number?
////        // return не предлагать он уже занят и его менять нельзя
////    }
//// КОНЕЦ ПРИМЕРА 16



//// ПРИМЕР 15
//// источник: Return не предлагать он уже занят и его менять нельзя , https://qna.habr.com/q/341470
//public class CallMethodFromMethod {
//    // Внизу задание, здесь решение (всё дело в модификаторе static перед названием переменной)
//    static int number = 0;
//    static String str = "Я ничто";
//
//    public static void main(String[] args) {
//        b();
////        System.out.println(number);
////        System.out.println(str);
//    }
//    public static void a() {
//        number = 5;
//        str = "Я всё!";
//    }
//
//    public static void b() {
//        a();
//        System.out.println(number);
//        System.out.println(str);
//    }
//}
//
////    // Это задание
////    private void a() {
////        int number = 5;
////    }
////
////    private void b() {
////        // Как считать переменную number?
////        // return не предлагать он уже занят и его менять нельзя
////    }
//// КОНЕЦ ПРИМЕРА 15



//// ПРИМЕР 14
//// ОЧЕНЬ ХОРОШАЯ! Перегрузка методов, источник: https://youtu.be/O-69kG6-wxw
//public class CallMethodFromMethod {
//    public static int sum(int a, int b) {
//        return a + b;
//    }
//
//    public static int sum(int a, int b, int g) {
//        return a + b + g;
//    }
//
//    public static float sum(float a, int b, float g) {
//        return a + b + g;
//    }
//
//
//    public static void main(String[] args) {
//        System.out.println(sum(23, 12));
//        System.out.println(sum(23.23f, 12, 15.17f));
//    }
//}
//// КОНЕЦ ПРИМЕРА 14




//// ПРИМЕР 13
//// источник: https://javarush.com/groups/posts/1381-metodih-v-java
//public class CallMethodFromMethod {
//    public String constructHelloSentence(String name) {
//        String resultSentence = "Hello world! My name is " + name;
//        System.out.println(resultSentence);
//        return resultSentence;
//    }
//}

//    // Тестовые классы начались:
//    class Application {
//        public static void main(String[] args) {
//            CallMethodFromMethod cM = new CallMethodFromMethod();
//            cM.constructHelloSentence("Dany");
//        }
//    }
//// КОНЕЦ ПРИМЕРА 13


//// ПРИМЕР 12
//// источник: https://www.youtube.com/watch?v=7FsDj1Otco8
//// все уроки: https://www.youtube.com/playlist?list=PLVfMKQXDAhGVWEKi2wKx4y-yNLk7QKam3
//// о канале: https://www.youtube.com/channel/UCe_H8hzx9WV7Ca7Ps5gt72Q/about,
//// канал: www.youtube.com/@SuprunAlexey
//public class CallMethodFromMethod {
//    public static void main(String[] args) {
//        System.out.println("Hello world");
//
//        hello();
//        welcome();
//
//        int a = 7;
//        int b = 8;
//        sum(a, b);
////        sum(10, 11);
//
//        sum("Hello", 9);
//
//        int z = sum(1, 3, 1000);
//        System.out.println(z);
//        sum(2, 5, 2000);
//    }
//
//
//    // Другие методы начались:
//    static void hello() {
//        System.out.println("Hello ");
//    }
//
//    static void welcome() {
//        System.out.println("welcome world ");
//    }
//
//    static void sum(int x, int y) {
//        int z = x + y;
//        System.out.println(z);
//    }
//
//    static void sum(String x, int y) {
//        System.out.println(x);
//        System.out.println(y);
//    }
//
//    static int sum(int x, int y, int z) {
//        System.out.println(z);
//        return x + y + z;
//    }
//}
//// КОНЕЦ ПРИМЕРА 12


//// ПРИМЕР 11
//// источник: https://youtu.be/YuhItH5wwvE
//public class CallMethodFromMethod {
//    public static void main(String[] args) {
//        StringBuilder name = new StringBuilder();
//        speak(name);
//        System.out.println(name);
//    }
//
//    public static void speak (StringBuilder s) {
//        s.append("Матвей Внутри");
//    }
//}
//// КОНЕЦ ПРИМЕРА 11


//// ПРИМЕР 10
//// источник: https://youtu.be/YuhItH5wwvE
//public class CallMethodFromMethod {
//    public static void main(String[] args) {
//        String name = "Матвей";
//        name = speak(name);
////        speak(name);
//        System.out.println(name);
//    }
//
//    //    public static void speak (String name) {
////        или
//    public static String speak (String name) {
//        System.out.println(name + 2);
////        или
//        return name = name + 2;
//    }
//}
//// КОНЕЦ ПРИМЕРА 10



//// ПРИМЕР 9
//// источник: https://youtu.be/YuhItH5wwvE
//public class CallMethodFromMethod {
//    public static void main(String[] args) {
//        int num = 4;
//        num = newNumber(5);
////        или
////        newNumber(5);
//        System.out.println(num);
//    }
//
//    public static int newNumber(int num) {
//        return num = 8;
//    }
//}
//// КОНЕЦ ПРИМЕРА 9


//// ПРИМЕР 8
//// источник: https://ru.stackoverflow.com/questions/899840/%D0%9A%D0%B0%D0%BA-%D0%B8%D0%B7-%D0%BC%D0%B5%D1%82%D0%BE%D0%B4%D0%B0-%D0%B2%D1%8B%D0%B7%D0%B2%D0%B0%D1%82%D1%8C-%D0%B4%D1%80%D1%83%D0%B3%D0%BE%D0%B9-%D0%BC%D0%B5%D1%82%D0%BE%D0%B4?ysclid=lsoa4aww973213957
//public class CallMethodFromMethod {
//    public static void main(String[] args) {
//        Example obj = new Example();
//        obj.someMethod().anotherMethod();
//    }
//}
//class Example {
//    public Example someMethod() {
//        System.out.println("Hello");
//        return this;
//    }
//
//    public Example anotherMethod() {
//        System.out.println("World");
//        return this;
//    }
//}
//// КОНЕЦ ПРИМЕРА 8


//// ПРИМЕР 7
//// источник: https://youtu.be/z1dnIG5ZrKo
//public class CallMethodFromMethod {
//
//    static void method() {
//        method("String", 34, 23.7);
//    }
//
//    static void method(String str, int num, double num2) {
//        System.out.println(str + ", " + num + ", " + num2);
//    }
//
//    static void method(String str, int num, double num2, int num3) {
//        System.out.println(str + ", " + num + ", " + num2);
//    }
//
//    static void method(String str) {
//        method(str, 44, 33.3);
//    }
//
//    static void method(String str, int num) {
//        method(str, num, 777.7);
//    }
//
//    public static void main(String[] args) throws Exception {
//        method();
//        method("String2");  // String2 берет из метода String str, а числа берет из метода String str, int num, double num2
//        method("String3", 99);
//        method("String4", 34, 56.3);
//    }
//}
//// КОНЕЦ ПРИМЕРА 7


//// ПРИМЕР 6
//// источник: https://www.youtube.com/watch?v=7EpuKw9R7go
//public class CallMethodFromMethod {
//    public static void main(String[] args) throws Exception {
//        int x = sum(5, 7);
//        System.out.println(x);
//        name("Vlad");
//        System.out.println(max(10, 20));
//        System.out.println(fullName("John", "Majong"));
//
//    }
//
//    public static int max(int a, int b) {
//
//        if (a>b) return a;
//        else return b;
//
//
//
////        if (a > b) {
////            max = a;
////        } else {
////            max = b;
////        }
////        return max;
//    }
//
//    public static int sum (int a, int b) {
//        int sum = a + b;
//        return sum;
//    }
//
//    public static String fullName(String name, String surname) {
//        String fullName = name + " " + surname;
//
//        return fullName;
//    }
//
//    public static void name (String s) {
//        System.out.println("Your name is " + s);
//    }
//}
//// КОНЕЦ ПРИМЕРА 6



//// ПРИМЕР 5
//// источник: https://javarush.com/help/26995
//public class CallMethodFromMethod {
//    private BufferedReader reader;
//    //зачем промежуточная переменная sHour?
//    //вы ее как-то валидируете? проверяете на null? на то, что это число?
//    String sHour = reader.readLine();
//    int hour = Integer.parseInt(sHour);
//
//    public CallMethodFromMethod() throws IOException {
//    }
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Введите количество отработанных часов");
//        int hour = Integer.parseInt(reader.readLine());
//        int vadimStavka = 250;
//        double kursUa = 2.67;
//
//        System.out.println("К выплате Вадиму: " + (hour * vadimStavka) + " рублей");
//        System.out.println("или " + (Math.round(hour * vadimStavka / kursUa)) + " гривен.");
//    }
//}
//// КОНЕЦ ПРИМЕРА 5


//// ПРИМЕР 4
//// источник: https://javarush.com/help/26995
//public class CallMethodFromMethod {
//    public static void main(String[] args) throws Exception {
//        int zpVadimaInRub = raschetZpVadima();
//        double kursUa = 2.67;
//        double  zpVadimaInUa = zpVadimaInRub / kursUa;
//
//        System.out.println("К выплате Вадиму: " + zpVadimaInRub + " рублей");
//        System.out.println("или " + zpVadimaInUa + " гривен.");
//
//    }
//    public static int raschetZpVadima() throws Exception {
//        int vadimStavka = 250;
//        System.out.println("Введите количество отработанных часов");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String sHour = reader.readLine();
//        int hour = Integer.parseInt(sHour);
//        int zpVadima = hour * vadimStavka;
//        return zpVadima;
//    }
//}
//// КОНЕЦ ПРИМЕРА 4


//// ПРИМЕР 3
//// источник: https://javarush.com/help/26995
//public class CallMethodFromMethod {
//    public static int raschetZpVadima() throws Exception {
//        int vadimStavka = 250;
//        System.out.println("Введите количество отработанных часов");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String sHour = reader.readLine();
//        int hour = Integer.parseInt(sHour);
//        int zpVadima = hour * vadimStavka;
//        return zpVadima;
//    }
//
//    public static double convertUa() throws Exception {
//        CallMethodFromMethod zpVadima = new CallMethodFromMethod();
//        return zpVadima.raschetZpVadima() / 2.67;
//    }
//
//    public static void main(String[] args) throws Exception {
//        System.out.println("К выплате Вадиму: " + raschetZpVadima() + " рублей");
//        System.out.println("или " + convertUa() + " гривен.");
//    }
//}
//// КОНЕЦ ПРИМЕРА 3


// ПРИМЕР 2
//// источник: https://ru.hexlet.io/qna/java/questions/kak-ispolzovat-metod-iz-drugogo-klassa-java?ysclid=lriys3yarr14846829
//public class CallMethodFromMethod {
//    public static void main(String[] args) {
//        // Создаем объект класса
//        Greetings2 greetings2 = new Greetings2();
//        // Вызываем метод
//        greetings2.printHello(); // => Hello
//        // Вызываем статический метод
//        Greetings2.printHexlet(); // => Hexlet
//    }
//}
//
//class Greetings2 {
//    public void printHello() {
//        System.out.println("Hello");
////        Greetings2.printHexlet(); // => Hexlet
//    }
//
//    public static void printHexlet() {
//        System.out.println("Hexlet");
////        Greetings2 greetings = new Greetings2();
////        greetings.printHello(); // => Hello
//
//    }
//}
// КОНЕЦ ПРИМЕРА 2
