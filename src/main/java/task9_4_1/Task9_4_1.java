package task9_4_1;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

// botName: javatestbot  или  t.me/my_gfjhfghfjhgfjhgfghfhgfjf_bot
// userName: @my_gfjhfghfjhgfjhgfghfhgfjf_bot  или  my_gfjhfghfjhgfjhgfghfhgfjf_bot
// token: 6750924950:AAGOE5XBnuJDlNIKHYq61S7bMupKXhKRZZo

// прервался на мин 08 28 проверил, все работает
// прервался на мин 11 36
// прервался на мин 24 20 _совпадает, но есть подчеркнутые красным
// прервался на мин 31 31 _совпадает, но есть подчеркнутые красным
public class Task9_4_1 {
    public static void main(String[] args) throws Exception {
        System.out.println("""
            Задание:\s
            Модуль 9. UI и использование готовых SDK. Задание №4:\s
                1. Разработайте команды для бота при помощи которых можно выбрать какой фильтр применить для
                   обработки полученного ботом изображения\s

            Решение:\s""");

        System.out.println("Отредактированное изображение сохранено в файле 'cloned_image.jpg' в папке: " +
                "`src/main/java/task9_4_1`.");

//        final BufferedImage image = ImageUtils.getImage("src/main/java/task9_4_1/logoJAVA.jpg");
//        final RgbMaster rgbMaster = new RgbMaster(image);
//        rgbMaster.changeImage(FilterOperation::greyScale);
//        ImageUtils.saveImage(rgbMaster.getImage(),"src/main/java/task9_4_1/cloned_logoJAVA.jpg");

        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        BotSession botSession = api.registerBot(new Bot());

    }
}
