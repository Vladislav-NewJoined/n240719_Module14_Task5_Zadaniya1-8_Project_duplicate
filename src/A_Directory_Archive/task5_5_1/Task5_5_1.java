package task5_5_1;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

abstract class SongLyrics {
    protected abstract String getLyrics(String song);
}

public class Task5_5_1 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №5:\s
                    1. Создайте абстрактный класс SongLyrics, который возвращает текст песни
                       по названию.\s

                Решение:\s""");

        LyricsDownloader lyricsDownloader = new LyricsDownloader();
        String song = "Lose yourself";

        String lyrics = lyricsDownloader.getLyrics(song);
        System.out.println("Выводим текст песни '" + song + "', исполнителя 'Eminem':\n" + lyrics);
    }
}

class LyricsDownloader extends SongLyrics {

    @Override
    protected String getLyrics(String song) {
        StringBuilder response = new StringBuilder();

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(getURL("Eminem", song)).openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String lyrics = response.toString().split("\"lyrics\":\"")[1].split("\"")[0];
        return lyrics;
    }

    private String getURL(String artist, String song) {
        return "https://api.lyrics.ovh/v1/" + artist + "/" + song.replace(" ", "%20");
    }
}




//// ПРИМЕР 3
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class Task5_5_1 {
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №5:\s
//                    1. Создайте абстрактный класс SongLyrics, который возвращает текст песни
//                       по названию.\s
//
//                Решение:\s""");
//
//        LyricsDownloader lyricsDownloader = new LyricsDownloader();
//        String artist = "Eminem";
//        String song = "Lose yourself";
//
//        try {
//            String lyrics = lyricsDownloader.downloadLyrics(artist, song);
//            System.out.println("Текст песни '" + song + "', исполнителя '" + artist + "':\n" + lyrics);
//        } catch (IOException e) {
//            System.out.println("Error downloading lyrics: " + e.getMessage());
//        }
//    }
//}
//
//class LyricsDownloader {
//    public static String downloadLyrics(String artist, String song) throws IOException {
//        String url = "https://api.lyrics.ovh/v1/" + artist + "/" + song.replace(" ", "%20");
//        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//        connection.setRequestMethod("GET");
//
//        StringBuilder response = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                response.append(line);
//            }
//        }
//
//        // Обработка JSON ответа, чтобы получить текст песни
//        String lyrics = response.toString().split("\"lyrics\":\"")[1].split("\"")[0];
//        return lyrics;
//    }
//}
//// КОНЕЦ ПРИМЕРА 3




//// ПРИМЕР 2
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//abstract class SongLyrics {
//    protected abstract String getLyrics(String artist, String song);
//}
//
//public class Task5_5_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №5:\s
//                    1. Создайте абстрактный класс SongLyrics, который возвращает текст песни
//                       по названию.\s
//
//                Решение:\s""");
//
//        LyricsDownloader lyricsDownloader = new LyricsDownloader();
//        String artist = "Eminem";
//        String song = "Lose yourself";
//
//        String lyrics = lyricsDownloader.getLyrics(artist, song);
//        System.out.println("Текст песни '" + song + "', исполнителя '" + artist + "':\n" + lyrics);
//    }
//}
//
//class LyricsDownloader extends SongLyrics {
//
//    @Override
//    protected String getLyrics(String artist, String song) {
//        String url = "https://api.lyrics.ovh/v1/" + artist + "/" + song.replace(" ", "%20");
//        StringBuilder response = new StringBuilder();
//
//        try {
//            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.setRequestMethod("GET");
//
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    response.append(line);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Обработка JSON ответа, чтобы получить текст песни
//        String lyrics = response.toString().split("\"lyrics\":\"")[1].split("\"")[0];
//        return lyrics;
//    }
//}
//// КОНЕЦ ПРИМЕРА 2