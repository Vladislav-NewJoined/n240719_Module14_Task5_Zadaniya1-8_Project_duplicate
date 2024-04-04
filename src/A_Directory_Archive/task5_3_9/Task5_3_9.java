package task5_3_9;

import java.io.IOException;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;

public class Task5_3_9 {
    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
                    9. Реализуйте метод equals для класса NasaPictureInfo. Как сделать его максимально просто?\s

                Решение:\s""");

        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите дату с разделителем '-' и нажмите Enter, пример: 2023-02-01: ");
        String currentDate = buffered.readLine();  // Input date
        String PageWithCodeOfCurrentDate = "https://api.nasa.gov/planetary/apod?api_key=YrhwoWaECiY1j28zoUenXe0GiIGSKTKBBL77Y9V8&date=" + currentDate;
        // Или можно воспользоваться ключом DEMO_KEY (количество изображений для скачивания в сутки ограничено), тогда строка будет такая:
        // String PageWithCodeOfCurrentDate = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=" + currentDate;

        String currentCodeItself = downloadWebPage(PageWithCodeOfCurrentDate);
        System.out.println("Пишем взятое из исходной страницы пояснение (или explanation) к фото NASA:\n");
        System.out.println(currentCodeItself);
        System.out.println();
        System.out.println("""
                Простейший способ реализации метода equals() для класса Task5_3_9 заключается в сравнении
                ссылок на объекты. Это означает, что два объекта считаются равными только в том случае,
                если они ссылаются на один и тот же объект в памяти. В данном случае, метод equals() просто
                будет возвращать true, если переданный объект и текущий объект являются одним и тем же
                объектом (ссылаются на один и тот же адрес памяти), и false в противном случае.
                               
                При реализации метода equals() как в этом примере, он будет сверять объекты по ссылкам
                и возвращать true, только если оба объекта являются одним и тем же объектом в памяти.\s""");
    }

    private static String downloadWebPage(String url) throws IOException {
        StringBuilder result = new StringBuilder();
        String line;
        URLConnection urlConnection = new URL(url).openConnection();
        try (InputStream is = urlConnection.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}




//// ПРИМЕР 2
//import java.io.IOException;
//import java.io.*;
//import java.net.URL;
//import java.net.URLConnection;
//import java.text.ParseException;
//
//public class Task5_3_9 {
//    public static void main(String[] args) throws IOException, ParseException {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №3:\s
//                    9. Реализуйте метод equals для класса NasaPictureInfo. Как сделать его максимально просто?\s
//
//                Решение:\s""");
//
//        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Введите дату с разделителем '-' и нажмите Enter, пример: 2023-02-01: ");
//        String currentDate = buffered.readLine();  // Input date
//        String PageWithCodeOfCurrentDate = "https://api.nasa.gov/planetary/apod?api_key=YrhwoWaECiY1j28zoUenXe0GiIGSKTKBBL77Y9V8&date=" + currentDate;
//        // Или можно воспользоваться ключом DEMO_KEY (количество изображений для скачивания в сутки ограничено), тогда строка будет такая:
//        // String PageWithCodeOfCurrentDate = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=" + currentDate;
//
//        String currentCodeItself = downloadWebPage(PageWithCodeOfCurrentDate);
//        System.out.println(currentCodeItself);
//    }
//
//    private static String downloadWebPage(String url) throws IOException {
//        StringBuilder result = new StringBuilder();
//        String line;
//        URLConnection urlConnection = new URL(url).openConnection();
//        try (InputStream is = urlConnection.getInputStream();
//             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
//            while ((line = br.readLine()) != null) {
//                result.append(line);
//            }
//        }
//        return result.toString();
//    }
//}
//// КОНЕЦ ПРИМЕРА 2