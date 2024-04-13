package task8_2_1;

import java.util.Scanner;

public class Task8_2_1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("""
                Задание:\s
                Модуль 8. Многопоточность. Задание №2:\s
                    1. Напишите программу, которая сможет в одном потоке читать данные из консоли, а в другом потоке
                       будет их выводить.\s

                Решение:\s""");

        Thread t = new Thread(new ThreadTimerStart());
        t.start();

        Thread t2 = new Thread(new ThreadTimerFinish());
        t2.start();

    }
}

class ThreadTimerStart implements Runnable {

    int count = 0;

    @Override
    public void run() {
        System.out.println("РЕАЛИЗУЕМ СЕКУНДОМЕР В ПОТОКОВОМ МЕТОДЕ run " +
                "\n(для остановки секундомера поставьте курсор в консоль, " +
                "наберите любой текст и нажмите ENTER): ");
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            System.out.println(count + " сек.");
        }
    }
}

class ThreadTimerFinish extends Thread {
    @Override
    public void run() {
        super.run();
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        if (s != null) {
            System.exit(0);
        }
    }
}