package task6_4_1;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Task6_4_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 6. Основные структуры данных. Задание №4:\s
                    1. Напишите свой компаратор для тима Customer и используйте его в коде из урока.\s

                Решение:\s""");

        // Создаем компаратор для типа Customer, сортирующий по убыванию loyaltyPoints
        Comparator<Customer> loyaltyPointsComparator = Comparator.comparingInt(Customer::getLoyaltyPoints).reversed();

        // Создаем приоритетную очередь с использованием компаратора
        PriorityQueue<Customer> queue = new PriorityQueue<>(loyaltyPointsComparator);

        // Добавляем элементы в очередь
        queue.add(new Customer("user1", 2));
        queue.add(new Customer("user2", 4));
        queue.add(new Customer("user3", 10));

        // Выводим элементы из очереди
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}

class Customer {
    String fulLName;
    int loyaltyPoints;

    public Customer(String fulLName, int loyaltyPoints) {
        this.fulLName = fulLName;
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "fulLName='" + fulLName + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }
}





//// ПРИМЕР 3
//import java.util.Comparator;
//import java.util.PriorityQueue;
//
//public class Task6_4_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 6. Основные структуры данных. Задание №4:\s
//                    4. Напишите свой компаратор для тима Customer и используйте его в коде из урока.\s
//
//                Решение:\s""");
//
//        // Создаем компаратор для типа Customer
//        Comparator<Customer> loyaltyPointsComparator = Comparator.comparingInt(Customer::getLoyaltyPoints);
//
//        // Создаем приоритетную очередь с использованием компаратора
//        PriorityQueue<Customer> queue = new PriorityQueue<>(loyaltyPointsComparator);
//
//        // Добавляем элементы в очередь
//        queue.add(new Customer("user1", 2));
//        queue.add(new Customer("user2", 4));
//        queue.add(new Customer("user3", 10));
//
//        // Выводим элементы из очереди
//        while (!queue.isEmpty()) {
//            System.out.println(queue.poll());
//        }
//    }
//}
//
//class Customer {
//    String fulLName;
//    int loyaltyPoints;
//
//    public Customer(String fulLName, int loyaltyPoints) {
//        this.fulLName = fulLName;
//        this.loyaltyPoints = loyaltyPoints;
//    }
//
//    public int getLoyaltyPoints() {
//        return loyaltyPoints;
//    }
//
//    @Override
//    public String toString() {
//        return "Customer{" +
//                "fulLName='" + fulLName + '\'' +
//                ", loyaltyPoints=" + loyaltyPoints +
//                '}';
//    }
//}
//// КОНЕЦ ПРИМЕРА 3




//// ПРИМЕР 2 _сделано, но не отсортировано
//import java.util.ArrayList;
//import java.util.Random;
//import java.util.Comparator;
//
//public class Task6_3_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 6. Основные структуры данных. Задание №3:\s
//                    1. Нужно отсортировать случайный список чисел.
//                       Числа нужно отсортировать следующим образом:
//                       Четные идут в начале по возрастанию, нечетные по убыванию в конце списка.\s
//
//                Решение:\s""");
//
//        System.out.println("""
//                Some text""");
//
//        // Создаем список для хранения случайных чисел
//        ArrayList<Integer> randomList = new ArrayList<>();
//
//        // Генерируем случайные числа и добавляем их в список
//        Random random = new Random();
//        for (int i = 0; i < 20; i++) {
//            // Генерируем случайное число от -100 до 100
//            int randomNumber = random.nextInt(201) - 100;
//            randomList.add(randomNumber);
//        }
//
//        // Преобразуем список в массив типа int
//        int[] array = new int[randomList.size()];
//        for (int i = 0; i < randomList.size(); i++) {
//            array[i] = randomList.get(i);
//        }
//
//        // Выводим полученный массив
//        System.out.println("Случайный массив чисел:");
//        for (int num : array) {
//            System.out.print(num + " ");
//        }
//    }
//}
//// КОНЕЦ ПРИМЕРА 2
