package task5_2_1;

import java.io.File;

public class Task5_2_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №2:\s
                    1. У класса FileInformation из предыдущих уроков сделайте метод toString, возвращающий
                       информацию по всем полям.\s

                Решение:\s""");

        FileInformation fileInformation = new FileInformation("text.txt", "src\\task5_2_1", 1000);
        fileInformation.fileName = "text.txt";
        fileInformation.size = 1000;
        fileInformation.absolutePath = "src\\task5_2_1";

    }
}

class FileInformation {
    String fileName;
    String absolutePath;
    long size;

    FileInformation(String inputFileName, String inputAbsolutePath, long inputSize) {
        this.fileName = inputFileName;
        this.absolutePath = inputAbsolutePath;
        this.size = inputSize;
    }
}




//// ПРИМЕР 2 , взято из репозитория
//import java.io.File;
//
//public class Task5_2_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №2:\s
//                    1. У класса FileInformation из предыдущих уроков сделайте метод toString, возвращающий
//                       информацию по всем полям.\s
//
//                Решение:\s""");
//
//        FileInformation fileInformation = new FileInformation("text.txt", "src\\task5_2_1", 1000);
////        fileInformation.init("text.txt", "src\\task5_2_1", 1000);
//        fileInformation.fileName = "text.txt";
//        fileInformation.size = 1000;
//        fileInformation.absolutePath = "src\\task5_2_1";
//
//    }
//}
//
//class FileInformation {
//    String fileName;
//    String absolutePath;
//    long size;
//
//    //    void init(String inputFileName, String inputAbsolutePath, long inputSize) {
//    FileInformation(String inputFileName, String inputAbsolutePath, long inputSize) {
//        this.fileName = inputFileName;
//        this.absolutePath = inputAbsolutePath;
//        this.size = inputSize;
//    }
//
//    FileInformation(File inputFile) {
//        this.absolutePath = inputFile.getAbsolutePath();
//        this.fileName = inputFile.getName();
//        this.size = inputFile.length();
//    }
//}
//// КОНЕЦ ПРИМЕРА 2