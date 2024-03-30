package task5_3_7;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Task5_3_7 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
                    7. Реализуйте метод equals для класса ITunesProduct.\s

                Решение:\s""");

        RandomMoviePicker5 moviePicker = new RandomMoviePicker5();
        ITunesProduct[] movies = moviePicker.getRandomMovieNames();
        System.out.println("Choose film to watch: ");
        for (int i=0; i<movies.length; i++) {
            int number = i+1;
            System.out.println(number + ": " + movies[i].name + "(" + movies[i].year + ")");
        }
        System.out.print("Enter chosen film number: ");
        Scanner scanner = new Scanner(System.in);
        int chosenNumber = scanner.nextInt();
        int chosenIndex = chosenNumber - 1;
        ITunesMoviePlayer player = new ITunesMoviePlayer();
        player.playMovie(movies[chosenIndex].name);

        // Check if two movies are equal
        ITunesProduct movie1 = new ITunesProduct("Movie1", "2021");
        ITunesProduct movie2 = new ITunesProduct("Movie2", "2022");
        ITunesProduct movie3 = new ITunesProduct("Movie1", "2021");

        System.out.println("movie1 equals to movie2: " + movie1.equals(movie2));
        System.out.println("movie1 equals to movie3: " + movie1.equals(movie3));
    }
}

class RandomMoviePicker5 {
    PageDownloader downloader = new PageDownloader();

    ITunesProduct[] getRandomMovieNames() {
        String url = "https://randommer.io/random-movies";
        String page = downloader.downloadWebPage(url);

        ITunesProduct[] movies = new ITunesProduct[16];
        int endIndex = 0;
        for (int i=0; i<16; i++) {
            int captionIndex = page.indexOf("<div class=\"caption\"", endIndex);
            int startIndex = captionIndex + 52;
            endIndex = page.indexOf("</div>", startIndex) - 28;
            String movieName = page.substring(startIndex, endIndex);
            String nameWithoutYear = movieName.substring(0, movieName.length() - 6);
            String year = movieName.substring(movieName.length()-5, movieName.length()-1);
            movies[i] = new ITunesProduct(nameWithoutYear, year);
        }
        return movies;
    }
}

class ITunesProduct {
    String name;
    String year;

    ITunesProduct(String inputName, String inputYear) {
        this.name = inputName;
        this.year = inputYear;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ITunesProduct other = (ITunesProduct) obj;
        return this.name.equals(other.name) && this.year.equals(other.year);
    }
}

class ITunesMoviePlayer {
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
            Files.copy(in, Paths.get("src/task5_3_7/" + fileName));
            File file = new File("src/task5_3_7/" + fileName);
            if (file.exists()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
                System.out.println("Downloaded and opened!\n");
            } else {
                System.out.println("Error: The file " + fileName + " doesn't exist.\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Проверяем при при помощи метода equals(), совпадают ли два условно" +
                " найденных фильма:");
    }
    private String getTag(String page, String tagName) {
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
    public String downloadWebPage(String url) {
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




//// ПРИМЕР 4 _работает, но есть закомментированные строки, которые в чистовом варианте убраны.
//import java.awt.*;
//import java.io.*;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Scanner;
//
//public class Task5_3_7 {
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
//                    7. Реализуйте метод equals для класса ITunesProduct.\s
//
//                Решение:\s""");
//
//        RandomMoviePicker5 moviePicker = new RandomMoviePicker5();
//        ITunesProduct[] movies = moviePicker.getRandomMovieNames();
//        System.out.println("Choose film to watch: ");
//        for (int i=0; i<movies.length; i++) {
//            int number = i+1;
//            System.out.println(number + ": " + movies[i].name + "(" + movies[i].year + ")");
//        }
//        System.out.print("Enter chosen film number: ");
//        Scanner scanner = new Scanner(System.in);
//        int chosenNumber = scanner.nextInt();
//        int chosenIndex = chosenNumber - 1;
//        ITunesMoviePlayer player = new ITunesMoviePlayer();
//        player.playMovie(movies[chosenIndex].name);
//
//        // Check if two movies are equal
//        ITunesProduct movie1 = new ITunesProduct("Movie1", "2021");
//        ITunesProduct movie2 = new ITunesProduct("Movie2", "2022");
//        ITunesProduct movie3 = new ITunesProduct("Movie1", "2021");
//
//        System.out.println("movie1 equals to movie2: " + movie1.equals(movie2));
//        System.out.println("movie1 equals to movie3: " + movie1.equals(movie3));
//    }
//}
//
//class RandomMoviePicker5 {
//    PageDownloader downloader = new PageDownloader();
//
//    ITunesProduct[] getRandomMovieNames() {
//        String url = "https://randommer.io/random-movies";
//        String page = downloader.downloadWebPage(url);
//
//        ITunesProduct[] movies = new ITunesProduct[16];
//        int endIndex = 0;
//        for (int i=0; i<16; i++) {
//            int captionIndex = page.indexOf("<div class=\"caption\"", endIndex);
//            int startIndex = captionIndex + 52;
//            endIndex = page.indexOf("</div>", startIndex) - 28;
//            String movieName = page.substring(startIndex, endIndex);
//            String nameWithoutYear = movieName.substring(0, movieName.length() - 6);
//            String year = movieName.substring(movieName.length()-5, movieName.length()-1);
//            movies[i] = new ITunesProduct(nameWithoutYear, year);
//        }
//        return movies;
//    }
//}
//
//class ITunesProduct {
//    String name;
//    String year;
//
//    ITunesProduct(String inputName, String inputYear) {
//        this.name = inputName;
//        this.year = inputYear;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null || getClass() != obj.getClass()) {
//            return false;
//        }
//        ITunesProduct other = (ITunesProduct) obj;
//        return this.name.equals(other.name) && this.year.equals(other.year);
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
//        String fileExtension = previewUrl.substring(previewUrl.length() - 3);
//        String fileName = movieName + "." + fileExtension;
//        System.out.println("Will download " + movieName);
//        try (InputStream in = new URL(previewUrl).openStream()) {
//            Files.copy(in, Paths.get("src/task5_3_7/" + fileName));
//            File file = new File("src/task5_3_7/" + fileName);
//            if (file.exists()) {
//                Desktop desktop = Desktop.getDesktop();
//                desktop.open(file);
//                System.out.println("Downloaded and opened!\n");
//            } else {
//                System.out.println("Error: The file " + fileName + " doesn't exist.\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Проверяем при при помощи метода equals(), совпадают ли два условно" +
//                " найденных фильма:");
//    }
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
//// КОНЕЦ ПРИМЕРА 4




//// ПРИМЕР 3 _работает без метода equals
//import java.awt.*;
//import java.io.*;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Scanner;
//
//public class Task5_3_7 {
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
//                    7. Реализуйте метод equals для класса ITunesProduct.\s
//
//                Решение:\s""");
//
//        RandomMoviePicker5 moviePicker = new RandomMoviePicker5();
//        ITunesProduct[] movies = moviePicker.getRandomMovieNames();
//        System.out.println("Choose film to watch: ");
//        for (int i=0; i<movies.length; i++) {
//            int number = i+1;
//            System.out.println(number + ": " + movies[i].name + "(" + movies[i].year + ")");
//        }
//        System.out.print("Film number: ");
//        Scanner scanner = new Scanner(System.in);
//        int chosenNumber = scanner.nextInt();
//        int chosenIndex = chosenNumber - 1;
//        ITunesMoviePlayer player = new ITunesMoviePlayer();
//        player.playMovie(movies[chosenIndex].name);
//    }
//}
//
//class RandomMoviePicker5 {
//    PageDownloader downloader = new PageDownloader();
//
//    ITunesProduct[] getRandomMovieNames() {
//        String url = "https://randommer.io/random-movies";
//        String page = downloader.downloadWebPage(url);
//
//        ITunesProduct[] movies = new ITunesProduct[16];
//        int endIndex = 0;
//        for (int i=0; i<16; i++) {
//            int captionIndex = page.indexOf("<div class=\"caption\"", endIndex);
//            int startIndex = captionIndex + 52;
//            endIndex = page.indexOf("</div>", startIndex) - 28;
//            String movieName = page.substring(startIndex, endIndex);
//            String nameWithoutYear = movieName.substring(0, movieName.length() - 6);
//            String year = movieName.substring(movieName.length()-5, movieName.length()-1);
//            movies[i] = new ITunesProduct(nameWithoutYear, year);
//        }
//        return movies;
//    }
//}
//
//class ITunesProduct {
//    String name;
//    String year;
//
//    ITunesProduct(String inputName, String inputYear) {
//        this.name = inputName;
//        this.year = inputYear;
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
//        String fileExtension = previewUrl.substring(previewUrl.length() - 3);
//        String fileName = movieName + "." + fileExtension;
//        System.out.println("Will download " + movieName);
//        try (InputStream in = new URL(previewUrl).openStream()) {
//            Files.copy(in, Paths.get("src/task5_3_7/" + fileName));
//            File file = new File("src/task5_3_7/" + fileName);
//            if (file.exists()) {
//                Desktop desktop = Desktop.getDesktop();
//                desktop.open(file);
//                System.out.println("Downloaded and opened!");
//            } else {
//                System.out.println("Error: The file " + fileName + " doesn't exist.");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
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
//// КОНЕЦ ПРИМЕРА 3




//// ПРИМЕР 2 _Всё работает, но без ITunesProduct
//import java.awt.*;
//import java.io.*;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Scanner;
//
//public class Task5_3_7 {
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
//                    7. Реализуйте метод equals для класса ITunesProduct.\s
//
//                Решение:\s""");
//
//        RandomMoviePicker5 moviePicker = new RandomMoviePicker5();
//        ITunesProduct[] movies = moviePicker.getRandomMovieNames();
//        System.out.println("Choose film to watch: ");
////        System.out.println(Arrays.toString(movies));
////        System.out.println(/*Arrays.toString(movies)*/movies);
//        for (int i=0; i<movies.length; i++) {
//            int number = i+1;
//            System.out.println(number + ": " + movies[i].name + "(" + movies[i].year + ")");
//        }
//        System.out.print("Film number: ");
//        Scanner scanner = new Scanner(System.in);
//        int chosenNumber = scanner.nextInt();
//        int chosenIndex = chosenNumber - 1;
//        ITunesMoviePlayer player = new ITunesMoviePlayer();
//        player.playMovie(movies[chosenIndex].name);
//    }
//}
//
//class RandomMoviePicker5 {
//    PageDownloader downloader = new PageDownloader();
//
//    ITunesProduct[] getRandomMovieNames() {
//        String url = "https://randommer.io/random-movies";
//        String page = downloader.downloadWebPage(url);
//
//        ITunesProduct[] movies = new ITunesProduct[16];
//        int endIndex = 0;
//        for (int i=0; i<16; i++) {
//            int captionIndex = page.indexOf("<div class=\"caption\"", endIndex);
//            int startIndex = captionIndex + 52;
//            endIndex = page.indexOf("</div>", startIndex) - 28;
//            String movieName = page.substring(startIndex, endIndex);
//            String nameWithoutYear = movieName.substring(0, movieName.length() - 6);
//            String year = movieName.substring(movieName.length()-5, movieName.length()-1);
//            movies[i] = new ITunesProduct(nameWithoutYear, year);
//        }
//        return movies;
//    }
//}
//
//class ITunesProduct {
//    String name;
//    String year;
//
//    ITunesProduct(String inputName, String inputYear) {
//        this.name = inputName;
//        this.year = inputYear;
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
//        String fileExtension = previewUrl.substring(previewUrl.length() - 3);
//        String fileName = movieName + "." + fileExtension;
//        System.out.println("Will download " + movieName);
//        try (InputStream in = new URL(previewUrl).openStream()) {
//            Files.copy(in, Paths.get("src/task5_3_7/" + fileName));
//            File file = new File("src/task5_3_7/" + fileName);
//            if (file.exists()) {
//                Desktop desktop = Desktop.getDesktop();
//                desktop.open(file);
//                System.out.println("Downloaded and opened!");
//            } else {
//                System.out.println("Error: The file " + fileName + " doesn't exist.");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
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
//// КОНЕЦ ПРИМЕРА 2