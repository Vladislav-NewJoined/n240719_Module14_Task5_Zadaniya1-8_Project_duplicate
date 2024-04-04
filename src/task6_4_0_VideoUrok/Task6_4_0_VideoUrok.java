package task6_4_0_VideoUrok;

import java.util.PriorityQueue;

public class Task6_4_0_VideoUrok {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 6. Основные структуры данных. Задание №4:\s
                    4. Напишите свой компаратор для тима Customer и используйте его в коде из урока.\s

                Решение:\s""");

        PriorityQueue<Customer> queue = new PriorityQueue<>();
        queue.add(new Customer("user1",2));
        queue.add(new Customer("user2",4));
        queue.add(new Customer("user3",10));
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}

class Customer implements Comparable<Customer> {
    String fulLName;

    public Customer(String fulLName, int loyaltyPoints) {
        this.fulLName = fulLName;
        this.loyaltyPoints = loyaltyPoints;
    }

    int loyaltyPoints;

    @Override
    public String toString() {
        return "Customer{" +
                "fulLName='" + fulLName + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
        //        return super.toString();
    }

    @Override
    public int compareTo(Customer o) {
        return o.loyaltyPoints - loyaltyPoints;
    }
}




//// ПРИМЕР 2
//import java.util.PriorityQueue;
//
//public class Task6_4_0_VideoUrok {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 6. Основные структуры данных. Задание №4:\s
//                    4. Напишите свой компаратор для тима Customer и используйте его в коде из урока.\s
//
//                Решение:\s""");
//
//        PriorityQueue<Integer> queue = new PriorityQueue<>();
//        queue.add(3);
//        queue.add(5);
//        queue.add(1);
//        queue.add(8);
//        while (!queue.isEmpty()) {
//            System.out.println(queue.poll());
//        }
//    }
//}
//// КОНЕЦ ПРИМЕРА 2