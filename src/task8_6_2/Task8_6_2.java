package task8_6_2;

public class Task8_6_2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("""
                Задание:\s
                Модуль 8. Многопоточность. Задание №6. Проект:\s
                    2. Создать класс реализующий Runnable
                         Создать класс, реализующий интерфейс Runnable.
                         Переопределить run() метод. Создать цикл for. В цикле распечатываем значения от 0 до 100 делящиеся на 10 без остатка.
                         Используем статический метод Thread.sleep(), чтобы сделать паузу.
                         Создать три потока, выполняющих задачу распечатки значений.

                Решение:\s""");

        Thread t1 = new Thread(new T1());
        Thread t2 = new Thread(new T2());
        Thread t3 = new Thread(new T3());
        Thread t4 = new Thread(new T4());

        try {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
            t4.start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class T1 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(12);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("""
            1. Реализуем решение по заданию:
                - Создать класс, реализующий интерфейс Runnable.
                - Реализовано на примере создания счётчика от 1-го до 5-ти:""");

            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Runnable count " + (i + 1));
            }
            System.out.println();
        }
    }

    static class T2 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(12);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("""
            2. Реализуем решение по заданию:
                - Переопределить run() метод. Создать цикл for. В цикле распечатываем значения
                  от 0 до 100 делящиеся на 10 без остатка:""");

            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i % 10 == 0) {
                    System.out.println(i);
                }
            }
            System.out.println();
        }
    }

    static class T3 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(12);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("""
            3. Реализуем решение по заданию:
                - Используем статический метод Thread.sleep(), чтобы сделать паузу.
                - Реализовано путём создания паузы 1200 миллисекунд между выводом на печать\s
                  значений счетчика '1' и '2':""");

            for (int i = 0; i < 2; i++) {
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Counter value: " + (i + 1));
            }
            System.out.println();
        }
    }

    static class T4 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(12);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("""
            4. Реализуем решение по заданию:\s
                - Создать три потока, выполняющих задачу распечатки значений.
                - Реализовано на примере вывода уведомлений о старте и финише трёх потоков:""");
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 3; i++) {
                System.out.println("Thread" + (i + 1) + " started");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < 3; i++) {
                System.out.println("Thread" + (i + 1) + " finished");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.exit(0);
        }
    }
}




//// ПРИМЕР 2 _Всё работает, но вместе с Заданием 1.
//public class Task8_6_2 {
//    public static void main(String[] args) throws InterruptedException {
//        System.out.println("""
//                Задание:\s
//                Модуль 8. Многопоточность. Задание №6. Проект:\s
//                    2. Создать класс реализующий Runnable
//                         Создать класс, реализующий интерфейс Runnable.
//                         Переопределить run() метод. Создать цикл for. В цикле распечатываем значения от 0 до 100 делящиеся на 10 без остатка.
//                         Используем статический метод Thread.sleep(), чтобы сделать паузу.
//                         Создать три потока, выполняющих задачу распечатки значений.
//
//                Решение:\s""");
//
//        Thread t1 = new Thread(new T1());
//        Thread t2 = new Thread(new T2());
//        Thread t3 = new Thread(new T3());
//        Thread t4 = new Thread(new T4());
//        Thread t5 = new Thread(new T5());
//        Thread t6 = new Thread(new T6());
//        Thread t7 = new Thread(new T7());
//
//        t1.start();
//        try {
//            t1.join();
//            t2.start();
//            t2.join();
//            t3.start();
//            t3.join();
//            t4.start();
//            t4.join();
//            t5.start();
//            t5.join();
//            t6.start();
//            t6.join();
//            t7.start();
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    static class T1 extends Thread {
//        @Override
//        public void run() {
//            super.run();
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("""
//            1. Реализуем решение по Заданию 1. Создать класс расширяющий Thread.
//            1. Создать класс NewThread расширяющий Thread.
//            Реализовано c помощью оператора getName().""");
//
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println(getName());
//            System.out.println();
//        }
//    }
//
//    static class T2 extends Thread {
//        @Override
//        public void run() {
//            super.run();
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("""
//            2. Реализуем решение по Заданию 1. Создать класс расширяющий Thread.
//            2.	Переопределить метод run(). В цикле for вывести на консоль
//            символ 100 раз.""");
//
//            for (int i = 0; i < 100; i++) {
//                try {
//                    Thread.sleep(25);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("№: " + i + " раз");
//            }
//            System.out.println();
//        }
//    }
//
//    static class T3 extends Thread {
//        @Override
//        public void run() {
//            super.run();
//            System.out.println("""
//            3. Реализуем решение по Заданию 1. Создать класс расширяющий Thread.
//            3.	Создать экземпляр класса и запустить новый поток.""");
//
//            System.out.println("СПОСОБ 1:");
//            System.out.println("Main thread started (Способ 1)...");
//            for (int i = 0; i < 3; i++) {
//                Thread t = new Thread(new MyThreadLogic());
//                t.start();
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            System.out.println("Main thread finished (Способ 1)...");
//            System.out.println();
//
//            System.out.println("СПОСОБ 2:");
//            System.out.println("Main thread started (Способ 2)...");
//            for (int i = 0; i < 3; i++) {
//                Thread t2 = new Thread(() -> {
//                    System.out.println("Способ 2");
//                }, "Thread");
//                t2.start();
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            System.out.println("Main thread finished (Способ 2)...");
//            System.out.println();
//        }
//    }
//
//    static class MyThreadLogic implements Runnable {
//        @Override
//        public void run() {
//            System.out.println("Способ 1");
//        }
//    }
//
//    static class T4 implements Runnable {
//        @Override
//        public void run() {
//            try {
//                Thread.sleep(12);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("""
//            4. Реализуем решение по Заданию 2. Создать класс реализующий Runnable.
//            1.	Создать класс, реализующий интерфейс Runnable.
//            Реализовано на примере создания счётчика от 1-го до 5-ти:""");
//
//            for (int i = 0; i < 5; i++) {
//                try {
//                    Thread.sleep(600);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("Runnable count " + (i + 1));
//            }
//            System.out.println();
//        }
//    }
//
//    static class T5 implements Runnable {
//        @Override
//        public void run() {
//            try {
//                Thread.sleep(12);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("""
//            5. Реализуем решение по Заданию 2. Создать класс реализующий Runnable.
//            2. Переопределить run() метод. Создать цикл for. В цикле распечатываем значения
//            от 0 до 100 делящиеся на 10 без остатка:""");
//
//            for (int i = 0; i <= 100; i++) {
//                try {
//                    Thread.sleep(30);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                if (i % 10 == 0) {
//                    System.out.println(i);
//                }
//            }
//            System.out.println();
//        }
//    }
//
//    static class T6 implements Runnable {
//        @Override
//        public void run() {
//            try {
//                Thread.sleep(12);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("""
//            6. Реализуем решение по Заданию 2. Создать класс реализующий Runnable.
//            3. Используем статический метод Thread.sleep(), чтобы сделать паузу.
//            Реализовано путём создания паузы 1200 миллисекунд между выводом на печать\s
//            значений счетчика '1' и '2':""");
//
//            for (int i = 0; i < 2; i++) {
//                try {
//                    Thread.sleep(1200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("Counter value: " + (i + 1));
//            }
//            System.out.println();
//        }
//    }
//
//    static class T7 implements Runnable {
//        @Override
//        public void run() {
//            try {
//                Thread.sleep(12);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println("""
//            7. Реализуем решение по Заданию 2. Создать класс реализующий Runnable.\s
//            4. Создать три потока, выполняющих задачу распечатки значений.
//            Реализовано на примере вывода уведомлений о старте и финише трёх потоков:""");
//            try {
//                Thread.sleep(400);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            for (int i = 0; i < 3; i++) {
//                System.out.println("Thread" + (i + 1) + " started");
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            for (int i = 0; i < 3; i++) {
//                System.out.println("Thread" + (i + 1) + " finished");
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.exit(0);
//        }
//    }
//}
//// КОНЕЦ ПРИМЕРА 2