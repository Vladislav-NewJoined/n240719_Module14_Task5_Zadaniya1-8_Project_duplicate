package a_shablony_ITunesRandomMoviePicker;

//import java.awt.*;
//import java.io.*;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Scanner;
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
//        player.playMovie(String.valueOf(movies[chosenIndex].name));
//    }
//}
///*public */class RandomMoviePicker {
//    PageDownloader downloader = new PageDownloader();
//
//    Movie[] getRandomMovieNames() {
//        String url = "https://randommer.io/random-movies";
//        String page = downloader.downloadWebPage(url);
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
//class PageDownloader {
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
//
//class ITunesMoviePlayer {
////    // источник: https://www.javatpoint.com/how-to-open-a-file-in-java
////    void playMovie(String searchRequest) throws IOException {
////        playSongInternal(searchRequest, 1);
////    }
////    // конец источника: https://www.javatpoint.com/how-to-open-a-file-in-java
//
//    PageDownloader downloader = new PageDownloader();
//
//    void playMovie(String searchRequest) throws IOException {
//        String url = buildUrl(searchRequest);
//        System.out.println("Will search film by term: " + searchRequest);
//        String page = downloader.downloadWebPage(url);
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



//// ПРИМЕР 8 работает, но выводит нули
//import java.awt.*;
//import java.io.*;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Scanner;
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
//        player.playMovie(String.valueOf(movies[chosenIndex].name));
//    }
//}
///*public */class RandomMoviePicker {
//    PageDownloader downloader = new PageDownloader();
//
//    Movie[] getRandomMovieNames() {
//        String url = "https://randommer.io/random-movies";
//        String page = downloader.downloadWebPage(url);
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
//class PageDownloader {
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
//
//class ITunesMoviePlayer {
////    // источник: https://www.javatpoint.com/how-to-open-a-file-in-java
////    void playMovie(String searchRequest) throws IOException {
////        playSongInternal(searchRequest, 1);
////    }
////    // конец источника: https://www.javatpoint.com/how-to-open-a-file-in-java
//
//    PageDownloader downloader = new PageDownloader();
//
//    void playMovie(String searchRequest) throws IOException {
//        String url = buildUrl(searchRequest);
//        System.out.println("Will search film by term: " + searchRequest);
//        String page = downloader.downloadWebPage(url);
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
//// КОНЕЦ ПРИМЕРА 8




//// ПРИМЕР 7_2
import java.io.IOException;
import java.util.Scanner;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

// проверка: успешно работает! мин 56 05 видеоурок Модуль1 Тема4 Урок 2. Конструкторы
// прервался на мин КОНЕЦ ВИДЕОУРОКА видеоурок Модуль1 Тема4 Урок 2. Конструкторы

public class ITunesRandomMoviePicker {
    // 1. Конструктор - это просто метод, разница в том, что вызывается он через new <ИмяКласса>(..,)
    //    то есть - то же самое, как если сделать метод init, только чуть короче

    public static void main(String[] args) throws IOException {
        RandomMoviePicker5 moviePicker = new RandomMoviePicker5();
        Movie[] movies = moviePicker.getRandomMovieNames();
        System.out.println("Choose film to watch: ");
//        System.out.println(Arrays.toString(movies));
//        System.out.println(/*Arrays.toString(movies)*/movies);
        for (int i=0; i<movies.length; i++) {
            int number = i+1;
            System.out.println(number + ": " + movies[i].name + "(" + movies[i].year + ")");
        }
        System.out.print("Film number: ");
        Scanner scanner = new Scanner(System.in);
        int chosenNumber = scanner.nextInt();
        int chosenIndex = chosenNumber - 1;
        ITunesMoviePlayer player = new ITunesMoviePlayer();
        player.playMovie(movies[chosenIndex].name);
    }
}

class RandomMoviePicker5 {
    PageDownloader downloader = new PageDownloader();

    //    void getRandomMovieNames() {
    Movie[] getRandomMovieNames() {
        String url = "https://randommer.io/random-movies";
        String page = downloader.downloadWebPage(url);

        Movie[] movies = new Movie[16];
        int endIndex = 0;
        for (int i=0; i<16; i++) {
            int captionIndex = page.indexOf("<div class=\"caption\"", endIndex);
            int startIndex = captionIndex + 52;
            endIndex = page.indexOf("</div>", startIndex) - 28;
            String movieName = page.substring(startIndex, endIndex);
            String nameWithoutYear = movieName.substring(0, movieName.length() - 6);
            String year = movieName.substring(movieName.length()-5, movieName.length()-1);
            movies[i] = new Movie(nameWithoutYear, year);
        }
        return movies;
    }
}

class Movie {
    String name;
    String year;

    Movie(String inputName, String inputYear) {
        this.name = inputName;
        this.year = inputYear;
    }
}

class ITunesMoviePlayer {
//    // источник: https://www.javatpoint.com/how-to-open-a-file-in-java
//    void playMovie(String searchRequest) throws IOException {
//        playSongInternal(searchRequest, 1);
//    }
//    // конец источника: https://www.javatpoint.com/how-to-open-a-file-in-java

    PageDownloader downloader = new PageDownloader();

    void playMovie(String searchRequest) throws IOException {
        String url = buildUrl(searchRequest);
        System.out.println("Will search film by term: " + searchRequest);
        String page = downloader.downloadWebPage(url);

        String movieName = getTag(page, "trackName");
        String previewUrl = getTag(page, "previewUrl");
        String fileExtension = previewUrl.substring(previewUrl.length() - 3);
        String fileName = movieName + "." + fileExtension;
        System.out.println("Will download " + movieName);
        try (InputStream in = new URL(previewUrl).openStream()) {
            Files.copy(in, Paths.get("src/a_shablony_ITunesRandomMoviePicker/" + fileName));
            File file = new File("src/a_shablony_ITunesRandomMoviePicker/" + fileName);
            if (file.exists()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
                System.out.println("Downloaded and opened!");
            } else {
                System.out.println("Error: The file " + fileName + " doesn't exist.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

class PageDownloader {
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
//// КОНЕЦ ПРИМЕРА 7_2




//// ПРИМЕР 7
//import java.io.IOException;
//import java.util.Scanner;
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//
//// В видеоуроке про это здесь: Модуль 4. Наследование, Задание 2. Конструкторы. мин 18 50.
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
//// КОНЕЦ ПРИМЕРА 7




////// ПРИМЕР 6
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class ITunesRandomMoviePicker {
//
//    private static final String RANDOM_MER_URL = "https://randommer.io/random-movies";
//
//    public static void main(String[] args) {
//        try {
//            URL url = new URL(RANDOM_MER_URL);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            StringBuilder response = new StringBuilder();
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            String moviesJson = response.toString();
//            Pattern pattern = Pattern.compile("\"title\":\"(.*?)\"");
//            Matcher matcher = pattern.matcher(moviesJson);
//
//            int count = 0;
//            while (matcher.find() && count < 16) {
//                System.out.println("Random movie: " + matcher.group(1));
//                count++;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//// КОНЕЦ ПРИМЕРА 6




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
