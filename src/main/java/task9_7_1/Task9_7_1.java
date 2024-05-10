package task9_7_1;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Task9_7_1 {
    public static void main(String[] args) throws Exception {
        System.out.println("""
            Задание:\s
            Модуль 9. UI и использование готовых SDK. Задание №7:\s
                1. Продолжая работу над ботом из предыдущих уроков, реализуйте возможность вызова соответствующего
                   обработчика (класса)  в зависимости от того, прислал пользователь изображение или текстовое
                   сообщение\s

            Решение:\s""");

        System.out.println("В Telegram боте созданы .");

        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        BotSession botSession = api.registerBot(new Bot());

    }
}
