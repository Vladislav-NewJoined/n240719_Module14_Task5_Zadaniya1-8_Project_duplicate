package a_shablony;

import java.io.IOException;

// Пример класса здесь: https://github.com/Voldemarium/Synergy_Section_1/blob/5e5e62b790d124512dc23d5b153c7d28e4223ab6/src/theme_5_interface/task_40/ITunesProduct.java#L4
public class ITunesProduct {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
                    7. Реализуйте класс ITunesProduct.\s

                Решение:\s""");
    }

        String artistName;
        String collectionName;
        String previewUrl;
        String country;

        void printIntroducePreview() {
            System.out.println(this.artistName + "-" + this.collectionName);
            System.out.println("url to preview: " + this.previewUrl);
    }
}