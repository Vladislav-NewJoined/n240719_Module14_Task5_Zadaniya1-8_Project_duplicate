package task4_9_5;

public class Task4_9_5 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №9:\s
                    5. Нарисуйте иерархию классов exception и runtime exception до самого начала\s

                Решение:\s""");

        System.out.println("""
                Иерархия классов исключений в Java:
                      Throwable
                         |
                       Error
                         |
                VirtualMachineError
                OutOfMemoryError
                         |
                     Exception
          |------------------------------|
      RuntimeException              IOException
          |                |                    |
    ArithmeticException  NullPointerException  SQLException
                               |
                         IndexOutOfBoundsException
          """);
    }
}