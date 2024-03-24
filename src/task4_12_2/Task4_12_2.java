package task4_12_2;

public class Task4_12_2 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №12:\s
                    2. Как работает аннотация @Override?\s

                Решение:\s""");

        System.out.println("""
                Аннотация @Override в языке программирования Java используется для явного указания
                компилятору о том, что метод в текущем классе переопределяет метод из его суперкласса.
                Если мы используем аннотацию @Override перед методом, который, как мы предполагаем,
                должен переопределять метод из суперкласса, и на самом деле этот метод не переопределяет
                ни один метод из суперкласса, то компилятор выдаст ошибку.
                
                Использование аннотации @Override позволяет обнаружить ошибки при переопределении
                методов на этапе компиляции, а не на этапе выполнения программы. Это помогает облегчить
                отладку кода и делает его более надежным.
                
                Ниже пример кода, который иллюстрирует аннотацию @Override.
                
                В этом примере метод makeSound() в классе Dog явно переопределяет метод из класса
                Animal, и использование аннотации @Override помогает убедиться в правильности
                переопределения.
                \s""");

//        System.out.println("Animal makes a sound");
        Animal animal = new Animal();
        animal.makeSound();
        Dog dog = new Dog();
//        dog.makeSound();
    }
}

class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }
}