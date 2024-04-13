package a_shablony_Statia_Iz_Vikipedii;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class A_shablony_Statia_Iz_Vikipedii {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №2:\s
                    2. Реализуйте класс, отображающий страничку в википедии. Пусть метод toString у него
                       возвращает текст этой странички. Покажите его использование неявно, так:
                       WikiPage wikiPage = new WikiPage(“Java”);
                       System.out.println(wikiPage);\s

                Решение:\s""");

        String sourceCode = downloadWebPage("https://ru.wikipedia.org/wiki/%D0%A1%D0%BB%D1%83%D0%B6%D0%B5%D0%B1%D0%BD%D0%B0%D1%8F:%D0%A1%D0%BB%D1%83%D1%87%D0%B0%D0%B9%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0");
        writeToFile(sourceCode);
        System.out.println("Статья сохранена в соответствующей папке Package. Открывать так: " +
                "навести курсор на файл, правой кнопкой мыши => Open in => Browser => " +
                "Выбрать браузер.");

    }

    private static String downloadWebPage(String url) throws IOException {
        StringBuilder result = new StringBuilder();
        String line;
        URLConnection urlConnection = new URL(url).openConnection();
        try (InputStream is = urlConnection.getInputStream(); BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }

    public static void writeToFile(String str) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\a_shablony_Statia_Iz_Vikipedii\\RandomArticle.html"));
        writer.write(str);
        writer.close();
    }
}




//// ПРИМЕР 2
//import java.io.*;
//import java.net.URL;
//import java.net.URLConnection;
//
//public class A_shablony_Statia_Iz_Vikipedii {
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №2:\s
//                    2. Реализуйте класс, отображающий страничку в википедии. Пусть метод toString у него
//                       возвращает текст этой странички. Покажите его использование неявно, так:
//                       WikiPage wikiPage = new WikiPage(“Java”);
//                       System.out.println(wikiPage);\s
//
//                Решение:\s""");
//
//        WikiPage wikiPage = new WikiPage("Special:Random");
//        System.out.println(wikiPage.toString());
//    }
//}
//
//class WikiPage {
//    private String pageTitle;
//    private String pageContent;
//
//    public WikiPage(String pageTitle) {
//        this.pageTitle = pageTitle;
//        try {
//            String sourceCode = downloadWebPage("https://en.wikipedia.org/wiki/" + pageTitle);
//            this.pageContent = sourceCode;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public String toString() {
//        return pageContent;
//    }
//
//    private String downloadWebPage(String url) throws IOException {
//        StringBuilder result = new StringBuilder();
//        String line;
//        URLConnection urlConnection = new URL(url).openConnection();
//        try (InputStream is = urlConnection.getInputStream(); BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
//            while ((line = br.readLine()) != null) {
//                result.append(line);
//            }
//        }
//        return result.toString();
//    }
//}
//// КОНЕЦ ПРИМЕРА 2