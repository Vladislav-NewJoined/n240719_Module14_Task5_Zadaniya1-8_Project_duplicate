package a_shablony_ITunes_Pesnia_Po_Nazvaniyu;

import java.io.BufferedReader;
import java.io.IOException;
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