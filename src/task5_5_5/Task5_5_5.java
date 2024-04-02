package task5_5_5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

abstract class SongLyrics {
    protected abstract String getLyrics(String song);
}

public class Task5_5_5 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №5:\s
                    5. Создайте метод printLyrics(String artist, String song, SongLyrics songLyrics) -
                       который выводит текст песни. Вызовите этот метод с наследником по выбору
                       пользователя (из файла, папки или апи)\s

                Решение:\s""");

        // Запрос у пользователя о выборе источника текста песни
        System.out.println("Выберите источник текста песни:\n" +
                "1. Файл (введите 'fil')\n" +
                "2. Папка (введите 'dir')\n" +
                "3. API (введите 'api')");

        // Считывание выбора пользователя
        String source = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            source = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SongLyrics songLyrics = null;
        switch (source) {
            case "fil":
                songLyrics = new FileSongLyrics("src/task5_5_5/SongLyrics.txt");
                break;
            case "dir":
                songLyrics = new DirectorySongLyrics("src/task5_5_5/DirectorySongLyrics");
                break;
            case "api":
                songLyrics = new ApiSongLyrics();
                break;
            default:
                System.out.println("Неверный выбор источника текста песни.");
                return;
        }

        printLyrics("Eminem", "Lose yourself", songLyrics);
    }

    // Метод для вывода текста песни
    private static void printLyrics(String artist, String song, SongLyrics songLyrics) {
        String lyrics = songLyrics.getLyrics(song);
        System.out.println("Выводим текст песни '" + song + "', исполнителя '" + artist + "':\n" + lyrics);
    }
}

class FileSongLyrics extends SongLyrics {
    private String filePath;

    public FileSongLyrics(String filePath) {
        this.filePath = filePath;
    }

    @Override
    protected String getLyrics(String song) {
        StringBuilder lyrics = new StringBuilder();
        boolean foundSong = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().equals(song)) {
                    foundSong = true;
                    continue;
                }
                if (foundSong) {
                    if (line.trim().isEmpty()) {
                        break;
                    }
                    lyrics.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (foundSong) {
            return lyrics.toString();
        } else {
            return "Песня не найдена";
        }
    }
}

class DirectorySongLyrics extends SongLyrics {
    private String directoryPath;

    public DirectorySongLyrics(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    protected String getLyrics(String song) {
        StringBuilder lyrics = new StringBuilder();
        boolean foundSong = false;

        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().equalsIgnoreCase(song + ".txt")) {
                        foundSong = true;
                        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                lyrics.append(line).append("\n");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }

        if (foundSong) {
            return lyrics.toString();
        } else {
            return "Песня не найдена";
        }
    }
}

class ApiSongLyrics extends SongLyrics {
    @Override
    protected String getLyrics(String song) {
        StringBuilder response = new StringBuilder();

        try {
            // Формируем URL для получения текста песни через API
            String apiUrl = "https://api.lyrics.ovh/v1/Eminem/" + song.replace(" ", "%20");
            URL url = new URL(apiUrl);

            // Устанавливаем соединение и получаем ответ
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Проверяем код ответа
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Если ответ успешный, считываем данные
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                }
            } else {
                // Если ответ не успешный, возвращаем сообщение об ошибке
                return "Ошибка: " + responseCode;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при загрузке текста песни через API";
        }

        // Обработка JSON ответа, чтобы получить текст песни
        String lyrics = response.toString().split("\"lyrics\":\"")[1].split("\"")[0];
        return lyrics;
    }
}






//// ПРИМЕР 5
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//abstract class SongLyrics {
//    protected abstract String getLyrics(String song);
//}
//
//public class Task5_5_5 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №5:\s
//                    5. Создайте метод printLyrics(String artist, String song, SongLyrics songLyrics) -
//                       который выводит текст песни. Вызовите этот метод с наследником по выбору
//                       пользователя (из файла, папки или апи)\s
//
//                Решение:\s""");
//
//        // Запрос у пользователя о выборе источника текста песни
//        System.out.println("Выберите источник текста песни:\n" +
//                "1. Файл (введите 'fil')\n" +
//                "2. Папка (введите 'dir')\n" +
//                "3. API (введите 'api')");
//
//        // Считывание выбора пользователя
//        String source = null;
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//            source = reader.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        SongLyrics songLyrics = null;
//        switch (source) {
//            case "fil":
//                songLyrics = new FileSongLyrics("src/task5_5_5/SongLyrics.txt");
//                break;
//            case "dir":
//                songLyrics = new DirectorySongLyrics("src/task5_5_5/DirectorySongLyrics");
//                break;
//            case "api":
//                songLyrics = new ApiSongLyrics();
//                break;
//            default:
//                System.out.println("Неверный выбор источника текста песни.");
//                return;
//        }
//
//        printLyrics("Eminem", "Lose yourself", songLyrics);
//    }
//
//    // Метод для вывода текста песни
//    private static void printLyrics(String artist, String song, SongLyrics songLyrics) {
//        String lyrics = songLyrics.getLyrics(song);
//        System.out.println("Выводим текст песни '" + song + "', исполнителя '" + artist + "':\n" + lyrics);
//    }
//}
//
//class FileSongLyrics extends SongLyrics {
//    private String filePath;
//
//    public FileSongLyrics(String filePath) {
//        this.filePath = filePath;
//    }
//
//    @Override
//    protected String getLyrics(String song) {
//        StringBuilder lyrics = new StringBuilder();
//        boolean foundSong = false;
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                if (line.trim().equals(song)) {
//                    foundSong = true;
//                    continue;
//                }
//                if (foundSong) {
//                    if (line.trim().isEmpty()) {
//                        break;
//                    }
//                    lyrics.append(line);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (foundSong) {
//            return lyrics.toString();
//        } else {
//            return "Песня не найдена";
//        }
//    }
//}
//
//class DirectorySongLyrics extends SongLyrics {
//    private String directoryPath;
//
//    public DirectorySongLyrics(String directoryPath) {
//        this.directoryPath = directoryPath;
//    }
//
//    @Override
//    protected String getLyrics(String song) {
//        StringBuilder lyrics = new StringBuilder();
//        boolean foundSong = false;
//
//        File directory = new File(directoryPath);
//        if (directory.exists() && directory.isDirectory()) {
//            File[] files = directory.listFiles();
//            if (files != null) {
//                for (File file : files) {
//                    if (file.isFile() && file.getName().equalsIgnoreCase(song + ".txt")) {
//                        foundSong = true;
//                        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//                            String line;
//                            while ((line = br.readLine()) != null) {
//                                lyrics.append(line).append("\n");
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//
//        if (foundSong) {
//            return lyrics.toString();
//        } else {
//            return "Песня не найдена";
//        }
//    }
//}
//
//class ApiSongLyrics extends SongLyrics {
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
//// КОНЕЦ ПРИМЕРА 5




//// ПРИМЕР 4
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//
//abstract class SongLyrics {
//    protected abstract String getLyrics(String song);
//}
//
//public class Task5_5_5 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №5:\s
//                    5. Создайте метод printLyrics(String artist, String song, SongLyrics songLyrics) -
//                       который выводит текст песни. Вызовите этот метод с наследником по выбору
//                       пользователя (из файла, папки или апи)\s
//
//                Решение:\s""");
//
//        // Пользовательский выбор источника текста песни
//        String source = "file"; // Можно изменить на "directory" или "api" в зависимости от выбора пользователя
//
//        SongLyrics songLyrics = null;
//        switch (source) {
//            case "file":
//                songLyrics = new FileSongLyrics("src/task5_5_5/SongLyrics.txt");
//                break;
//            case "directory":
//                songLyrics = new DirectorySongLyrics("src/task5_5_5/DirectorySongLyrics");
//                break;
//            case "api":
//                songLyrics = new ApiSongLyrics();
//                break;
//            default:
//                System.out.println("Неверный выбор источника текста песни.");
//                return;
//        }
//
//        printLyrics("Eminem", "Lose yourself", songLyrics);
//    }
//
//    // Метод для вывода текста песни
//    private static void printLyrics(String artist, String song, SongLyrics songLyrics) {
//        String lyrics = songLyrics.getLyrics(song);
//        System.out.println("Выводим текст песни '" + song + "', исполнителя '" + artist + "':\n" + lyrics);
//    }
//}
//
//class FileSongLyrics extends SongLyrics {
//    private String filePath;
//
//    public FileSongLyrics(String filePath) {
//        this.filePath = filePath;
//    }
//
//    @Override
//    protected String getLyrics(String song) {
//        StringBuilder lyrics = new StringBuilder();
//        boolean foundSong = false;
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                if (line.trim().equals(song)) {
//                    foundSong = true;
//                    continue;
//                }
//                if (foundSong) {
//                    if (line.trim().isEmpty()) {
//                        break;
//                    }
//                    lyrics.append(line);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (foundSong) {
//            return lyrics.toString();
//        } else {
//            return "Песня не найдена";
//        }
//    }
//}
//
//class DirectorySongLyrics extends SongLyrics {
//    private String directoryPath;
//
//    public DirectorySongLyrics(String directoryPath) {
//        this.directoryPath = directoryPath;
//    }
//
//    @Override
//    protected String getLyrics(String song) {
//        StringBuilder lyrics = new StringBuilder();
//        boolean foundSong = false;
//
//        File directory = new File(directoryPath);
//        if (directory.exists() && directory.isDirectory()) {
//            File[] files = directory.listFiles();
//            if (files != null) {
//                for (File file : files) {
//                    if (file.isFile() && file.getName().equalsIgnoreCase(song + ".txt")) {
//                        foundSong = true;
//                        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//                            String line;
//                            while ((line = br.readLine()) != null) {
//                                lyrics.append(line).append("\n");
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//
//        if (foundSong) {
//            return lyrics.toString();
//        } else {
//            return "Песня не найдена";
//        }
//    }
//}
//
//class ApiSongLyrics extends SongLyrics {
//    @Override
//    protected String getLyrics(String song) {
//        // Реализация получения текста песни через API
//        return "Текст песни из API";
//    }
//}
//// КОНЕЦ ПРИМЕРА 4




//// ПРИМЕР 3
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//abstract class SongLyrics {
//    protected abstract String getLyrics(String song);
//}
//
//public class Task5_5_5 {
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №5:\s
//                    5. Создайте метод printLyrics(String artist, String song, SongLyrics songLyrics) -
//                       который выводит текст песни. Вызовите этот метод с наследником по выбору
//                       пользователя (из файла, папки или апи)\s
//
//                Решение:\s""");
//        System.out.println("В файл src/task5_5_5/SongLyrics.txt в тестовом режиме записаны" +
//                " названия и тексты трёх песен. Ищем в файле песни по названию.\n");
//        FileSongLyrics fileSongLyrics = new FileSongLyrics();
//        String song = "Life is life";
//
//        String lyrics = fileSongLyrics.getLyrics(song);
//        System.out.println("Выводим текст песни '" + song + "':\n" + lyrics);
//    }
//}
//
//class FileSongLyrics extends SongLyrics {
//    @Override
//    protected String getLyrics(String song) {
//        StringBuilder lyrics = new StringBuilder();
//        boolean foundSong = false;
//
//        try (BufferedReader br = new BufferedReader(new FileReader("src/task5_5_5/SongLyrics.txt"))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                if (line.trim().equals(song)) {
//                    foundSong = true;
//                    continue;
//                }
//                if (foundSong) {
//                    if (line.trim().isEmpty()) {
//                        break;
//                    }
//                    lyrics.append(line);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (foundSong) {
//            return lyrics.toString();
//        } else {
//            return "Песня не найдена";
//        }
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
