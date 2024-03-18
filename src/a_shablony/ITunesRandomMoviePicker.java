package a_shablony;

import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ITunesRandomMoviePicker {
    public static void main(String[] args) throws IOException {
        RandomMoviePicker moviePicker = new RandomMoviePicker();
        Movie[] movies = moviePicker.getRandomMovieNames();
        System.out.println("Chose film to watch: ");
        for (int i = 0; i < movies.length; i++) {
            int number = i + 1;
            System.out.println(number + ": " + movies[i].name + "(" + movies[i].year + ")");
        }
        System.out.print("Film number: ");
        Scanner scanner = new Scanner(System.in);
        int chosenNumber = scanner.nextInt();
        int chosenIndex = chosenNumber - 1;
        ITunesMoviePlayer player = new ITunesMoviePlayer();
//        player.playMovie(movies[chosenIndex].name);
        player.playMovie(String.valueOf(movies[chosenIndex].name));
    }
}
class RandomMoviePicker {
    PageDownloader2 downloader2 = new PageDownloader2();

    Movie[] getRandomMovieNames() {
        String url = "https://randommer.io/random-movies";
        String page = downloader2.downloadWebPage(url);

        Movie[] movies = new Movie[16];
        int endIndex = 0;
        for (int i = 0; i < 16; i++) {
            int captionIndex = page.indexOf("<div class=caption", endIndex);
            int startIndex = captionIndex + 22;
            endIndex = page.indexOf("</div>", startIndex);
            String movieName = page.substring(startIndex, endIndex);
            String nameWithoutYear = movieName.substring(0, movieName.length() - 6);
            String year = movieName.substring(movieName.length() - 5, movieName.length() - 1);
            movies[i] = new Movie(nameWithoutYear, year);
        }
        return movies;
    }
}

class Movie {

    public int year;
    public int name;

    public Movie(String nameWithoutYear, String year) {

    }
}

class PageDownloader2 {
    public String downloadWebPage(String url) /*throws IOException */{
        StringBuilder result = new StringBuilder();
        String line;
        URLConnection urlConnection = null;
        try {
            urlConnection = new URL(url).openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (InputStream is = urlConnection.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result.toString(); // goto 13 line
    }
}




//// ПРИМЕР 5
//import java.io.IOException;
//import java.util.Scanner;
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//
//public class ITunesRandomMoviePicker {
//    public static void main(String[] args) throws IOException {
//        RandomMoviePicker moviePicker = new RandomMoviePicker();
//        Movie[] movies = moviePicker.getRandomMovieNames();
//        System.out.println("Chose film to watch: ");
//        for (int i = 0; i < movies.length; i++) {
//            int number = i + 1;
//            System.out.println(number + ": " + movies[i].name + "(" + movies[i].year + ")");
//        }
//        System.out.print("Film number: ");
//        Scanner scanner = new Scanner(System.in);
//        int chosenNumber = scanner.nextInt();
//        int chosenIndex = chosenNumber - 1;
//        ITunesMoviePlayer player = new ITunesMoviePlayer();
////        player.playMovie(movies[chosenIndex].name);
//        player.playMovie(String.valueOf(movies[chosenIndex].name));
//    }
//}
//class RandomMoviePicker {
//    PageDownloader2 downloader2 = new PageDownloader2();
//
//    Movie[] getRandomMovieNames() {
//        String url = "https://randommer.io/random-movies";
//        String page = downloader2.downloadWebPage(url);
//
//        Movie[] movies = new Movie[16];
//        int endIndex = 0;
//        for (int i = 0; i < 16; i++) {
//            int captionIndex = page.indexOf("<div class=caption", endIndex);
//            int startIndex = captionIndex + 22;
//            endIndex = page.indexOf("</div>", startIndex);
//            String movieName = page.substring(startIndex, endIndex);
//            String nameWithoutYear = movieName.substring(0, movieName.length() - 6);
//            String year = movieName.substring(movieName.length() - 5, movieName.length() - 1);
//            movies[i] = new Movie(nameWithoutYear, year);
//        }
//        return movies;
//    }
//}
//
//class Movie {
//
//    public int year;
//    public int name;
//
//    public Movie(String nameWithoutYear, String year) {
//
//    }
//}
//
//class PageDownloader2 {
//    public String downloadWebPage(String url) /*throws IOException */{
//        StringBuilder result = new StringBuilder();
//        String line;
//        URLConnection urlConnection = null;
//        try {
//            urlConnection = new URL(url).openConnection();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try (InputStream is = urlConnection.getInputStream();
//             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
//            while ((line = br.readLine()) != null) {
//                result.append(line);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return result.toString(); // goto 13 line
//    }
//}
//// КОНЕЦ ПРИМЕРА 5





//// ПРИМЕР 4
//import java.io.IOException;
//import java.util.Scanner;
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//
//public class ITunesRandomMoviePicker {
//    public static void main(String[] args) throws IOException {
//        RandomMoviePicker moviePicker = new RandomMoviePicker();
//        Movie[] movies = moviePicker.getRandomMovieNames();
//        System.out.println("Chose film to watch: ");
//        for (int i = 0; i < movies.length; i++) {
//            int number = i + 1;
//            System.out.println(number + ": " + movies[i].name + "(" + movies[i].year + ")");
//        }
//        System.out.print("Film number: ");
//        Scanner scanner = new Scanner(System.in);
//        int chosenNumber = scanner.nextInt();
//        int chosenIndex = chosenNumber - 1;
//        ITunesMoviePlayer player = new ITunesMoviePlayer();
//        player.playMovie(movies[chosenIndex].name);
////        player.playMovie(String.valueOf(movies[chosenIndex].name));
//    }
//}
//class RandomMoviePicker {
//    PageDownloader2 downloader2 = new PageDownloader2();
//
//    Movie[] getRandomMovieNames() {
//        String url = "https://randommer.io/random-movies";
//        String page = downloader2.downloadWebPage(url);
//
//        Movie[] movies = new Movie[16];
//        int endIndex = 0;
//        for (int i = 0; i < 16; i++) {
//            int captionIndex = page.indexOf("<div class=caption", endIndex);
//            int startIndex = captionIndex + 22;
//            endIndex = page.indexOf("</div>", startIndex);
//            String movieName = page.substring(startIndex, endIndex);
//            String nameWithoutYear = movieName.substring(0, movieName.length() - 6);
//            String year = movieName.substring(movieName.length() - 5, movieName.length() - 1);
//            movies[i] = new Movie(nameWithoutYear, year);
//        }
//        return movies;
//    }
//}
//
//class Movie {
//
//    public int year;
//    public int name;
//
//    public Movie(String nameWithoutYear, String year) {
//
//    }
//}
//
//class PageDownloader2 {
//    public String downloadWebPage(String url) /*throws IOException */{
//        StringBuilder result = new StringBuilder();
//        String line;
//        URLConnection urlConnection = null;
//        try {
//            urlConnection = new URL(url).openConnection();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try (InputStream is = urlConnection.getInputStream();
//             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
//            while ((line = br.readLine()) != null) {
//                result.append(line);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return result.toString(); // goto 13 line
//    }
//}
//// КОНЕЦ ПРИМЕРА 4




//// ПРИМЕР 3
//import java.io.IOException;
//import java.util.Scanner;
//
//public class ITunesRandomMoviePicker {
//    public static void main(String[] args) throws IOException {
////        RandomMoviePicker moviePicker = new RandomMoviePicker();
//        RandomMoviePicker moviePicker = new RandomMoviePicker();
//        Movie[] movies = moviePicker.getRandomMovieNames();
//        System.out.println("Chose film to watch: ");
//        for (int i = 0; i < movies.length; i++) {
//            int number = i + 1;
//            System.out.println(number + ": " + movies[i].name + "(" + movies[i].year + ")");
//        }
//        System.out.print("Film number: ");
//        Scanner scanner = new Scanner(System.in);
//        int chosenNumber = scanner.nextInt();
//        int chosenIndex = chosenNumber - 1;
//        ITunesMoviePlayer player = new ITunesMoviePlayer();
//        player.playMovie(String.valueOf(movies[chosenIndex].name));
//    }
//}
///*public */class RandomMoviePicker {
//    PageDownloader2 downloader2 = new PageDownloader2();
//
//    Movie[] getRandomMovieNames() {
//        String url = "https://randommer.io/random-movies";
//        String page = downloader2.downloadWebPage(url);
//
//        Movie[] movies = new Movie[16];
//        int endIndex = 0;
//
//        for (int i = 0; i < 16; i++) {
//            int captionIndex = page.indexOf("<div class=caption", endIndex);
//            int startIndex = captionIndex + 22;
//            endIndex = page.indexOf("</div>", startIndex);
//            String movieName = page.substring(startIndex, endIndex);
//            String nameWithoutYear = movieName.substring(0, movieName.length() - 6);
//            String year = movieName.substring(movieName.length() - 5, movieName.length() - 1);
//            movies[i] = new Movie(nameWithoutYear, year);
//        }
//        return movies;
//    }
//}
//
///*private */class Movie {
//
//    public int year;
//    public int name;
//
//    public Movie(String nameWithoutYear, String year) {
//
//    }
//}
//// КОНЕЦ ПРИМЕРА 3




//// ПРИМЕР 2
//public class ITunesRandomMoviePicker {
////    // источник: https://www.javatpoint.com/how-to-open-a-file-in-java
////    void playMovie(String searchRequest) throws IOException {
////        playSongInternal(searchRequest, 1);
////    }
////    // конец источника: https://www.javatpoint.com/how-to-open-a-file-in-java
//
//    a_shablony.PageDownloader2 downloader2 = new a_shablony.PageDownloader2();
//
//    void playMovie(String searchRequest) throws IOException {
//        String url = buildUrl(searchRequest);
//        System.out.println("Will search film by term: " + searchRequest);
//        String page = downloader2.downloadWebPage(url);
//
//        String movieName = getTag(page, "trackName");
//        String previewUrl = getTag(page, "previewUrl");
//        String fileExtension = previewUrl.substring(previewUrl.length()-3);
//        String fileName = movieName + "." + fileExtension;
//        System.out.println("Will download " + movieName);
//        try (InputStream in = new URL(previewUrl).openStream()) {
//            Files.copy(in, Paths.get(fileName));
//        }
//        System.out.println("Downloaded!");
//
//        // источник: https://www.javatpoint.com/how-to-open-a-file-in-java
//        if (!Desktop.isDesktopSupported()) //check if Desktop is supported by Platform or not
//        {
//            System.out.println("File opening not supported");
//            return;
//        }
//
//        Desktop desktop = Desktop.getDesktop();
//        File file = new File(fileName);
//        desktop.open(file); //opens the specified file
//    }
//
//    private String getTag(String page, String tagName) {
////        int start = page.indexOf("artistName") + "artistName".length() + 3;
//        int start = page.indexOf(tagName) + tagName.length() + 3;
//        int end = page.indexOf("\"", start);
//        String value = page.substring(start, end);
//        return value;
//    }
//
//    private String buildUrl(String searchRequest) {
//        int limit = 50;
//        String term = searchRequest.replaceAll(" ", "+");
//        String itunesApi = "https://itunes.apple.com/search?term=";
//        String limitParam = "&limit=1" + limit;
//        String mediaParam = "&media=movie";
//        StringBuilder builder = new StringBuilder();
//        builder.append(itunesApi);
//        builder.append(term);
//        builder.append(limitParam);
//        builder.append(mediaParam);
//        return builder.toString();
//    }
//
//    public String downloadWebPage(String url) throws IOException {
//        StringBuilder result = new StringBuilder();
//        String line;
//        URLConnection urlConnection = new URL(url).openConnection();
//        try (InputStream is = urlConnection.getInputStream();
//             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
//            while ((line = br.readLine()) != null) {
//                result.append(line);
//            }
//        }
//        return result.toString(); // goto 13 line
//    }
//}
//// КОНЕЦ ПРИМЕРА 2
