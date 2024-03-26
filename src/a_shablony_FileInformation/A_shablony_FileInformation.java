package a_shablony_FileInformation;

import java.io.File;

public class A_shablony_FileInformation {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №2:\s
                    1. У класса FileInformation из предыдущих уроков сделайте метод toString, возвращающий
                       информацию по всем полям.\s

                Решение:\s""");

        FileInformation fileInformation = new FileInformation("text.txt", "src\\a_shablony_FileInformation", 1000);
//        fileInformation.init("text.txt", "src\\a_shablony_FileInformation", 1000);
        fileInformation.fileName = "text.txt";
        fileInformation.size = 1000;
        fileInformation.absolutePath = "src\\a_shablony_FileInformation";

    }
}

class FileInformation {
    String fileName;
    String absolutePath;
    long size;

    //    void init(String inputFileName, String inputAbsolutePath, long inputSize) {
    FileInformation(String inputFileName, String inputAbsolutePath, long inputSize) {
        this.fileName = inputFileName;
        this.absolutePath = inputAbsolutePath;
        this.size = inputSize;
    }

    FileInformation(File inputFile) {
        this.absolutePath = inputFile.getAbsolutePath();
        this.fileName = inputFile.getName();
        this.size = inputFile.length();
    }
}