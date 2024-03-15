package a_shablony;

//import task1_4_2_0_whole_video.PageDownloader;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ITunesMoviePlayer {
//    // источник: https://www.javatpoint.com/how-to-open-a-file-in-java
//    void playMovie(String searchRequest) throws IOException {
//        playSongInternal(searchRequest, 1);
//    }
//    // конец источника: https://www.javatpoint.com/how-to-open-a-file-in-java

    a_shablony.PageDownloader downloader = new a_shablony.PageDownloader();

    void playMovie(String searchRequest) throws IOException {
        String url = buildUrl(searchRequest);
        System.out.println("Will search film by term: " + searchRequest);
        String page = downloader.downloadWebPage(url);

        String movieName = getTag(page, "trackName");
        String previewUrl = getTag(page, "previewUrl");
        String fileExtension = previewUrl.substring(previewUrl.length()-3);
        String fileName = movieName + "." + fileExtension;
        System.out.println("Will download " + movieName);
        try (InputStream in = new URL(previewUrl).openStream()) {
            Files.copy(in, Paths.get(fileName));
        }
        System.out.println("Downloaded!");

        // источник: https://www.javatpoint.com/how-to-open-a-file-in-java
        if (!Desktop.isDesktopSupported()) //check if Desktop is supported by Platform or not
        {
            System.out.println("File opening not supported");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        File file = new File(fileName);
        desktop.open(file); //opens the specified file
    }

    private String getTag(String page, String tagName) {
//        int start = page.indexOf("artistName") + "artistName".length() + 3;
        int start = page.indexOf(tagName) + tagName.length() + 3;
        int end = page.indexOf("\"", start);
        String value = page.substring(start, end);
        return value;
    }

    private String buildUrl(String searchRequest) {
        int limit = 50;
        String term = searchRequest.replaceAll(" ", "+");
        String itunesApi = "https://itunes.apple.com/search?term=";
        String limitParam = "&limit=1" + limit;
        String mediaParam = "&media=movie";
        StringBuilder builder = new StringBuilder();
        builder.append(itunesApi);
        builder.append(term);
        builder.append(limitParam);
        builder.append(mediaParam);
        return builder.toString();
    }

    public String downloadWebPage(String url) throws IOException {
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
