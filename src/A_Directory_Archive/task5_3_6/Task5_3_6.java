package task5_3_6;

import java.io.IOException;

public class Task5_3_6 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
                    6. Реализуйте метод equals для класса FileInformation. Покажите, работает?\s

                Решение:\s""");

        FileInformation fileInformation1 = new FileInformation("text.txt", "src\\a_shablony_FileInformation", 1000);
        FileInformation fileInformation2 = new FileInformation("text.txt", "src\\a_shablony_FileInformation", 1000);

        System.out.println("""
                Выводим результат сравнения с помощью метода equals():\s""");
        System.out.println("Получаем выражение " + fileInformation1.equals(fileInformation2) + ", т.е. значения fileInformation1 и fileInformation2 равны между собой.");
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        FileInformation fileInfo = (FileInformation) obj;

        if (size != fileInfo.size) {
            return false;
        }
        if (!fileName.equals(fileInfo.fileName)) {
            return false;
        }
        return absolutePath.equals(fileInfo.absolutePath);
    }
}




//// ПРИМЕР 2 _оригинальный код FileInformation
//import java.io.IOException;
//import java.io.File;
//
//public class Task5_3_6 {
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
//                    6. Реализуйте метод equals для класса FileInformation. Покажите, работает?\s
//
//                Решение:\s""");
//
//        System.out.println("""
//                Some text.\s""");
//
//        FileInformation fileInformation = new FileInformation("text.txt", "src\\a_shablony_FileInformation", 1000);
//        fileInformation.fileName = "text.txt";
//        fileInformation.size = 1000;
//        fileInformation.absolutePath = "src\\task5_3_6";
//
//        System.out.println("\n" + fileInformation.fileName);
//        System.out.println(fileInformation.size);
//        System.out.println(fileInformation.absolutePath);
//    }
//}
//
//class FileInformation {
//    String fileName;
//    String absolutePath;
//    long size;
//
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