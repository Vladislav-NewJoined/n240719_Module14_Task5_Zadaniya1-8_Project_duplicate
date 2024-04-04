package task5_2_2;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Task5_2_2 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №2:\s
                    2. Реализуйте класс, отображающий страничку в википедии. Пусть метод toString у него
                       возвращает текст этой странички. Покажите его использование неявно, так:
                       WikiPage wikiPage = new WikiPage(“Java”);
                       System.out.println(wikiPage);\s

                Решение:\s""");

        WikiPage wikiPage = new WikiPage("Special:Random");
        System.out.println(wikiPage.toString());
    }
}

class WikiPage {
    private String pageTitle;
    private String pageContent;

    public WikiPage(String pageTitle) {
        this.pageTitle = pageTitle;
        try {
            String sourceCode = downloadWebPage("https://en.wikipedia.org/wiki/" + pageTitle);
            this.pageContent = sourceCode;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return pageContent;
    }

    private String downloadWebPage(String url) throws IOException {
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
}