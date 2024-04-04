package task4_6_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


class InvalidInputException extends Exception {
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}

class NoCurrencyExchangeRateException extends Exception {
    public NoCurrencyExchangeRateException(String errorMessage) {
        super(errorMessage);
    }
}

public class Task4_6_3 {
    public static void main(String[] args) throws IOException, ParseException, InvalidInputException {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №6:\s
                    3. Аналогичным образом, доработайте запрос курса валют на дату: при некорректном
                       вводе бросайте исключение. При отсутствии курса валют в ответе, бросайте другое
                       исключение.\s

                Решение:\s""");

        try {
            BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Реализуем код с обработкой исключений.");
            System.out.println("Введите в следующей строке исходную дату с разделителем '/' и нажмите Enter," +
                    " пример: 14/02/2020");
            String originalDate = buffered.readLine();  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(originalDate));
            c.add(Calendar.DATE, -1);  // number of days to add
            String oneDayBeforeDate = sdf.format(c.getTime());
            c.add(Calendar.DATE, 2);  // number of days to add
            String oneDayAfterDate = sdf.format(c.getTime());

            String originalStrText = "https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235";
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

            if (!page1.contains("<Value")) {
                throw new NoCurrencyExchangeRateException("На введённую дату нет информации о курсе");
            }

            int startIndex1 = page1.lastIndexOf("<Value>");
            int endIndex1 = page1.lastIndexOf("</Value>");
            String courseStr1 = page1.substring(startIndex1 + 7, endIndex1);
            double course1 = Double.parseDouble(courseStr1.replace(",", "."));
            courseDouble1 = course1;

            // Проверяем наличие курсов и бросаем исключение, если все три значения равны 0
            if (courseDouble1 == 0 && courseDouble2 == 0 && courseDouble3 == 0) {
                throw new NoCurrencyExchangeRateException("Курс валют отсутствует на всех трех датах. " +
                        "Повторите программу и введите другие даты.");
            } else {
                // Продолжаем сравнение и вывод информации
                String noCurse = "";
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
            }

            DecimalFormat df = new DecimalFormat("0.000");
            df.setRoundingMode(RoundingMode.DOWN);
            if (courseDouble1 == 0 || courseDouble2 == 0 || courseDouble3 == 0) {
                System.out.println("Имеются даты с отсутствующим курсом. Повторите программу и введите " +
                        "другую дату.");
            } else {
                if (courseDouble3 > courseDouble2) {
                    System.out.print("Курс вырос на ");
                    System.out.println(df.format(courseDouble3 - courseDouble2) + "\n");
                } else {
                    if (courseDouble3 < courseDouble2) {
                        System.out.print("Курс снизился на ");
                        System.out.println(df.format(courseDouble2 - courseDouble3) + "\n");
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
                    System.out.println("Минимальный курс: " + courseDouble1 + "; " + "Приходится на дату: "
                            + originalDate);
                } else {
                    if (courseDouble2Min) {
                        System.out.println("Минимальный курс: " + courseDouble2 + "; " + "Приходится на дату: "
                                + oneDayBeforeDate);
                    } else {
                        System.out.println("Минимальный курс: " + courseDouble3 + "; " + "Приходится на дату: "
                                + oneDayAfterDate);
                    }
                }
            }

        } catch (ParseException | IOException | NoCurrencyExchangeRateException e) {
            throw new InvalidInputException("На введённую дату курс отсутствует");
        }
    }

    // Метод для загрузки содержимого веб-страницы
    private static String downloadWebPage(String urlString) throws IOException {
        URL url = new URL(urlString);
        URLConnection urlConnection = url.openConnection();
        InputStream is = urlConnection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}