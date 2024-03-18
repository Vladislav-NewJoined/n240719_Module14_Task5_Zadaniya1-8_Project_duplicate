package a_shablony;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;

// источник: https://www.youtube.com/watch?v=SginvSUGGoI  от: Илья Ландар , как прикрепить картинку в html
public class NASA_Photo_Of_InputDate {
    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("""
                Задание:\s
                Пользователь вводит месяц и год.
                Скачать все снимки за месяц в папку.
                Сгенерировать html страницу в этой папке, которая отобразит все скачанные снимки на одной странице. Пример:
                <img src=“1.png”/>
                <img src=“2.png”/>

                Решение:\s""");

        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите дату с разделителем '-' и нажмите Enter, пример: 2023-02-01: ");
            String currentDate = buffered.readLine();  // Input date
            System.out.println(currentDate);
            System.out.println("Сохранено фото за " + currentDate + ":");

//            Сгенерирован ключ для запросов на api.nasa.gov на дату 27.12.2023:
//            YrhwoWaECiY1j28zoUenXe0GiIGSKTKBBL77Y9V8
            String PageWithCodeOfCurrentDate = "https://api.nasa.gov/planetary/apod?api_key=YrhwoWaECiY1j28zoUenXe0GiIGSKTKBBL77Y9V8&date=" + currentDate;
            // Или можно воспользоваться ключом DEMO_KEY (количество изображений для скачивания в сутки ограничено), тогда строка будет такая:
            // String PageWithCodeOfCurrentDate = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=" + currentDate;

            String currentCodeItself = downloadWebPage(PageWithCodeOfCurrentDate);
            System.out.println(PageWithCodeOfCurrentDate);
            System.out.println(currentCodeItself);

            int urlBegin = currentCodeItself.lastIndexOf(",\"url");
            int urlEnd = currentCodeItself.lastIndexOf("}");
            String urlOfCurrentPhoto = currentCodeItself.substring(urlBegin + 8, urlEnd - 1);
            System.out.println(urlOfCurrentPhoto);
            System.out.println();
            try (InputStream in = new URL(urlOfCurrentPhoto).openStream()) {
                Files.copy(in, Paths.get("src\\a_shablony\\NASA_Photos_Of_Month\\image"/* + i*/ + ".png"), StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException exception) {
                System.out.println("Input/Output error");
            }

        try {
            final var writer = getPrintWriter();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Объединяем все фото в одну картинку в формате html
        File[] imgFiles = new File("src\\a_shablony\\NASA_Photos_Of_Month").listFiles();
        assert imgFiles != null;
        int imagesCount = imgFiles.length;
        BufferedImage[] buffImages = new BufferedImage[imgFiles.length];

        int cols = 1;
        int chunks = imagesCount * cols;

        int chunkWidth;

        for(int j = 0; j < buffImages.length; j++) {
            buffImages[j] = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
//            buffImages[j] = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = buffImages[j].createGraphics();
            g2d.drawRect(10, 10, 80, 80);
            g2d.dispose();
        }

        for (int i = 0; i < chunks; i++) {
            buffImages[i] = ImageIO.read(imgFiles[i]);
        }
        chunkWidth = buffImages[0].getWidth();
//        chunkHeight = buffImages[0].getHeight();

        // Инициализируем finalImg
        int heightTotal = 0;
        for (BufferedImage buffImage : buffImages) {
            heightTotal += buffImage.getHeight();
        }
        int heightCurr = 0;
        BufferedImage finalImg = new BufferedImage((int) ((chunkWidth * cols)*0.75), heightTotal, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = finalImg.createGraphics();
        for (BufferedImage buffImage : buffImages) {
            g2d.drawImage(buffImage, 0, heightCurr, null);
            heightCurr += buffImage.getHeight();
        }
        g2d.dispose();
        System.out.println("Изображения объединены в папке 'Joined_Images', файл 'Joined_Photos.png'");
        ImageIO.write(finalImg, "png", new File("src\\a_shablony\\Joined_Images\\Joined_Photos.png"));
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

    private static PrintWriter getPrintWriter() throws IOException {
        PrintWriter writer =
                new PrintWriter(new BufferedWriter(new FileWriter("src\\a_shablony\\Joined_Images\\"
                        + "Joined_PhotosHTML" + ".html")));
        writer.println("<html lang=\"\">");
        writer.println("<head>");
        writer.println("<title>All_Photos_In_One_File</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>ALL  IMAGES</h1>");
        writer.println("<img src=\"../Joined_Images/Joined_Photos.png\" height=\"\" width=\"\" alt=\"\">");
        writer.println("<br>");
        writer.println("</body>");
        writer.println("</html>");
        return writer;
    }
}
