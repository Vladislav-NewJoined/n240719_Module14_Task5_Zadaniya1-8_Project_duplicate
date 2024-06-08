package task9_7_1_part3_TEST.utils;

import org.telegram.telegrambots.meta.api.objects.File;
import task9_7_1_part3_TEST.functions.FilterOperation;
import task9_7_1_part3_TEST.functions.ImageOperation;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class PhotoMessageUtils {
    public static List<String> savePhotos(List<File> files, String botToken) throws IOException {
        Random random = new Random();
        ArrayList<String> paths = new ArrayList<>();
        for (File file: files) {
            final String imageUrl = "https://api.telegram.org/file/bot" + botToken + "/" + file.getFilePath(); // TODO Так было в видеоуроке 04 на минуте 20 21 ПОПРОБОВАТЬ ТОЖЕ
//            final String imageUrl = "https://api.telegram.org/file/bot" + botToken + "/" + file.getFilePath() + file.getClass(); // TODO Так было у меня, почему-то добавлено: + file.getClass()
            final String localFileName = "src/main/java/task9_7_1_part3_TEST/images/" + new Date().getTime() + random.nextLong() + ".jpeg"; // TODO Это я так изменил, возможно его нужно будет потом удалить
//            final String localFileName = "images/" + new Date().getTime() + random.nextLong() + ".jpeg"; // TODO Так было в видеоуроке
            saveImage(imageUrl, localFileName);
            paths.add(localFileName);
        }



//        // TODO Строки метода из части 1, return такой же
//        Random random2 = new Random();
//        ArrayList<String> paths2 = new ArrayList<>();
//        for (File file: files) {
//            final String imageUrl = "https://api.telegram.org/file/bot" + botToken + "/" + file.getFilePath() + file.getClass();
//            final String localFileName = "src/main/java/task9_7_1_part3_TEST/images/" + new Date().getTime() + random.nextLong() + ".jpg";
//            saveImage(imageUrl, localFileName);
//            paths2.add(localFileName);
//        }

        return paths;
    }

    public static void saveImage(String url, String fileName) throws IOException {
        URL urlModel = new URL(url);
        InputStream inputStream = urlModel.openStream();
        OutputStream outputStream = new FileOutputStream(fileName);
        byte[] b = new byte[2048];
        int length;
        while ((length = inputStream.read(b)) != -1) {
            outputStream.write(b, 0, length);
        }
        inputStream.close();
        outputStream.close();




    }

    // TODO Новый метод создан,с двойкой в названии
    public static void processingImage2(String fileName, ImageOperation operation) throws Exception {
        final BufferedImage image = ImageUtils.getImage(fileName);
        final RgbMaster rgbMaster = new RgbMaster(image);
        rgbMaster.changeImage(operation);
        ImageUtils.saveImage(rgbMaster.getImage(), fileName);
    }




    // TODO ДАЛЕЕ ВСЕ ИЗНАЧАЛЬНЫЕ МЕТОДЫ
    public static void processingImage(String fileName) throws Exception {
        final BufferedImage image = ImageUtils.getImage(fileName);
        final RgbMaster rgbMaster = new RgbMaster(image);
        rgbMaster.changeImage(FilterOperation::greyScale);
        ImageUtils.saveImage(rgbMaster.getImage(), fileName);
    }

    public static void processingImage(String fileName,/*, ImageOperation operation*/ImageOperation operation) throws Exception {
        final BufferedImage image = ImageUtils.getImage(fileName);
        final RgbMaster rgbMaster = new RgbMaster(image);
        rgbMaster.changeImage(FilterOperation::greyScale);
        ImageUtils.saveImage(rgbMaster.getImage(), fileName);
    }

}



//// ПРИМЕР 2 _Изначальный, который работает
//import org.telegram.telegrambots.meta.api.objects.File;
//import task9_7_1_part3_TEST.functions.FilterOperation;
//import task9_7_1_part3_TEST.functions.ImageOperation;
//
//import java.awt.image.BufferedImage;
//import java.io.*;
//        import java.net.URL;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//
//public class PhotoMessageUtils {
//    public static List<String> savePhotos(List<File> files, String botToken) throws IOException {
//        Random random = new Random();
//        ArrayList<String> paths = new ArrayList<>();
//        for (File file: files) {
//            final String imageUrl = "https://api.telegram.org/file/bot" + botToken + "/" + file.getFilePath() + file.getClass();
//            final String localFileName = "images/" + new Date().getTime() + random.nextLong() + ".jpeg";
//            saveImage(imageUrl, localFileName);
//            paths.add(localFileName);
//        }
//        return paths;
//    }
//
//    public static void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//
//    public static void processingImage(String fileName,/*, ImageOperation operation*/ImageOperation operation) throws Exception {
//        final BufferedImage image = ImageUtils.getImage(fileName);
//        final RgbMaster rgbMaster = new RgbMaster(image);
//        rgbMaster.changeImage(FilterOperation::greyScale);
//        ImageUtils.saveImage(rgbMaster.getImage(), fileName);
//    }
//}
//// КОНЕЦ ПРИМЕРА 2
