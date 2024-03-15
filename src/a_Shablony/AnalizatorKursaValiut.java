package a_Shablony;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AnalizatorKursaValiut {
    public static void main(String[] args) throws IOException, ParseException {
        String originalString = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235");

        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("""
            Задание:\s
            Модуль 1. Тема 1. Кейс 1. «Анализатор курса валют».
            Задание 1. Произвести анализ курса валют на эту дату, предыдущую и следующую:
                - Вывести, все три курса
                - Насколько курс вырос/упал
                - Наибольший и наименьшие значения из этих трех
                - Сохранить в отдельную директорию лучший снимок NASA за эту дату :)

            Решение:\s""");

        System.out.println("Введите в следующей строке исходную дату с разделителем '/' и нажмите Enter, пример: 14/02/2020");
        String originalDate = buffered.readLine();  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(originalDate));
        c.add(Calendar.DATE, -1);  // number of days to add
        String oneDayBeforeDate;
        oneDayBeforeDate = sdf.format(c.getTime());  // entering oneDayBeforeDate
        c.add(Calendar.DATE, 2);  // number of days to add
        String oneDayAfterDate;
        oneDayAfterDate = sdf.format(c.getTime());  // entering oneDayAfterDate

        // Задаём адрес исходной веб-страницы Центробанка в текстовом формате.
        String originalStrText = "https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235";
//        System.out.println(originalStrText + System.lineSeparator());

        // Меняем в адресе исходной страницы даты на введённые (в трех строках).
        String urlWithDate1 = originalStrText.replaceAll("12/11/2021", originalDate);
        String urlWithDate2 = originalStrText.replaceAll("12/11/2021", oneDayBeforeDate);
        String urlWithDate3 = originalStrText.replaceAll("12/11/2021", oneDayAfterDate);

        String page1 = downloadWebPage(urlWithDate1);
        String page2 = downloadWebPage(urlWithDate2);
        String page3 = downloadWebPage(urlWithDate3);

        // Задаём курсы с типом переменной double.
        double courseDouble1 = 0;
        double courseDouble2 = 0;
        double courseDouble3 = 0;

        if (page1.contains("<Value>")) {
            int startIndex1 = page1.lastIndexOf("<Value>");
            int endIndex1 = page1.lastIndexOf("</Value>");
            String courseStr1 = page1.substring(startIndex1 + 7, endIndex1);
            double course1 = Double.parseDouble(courseStr1.replace(",", "."));
            courseDouble1 = course1;
        }
        if (page2.contains("<Value>")) {
            int startIndex2 = page2.lastIndexOf("<Value>");
            int endIndex2 = page2.lastIndexOf("</Value>");
            String courseStr2 = page2.substring(startIndex2 + 7, endIndex2);
            double course2 = Double.parseDouble(courseStr2.replace(",", "."));
            courseDouble2 = course2;
        }
        if (page3.contains("<Value>")) {
            int startIndex3 = page3.lastIndexOf("<Value>");
            int endIndex3 = page3.lastIndexOf("</Value>");
            String courseStr3 = page3.substring(startIndex3 + 7, endIndex3);
            double course3 = Double.parseDouble(courseStr3.replace(",", "."));
            courseDouble3 = course3;
        }

        System.out.println();

        String noCurse = "На указанную дату курс отсутствует";
        if (courseDouble1 == 0) {
            System.out.println("Курс на " + originalDate + ": " + noCurse);
        } else {
            System.out.println("Курс на " + originalDate + ": " + courseDouble1);
        }

        if (courseDouble2 == 0) {
            System.out.println("Курс на " + oneDayBeforeDate + ": " + noCurse);
        } else {
            System.out.println("Курс на " + oneDayBeforeDate + ": " + courseDouble2);
        }

        if (courseDouble3 == 0) {
            System.out.println("Курс на " + oneDayAfterDate + ": " + noCurse);
        } else {
            System.out.println("Курс на " + oneDayAfterDate + ": " + courseDouble3);
        }

        System.out.println();

        if (courseDouble1 == 0 || courseDouble2 == 0 || courseDouble3 == 0) {
            System.out.println("Имеются даты с отсутствующим курсом. Повторите программу и введите другую дату.");
        } else {
            if (courseDouble3 > courseDouble2) {
                System.out.print("Курс вырос на ");
                System.out.println(courseDouble3 - courseDouble2 + "\n");
            } else {
                if (courseDouble3 < courseDouble2) {
                    System.out.print("Курс снизился на ");
                    System.out.println(courseDouble2 - courseDouble3 + "\n");
                }
            }
            boolean courseDouble1Max = courseDouble1 > courseDouble2 && courseDouble1 > courseDouble3;
            boolean courseDouble2Max = courseDouble2 > courseDouble1 && courseDouble2 > courseDouble3;
            if (courseDouble1Max) {
                System.out.println("Максимальный курс: " + courseDouble1 + "; " + "Приходится на дату: " + originalDate);
            } else {
                if (courseDouble2Max) {
                    System.out.println("Максимальный курс: " + courseDouble2 + "; " + "Приходится на дату: " + oneDayBeforeDate);
                } else {
                    System.out.println("Максимальный курс: " + courseDouble3 + "; " + "Приходится на дату: " + oneDayAfterDate);
                }
            }
            boolean courseDouble1Min = courseDouble1 < courseDouble2 && courseDouble1 < courseDouble3;
            boolean courseDouble2Min = courseDouble2 < courseDouble1 && courseDouble2 < courseDouble3;
            if (courseDouble1Min) {
                System.out.println("Минимальный курс: " + courseDouble1 + "; " + "Приходится на дату: " + originalDate);
            } else {
                if (courseDouble2Min) {
                    System.out.println("Минимальный курс: " + courseDouble2 + "; " + "Приходится на дату: " + oneDayBeforeDate);
                } else {
                    System.out.println("Минимальный курс: " + courseDouble3 + "; " + "Приходится на дату: " + oneDayAfterDate);
                }
            }
        }

        String pageNasa = downloadWebPage("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY");
        int urlBegin = pageNasa.lastIndexOf("url");
        int urlEnd = pageNasa.lastIndexOf("}");
        String urlPhoto = pageNasa.substring(urlBegin + 6, urlEnd - 1);
        try (InputStream in = new URL(urlPhoto).openStream()) {
            Files.copy(in, Paths.get("src/a_Shablony/new.jpg"));
        }

        System.out.println("\n" + "Картинка сохранена в файле src/a_Shablony/new.jpg");

        int explanationBegin = pageNasa.lastIndexOf("explanation");
        int explanationEnd = pageNasa.lastIndexOf("hdurl");
        String explanation = pageNasa.substring(explanationBegin + 14, explanationEnd - 3);
        System.out.println(explanation);

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
}


//// ПРИМЕР 3
//import java.io.*;
//        import java.math.RoundingMode;
//import java.net.URL;
//import java.net.URLConnection;
//import java.text.DecimalFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.YearMonth;
//import java.time.format.DateTimeFormatter;
//import java.time.format.FormatStyle;
//import java.util.*;
//
//public class Case_3_1_1 {
//
//    public static void main(String[] args) throws IOException, ParseException {
//        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("""
//            Задание:\s
//            Модуль 1. Тема 1. Кейс 1. «Анализатор курса валют».
//            Задание 3. Очень сложное:
//                - Найти самые сильные скачки в этот промежуток, дни, когда курс сильно вырос
//                  или упал. Автоматически скачать текст статьи из википедии, отвечающей за факты на эту дату
//                  (пример https://ru.wikipedia.org/wiki/%D0%A4%D0%B5%D0%B2%D1%80%D0%B0%D0%BB%D1%8C_2021_%D0%B3%D0%BE%D0%B4%D0%B0\s
//                  и показать пользователю
//
//            Решение:\s""");
//
//        System.out.print("Введите в этой строке исходные месяц и год с разделителем '/' и нажмите Enter, " +
//                "пример: 03/2023: ");
//        String origMonth = buffered.readLine();  // Start month
//        System.out.println();
//
//        // Делаем парсинг введённой строки методом Split.
//        String[] items = origMonth.split("/");
//        String mon = items[0];
//        String yea = items[1];
//
//        int monI = Integer.parseInt(mon);
//        int yeaI = Integer.parseInt(yea);
//
//        // Преобразовываем ввод через переменную YearMonth.
//        YearMonth ym = YearMonth.of(yeaI, monI);
//
//        // Скачиваем исходный код веб-страницы Центробанка.
//        // https://cbr.ru/scripts/XML_dynamic.asp?date_req1=28/02/2023&date_req2=31/03/2023&VAL_NM_RQ=R01235
//        String originalPage = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235");
//        // Задаём адрес исходной веб-страницы Центробанка в текстовом формате.
//        String originalPageText = "https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235";
//
//        int lastDay = ym.lengthOfMonth();
//
//        List<Double> ratesList = new ArrayList<>();
//        List<Double> ratesListPlusPreviousDay = new ArrayList<>();
//
//        // Задаём Map.
//        Map<String, Double> mapRatesInDates = new HashMap<>();
//        Map<String, Double> mapRatesInDatesPlusPreviousDay = new HashMap<>();
//
//        //    loop 1 through the days
//        for (int day = 1; day <= lastDay; day++) {
//            // create the day
//            LocalDate dt = ym.atDay(day);
//
//            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            String dtStr = dt.format(f);
//            // set to midnight at JVM default timezone
//            int startIndex = originalPage.lastIndexOf("<Value>") + 7;
//            int endIndex = originalPage.lastIndexOf("</Value>");
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            Calendar c = Calendar.getInstance();
//            c.setTime(sdf.parse(dtStr));
//            String currentDate;
//            currentDate = sdf.format(c.getTime());  // entering current Date
//
//            // Приводим currentDate к формату LocalDate
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            LocalDate localDate = LocalDate.parse(currentDate, formatter);
//
//            // Меняем в адресе исходной страницы дату на текущую.
//            String urlWithCurrentDate = originalPageText.replaceAll("12/11/2021", currentDate);
//
//            String currentPage = downloadWebPage(urlWithCurrentDate);
//
//            if (currentPage.contains("<Value>")) {
//                String currentRatePage = currentPage.substring(startIndex, endIndex);
//                // Задаём курс в виде переменной Double.
//                double doubleCurrentRate = Double.parseDouble(currentRatePage.replace(",", "."));
//                System.out.println(/*dt + "*/"Курс на " + currentDate + "    " + doubleCurrentRate);
//                mapRatesInDates.put(String.valueOf(localDate), doubleCurrentRate);
//                ratesList.add(doubleCurrentRate);
//            } else {
//                System.out.println(/*dt + "*/"Курс на " + currentDate);
//            }
//        }
//
//        System.out.println();
//
//        //    loop 2 through the days
//        for (int day = 1; day <= 1; day++) {
//            // create the day
//            LocalDate dt = ym.atDay(day).minusDays(1);
//
//            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            String dtStr = dt.format(f);
//            // set to midnight at JVM default timezone
//            int startIndex = originalPage.lastIndexOf("<Value>") + 7;
//            int endIndex = originalPage.lastIndexOf("</Value>");
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            Calendar c = Calendar.getInstance();
//            c.setTime(sdf.parse(dtStr));
//            String currentDate;
//            currentDate = sdf.format(c.getTime());  // entering current Date
//
//            // Приводим currentDate к формату LocalDate
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            LocalDate localDate = LocalDate.parse(currentDate, formatter);
//
//            // Меняем в адресе исходной страницы дату на текущую.
//            String urlWithCurrentDate = originalPageText.replaceAll("12/11/2021", currentDate);
//
//            String currentPage = downloadWebPage(urlWithCurrentDate);
//
//            if (currentPage.contains("<Value>")) {
//                String currentRatePage = currentPage.substring(startIndex, endIndex);
//                // Задаём курс в виде переменной Double.
//                double doubleCurrentRate = Double.parseDouble(currentRatePage.replace(",", "."));
//                mapRatesInDatesPlusPreviousDay.put(String.valueOf(localDate), doubleCurrentRate);
//                ratesListPlusPreviousDay.add(doubleCurrentRate);
//            } else {
//            }
//        }
//
//        for (int day = 1; day <= lastDay; day++) {
//            // create the day
//            LocalDate dt = ym.atDay(day);
//
//            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            String dtStr = dt.format(f);
//            // set to midnight at JVM default timezone
//            int startIndex = originalPage.lastIndexOf("<Value>") + 7;
//            int endIndex = originalPage.lastIndexOf("</Value>");
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            Calendar c = Calendar.getInstance();
//            c.setTime(sdf.parse(dtStr));
//            String currentDate;
//            currentDate = sdf.format(c.getTime());  // entering current Date
//
//            // Приводим currentDate к формату LocalDate
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            LocalDate localDate = LocalDate.parse(currentDate, formatter);
//
//            // Меняем в адресе исходной страницы дату на текущую.
//            String urlWithCurrentDate = originalPageText.replaceAll("12/11/2021", currentDate);
//
//            String currentPage = downloadWebPage(urlWithCurrentDate);
//
//            if (currentPage.contains("<Value>")) {
//                String currentRatePage = currentPage.substring(startIndex, endIndex);
//                // Задаём курс в виде переменной Double.
//                double doubleCurrentRate = Double.parseDouble(currentRatePage.replace(",", "."));
//                mapRatesInDatesPlusPreviousDay.put(String.valueOf(localDate), doubleCurrentRate);
//                ratesListPlusPreviousDay.add(doubleCurrentRate);
//            } else {
//            }
//        }
//
//        // Сортируем и распечатываем отсортированный список Map. Про сортировку здесь: https://rukovodstvo.net/posts/id_598/
//        Map<String, Double> sortedMap = new TreeMap<>(mapRatesInDates); // - ИЗ ЭТОГО Мапа МАКСИМАЛЬНЫЕ ПЕРЕПАДЫ КУРСА БРАТЬ.
//        Map<String, Double> sortedMap2 = new TreeMap<>(mapRatesInDatesPlusPreviousDay); // - ИЗ ЭТОГО Мапа МАКСИМАЛЬНЫЕ ПЕРЕПАДЫ КУРСА БРАТЬ.
//
//        List<String> keys = new ArrayList<String>(sortedMap2.keySet());
//        Double maxDiffer = sortedMap2.get(keys.get(1)) - sortedMap2.get(keys.get(0));
//        String dateOfMaxDiffer = keys.get(0);
//        String dateOfMaxDifferFormatted = null;
//
//        Double minDiffer = sortedMap2.get(keys.get(1)) - sortedMap2.get(keys.get(0));
//        String dateOfMinDiffer = keys.get(0);
//        String dateOfMinDifferFormatted = null;
//
//        for (int i = 0; i < keys.size(); i++) {
//            String key = keys.get(i);
//            Double value = sortedMap2.get(key);
//        }
//
//        for (int i = 1; i < keys.size(); i++) {
//            String key = keys.get(i);
//            Double value = sortedMap2.get(key);
//            String keyMinusOne = keys.get(i - 1);
//            Double valueMinusOne = sortedMap2.get(keyMinusOne);
//
//            Double Differ = (value - valueMinusOne);
//            DecimalFormat df2 = new DecimalFormat("0.000");
//            df2.setRoundingMode(RoundingMode.DOWN);
//            if ((sortedMap2.get(keys.get(i)) - sortedMap2.get(keys.get(i - 1)) > maxDiffer)) {
//                maxDiffer = (value - valueMinusOne);
//                dateOfMaxDiffer = key;
//                String[] frs = dateOfMaxDiffer.split("-");
//                String fr1 = frs[0];
//                String fr2 = frs[1];
//                String fr3 = frs[2];
//                dateOfMaxDifferFormatted = (fr3 + "/" + fr2 + "/" + fr1);
//
//
//            }
//            if ((sortedMap2.get(keys.get(i)) - sortedMap2.get(keys.get(i - 1)) < minDiffer)) {
//                minDiffer = (value - valueMinusOne);
//                dateOfMinDiffer = key;
//                String[] frs = dateOfMinDiffer.split("-");
//                String fr1 = frs[0];
//                String fr2 = frs[1];
//                String fr3 = frs[2];
//                dateOfMinDifferFormatted = (fr3 + "/" + fr2 + "/" + fr1);
//
//            }
//        }
//
//        // The printed result
//        DecimalFormat df = new DecimalFormat("0.000");
//        df.setRoundingMode(RoundingMode.DOWN);
//        System.out.println("За указанный месяц максимальный прирост курса между двумя соседними датами: " + df.format(maxDiffer) + ", это пришлось на дату: " + dateOfMaxDiffer);
//        System.out.println("За указанный месяц максимальное снижение курса между двумя соседними датами: " + df.format(minDiffer) + ", это пришлось на дату: " + dateOfMinDiffer);
//
//        //Сохраняем страницу из википедии max
//        String dtStrMax = dateOfMaxDifferFormatted;
//        String[] items2 = dtStrMax.split("/");
//        String dat2 = items2[0];
//        String mon2 = items2[1];
//        String yea2 = items2[2];
//        String dtStr2 = (yea2 + "-" + mon2 + "-" + dat2);
//
//        LocalDate localDate2 = LocalDate.parse(dtStr2);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
//        String formattedDate1 = formatter.format(localDate2);
//
//        String[] items3 = formattedDate1.split(" ");
//        String dat3 = items3[0];
//        String mon3 = items3[1];
//        String yea3 = items3[2];
//        String gFirst3 = items3[3];
//        String g3 = gFirst3.replace("г.", "года");
//
//        String dtStrForChangeMax = (dat3 + "_" + mon3 + "_" + yea3 + "_" + g3);
//
//        String pageWikiOriginText = "https://ru.wikinews.org/wiki/Лента_новостей_31_марта_2023_года";
//        String pageWikiOriginChangedTextMax = pageWikiOriginText.replaceAll("31_марта_2023_года", dtStrForChangeMax);
//        String pageWikiOriginChanged1 = downloadWebPage(pageWikiOriginChangedTextMax);
//
//        //Сохраняем страницу из википедии min
//        String dtStrMin = dateOfMinDifferFormatted;
//        String[] items4 = dtStrMin.split("/");
//        String dat4 = items4[0];
//        String mon4 = items4[1];
//        String yea4 = items4[2];
//        String dtStr4 = (yea4 + "-" + mon4 + "-" + dat4);
//
//        LocalDate localDate4 = LocalDate.parse(dtStr4);
//
//        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
//        String formattedDate2 = formatter2.format(localDate4);
//
//        String[] items5 = formattedDate2.split(" ");
//        String dat5 = items5[0];
//        String mon5 = items5[1];
//        String yea5 = items5[2];
//        String gSecond5 = items5[3];
//        String g5 = gSecond5.replace("г.", "года");
//
//        String dtStrForChangeMin = (dat5 + "_" + mon5 + "_" + yea5 + "_" + g5);
//
//        String pageWikiOriginChangedTextMin = pageWikiOriginText.replaceAll("31_марта_2023_года", dtStrForChangeMin);
//        String pageWikiOriginChanged2 = downloadWebPage(pageWikiOriginChangedTextMin);
//
//        // создаём новый буферизированный объект
//        BufferedWriter writer1 = new BufferedWriter(new FileWriter("1pageGrowthRate.html"));
//        // добавляем название переменной со страницей, которую сохраняем
//        writer1.write(pageWikiOriginChanged1);
//        // закрываем writer
//        writer1.close();
//
//        BufferedWriter writer2 = new BufferedWriter(new FileWriter("2pageDeclineRate.html"));
//        writer2.write(pageWikiOriginChanged2);
//        writer2.close();
//
//        System.out.println("\nСтраницы из Википедии сохранены в файлах: 1pageGrowthRate.html, 2pageDeclineRate.html");
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
//// КОНЕЦ ПРИМЕРА 3


//// ПРИМЕР 2
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.YearMonth;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//public class Case_2_1_1 {
//    public static void main(String[] args) throws IOException, ParseException {
//        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("""
//            Задание:\s
//            Модуль 1. Тема 1. Кейс 1. «Анализатор курса валют».
//            Задание 2. Продвинутое, усложненное задание:
//                - Пользователь вводит месяц и год. Вывести курс рубля за этот месяц, найти наибольший и наименьшие значения
//
//            Решение:\s""");
//        System.out.println("Введите в следующей строке исходные месяц и год с разделителем '/' и нажмите Enter, " +
//                "пример: 02/2020:");
//        String origMonth = buffered.readLine();  // Start month
//
//        // Делаем парсинг введённой строки методом Split.
//        String[] items = origMonth.split("/");
//        String mon = items[0];
//        String yea = items[1];
//
//        int monI = Integer.parseInt(mon);
//        int yeaI = Integer.parseInt(yea);
//
//        // Преобразовываем ввод через переменную YearMonth.
//        YearMonth ym = YearMonth.of(yeaI, monI);
//
//        // Скачиваем исходный код веб-страницы Центробанка.
//        String originalPage = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235");
//        // Задаём адрес исходной веб-страницы Центробанка в текстовом формате.
//        String originalPageText = "https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235";
//
////    get the last day of month
//        int lastDay = ym.lengthOfMonth();
////    Создаем массив ArrayList, куда записываем в качестве элементов курс на текущую дату.
//        List<Double> list = new ArrayList<>();
//
////    loop through the days
//        for (int day = 1; day <= lastDay; day++) {
//            // create the day
//            LocalDate dt = ym.atDay(day);
//            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            String dtStr = dt.format(f);
//            // set to midnight at JVM default timezone
//            int startIndex = originalPage.lastIndexOf("<Value>") + 7;
//            int endIndex = originalPage.lastIndexOf("</Value>");
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            Calendar c = Calendar.getInstance();
//            c.setTime(sdf.parse(String.valueOf(dtStr)));
//            String nextDate;
//            nextDate = sdf.format(c.getTime());  // entering nextDate
//
//            // Меняем в адресе исходной страницы дату на следующую.
//            String urlWithNextDate = originalPageText.replaceAll("12/11/2021", nextDate);
//
//            String nextPage = downloadWebPage(urlWithNextDate);
//
//            if (nextPage.contains("<Value>")) {
//                String courseNextPage = nextPage.substring(startIndex, endIndex);
//                // Задаём курс в виде переменной Double.
//                double courseNextDouble = Double.parseDouble(courseNextPage.replace(",", "."));
//                // System.out.println("Курс в типе переменной Double:");
//                // System.out.println(courseNextDouble);
//                // Выводим на экран дату и соответствующий курс.
//                System.out.println("Курс на " + nextDate + "    " + courseNextDouble);
//                list.add(courseNextDouble);
//            } else {
//                String courseNextPage = "";
//                System.out.println("Курс на " + nextDate);
//            }
//        }
//
////        Нахождение наибольшего и наименьшего значений.
////        Инициализируем исходную переменную: от которой начинаем считать.
//        Double min = list.get(0);
//        Double max = list.get(0);
////        Инициализируем переменную: длина (или размер) массива.
//        int n = list.size();
//
////        Задаем цикл перебора массива для поиска наибольшего и наименьшего значений.
//        for (int i = 1; i < n; i++) {
//            if (list.get(i) > max) {
//                max = list.get(i);
//            }
//        }
//
//        for (int i = 1; i < n; i++) {
//            if (list.get(i) < min) {
//                min = list.get(i);
//            }
//        }
//        System.out.println("\nНаибольшее значение курса: " + max);
//        System.out.println("Наименьшее значение курса: " + min);
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