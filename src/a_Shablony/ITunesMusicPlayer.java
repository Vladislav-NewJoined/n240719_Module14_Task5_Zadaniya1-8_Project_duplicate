package a_Shablony;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

// "Давайте напишем заново" мин 12 54 ITunesMusicPlayer Модуль1 Тема3 Урок 8. Перегрузка методов
// из предыдущего прервался на мин 35 06
// проверка успешно работает! мин 35 06 в классе Task1_4_2_0_MyClass24 из Модуль1 Тема3 Урок 8. Перегрузка методов
// как сделать, чтобы скачанный файл проигрывался прямо в Intellij Idea см. в следующей строке
// мин 27 03 ITunesMusicPlayer Модуль1 Тема3 Урок 8. Перегрузка методов

public class ITunesMusicPlayer {
    void playSong(String searchRequest) throws IOException {
        playSongInternal(searchRequest, 1);
    }
    // конец источника: https://www.javatpoint.com/how-to-open-a-file-in-java

    void playSong(String searchRequest, int limit) throws IOException {
        playSongInternal(searchRequest, limit);
    }

    private void playSongInternal(String searchRequest, int limit) throws IOException {
        String url = buildUrl(searchRequest, limit);
        String page = downloadWebPage(url);

        String artistName = getTag(page, "artistName");
        String trackName = getTag(page, "trackName");
        String previewUrl = getTag(page, "previewUrl");
//        System.out.println(page);
        System.out.println(artistName + " - " + trackName);
        try (InputStream in = new URL(previewUrl).openStream()) {
            Files.copy(in, Paths.get(trackName + ".m4a"));
        }
        System.out.println("Downloaded!");

        // источник: https://www.javatpoint.com/how-to-open-a-file-in-java
        if (!Desktop.isDesktopSupported()) //check if Desktop is supported by Platform or not
        {
            System.out.println("File opening not supported");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        File file = new File(trackName + ".m4a");
        desktop.open(file); //opens the specified file
    }

    private String getTag(String page, String tagName) {
//        int start = page.indexOf("artistName") + "artistName".length() + 3;
        int start = page.indexOf(tagName) + tagName.length() + 3;
        int end = page.indexOf("\"", start);
        String value = page.substring(start, end);
        return value;
    }

    private String buildUrl(String searchRequest, int limit) {
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
        return result.toString(); // goto 13 line
    }
}
