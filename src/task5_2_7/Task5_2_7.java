package task5_2_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Task5_2_7 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №2:\s
                    7. Сделайте метод toString у класса ITunesSong, который возвращает информацию
                       в формате JSON\s

                Решение:\s""");

        Task5_2_7 task = new Task5_2_7();
        String searchResult = task.playSong("Rick Ross");
        System.out.println(searchResult);
    }

    static String playSong(String searchRequest) throws IOException {
        return playSongInternal(searchRequest, 1);
    }

    private static String playSongInternal(String searchRequest, int limit) throws IOException {
        String url = buildUrl(searchRequest, limit);
        String page = downloadWebPage(url);

        String artistName = getTag(page, "artistName");
        String trackName = getTag(page, "trackName");
        return toString(artistName, trackName);
    }

    private static String getTag(String page, String tagName) {
        int start = page.indexOf(tagName) + tagName.length() + 3;
        int end = page.indexOf("\"", start);
        String value = page.substring(start, end);
        return value;
    }

    private static String buildUrl(String searchRequest, int limit) {
        String term = searchRequest.replaceAll(" ", "+");
        String itunesApi = "https://itunes.apple.com/search?term=";
        String limitParam = "&limit=1" + limit;
        String mediaParam = "&media=music";
        StringBuilder builder = new StringBuilder();
        builder.append(itunesApi);
        builder.append(term);
        builder.append(limitParam);
        builder.append(mediaParam);
        return builder.toString();
    }

    static String downloadWebPage(String url) throws IOException {
        StringBuilder result = new StringBuilder();
        String line;
        URLConnection urlConnection = new URL(url).openConnection();
        try (InputStream is = urlConnection.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }

    public static String toString(String artist, String track) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"artist\": \"").append(artist).append("\", \"track\": \"").append(track).append("\"}");
        return sb.toString();
    }
}




