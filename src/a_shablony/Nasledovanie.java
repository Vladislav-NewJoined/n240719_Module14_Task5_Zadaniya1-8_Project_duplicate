package a_shablony;

// источник: Animal Dog eat sleep  https://youtu.be/28NP_V2yc60
public class Nasledovanie {
    public static void main(String[] args) {
        System.out.println("""
                Задание :\s
                Модуль 4. Наследование. Задание №3:\s
                    2. Наследование.\s

                Решение:\s""");

        Animal animal = new Animal();
        animal.eat();
//        animal.sleep();
        Dog dog = new Dog();
        dog.eat();
        dog.sleep();
        dog.bark();
        dog.showName();

    }
}

class Animal {

//    String name;
    String name = "Some animal";

    public void eat() {
        System.out.println("Animal is eating");
    }

    public void sleep() {
        System.out.println("Animal is sleeping");
    }
}

class Dog extends Animal {
    public void eat() {
        System.out.println("Dog is eating");
    }

    public void bark() {
        System.out.println("I am barking");
    }

    public void showName() {
        System.out.println(name);
    }
}