package task9_6_2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Task9_6_2 {

    public static void sayHello() {
        System.out.println("Hello from the sayHello method!");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("""
            Задание:\s
            Модуль 9. UI и использование готовых SDK. Задание №6:\s
                2.Приведите несколько сценариев возможного использования рефлексии в JAVA?\s

            Решение:\s""");

        System.out.println("""
                1. Фреймворки и библиотеки: Некоторые фреймворки и библиотеки Java, такие как Spring Framework,
                   используют рефлексию для автоматической конфигурации компонентов, внедрения зависимостей и
                   выполнения других действий, не зная заранее, какие классы и методы будут использоваться.
                
                2. Инструменты анализа кода: Некоторые инструменты статического анализа кода и тестирования,
                   такие как JUnit или Mockito, используют рефлексию для создания тестовых случаев, выполнения
                   мокирования объектов и вызова приватных методов для тестирования.
                
                3. Сериализация: Механизм сериализации в Java использует рефлексию для чтения и записи объектов в
                   поток байтов. Это позволяет сохранять и загружать объекты без необходимости явного определения
                   методов сериализации и десериализации.
                
                4. Динамическое создание объектов: С помощью рефлексии можно динамически создавать экземпляры классов,
                   даже если тип класса неизвестен на этапе компиляции. Это может быть полезно, например, при чтении
                   конфигурационных файлов или при работе с плагинами.
                
                5. Работа с аннотациями: Рефлексия позволяет получать информацию об аннотациях, примененных к классам,
                   методам и полям, и использовать их для определения поведения программы или для создания прозрачности
                   в коде.
                
                Ниже два примера кодов, в которых показано, как используется рефлексия:
                """);

        System.out.println("Пример 1: Вызов статического метода sayHello в классе ReflectionExample: ");
        try {
            Class<?> clazz2 = Class.forName("ReflectionExample");

            Method method = clazz2.getDeclaredMethod("sayHello");
            method.invoke(null); // Передаем null вместо объекта, так как метод статический
        } catch (Exception e) {
            System.out.println("Ошибка при использовании рефлексии: " + e.getMessage());
        }


        System.out.println("\nПример 2: Получение информации о классе с использованием рефлексии: ");
        Class<?> clazz = String.class;

        System.out.println("Имя класса: " + clazz.getName());

        Method[] methods = clazz.getMethods();
        System.out.println("Методы класса:");
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        Field[] fields = clazz.getDeclaredFields();
        System.out.println("Поля класса:");
        for (Field field : fields) {
            System.out.println(field.getName());
        }

    }
}

