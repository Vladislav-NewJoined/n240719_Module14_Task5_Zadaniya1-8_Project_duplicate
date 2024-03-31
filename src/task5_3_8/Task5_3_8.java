package task5_3_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Task5_3_8 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
                    8. Доработайте медиабраузер iTunes: он будет сохранять все найденные результаты в массив,
                       далее давай пользователю возможность поискать еще раз; но скрывая повторяющиеся
                       результаты (сверяясь с массивом)\s

                Решение:\s""");

        RandomMoviePicker moviePicker = new RandomMoviePicker();
        ITunesProduct[] movies = moviePicker.getRandomMovieNames();
        System.out.println("Films to watch: ");
        for (int i = 0; i < movies.length; i++) {
            int number = i + 1;
            System.out.println(number + ": " + movies[i].name + "(" + movies[i].year + ")");
        }

        System.out.println("Continue the selection? 'yes' or 'no'");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = reader.readLine();

        if (userInput.equals("yes")) {
            ITunesProduct[] movies2 = moviePicker.getRandomMovieNames();
            System.out.println("Additional films to watch: ");
            for (int i = 0; i < movies2.length; i++) {
                int number = i + 1;
                System.out.println(number + ": " + movies2[i].name + "(" + movies2[i].year + ")");
            }

            // Compare the movies arrays
            System.out.println("Checking for coincidences between movies:");
            for (ITunesProduct movie1 : movies) {
                for (ITunesProduct movie2 : movies2) {
                    if (movie1.name.equals(movie2.name) && movie1.year.equals(movie2.year)) {
                        System.out.println("Coincidence found: " + movie1.name + "(" + movie1.year + ")");
                    }
                }
            }
        } else if (userInput.equals("no")) {
            System.out.println("Program closed.");
        } else {
            System.out.println("Invalid input. Program closed.");
        }
    }
}

class RandomMoviePicker {
    PageDownloader downloader = new PageDownloader();

    ITunesProduct[] getRandomMovieNames() {
        String url = "https://randommer.io/random-movies";
        String page = downloader.downloadWebPage(url);

        ITunesProduct[] movies = new ITunesProduct[16];
        int endIndex = 0;
        for (int i = 0; i < 16; i++) {
            int captionIndex = page.indexOf("<div class=\"caption\"", endIndex);
            int startIndex = captionIndex + 52;
            endIndex = page.indexOf("</div>", startIndex) - 28;
            String movieName = page.substring(startIndex, endIndex);
            String nameWithoutYear = movieName.substring(0, movieName.length() - 6);
            String year = movieName.substring(movieName.length() - 5, movieName.length() - 1);
            movies[i] = new ITunesProduct(nameWithoutYear, year);
        }
        return movies;
    }
}

class ITunesProduct {
    String name;
    String year;
    String artistName;
    String collectionName;
    String previewUrl;
    String country;

    ITunesProduct(String inputName, String inputYear) {
        this.name = inputName;
        this.year = inputYear;
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







//// ПРИМЕР 6 _работает для создания 2-го массива. Осталось сравнить массивы
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//
//public class Task5_3_8 {
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
//                    8. Доработайте медиабраузер iTunes: он будет сохранять все найденные результаты в массив,
//                       далее давай пользователю возможность поискать еще раз; но скрывая повторяющиеся
//                       результаты (сверяясь с массивом)\s
//
//                Решение:\s""");
//
//        RandomMoviePicker moviePicker = new RandomMoviePicker();
//        ITunesProduct[] movies = moviePicker.getRandomMovieNames();
//        System.out.println("Films to watch: ");
//        for (int i = 0; i < movies.length; i++) {
//            int number = i + 1;
//            System.out.println(number + ": " + movies[i].name + "(" + movies[i].year + ")");
//        }
//
//        System.out.println("Continue the selection? 'yes' or 'no'");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String userInput = reader.readLine();
//
//        if (userInput.equals("yes")) {
//            ITunesProduct[] movies2 = moviePicker.getRandomMovieNames();
//            System.out.println("Additional films to watch: ");
//            for (int i = 0; i < movies2.length; i++) {
//                int number = i + 1;
//                System.out.println(number + ": " + movies2[i].name + "(" + movies2[i].year + ")");
//            }
//        } else if (userInput.equals("no")) {
//            System.out.println("Exiting program.");
//        } else {
//            System.out.println("Invalid input. Exiting program.");
//        }
//    }
//}
//
//class RandomMoviePicker {
//    PageDownloader downloader = new PageDownloader();
//
//    ITunesProduct[] getRandomMovieNames() {
//        String url = "https://randommer.io/random-movies";
//        String page = downloader.downloadWebPage(url);
//
//        ITunesProduct[] movies = new ITunesProduct[16];
//        int endIndex = 0;
//        for (int i = 0; i < 16; i++) {
//            int captionIndex = page.indexOf("<div class=\"caption\"", endIndex);
//            int startIndex = captionIndex + 52;
//            endIndex = page.indexOf("</div>", startIndex) - 28;
//            String movieName = page.substring(startIndex, endIndex);
//            String nameWithoutYear = movieName.substring(0, movieName.length() - 6);
//            String year = movieName.substring(movieName.length() - 5, movieName.length() - 1);
//            movies[i] = new ITunesProduct(nameWithoutYear, year);
//        }
//        return movies;
//    }
//}
//
//class ITunesProduct {
//    String name;
//    String year;
//    String artistName;
//    String collectionName;
//    String previewUrl;
//    String country;
//
//    ITunesProduct(String inputName, String inputYear) {
//        this.name = inputName;
//        this.year = inputYear;
//    }
//}
//
//class PageDownloader {
//    public String downloadWebPage(String url) {
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
//// КОНЕЦ ПРИМЕРА 6



//// ПРИМЕР 5 _очищенный пример 2, без сохранения файла с фильмом
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//
//public class Task5_3_8 {
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
//                    8. Доработайте медиабраузер iTunes: он будет сохранять все найденные результаты в массив,
//                       далее давай пользователю возможность поискать еще раз; но скрывая повторяющиеся
//                       результаты (сверяясь с массивом)\s
//
//                Решение:\s""");
//
//        RandomMoviePicker moviePicker = new RandomMoviePicker();
//        ITunesProduct[] movies = moviePicker.getRandomMovieNames();
//        System.out.println("Films to watch: ");
//        for (int i=0; i<movies.length; i++) {
//            int number = i+1;
//            System.out.println(number + ": " + movies[i].name + "(" + movies[i].year + ")");
//        }
//    }
//}
//
//class RandomMoviePicker {
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
//    String artistName;
//    String collectionName;
//    String previewUrl;
//    String country;
//
//    ITunesProduct(String inputName, String inputYear) {
//        this.name = inputName;
//        this.year = inputYear;
//    }
//}
//
//class PageDownloader {
//    public String downloadWebPage(String url) {
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
//import java.io.*;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.Arrays;
//import java.util.Objects;
//import java.util.Scanner;
//
//public class Task5_3_8 {
//    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//
//        do {
//            System.out.println("Please choose a film to watch:");
//
//            RandomMoviePicker moviePicker = new RandomMoviePicker();
//            ITunesProduct[] movies = moviePicker.getRandomMovieNames();
//            for (int i = 0; i < movies.length; i++) {
//                int number = i + 1;
//                System.out.println(number + ": " + movies[i].name + " (" + movies[i].year + ")");
//            }
//
//            System.out.println("Continue the selection? 'yes' or 'no':");
//        } while (scanner.nextLine().equalsIgnoreCase("yes"));
//
//        scanner.close();
//        System.out.println("Coincidences are not found. Closing the program.");
//    }
//}
//
//class RandomMoviePicker {
//    PageDownloader downloader = new PageDownloader();
//
//    ITunesProduct[] getRandomMovieNames() {
//        String url = "https://randommer.io/random-movies";
//        String page = downloader.downloadWebPage(url);
//
//        ITunesProduct[] movies = new ITunesProduct[16];
//        int endIndex = 0;
//        int count = 0;
//        for (int i = 0; i < 16; i++) {
//            int captionIndex = page.indexOf("<div class=\"caption\"", endIndex);
//            int startIndex = captionIndex + 52;
//            endIndex = page.indexOf("</div>", startIndex) - 28;
//            String movieName = page.substring(startIndex, endIndex);
//            String nameWithoutYear = movieName.substring(0, movieName.length() - 6);
//            String year = movieName.substring(movieName.length() - 5, movieName.length() - 1);
//            ITunesProduct newMovie = new ITunesProduct(nameWithoutYear, year);
//
//            if (!hasDuplicate(movies, newMovie)) {
//                movies[count] = newMovie;
//                count++;
//            }
//        }
//        return Arrays.copyOf(movies, count);
//    }
//
//    private boolean hasDuplicate(ITunesProduct[] movies, ITunesProduct newMovie) {
//        for (ITunesProduct movie : movies) {
//            if (movie != null && movie.equals(newMovie)) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
//
//class PageDownloader {
//    public String downloadWebPage(String url) {
//        StringBuilder result = new StringBuilder();
//        String line;
//        URLConnection urlConnection;
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
//        return result.toString();
//    }
//}
//
//class ITunesProduct {
//    String name;
//    String year;
//    String artistName;
//    String collectionName;
//    String previewUrl;
//    String country;
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
//        ITunesProduct that = (ITunesProduct) obj;
//        return name.equals(that.name) && year.equals(that.year);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, year);
//    }
//}
//// КОНЕЦ ПРИМЕРА 4



//// ПРИМЕР 3 _создает два массива и ищет совпадения в массивах
//class ITunesProduct {
//    private String title;
//
//    public ITunesProduct(String title) {
//        this.title = title;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//}
//
//public class Task5_3_8 {
//    public static void main(String[] args) {
//        ITunesProduct[] movies = {new ITunesProduct("Movie1"), new ITunesProduct("Movie2"), new ITunesProduct("Movie3")};
//        ITunesProduct[] movies2 = {new ITunesProduct("Movie3"), new ITunesProduct("Movie4"), new ITunesProduct("Movie5")};
//
//        System.out.println("movies array: ");
//        for (ITunesProduct movie : movies) {
//            System.out.println(movie.getTitle());
//        }
//
//        System.out.println("\nmovies2 array: ");
//        for (ITunesProduct movie : movies2) {
//            System.out.println(movie.getTitle());
//        }
//
//        boolean foundCoincidences = false;
//
//        for (ITunesProduct movie : movies) {
//            for (ITunesProduct movie2 : movies2) {
//                if (movie.getTitle().equals(movie2.getTitle())) {
//                    foundCoincidences = true;
//                    System.out.println("\nFound coincidences: " + movie.getTitle());
//                }
//            }
//        }
//
//        if (foundCoincidences) {
//            System.out.println("Closing the program");
//        } else {
//            System.out.println("Coincidences are not found. Closing the program");
//        }
//    }
//}
//// КОНЕЦ ПРИМЕРА 3




//// ПРИМЕР 2  _все работает, взято из задания task5_3_7
//import java.awt.*;
//import java.io.*;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Scanner;
//
//public class Task5_3_8 {
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
//                    8. Доработайте медиабраузер iTunes: он будет сохранять все найденные результаты в массив,
//                       далее давай пользователю возможность поискать еще раз; но скрывая повторяющиеся
//                       результаты (сверяясь с массивом)\s
//
//                Решение:\s""");
//
//        RandomMoviePicker moviePicker = new RandomMoviePicker();
//        ITunesProduct[] movies = moviePicker.getRandomMovieNames();
//        System.out.println("Choose film to watch: ");
////        System.out.println(Arrays.toString(movies));
////        System.out.println(/*Arrays.toString(movies)*/movies);
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
//    }
//}
//
//class RandomMoviePicker {
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
//    String artistName;
//    String collectionName;
//    String previewUrl;
//    String country;
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
//            Files.copy(in, Paths.get("src/task5_3_8/" + fileName));
//            File file = new File("src/task5_3_8/" + fileName);
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