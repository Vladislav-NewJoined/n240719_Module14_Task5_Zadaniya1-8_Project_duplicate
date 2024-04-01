package task5_4_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Task5_4_2 implements NewsInterface {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №4:\s
                    2. Сделайте интерфейс, default-метод которого будет принимать категорию (String cat) -
                       который будет запрашивать новости по апи, пример
                       https://inshortsapi.vercel.app/news?category=science (смотреть в браузере)\s

                Решение:\s""");

        Task5_4_2 newsApp = new Task5_4_2();
        newsApp.getNews("science");
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

            System.out.println("Запрашиваем новости по категории '" + category + "':");
            System.out.println("Полученные по запросу новости:\n" + response.toString());
            System.out.println("\n" + "Интернет-ресурс, на который делается API запрос: " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




//// ПРИМЕР 2
//import java.io.IOException;
//
//public class Task5_4_2 {
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №4:\s
//                    2. Сделайте интерфейс, default-метод которого будет принимать категорию (String cat) -
//                       который будет запрашивать новости по апи, пример
//                       https://inshortsapi.vercel.app/news?category=science (смотреть в браузере)\s
//
//                Решение:\s""");
//
//        System.out.println("""
//                Default-методы в интерфейсах языка программирования Java были введены начиная с версии
//                Java 8. Они добавляют возможность предоставлять реализацию методов в интерфейсе, что
//                позволяет добавлять новые методы в существующие интерфейсы без необходимости изменения
//                всех классов, которые эти интерфейсы реализуют. Также default-методы могут использоваться
//                для предоставления общей реализации методов для всех классов, реализующих данный интерфейс.
//                Это позволяет уменьшить дублирование кода и улучшить переиспользование кода.\s""");
//    }
//}
//// КОНЕЦ ПРИМЕРА 2