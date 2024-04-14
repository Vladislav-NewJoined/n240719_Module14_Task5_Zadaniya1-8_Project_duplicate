package task8_1_3;

public class Task8_1_3 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("""
                Задание:\s
                Модуль 8. Многопоточность. Задание №1:\s
                    3. Создать экземпляр класса и запустить новый поток.\s

                Решение:\s""");

        System.out.println("Создаём экземпляр класса и запускаем новый поток");
        System.out.println("СПОСОБ 1:");
        System.out.println("Main thread started (Способ 1)...");
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(new MyThreadLogic());
            t.start();
            Thread.sleep(300);
        }
        System.out.println("Main thread finished (Способ 1)...");
        System.out.println();

        System.out.println("СПОСОБ 2:");
        System.out.println("Main thread started (Способ 2)...");
        for (int i = 0; i < 3; i++) {
            Thread t2 = new Thread(() -> {
                System.out.println("Способ 2");
            },"Thread");
            t2.start();
            Thread.sleep(300);
        }
        System.out.println("Main thread finished (Способ 2)...");
    }
}

class MyThreadLogic implements Runnable {
    @Override
    public void run() {
        System.out.println("Способ 1");
    }
}