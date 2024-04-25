package task9_3_1;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Task9_3_1 {
    public static void main(String[] args) throws TelegramApiException {
        System.out.println("""
            Задание:\s
            Модуль 2. Тема 3. Урок 3. Пишем бота для telegram. Работа с изображениями
                1. Продолжая работу над ботом из предыдущих занятий, добавьте боту возможность
                   отправлять сообщения\s
                2. Реализуйте возможность получения изображения от пользователя, а также его отправку
                   пользователю обратно\s

            Решение:\s""");

        System.out.println("Файл с ответом по заданию называется 'ОТВЕТ ПО УРОКУ', сохранен в " +
                "папке: src/main/java/task9_3_1");
        System.out.println("Файл с изображением, отправленным телеграм ботом пользователю обратно, " +
                "называется 'received_image.jpeg', сохранен в папке: src/main/java/task9_3_1");

        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        BotSession botSession = api.registerBot(new Bot());

    }
}
