package task5_5_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

abstract class SongLyrics {
    protected abstract String getLyrics(String artist, String song) throws IOException;
}

public class Task5_5_4 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №5:\s
                    4. Создайте ApiSongLyrics, который ищет текст песни в этом апи:
                       https://api.lyrics.ovh/v1/Eminem/Lose%20yourself (откройте в браузере). 
                       Артиста и название песни можно менять. Вместо пробелов вставляйте %20.\s

                Решение:\s""");

        ApiSongLyrics apiSongLyrics = new ApiSongLyrics();
        String artist = "Eminem";
        String song = "Lose yourself";

        try {
            String lyrics = apiSongLyrics.getLyrics(artist, song);
            System.out.println("Выводим текст песни '" + song + "', исполнителя '" + artist + "':\n" + lyrics);
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке текста песни: " + e.getMessage());
        }
    }
}

class ApiSongLyrics extends SongLyrics {
    @Override
    protected String getLyrics(String artist, String song) throws IOException {
        String url = "https://api.lyrics.ovh/v1/" + artist + "/" + song.replace(" ", "%20");
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        // Обработка JSON ответа, чтобы получить текст песни
        String lyrics = response.toString().split("\"lyrics\":\"")[1].split("\"")[0];
        return lyrics;
    }
}


//// ПРИМЕР 2
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//abstract class SongLyrics {
//    protected abstract String getLyrics(String song);
//}
//
//public class Task5_5_2 {
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №5:\s
//                    2. Создайте файл в формате: Название песни: текст песни, и так много песен
//                       (можете разделять разные песни пустыми строками, или другим способом)
//                       Создайте наследника - FileSongLyrics, который ищет в файле по названию
//                        песни, и если находит, возвращает текст песни.\s
//
//                Решение:\s""");
//
//        FileSongLyrics lyricsDownloader = new FileSongLyrics();
//        String song = "Lose yourself";
//
//        String lyrics = lyricsDownloader.getLyrics(song);
//        System.out.println("Выводим текст песни '" + song + "', исполнителя 'Eminem':\n" + lyrics);
//    }
//}
//
//class FileSongLyrics extends SongLyrics {
//
//    @Override
//    protected String getLyrics(String song) {
//        StringBuilder response = new StringBuilder();
//
//        try {
//            HttpURLConnection connection = (HttpURLConnection) new URL(getURL("Eminem", song)).openConnection();
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
//        String lyrics = response.toString().split("\"lyrics\":\"")[1].split("\"")[0];
//        return lyrics;
//    }
//
//    private String getURL(String artist, String song) {
//        return "https://api.lyrics.ovh/v1/" + artist + "/" + song.replace(" ", "%20");
//    }
//}
//// КОНЕЦ ПРИМЕРА 2
