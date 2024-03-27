package task5_2_6;

import java.io.IOException;

public class Task5_2_6 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №2:\s
                    6. Сделайте метод toString у класса ITunesSong, который возвращает информацию в
                       формате xml: <artist>Rick Ross</artist>… и так далее\s

                Решение:\s""");

    }
}





//import java.io.IOException;
//
//public class Task5_2_6 {
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №2:\s
//                    6. Сделайте метод toString у класса ITunesSong, который возвращает информацию в
//                       формате xml: <artist>Rick Ross</artist>… и так далее\s
//
//                Решение:\s""");
//
////  1. У класса FileInformation из предыдущих уроков сделайте метод toString, возвращающий информацию по всем полям.
//        FileInformation info = new FileInformation();
//        info.fileName = "file_1";
//        info.absolutePath = "/media/vladimir/D1/";
//        info.size = 32;
//        System.out.println(info);
//
////  2. Реализуйте класс, отображающий страничку в википедии. Пусть метод toString у него возвращает текст этой странички.
//        // Покажите его использование неявно, так:
//        WikiPage wikiPage = new WikiPage("Java");
//        System.out.println(wikiPage);
//
////  3. Поймайте в дебаггере метод toString у класса String
//        System.out.println(('a' + "").toString());
//        //См. toString_from_String.png
//
////  4. Поймайте в дебаггере метод toString у класса Object
//        Object object = new Object();
//        System.out.println(object.toString());
//        //См. toString_from_Object.png
//
////  5. Поймайте в дебаггере метод toString у класса StringBuilder
//        StringBuilder builder = new StringBuilder();
//        builder.append('a');
//        System.out.println(builder.toString());
//        //См. toString_from_StringBuilder.png
//
////  6. Сделайте метод toString у класса ITunesSong, который возвращает информацию в формате xml:
//        // <artist>Rick Ross</artist>... и так далее
//        ITunesSong song1 = new ITunesSong("music of love", 2, false);
//        System.out.println(song1);
//
////  7. Сделайте метод toString у класса ITunesSong, который возвращает информацию в формате JSON
//        ITunesSong song2 = new ITunesSong("music of love", 2, true);
//        System.out.println(song2);
//    }
//}



//// ПРИМЕР 3
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//
//public class Task5_2_6 {
//    public static void main(String[] args) throws IOException {
//        String url = "https://www.apple.com/itunes/";
//        Document doc = Jsoup.connect(url).get();
//
//        Elements songs = doc.select("div.song");
//        for(Element song : songs) {
//            String songName = song.select("div.title").text();
//            String artist = song.select("div.artist").text();
//
//            System.out.println("Downloading: " + songName);
//            // Code to download the song goes here
//            System.out.println("Artist: " + artist);
//        }
//    }
//}
//// КОНЕЦ ПРИМЕРА 3




//// ПРИМЕР 2 , работает без захода на интернет-сайт по ссылке
//import java.io.IOException;
//
//public class Task5_2_6 {
//    private final String artist;
//
//    public Task5_2_6(String artist) {
//        this.artist = artist;
//    }
//
//    @Override
//    public String toString() {
//        return "<artist>" + artist + "</artist>";
//    }
//
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №2:\s
//                    6. Сделайте метод toString у класса ITunesSong, который возвращает информацию в
//                       формате xml: <artist>Rick Ross</artist>… и так далее\s
//
//                Решение:\s""");
//
//        Task5_2_6 song = new Task5_2_6("Rick Ross");
//        System.out.println(song.toString());
//        System.out.println();
//
//        System.out.println("""
//                В этом коде мы создали класс ITunesSong с полем "artist" и конструктором, который
//                инициализирует это поле. Метод toString переопределен для возвращения строки в формате
//                XML с информацией об исполнителе. В методе main мы создаем объект ITunesSong с именем
//                "Rick Ross" и вызываем метод toString для вывода результата в консоль.\s""");
//
//    }
//}
//// КОНЕЦ ПРИМЕРА 2



