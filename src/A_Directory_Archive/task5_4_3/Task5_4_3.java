package task5_4_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Task5_4_3 implements NewsInterface {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №4:\s
                    3. Список категорий: all
                       national //Indian News only
                       business
                       sports
                       ...
                       и т.д.
                       Реализуйте для некоторых категорий этот интерфейс, который вызывает default-метод,
                       и передает туда соответствующую категорию

                Решение:\s""");

        Task5_4_3 newsApp = new Task5_4_3();

        List<String> categories = Arrays.asList("national", "business", "sports");

        for (String category : categories) {
            newsApp.getNews(category);
        }
    }
}

interface NewsInterface {
    default void getNews(String category) {
        try {
            String url = "https://inshortsapi.vercel.app/news?category=" + category;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("\nНовости по категории '" + category + "':");
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
