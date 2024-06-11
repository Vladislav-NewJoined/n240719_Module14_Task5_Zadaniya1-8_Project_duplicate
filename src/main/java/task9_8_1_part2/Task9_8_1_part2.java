package task9_8_1_part2;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import task9_8_1.Bot;

// Создан Telegram бот:
// botName: MyTestBot_002
// userName: @lgcyrrerrr_002_bot  или  lgcyrrerrr_002_bot
// token: 7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo

public class Task9_8_1_part2 {
    public static void main(String[] args) throws Exception {
        System.out.println("""
            Задание:\s
            Модуль 9. UI и использование готовых SDK. Задание №7:\s
                1. Продолжая работу над ботом из предыдущих уроков, реализуйте возможность вызова
                   соответствующего обработчика (класса)  в зависимости от того, прислал пользователь
                   изображение или текстовое сообщение\s

            Решение:\s""");

        System.out.println("Создан Telegram бот:\n" +
                "botName: MyTestBot_002\n" +
                "userName: @lgcyrrerrr_002_bot или lgcyrrerrr_002_bot\n" +
                "token: 7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo\n" +
                "\n" +
                "Предложено два решения:\n" +
                "-первое в папке package:\n" +
                "‘task9_8_1_part1’ - обрабатывает одно изображение\n" +
                "- второе в папке package:\n" +
                "‘task9_8_1’ - обрабатывает несколько изображений\n");
        System.out.println("Отредактированные изображения сохраняются в папке `src/main/java/task9_8_1/images`.");

        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        BotSession botSession = api.registerBot(new Bot());
    }
}




//// ПРИМЕР 0 _Это ТОЧНО скопировано из задания 7, т.е. n240610_Module9_Task7_Zadaniye1, package task9_8_1.
//// Всё, что выше - это задание 8
//import org.telegram.telegrambots.meta.TelegramBotsApi;
//import org.telegram.telegrambots.meta.generics.BotSession;
//import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
//import task9_8_1.Bot;
//
//// Создан Telegram бот:
//// botName: MyTestBot_002
//// userName: @lgcyrrerrr_002_bot  или  lgcyrrerrr_002_bot
//// token: 7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo
//
//public class Task9_8_1_part2 {
//    public static void main(String[] args) throws Exception {
//        System.out.println("""
//            Задание:\s
//            Модуль 9. UI и использование готовых SDK. Задание №7:\s
//                1. Продолжая работу над ботом из предыдущих уроков, реализуйте возможность вызова
//                   соответствующего обработчика (класса)  в зависимости от того, прислал пользователь
//                   изображение или текстовое сообщение\s
//
//            Решение:\s""");
//
//        System.out.println("Создан Telegram бот:\n" +
//                "botName: MyTestBot_002\n" +
//                "userName: @lgcyrrerrr_002_bot или lgcyrrerrr_002_bot\n" +
//                "token: 7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo\n" +
//                "\n" +
//                "Предложено два решения:\n" +
//                "-первое в папке package:\n" +
//                "‘task9_8_1_part1’ - обрабатывает одно изображение\n" +
//                "- второе в папке package:\n" +
//                "‘task9_8_1’ - обрабатывает несколько изображений\n");
//        System.out.println("Отредактированные изображения сохраняются в папке `src/main/java/task9_8_1/images`.");
//
//        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
//        BotSession botSession = api.registerBot(new Bot());
//    }
//}
//// конец ПРИМЕРА 0
