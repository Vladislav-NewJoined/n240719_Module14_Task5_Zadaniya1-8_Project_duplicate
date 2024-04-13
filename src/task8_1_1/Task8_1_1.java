package task8_1_1;

public class Task8_1_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 8. Многопоточность. Задание №1:\s
                    1. Создать класс NewThread расширяющий Thread.\s

                Решение:\s""");

        Thread t = new NewThread();
        t.start();
    }
}

class NewThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println(getName());
    }
}