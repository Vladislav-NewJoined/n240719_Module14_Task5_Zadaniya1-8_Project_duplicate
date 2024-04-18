package task9_2_0_VideoUrok;

//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import utils.PhotoMessageUtils;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Bot extends TelegramLongPollingBot {
//    @Override
//    public String getBotUsername() {
//        return "my_gfjhfghfjhgfjhgfghfhgfjf_bot";
//    }
//
//    @Override
//    public String getBotToken() {
//        return "6750924950:AAGOE5XBnuJDlNIKHYq61S7bMupKXhKRZZo";
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message = update.getMessage();
//        String chatId = message.getChatId().toString();
//        try {
//            ArrayList<String> photoPaths = new ArrayList<>(PhotoMessageUtils.savePhotos(getFilesByMessage(message), getBotToken()));
//            for (String path: photoPaths) {
//                PhotoMessageUtils.processingImage(path);
//                execute(preparePhotoMessage(path, chatId));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) {
//        List<PhotoSize> photoSizes = message.getPhoto();
//        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files = new ArrayList<>();
//        for (PhotoSize photoSize: photoSizes) {
//            final String fileId = photoSize.getFileId();
//            try {
//                files.add(sendApiMethod(new GetFile(fileId)));
//            } catch (TelegramApiException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return files;
//    }
//
//    private SendPhoto preparePhotoMessage(String localPath, String chatId) {
//        SendPhoto sendPhoto = new SendPhoto();
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath));
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//    }
//
//
//
//
//}




//// ПРИМЕР 2
//public class Task9_2_0_VideoUrok /*extends TelegramLongPollingBot*/ {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 9. UI и использование готовых SDK. Задание №2:\s
//                    1. В данном модуле вам предстоит разработать телеграм бота, а именно  в данном задании нужно
//                       создать Maven проект и добавить зависимость  api telegram bots и telegram bots meta\s
//
//                Решение:\s""");
//
//        // https://core.telegram.org/api - сюда заходим вначале урока, чтобы посмотреть Bot API
//        // https://t.me/BotFather - это BotFather в Telegram, в нём надо нажать /newbot - create a new bot, чтобы создать новый bot
//        // нажимаем на /newbot
//        // создаём newbot с таким именем: javak'l;k'lkk[puouityte.bot
//        // прописываем для него username: javalghfjghfhgffghdfgyuhbkj_bot
//        // получаем в ответ позддравление, что всё получилось, т.е. - Ок, и вот такое сообщение:
//        // BotFather, [17.04.2024 8:57]
//        //Sorry, this username is invalid.
//        //Владислав Созин, [17.04.2024 8:58]
//        //javalghfjghfhgffghdfgyuhbkj_bot
//        //BotFather, [17.04.2024 8:58]
//        //Done! Congratulations on your new bot. You will find it at t.me/javalghfjghfhgffghdfgyuhbkj_bot. You can now add a description, about section and profile picture for your bot, see /help for a list of commands. By the way, when you've finished creating your cool bot, ping our Bot Support if you want a better username for it. Just make sure the bot is fully operational before you do this.
//        //Use this token to access the HTTP API:
//        //6841958724:AAFpY6qwhEdzBG95pK6Mu82kw373LNvRlxA
//        //Keep your token secure and store it safely, it can be used by anyone to control your bot.
//        //For a description of the Bot API, see this page: https://core.telegram.org/bots/api
//        // ссылка на наш новый bot такая: t.me/javalghfjghfhgffghdfgyuhbkj_bot
//        // это ключ или token для управления нашим bot'ом: 6841958724:AAFpY6qwhEdzBG95pK6Mu82kw373LNvRlxA
//
////        TelegramBotsApi null;
//
//
//
//
//
//
//        System.out.println("**********************");
//    }
//}
//// КОНЕЦ ПРИМЕРА 2