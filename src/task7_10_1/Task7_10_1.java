package task7_10_1;


import task7_10_1.ImageUtils;
import task7_10_1.RgbMaster;
import task7_10_1.FilterOperation;

import java.awt.image.BufferedImage;

public class Task7_10_1 {
//    public static void main(String[] args) throws Exception {
//        System.out.println("""
//                Задание:\s
//                Модуль 7. Взаимодействие с API. Задание №10. Проект:\s
//                    1. Создать несколько фильтров для обработки изображений
//                        фильтры:
//                        монохромный
//                        только красный компонент пикселей
//                        только зеленый компонент пикселей
//                        только синий компонент пикселей
//                        сепия
//                    Пошаговое выполнение проекта
//                        Создание класса для чтения и записи изображения из файла
//                        Создание класса, хранящего набор пикселей изображения
//                        Создание фильтров для обработки изображения
//                    Фильтр должен представлять из себя лямбду, основанную на интерфейсе.\s
//
//                Решение: \s""");
//
//        System.out.println("Изображения после обрботки смотреть в директории: ClonedImages\n");
//        final BufferedImage image1 = ImageUtils.getImage("src/task7_10_1/Picture_Mountain_Lake.png");
//        final BufferedImage image2 = ImageUtils.getImage("src/task7_10_1/Picture_Mountain_Lake.png");
//        final BufferedImage image3 = ImageUtils.getImage("src/task7_10_1/Picture_Mountain_Lake.png");
//        final BufferedImage image4 = ImageUtils.getImage("src/task7_10_1/Picture_Mountain_Lake.png");
//        final BufferedImage image5 = ImageUtils.getImage("src/task7_10_1/Picture_Mountain_Lake.png");
//
//        // Способ 1. Применён фильтр: монохромный.
//        final RgbMaster rgbMaster1 = new RgbMaster(image1);
//        rgbMaster1.changeImage((int[] rgb) -> {
//            final int mean = (rgb[0] + rgb[1] + rgb[2]) / 3;
//            rgb[0] = mean;
//            rgb[1] = mean;
//            rgb[2] = mean;
//            return rgb;
//        });
//        ImageUtils.saveImage(rgbMaster1.getImage(), "src/task7_10_1/ClonedImages/cloned_Picture_Mountain_Lake1.png");
//        System.out.println("Выполнен Способ 1. Применён фильтр: монохромный.");
//        // Способ 1. Применён фильтр: монохромный. Конец
//
//        // Способ 2. Применён фильтр: только красный компонент пикселей
//        final RgbMaster rgbMaster2 = new RgbMaster(image2);
//        rgbMaster2.changeImage(FilterOperation::onlyRed);
//        ImageUtils.saveImage(rgbMaster2.getImage(), "src/task7_10_1/ClonedImages/cloned_Picture_Mountain_Lake2.png");
//        System.out.println("Выполнен Способ 2. Применён фильтр: только красный компонент пикселей.");
//        // Способ 2. Применён фильтр: только красный компонент пикселей. Конец
//
//        // Способ 3. Применён фильтр: только зеленый компонент пикселей
//        final RgbMaster rgbMaster3 = new RgbMaster(image3);
//        rgbMaster3.changeImage(FilterOperation::onlyGreen);
//        ImageUtils.saveImage(rgbMaster3.getImage(), "src/task7_10_1/ClonedImages/cloned_Picture_Mountain_Lake3.png");
//        System.out.println("Выполнен Способ 3. Применён фильтр: только зеленый компонент пикселей.");
//        // Способ 3. Применён фильтр: только зеленый компонент пикселей. Конец
//
//        // Способ 4. Применён фильтр: только синий компонент пикселей
//        final RgbMaster rgbMaster4 = new RgbMaster(image4);
//        rgbMaster4.changeImage(FilterOperation::onlyBlue);
//        ImageUtils.saveImage(rgbMaster4.getImage(), "src/task7_10_1/ClonedImages/cloned_Picture_Mountain_Lake4.png");
//        System.out.println("Выполнен Способ 4. Применён фильтр: только синий компонент пикселей.");
//        // Способ 4. Применён фильтр: только синий компонент пикселей. Конец
//
//        // Способ 5. Применён фильтр: сепия
//        final RgbMaster rgbMaster5 = new RgbMaster(image5);
//        rgbMaster5.changeImage(FilterOperation::sepia);
//        ImageUtils.saveImage(rgbMaster5.getImage(), "src/task7_10_1/ClonedImages/cloned_Picture_Mountain_Lake5.png");
//        System.out.println("Выполнен Способ 5. Применён фильтр: сепия.");
//        // Способ 5. Применён фильтр: сепия. Конец
//
//    }
}




//// ПРИМЕР
//import task7_10_1.ImageUtils;
//import task7_10_1.RgbMaster;
//import java.awt.image.BufferedImage;
//
//public class Task7_10_1 {
//    public static void main(String[] args) throws Exception {
//        System.out.println("""
//                Задание:\s
//                Модуль 7. Взаимодействие с API. Задание №10. Проект:\s
//                    1. Создать несколько фильтров для обработки изображений
//                        фильтры:
//                        монохромный
//                        только красный компонент пикселей
//                        только зеленый компонент пикселей
//                        только синий компонент пикселей
//                        сепия
//                    Пошаговое выполнение проекта
//                        Создание класса для чтения и записи изображения из файла
//                        Создание класса, хранящего набор пикселей изображения
//                        Создание фильтров для обработки изображения
//                    Фильтр должен представлять из себя лямбду, основанную на интерфейсе.\s
//
//                Решение: \s""");
//
//        System.out.println("Изображения после обрботки смотреть в директории: ClonedImages\n");
//        final BufferedImage image1 = ImageUtils.getImage("src/task7_10_1/Picture_Mountain_Lake.png");
//        final BufferedImage image2 = ImageUtils.getImage("src/task7_10_1/Picture_Mountain_Lake.png");
//        final BufferedImage image3 = ImageUtils.getImage("src/task7_10_1/Picture_Mountain_Lake.png");
//        final BufferedImage image4 = ImageUtils.getImage("src/task7_10_1/Picture_Mountain_Lake.png");
//        final BufferedImage image5 = ImageUtils.getImage("src/task7_10_1/Picture_Mountain_Lake.png");
//
//
//        // Способ 1. Применён фильтр: монохромный.
//        final RgbMaster rgbMaster1 = new RgbMaster(image1);
//        rgbMaster1.changeImage((float[] rgb) -> {
//            final float mean = (rgb[0] + rgb[1] + rgb[2]) / 3;
//            rgb[0] = mean;
//            rgb[1] = mean;
//            rgb[2] = mean;
//            return rgb;
//        });
//        ImageUtils.saveImage(rgbMaster1.getImage(), "src/task7_10_1/ClonedImages/cloned_Picture_Mountain_Lake1.png");
//        System.out.println("Выполнен Способ 1. Применён фильтр: монохромный.");
//        // Способ 1. Применён фильтр: монохромный. Конец
//
//
//        // Способ 2. Применён фильтр: только красный компонент пикселей
//        final RgbMaster rgbMaster2 = new RgbMaster(image2);
//        rgbMaster2.changeImage(FilterOperation::onlyRed);
//        ImageUtils.saveImage(rgbMaster2.getImage(), "src/task7_10_1/ClonedImages/cloned_Picture_Mountain_Lake2.png");
//        System.out.println("Выполнен Способ 2. Применён фильтр: только красный компонент пикселей.");
//        // Способ 2. Применён фильтр: только красный компонент пикселей. Конец
//
//
//        // Способ 3. Применён фильтр: только зеленый компонент пикселей
//        final RgbMaster rgbMaster3 = new RgbMaster(image3);
//        rgbMaster3.changeImage(FilterOperation::onlyGreen);
//        ImageUtils.saveImage(rgbMaster3.getImage(), "src/Task2_1_10_1/Images2_1_10_1/cloned_Picture_Mountain_Lake3.png");
//        System.out.println("Выполнен Способ 3. Применён фильтр: только зеленый компонент пикселей.");
//        // Способ 3. Применён фильтр: только зеленый компонент пикселей. Конец
//
//
//        // Способ 4. Применён фильтр: только синий компонент пикселей
//        final RgbMaster rgbMaster4 = new RgbMaster(image4);
//        rgbMaster4.changeImage(FilterOperation::onlyBlue);
//        ImageUtils.saveImage(rgbMaster4.getImage(), "src/task7_10_1/ClonedImages/cloned_Picture_Mountain_Lake4.png");
//        System.out.println("Выполнен Способ 4. Применён фильтр: только синий компонент пикселей.");
//        // Способ 4. Применён фильтр: только синий компонент пикселей. Конец
//
//
//        // Способ 5. Применён фильтр: сепия
//        final RgbMaster rgbMaster5 = new RgbMaster(image5);
//        rgbMaster5.changeImage(FilterOperation::/*greyScale *//**//**//**//*onlyRed*//**//* onlyGreen *//*
//                onlyBlue */sepia);
//        ImageUtils.saveImage(rgbMaster5.getImage(), "src/task7_10_1/ClonedImages/cloned_Picture_Mountain_Lake5.png");
//        System.out.println("Выполнен Способ 5. Применён фильтр: сепия.");
//        // Способ 5. Применён фильтр: сепия. Конец
//
//    }
//}
//// КОНЕЦ ПРИМЕРА
