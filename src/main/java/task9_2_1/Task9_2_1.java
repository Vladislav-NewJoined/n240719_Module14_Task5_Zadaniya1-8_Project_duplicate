package task9_2_1;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Task9_2_1 {
    public static void main(String[] args) throws TelegramApiException {
        System.out.println("""
                Задание:\s
                Модуль 9. UI и использование готовых SDK. Задание №2:\s
                    1. В данном модуле вам предстоит разработать телеграм бота, а именно  в данном задании нужно
                       создать Maven проект и добавить зависимость  api telegram bots и telegram bots meta

                Решение:\s""");

//        bot для создания bot'ов в телеграм: https://t.me/BotFather
//        Я такой bot создал: javatestttbottt
//        с таким именем: javatestttbottt_kjllkjlkjl_bot
//        это ссылка на созданный бот: https://core.telegram.org/bots/api
//        это ключ для бота: Use this token to access the HTTP API:
//        7000104813:AAFnPxRsc9poy4-mta3d8uy5hLCnlk1mh1I

        System.out.println("Файл с ответом по заданию называется 'ОТВЕТ ПО УРОКУ', сохранен в папке " +
                "src/main/java/task9_2_1");

        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);

        BotSession botSession = api.registerBot(new Bot());
    }
}
