package task9_7_1_part2;

// ПОДХОД 240523 __ __ к ПРИМЕРу 8 т.е. до конца видео 08 _Закомментировал все prepare кроме того, который с SendMedia. Не возвращается никакое изображение.
// Добавил эту строку: inputMedia2.setMedia(new java.io.File(path2), "path2"); в метод SendMediaGroup preparePhotoMessage2 с тремя параметрами
// Использован бот №2
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import task9_7_1_part2.commands.AppBotCommand;
import task9_7_1_part2.commands.BotCommonCommands;
import task9_7_1_part2.functions.FilterOperation;
import task9_7_1_part2.functions.ImageOperation;
import task9_7_1_part2.utils.PhotoMessageUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static task9_7_1.utils.PhotoMessageUtils.processingImage;

public class Bot extends TelegramLongPollingBot {

    Class[] commandClasses = new Class[] {BotCommonCommands.class};

    @Override
    public String getBotUsername() {
        return "lgcyrrerrr_002_bot"; // Название вашего бота
    }

    @Override
    public String getBotToken() {
        return "7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo"; // Токен вашего бота
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message2 = update.getMessage();
        try {
            SendMessage responseTextMessage = runCommonCommand(message2);
            if (responseTextMessage != null) {
                execute(responseTextMessage);
                return;
            }
        } catch (InvocationTargetException | IllegalAccessException | TelegramApiException e) {
            e.printStackTrace();
        }
        try {
            SendMediaGroup responseMediaMessage = runPhotoFilter(message2);
            if (responseMediaMessage != null) {
                execute(responseMediaMessage);
                return;
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


        // TODO То, что ниже, полностью из 7-го примера. При необходимости раскомментить
//        Message message2 = update.getMessage();
//        try {
//            SendMessage responseTextMessage2 = runCommonCommand(message2);
//            if (responseTextMessage2 != null) {
//                execute(responseTextMessage2);
//                return;
//            }
//        } catch (InvocationTargetException | IllegalAccessException | TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        Message message = update.getMessage();
//        String chatId = message.getChatId().toString();
//
//        String response = null;
//        response = runCommand(message.getText());
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        final String localFileName = "src/main/java/task9_7_1_part2/" + "cloned_image.jpg";
//        PhotoSize photoSize = message.getPhoto().get(0);
//        response = runCommand(message.getText());
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);
//        } catch (TelegramApiException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            processingImage(localFileName);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        SendPhoto sendPhoto2 = preparePhotoMessage(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto2.setChatId(message.getChatId().toString());
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localFileName));
//        sendPhoto2.setPhoto(newFile);
//        sendPhoto2.setCaption("cloned_image");
//
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        SendPhoto sendPhoto = preparePhotoMessage2(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto.setChatId(message.getChatId().toString());
//        newFile.setMedia(new File(localFileName));
//        sendPhoto.setPhoto(newFile);
//        sendPhoto.setCaption("cloned_image");
//
//        try {
//            execute(sendPhoto);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//
//// Пишу для 7-го Примера
//        try {
//            SendMediaGroup responseMediaMessage2 = runPhotoFilter(message2);
//            if (responseMediaMessage2 != null) {
//                execute(responseMediaMessage2);
//                return;
//            }
//        } catch (/*InvocationTargetException | IllegalAccessException | */TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String runCommand(String text)/* throws InvocationTargetException, IllegalAccessException*/ {
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                if (annotation.name().equals(text)) {
//                    try {
//                        method.setAccessible(true);
//                        return (String) method.invoke(commands);
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return "Команда не из кнопки";
    }

    private String runCommand(String text)/* throws InvocationTargetException, IllegalAccessException*/ {
        BotCommonCommands commands = new BotCommonCommands();
        Method[] classMethods = commands.getClass().getDeclaredMethods();

        for (Method method : classMethods) {
            if (method.isAnnotationPresent(AppBotCommand.class)) {
                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
                if (annotation.name().equals(text)) {
                    try {
                        method.setAccessible(true);
                        return (String) method.invoke(commands);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "Команда не из кнопки";
    }


    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
    private SendMessage runCommonCommand(Message message2) throws InvocationTargetException, IllegalAccessException {
        String text2 = message2.getText();
        BotCommonCommands commands2 = new BotCommonCommands();
        Method[] classMethods2 = commands2.getClass().getDeclaredMethods();
        for (Method method : classMethods2) {
            if (method.isAnnotationPresent(AppBotCommand.class)) {
                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
                if (command.name().equals(text2)) {
                    method.setAccessible(true);
                    String responseText2 = (String) method.invoke(commands2);
                    if (responseText2 != null) {
                        SendMessage sendMessage2 = new SendMessage();
                        sendMessage2.setChatId(message2.getChatId().toString());
                        sendMessage2.setText(responseText2);
                        return sendMessage2;
                    }
                }
            }
        }
        return null;
    }

    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
    private SendMediaGroup runPhotoFilter (Message message2) {
        ImageOperation operation2 = FilterOperation::greyScale;
        List<org.telegram.telegrambots.meta.api.objects.File> files2 = getFilesByMessage(message2);
        try {
            List<String> paths2 = PhotoMessageUtils.savePhotos(files2, getBotToken());
            String chatId = message2.getChatId().toString();
            return preparePhotoMessage2(paths2, operation2, chatId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message2) { // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Так было изначально
        List<PhotoSize> photoSizes = message2.getPhoto();
        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
//        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Так было изначально
        for (PhotoSize photoSize : photoSizes){
            final String fileId = photoSize.getFileId();
            try {
                files2.add(sendApiMethod(new GetFile(fileId)));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        return files2;
    }

    private SendMediaGroup preparePhotoMessage2(List<String> localPaths2, ImageOperation operation2, String chatId) throws Exception {
        SendMediaGroup mediaGroup2 = new SendMediaGroup();
        ArrayList<InputMedia> medias2 = new ArrayList<>();
        for (String path2 : localPaths2) {
            InputMedia inputMedia2 = new InputMediaPhoto();
//            try {
            PhotoMessageUtils.processingImage2(path2, operation2);
            inputMedia2.setMedia(new java.io.File(path2), "path2");      // TODO Это добавил препод после ПРИМЕРа 7, перед самым концом видеоурока 08
//            inputMedia2.setNewMediaFile();
            medias2.add(inputMedia2);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        }
        mediaGroup2.setMedias(medias2);
        mediaGroup2.setChatId(chatId);
        return mediaGroup2;

    }

    private void saveImage(String url, String fileName) throws IOException {
        URL urlModel = new URL(url);
        InputStream inputStream = urlModel.openStream();
        OutputStream outputStream = new FileOutputStream(fileName);
        byte[] b = new byte[2048];
        int length;
        while ((length = inputStream.read(b)) != -1) {
            outputStream.write(b, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }


    private SendPhoto preparePhotoMessage2(String localPath, String chatId) {
        SendPhoto sendPhoto = new SendPhoto();
// TODO НАШЁЛ! ЭТА СТРОКА ОТВЕЧАЕТ ЗА СОЗДАНИЕ ТРЁХ КНОПОК в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6, в методе SendPhoto preparePhotoMessage2
        sendPhoto.setReplyMarkup(getKeyboard()); // Это ТРИ кнопки  // TODO в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6 В методе preparePhotoMessage2. ДА! При комменте три кнопки не создадутся!
        sendPhoto.setChatId(chatId);
        InputFile newFile = new InputFile();
        newFile.setMedia(new java.io.File(localPath)); // TODO Здесь заменил File на java.io.File как в видеоуроке на мин 10 22
//        newFile.setMedia(new File(localPath)); // TODO Так было изначально
        sendPhoto.setPhoto(newFile);
        return sendPhoto;
    }


//    private SendPhoto preparePhotoMessage(String localFileName, String chatId) {
//        SendPhoto sendPhoto2 = new SendPhoto();
//        sendPhoto2.setPhoto(new InputFile(new File(localFileName)));
//        sendPhoto2.setChatId(chatId);
//        return sendPhoto2;
//    }

    private ReplyKeyboardMarkup getKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> allKeyboardRows = new ArrayList<>();
        allKeyboardRows.addAll(getKeyboardRows(BotCommonCommands.class));
        allKeyboardRows.addAll(getKeyboardRows(FilterOperation.class));

        replyKeyboardMarkup.setKeyboard(allKeyboardRows);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
        Method[] classMethods = someClass.getDeclaredMethods();
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        for (Method method : classMethods) {
            if (method.isAnnotationPresent(AppBotCommand.class)) {
                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
                KeyboardButton button = new KeyboardButton();
                button.setText(annotation.name());
                row.add(button);
            }
        }

        keyboardRows.add(row);
        return keyboardRows;
    }
}




//// ПОДХОД 240523 08 04 к ПРИМЕРу 8 т.е. до конца видео 08 _Закомментировал все prepare кроме того, который с SendMedia. Не возвращается никакое изображение.
//// Добавил эту строку: inputMedia2.setMedia(new java.io.File(path2), "path2"); в метод SendMediaGroup preparePhotoMessage2 с тремя параметрами
//// Дорабатываем Пример 3 _РАБОТАЕТ! Создаёт 3 кнопки и обрабатывает их, и создаёт лишнюю надпись "Команда не из кнопки".
//// И обрабатывает только одно изображение _Использован бот №2
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
//import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import task9_7_1_part2.commands.AppBotCommand;
//import task9_7_1_part2.commands.BotCommonCommands;
//import task9_7_1_part2.functions.FilterOperation;
//import task9_7_1_part2.functions.ImageOperation;
//import task9_7_1_part2.utils.PhotoMessageUtils;
//
//import java.io.*;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import static task9_7_1.utils.PhotoMessageUtils.processingImage;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[] {BotCommonCommands.class};
//
//    @Override
//    public String getBotUsername() {
//        return "lgcyrrerrr_002_bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message2 = update.getMessage();
//        try {
//            SendMessage responseTextMessage2 = runCommonCommand(message2);
//            if (responseTextMessage2 != null) {
//                execute(responseTextMessage2);
//                return;
//            }
//        } catch (InvocationTargetException | IllegalAccessException | TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        Message message = update.getMessage();
//        String chatId = message.getChatId().toString();
//
//        String response = null;
//        response = runCommand(message.getText());
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        final String localFileName = "src/main/java/task9_7_1_part2/" + "cloned_image.jpg";
//        PhotoSize photoSize = message.getPhoto().get(0);
//        response = runCommand(message.getText());
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);
//        } catch (TelegramApiException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            processingImage(localFileName);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
////        SendPhoto sendPhoto2 = preparePhotoMessage(localFileName, message.getChatId().toString());
////        ///
////        sendPhoto2.setChatId(message.getChatId().toString());
////        InputFile newFile = new InputFile();
////        newFile.setMedia(new File(localFileName));
////        sendPhoto2.setPhoto(newFile);
////        sendPhoto2.setCaption("cloned_image");
////
////        sendMessage.setChatId(chatId);
////        sendMessage.setText(response);
////
////        try {
////            execute(sendMessage);
////        } catch (TelegramApiException e) {
////            e.printStackTrace();
////        }
//
////        SendPhoto sendPhoto = preparePhotoMessage2(localFileName, message.getChatId().toString());
////        ///
////        sendPhoto.setChatId(message.getChatId().toString());
////        newFile.setMedia(new File(localFileName));
////        sendPhoto.setPhoto(newFile);
////        sendPhoto.setCaption("cloned_image");
////
////        try {
////            execute(sendPhoto);
////        } catch (TelegramApiException e) {
////            e.printStackTrace();
////        }
//
//
//
//
//
//// Пишу для 7-го Примера
//        try {
//            SendMediaGroup responseMediaMessage2 = runPhotoFilter(message2);
//            if (responseMediaMessage2 != null) {
//                execute(responseMediaMessage2);
//                return;
//            }
//        } catch (/*InvocationTargetException | IllegalAccessException | */TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String runCommand(String text)/* throws InvocationTargetException, IllegalAccessException*/ {
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                if (annotation.name().equals(text)) {
//                    try {
//                        method.setAccessible(true);
//                        return (String) method.invoke(commands);
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return "Команда не из кнопки";
//    }
//
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private SendMessage runCommonCommand(Message message2) throws InvocationTargetException, IllegalAccessException {
//        String text2 = message2.getText();
//        BotCommonCommands commands2 = new BotCommonCommands();
//        Method[] classMethods2 = commands2.getClass().getDeclaredMethods();
//        for (Method method : classMethods2) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
//                if (command.name().equals(text2)) {
//                    method.setAccessible(true);
//                    String responseText2 = (String) method.invoke(commands2);
//                    if (responseText2 != null) {
//                        SendMessage sendMessage2 = new SendMessage();
//                        sendMessage2.setChatId(message2.getChatId().toString());
//                        sendMessage2.setText(responseText2);
//                        return sendMessage2;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private SendMediaGroup runPhotoFilter (Message message2) {
//        ImageOperation operation2 = FilterOperation::greyScale;
//        List<org.telegram.telegrambots.meta.api.objects.File> files2 = getFilesByMessage(message2);
//        try {
//            List<String> paths2 = PhotoMessageUtils.savePhotos(files2, getBotToken());
//            String chatId = message2.getChatId().toString();
//            return preparePhotoMessage2(paths2, operation2, chatId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message2) { // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Так было изначально
//        List<PhotoSize> photoSizes = message2.getPhoto();
//        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Так было изначально
//        for (PhotoSize photoSize : photoSizes){
//            final String fileId = photoSize.getFileId();
//            try {
//                files2.add(sendApiMethod(new GetFile(fileId)));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        return files2;
//    }
//
//    private SendMediaGroup preparePhotoMessage2(List<String> localPaths2, ImageOperation operation2, String chatId) throws Exception {
//        SendMediaGroup mediaGroup2 = new SendMediaGroup();
//        ArrayList<InputMedia> medias2 = new ArrayList<>();
//        for (String path2 : localPaths2) {
//            InputMedia inputMedia2 = new InputMediaPhoto();
////            try {
//            PhotoMessageUtils.processingImage2(path2, operation2);
//            inputMedia2.setMedia(new java.io.File(path2), "path2");      // TODO Это добавил препод после ПРИМЕРа 7, перед самым концом видеоурока 08
////            inputMedia2.setNewMediaFile();
//            medias2.add(inputMedia2);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//
//        }
//        mediaGroup2.setMedias(medias2);
//        mediaGroup2.setChatId(chatId);
//        return mediaGroup2;
//
//    }
//
//    private void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//
//
////    private SendPhoto preparePhotoMessage2(String localPath, String chatId) {
////        SendPhoto sendPhoto = new SendPhoto();
////// TODO НАШЁЛ! ЭТА СТРОКА ОТВЕЧАЕТ ЗА СОЗДАНИЕ ТРЁХ КНОПОК в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6, в методе SendPhoto preparePhotoMessage2
////        sendPhoto.setReplyMarkup(getKeyboard()); // Это ТРИ кнопки  // TODO в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6 В методе preparePhotoMessage2. ДА! При комменте три кнопки не создадутся!
////        sendPhoto.setChatId(chatId);
////        InputFile newFile = new InputFile();
////        newFile.setMedia(new java.io.File(localPath)); // TODO Здесь заменил File на java.io.File как в видеоуроке на мин 10 22
//////        newFile.setMedia(new File(localPath)); // TODO Так было изначально
////        sendPhoto.setPhoto(newFile);
////        return sendPhoto;
////    }
//
//
////    private SendPhoto preparePhotoMessage(String localFileName, String chatId) {
////        SendPhoto sendPhoto2 = new SendPhoto();
////        sendPhoto2.setPhoto(new InputFile(new File(localFileName)));
////        sendPhoto2.setChatId(chatId);
////        return sendPhoto2;
////    }
//
//    private ReplyKeyboardMarkup getKeyboard() {
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        ArrayList<KeyboardRow> allKeyboardRows = new ArrayList<>();
//        allKeyboardRows.addAll(getKeyboardRows(BotCommonCommands.class));
//        allKeyboardRows.addAll(getKeyboardRows(FilterOperation.class));
//
//        replyKeyboardMarkup.setKeyboard(allKeyboardRows);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        return replyKeyboardMarkup;
//    }
//
//    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
//        Method[] classMethods = someClass.getDeclaredMethods();
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                KeyboardButton button = new KeyboardButton();
//                button.setText(annotation.name());
//                row.add(button);
//            }
//        }
//
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//}




//// достиг ПРИМЕРа 7 т.е. 28 03 мин _как в Примере 6 РАБОТАЕТ ХОРОШО, ОТСЮДА ДОВЕСТИ ДО КОНЦА ВИДЕО 08, т.е. изображение только одно приходит.
//// Дорабатываем Пример 3 _РАБОТАЕТ! Создаёт 3 кнопки и обрабатывает их, и создаёт лишнюю надпись "Команда не из кнопки".
//// И обрабатывает только одно изображение _Использован бот №2
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
//import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import task9_7_1_part2.commands.AppBotCommand;
//import task9_7_1_part2.commands.BotCommonCommands;
//import task9_7_1_part2.functions.FilterOperation;
//import task9_7_1_part2.functions.ImageOperation;
//import task9_7_1_part2.utils.PhotoMessageUtils;
//
//import java.io.*;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import static task9_7_1.utils.PhotoMessageUtils.processingImage;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[] {BotCommonCommands.class};
//
//    @Override
//    public String getBotUsername() {
//        return "lgcyrrerrr_002_bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message2 = update.getMessage();
//        try {
//            SendMessage responseTextMessage2 = runCommonCommand(message2);
//            if (responseTextMessage2 != null) {
//                execute(responseTextMessage2);
//                return;
//            }
//        } catch (InvocationTargetException | IllegalAccessException | TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        Message message = update.getMessage();
//        String chatId = message.getChatId().toString();
//
//        String response = null;
//        response = runCommand(message.getText());
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        final String localFileName = "src/main/java/task9_7_1_part2/" + "cloned_image.jpg";
//        PhotoSize photoSize = message.getPhoto().get(0);
//        response = runCommand(message.getText());
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);
//        } catch (TelegramApiException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            processingImage(localFileName);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        SendPhoto sendPhoto2 = preparePhotoMessage(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto2.setChatId(message.getChatId().toString());
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localFileName));
//        sendPhoto2.setPhoto(newFile);
//        sendPhoto2.setCaption("cloned_image");
//
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        SendPhoto sendPhoto = preparePhotoMessage2(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto.setChatId(message.getChatId().toString());
//        newFile.setMedia(new File(localFileName));
//        sendPhoto.setPhoto(newFile);
//        sendPhoto.setCaption("cloned_image");
//
//        try {
//            execute(sendPhoto);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//
//// Пишу для 7-го Примера
//        try {
//            SendMediaGroup responseMediaMessage2 = runPhotoFilter(message2);
//            if (responseMediaMessage2 != null) {
//                execute(responseMediaMessage2);
//                return;
//            }
//        } catch (/*InvocationTargetException | IllegalAccessException | */TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String runCommand(String text)/* throws InvocationTargetException, IllegalAccessException*/ {
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                if (annotation.name().equals(text)) {
//                    try {
//                        method.setAccessible(true);
//                        return (String) method.invoke(commands);
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return "Команда не из кнопки";
//    }
//
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private SendMessage runCommonCommand(Message message2) throws InvocationTargetException, IllegalAccessException {
//        String text2 = message2.getText();
//        BotCommonCommands commands2 = new BotCommonCommands();
//        Method[] classMethods2 = commands2.getClass().getDeclaredMethods();
//        for (Method method : classMethods2) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
//                if (command.name().equals(text2)) {
//                    method.setAccessible(true);
//                    String responseText2 = (String) method.invoke(commands2);
//                    if (responseText2 != null) {
//                        SendMessage sendMessage2 = new SendMessage();
//                        sendMessage2.setChatId(message2.getChatId().toString());
//                        sendMessage2.setText(responseText2);
//                        return sendMessage2;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private SendMediaGroup runPhotoFilter (Message message2) {
//        ImageOperation operation2 = FilterOperation::greyScale;
//        List<org.telegram.telegrambots.meta.api.objects.File> files2 = getFilesByMessage(message2);
//        try {
//            List<String> paths2 = PhotoMessageUtils.savePhotos(files2, getBotToken());
//            String chatId = message2.getChatId().toString();
//            return preparePhotoMessage2(paths2, operation2, chatId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message2) { // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Так было изначально
//        List<PhotoSize> photoSizes = message2.getPhoto();
//        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Так было изначально
//        for (PhotoSize photoSize : photoSizes){
//            final String fileId = photoSize.getFileId();
//            try {
//                files2.add(sendApiMethod(new GetFile(fileId)));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        return files2;
//    }
//
//    private SendMediaGroup preparePhotoMessage2(List<String> localPaths2, ImageOperation operation2, String chatId) throws Exception {
//        SendMediaGroup mediaGroup2 = new SendMediaGroup();
//        ArrayList<InputMedia> medias2 = new ArrayList<>();
//        for (String path2 : localPaths2) {
//            InputMedia inputMedia2 = new InputMediaPhoto();
////            try {
//            PhotoMessageUtils.processingImage2(path2, operation2);
//            inputMedia2.setNewMediaFile(new java.io.File(path2));
//            medias2.add(inputMedia2);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//
//        }
//        mediaGroup2.setMedias(medias2);
//        mediaGroup2.setChatId(chatId);
//        return mediaGroup2;
//
//    }
//
//    private void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//
//
//    private SendPhoto preparePhotoMessage2(String localPath, String chatId) {
//        SendPhoto sendPhoto = new SendPhoto();
//// TODO НАШЁЛ! ЭТА СТРОКА ОТВЕЧАЕТ ЗА СОЗДАНИЕ ТРЁХ КНОПОК в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6, в методе SendPhoto preparePhotoMessage2
//        sendPhoto.setReplyMarkup(getKeyboard()); // Это ТРИ кнопки  // TODO в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6 В методе preparePhotoMessage2. ДА! При комменте три кнопки не создадутся!
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new java.io.File(localPath)); // TODO Здесь заменил File на java.io.File как в видеоуроке на мин 10 22
////        newFile.setMedia(new File(localPath)); // TODO Так было изначально
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//    }
//
//
//    private SendPhoto preparePhotoMessage(String localFileName, String chatId) {
//        SendPhoto sendPhoto2 = new SendPhoto();
//        sendPhoto2.setPhoto(new InputFile(new File(localFileName)));
//        sendPhoto2.setChatId(chatId);
//        return sendPhoto2;
//    }
//
//    private ReplyKeyboardMarkup getKeyboard() {
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        ArrayList<KeyboardRow> allKeyboardRows = new ArrayList<>();
//        allKeyboardRows.addAll(getKeyboardRows(BotCommonCommands.class));
//        allKeyboardRows.addAll(getKeyboardRows(FilterOperation.class));
//
//        replyKeyboardMarkup.setKeyboard(allKeyboardRows);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        return replyKeyboardMarkup;
//    }
//
//    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
//        Method[] classMethods = someClass.getDeclaredMethods();
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                KeyboardButton button = new KeyboardButton();
//                button.setText(annotation.name());
//                row.add(button);
//            }
//        }
//
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//}





//// из ПРИМЕРа 6 _Промежуточный. Всё работает. Пройдена 26 19 мин. Создаёт три кнопки, одно фото, одну надпись Команда не из кнопки
//// Дорабатываем Пример 3 _РАБОТАЕТ! Создаёт 3 кнопки и обрабатывает их, и создаёт лишнюю надпись "Команда не из кнопки".
//// И обрабатывает только одно изображение _Использован бот №2
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
//import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import task9_7_1_part2.commands.AppBotCommand;
//import task9_7_1_part2.commands.BotCommonCommands;
//import task9_7_1_part2.functions.FilterOperation;
//import task9_7_1_part2.functions.ImageOperation;
//import task9_7_1_part2.utils.PhotoMessageUtils;
//
//import java.io.*;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import static task9_7_1.utils.PhotoMessageUtils.processingImage;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[] {BotCommonCommands.class};
//
//    @Override
//    public String getBotUsername() {
//        return "lgcyrrerrr_002_bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message2 = update.getMessage();
//        try {
//            SendMessage responseTextMessage2 = runCommonCommand(message2);
//            if (responseTextMessage2 != null) {
//                execute(responseTextMessage2);
//                return;
//            }
//        } catch (InvocationTargetException | IllegalAccessException | TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        Message message = update.getMessage();
//        String chatId = message.getChatId().toString();
//
//        String response = null;
//        response = runCommand(message.getText());
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        final String localFileName = "src/main/java/task9_7_1_part2/" + "cloned_image.jpg";
//        PhotoSize photoSize = message.getPhoto().get(0);
//        response = runCommand(message.getText());
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);
//        } catch (TelegramApiException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            processingImage(localFileName);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        SendPhoto sendPhoto2 = preparePhotoMessage(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto2.setChatId(message.getChatId().toString());
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localFileName));
//        sendPhoto2.setPhoto(newFile);
//        sendPhoto2.setCaption("cloned_image");
//
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        SendPhoto sendPhoto = preparePhotoMessage2(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto.setChatId(message.getChatId().toString());
//        newFile.setMedia(new File(localFileName));
//        sendPhoto.setPhoto(newFile);
//        sendPhoto.setCaption("cloned_image");
//
//        try {
//            execute(sendPhoto);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String runCommand(String text)/* throws InvocationTargetException, IllegalAccessException*/ {
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                if (annotation.name().equals(text)) {
//                    try {
//                        method.setAccessible(true);
//                        return (String) method.invoke(commands);
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return "Команда не из кнопки";
//    }
//
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private SendMessage runCommonCommand(Message message2) throws InvocationTargetException, IllegalAccessException {
//        String text2 = message2.getText();
//        BotCommonCommands commands2 = new BotCommonCommands();
//        Method[] classMethods2 = commands2.getClass().getDeclaredMethods();
//        for (Method method : classMethods2) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
//                if (command.name().equals(text2)) {
//                    method.setAccessible(true);
//                    String responseText2 = (String) method.invoke(commands2);
//                    if (responseText2 != null) {
//                        SendMessage sendMessage2 = new SendMessage();
//                        sendMessage2.setChatId(message2.getChatId().toString());
//                        sendMessage2.setText(responseText2);
//                        return sendMessage2;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private SendMediaGroup runPhotoFilter (Message message2) {
//        ImageOperation operation2 = FilterOperation::greyScale;
//        List<org.telegram.telegrambots.meta.api.objects.File> files2 = getFilesByMessage(message2);
//        try {
//            List<String> paths2 = PhotoMessageUtils.savePhotos(files2, getBotToken());
//            String chatId = message2.getChatId().toString();
//            return preparePhotoMessage2(paths2, operation2, chatId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message2) { // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Так было изначально
//        List<PhotoSize> photoSizes = message2.getPhoto();
//        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Так было изначально
//        for (PhotoSize photoSize : photoSizes){
//            final String fileId = photoSize.getFileId();
//            try {
//                files2.add(sendApiMethod(new GetFile(fileId)));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        return files2;
//    }
//
//    private SendMediaGroup preparePhotoMessage2(List<String> localPaths2, ImageOperation operation2, String chatId) throws Exception {
//        SendMediaGroup mediaGroup2 = new SendMediaGroup();
//        ArrayList<InputMedia> medias2 = new ArrayList<>();
//        for (String path2 : localPaths2) {
//            InputMedia inputMedia2 = new InputMediaPhoto();
////            try {
//            PhotoMessageUtils.processingImage2(path2, operation2);
//            inputMedia2.setNewMediaFile(new java.io.File(path2));
//            medias2.add(inputMedia2);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//
//        }
//        mediaGroup2.setMedias(medias2);
//        mediaGroup2.setChatId(chatId);
//        return mediaGroup2;
//
//    }
//
//    private void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//
//
//    private SendPhoto preparePhotoMessage2(String localPath, String chatId) {
//        SendPhoto sendPhoto = new SendPhoto();
//// TODO НАШЁЛ! ЭТА СТРОКА ОТВЕЧАЕТ ЗА СОЗДАНИЕ ТРЁХ КНОПОК в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6, в методе SendPhoto preparePhotoMessage2
//        sendPhoto.setReplyMarkup(getKeyboard()); // Это ТРИ кнопки  // TODO в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6 В методе preparePhotoMessage2. ДА! При комменте три кнопки не создадутся!
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new java.io.File(localPath)); // TODO Здесь заменил File на java.io.File как в видеоуроке на мин 10 22
////        newFile.setMedia(new File(localPath)); // TODO Так было изначально
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//    }
//
//
//    private SendPhoto preparePhotoMessage(String localFileName, String chatId) {
//        SendPhoto sendPhoto2 = new SendPhoto();
//        sendPhoto2.setPhoto(new InputFile(new File(localFileName)));
//        sendPhoto2.setChatId(chatId);
//        return sendPhoto2;
//    }
//
//    private ReplyKeyboardMarkup getKeyboard() {
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        ArrayList<KeyboardRow> allKeyboardRows = new ArrayList<>();
//        allKeyboardRows.addAll(getKeyboardRows(BotCommonCommands.class));
//        allKeyboardRows.addAll(getKeyboardRows(FilterOperation.class));
//
//        replyKeyboardMarkup.setKeyboard(allKeyboardRows);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        return replyKeyboardMarkup;
//    }
//
//    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
//        Method[] classMethods = someClass.getDeclaredMethods();
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                KeyboardButton button = new KeyboardButton();
//                button.setText(annotation.name());
//                row.add(button);
//            }
//        }
//
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//}

//// TODO ВЫШЕ ЭТОГО ВСЁ УДАЛИТЬ. ТРИ КНОППКИ СОЗДАЮТСЯ НАЧИНАЯ С МЕТОДА 6



//// ПРИМЕР 8 _Получившийся код по итогу, Т.Е. после просмотра всего видео 08. Но не работает. Возвращается
//// только одно изображение, и кажется кнопки не создаёт
// Дорабатываем Пример 3 _РАБОТАЕТ! Создаёт 3 кнопки и обрабатывает их, и создаёт лишнюю надпись "Команда не из кнопки".
// И обрабатывает только одно изображение _Использован бот №2
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
//import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import task9_7_1_part2.commands.AppBotCommand;
//import task9_7_1_part2.commands.BotCommonCommands;
//import task9_7_1_part2.functions.FilterOperation;
//import task9_7_1_part2.functions.ImageOperation;
//import task9_7_1_part2.utils.PhotoMessageUtils;
//
//import java.io.*;
//        import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import static task9_7_1.utils.PhotoMessageUtils.processingImage;
//import static task9_7_1_part2.utils.PhotoMessageUtils.processingImage2;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[] {BotCommonCommands.class};
//
//    @Override
//    public String getBotUsername() {
//        return "lgcyrrerrr_002_bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message2 = update.getMessage();
//        try {
//            SendMessage responseTextMessage2 = runCommonCommand(message2);
//            if (responseTextMessage2 != null) {
//                execute(responseTextMessage2);
//                return;
//            }
//        } catch (InvocationTargetException | IllegalAccessException | TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        // TODO Это написал уже в новом, т.е. во 2-ой части.
//        try {
//            SendMediaGroup responseMediaMessage2 = runPhotoFilter(message2);
//            if (responseMediaMessage2 != null) {
//                execute(responseMediaMessage2);
//                return;
//            }
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//
////        // TODO Написал уже в новом, но возможно придётся удалить
////        String chatId2 = message2.getChatId().toString();
////        try {
////            String response2 = runCommand(message2.getText());
////            SendMessage sendMessage2 = new SendMessage();
////            sendMessage2.setChatId(chatId2);
////            sendMessage2.setText(response2);
////            execute(sendMessage2);
////        } catch (/*InvocationTargetException | IllegalAccessException | */TelegramApiException e) {
////            e.printStackTrace();
////        }
//
////        try {
////            ArrayList<String> photoPaths = new ArrayList<>(PhotoMessageUtils.savePhotos(getFilesByMessage(message2), getBotToken()));
////            for (String path : photoPaths) {
////                PhotoMessageUtils.processingImage(path);
////                execute(preparePhotoMessage(path, chatId2));  // Здесь ссылка на этот метод: preparePhotoMessage (без двойки)
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//
//
//
//        // TODO Далее изначальные строки только этого метода
//        Message message = update.getMessage();
//        String chatId = message.getChatId().toString();
//
//        String response = null;
//        response = runCommand(message.getText());
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        final String localFileName = "src/main/java/task9_7_1_part2/" + "cloned_image.jpg";
//        PhotoSize photoSize = message.getPhoto().get(0);
//        response = runCommand(message.getText());
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);
//        } catch (TelegramApiException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
////            processingImage2(localFileName);
//            processingImage(localFileName);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
////        SendPhoto sendPhoto = preparePhotoMessage2(localFileName, message.getChatId().toString());
//        SendPhoto sendPhoto2 = preparePhotoMessage(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto2.setChatId(message.getChatId().toString());
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localFileName));
//        sendPhoto2.setPhoto(newFile);
//        sendPhoto2.setCaption("cloned_image");
//
//        try {
//            execute(sendPhoto2);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String runCommand(String text)/* throws InvocationTargetException, IllegalAccessException*/ {
//
//
//
//
//
//        // TODO Далее изначальные строки только этого метода
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                if (annotation.name().equals(text)) {
//                    try {
//                        method.setAccessible(true);
//                        return (String) method.invoke(commands);
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return "Команда не из кнопки";
//    }
//
//
//    // TODO Этого метода не было в ИЗНВЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private SendMessage runCommonCommand(Message message2) throws InvocationTargetException, IllegalAccessException {
//        String text2 = message2.getText();
//        BotCommonCommands commands2 = new BotCommonCommands();
//        Method[] classMethods2 = commands2.getClass().getDeclaredMethods();
//        for (Method method : classMethods2) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
//                if (command.name().equals(text2)) {
//                    method.setAccessible(true);
//                    String responseText2 = (String) method.invoke(commands2);
//                    if (responseText2 != null) {
//                        SendMessage sendMessage2 = new SendMessage();
//                        sendMessage2.setChatId(message2.getChatId().toString());
//                        sendMessage2.setText(responseText2);
//                        return sendMessage2;
//                    }
//                }
//            }
//        }
//
//        return null;
//    }
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private SendMediaGroup runPhotoFilter (Message message2) {
//        ImageOperation operation2 = FilterOperation::greyScale;
//        List<org.telegram.telegrambots.meta.api.objects.File> files2 = getFilesByMessage(message2);
//        try {
//            List<String> paths2 = PhotoMessageUtils.savePhotos(files2, getBotToken());
//            String chatId = message2.getChatId().toString();
//            return preparePhotoMessage2(paths2, operation2, chatId);
////            ArrayList<String> photoPaths = new ArrayList<>(paths2);
////            for (String path2 : paths2) {
//////                PhotoMessageUtils.processingImage2(path2, FilterOperation::greyScale);
////
////
////
////
////
//////                SendMediaGroup mediaGroup2 = new SendMediaGroup();
//////                InputMedia inputMedia2 = new InputMediaPhoto();
//////                inputMedia2.setNewMediaFile();
//////                mediaGroup2.setMedias();
////                execute(photo2);  // Здесь ссылка на этот метод: preparePhotoMessage2 (с двойкой)
////            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message2) { // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Так было изначально
//        List<PhotoSize> photoSizes = message2.getPhoto();
//        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Так было изначально
//        for (PhotoSize photoSize : photoSizes){
//            final String fileId = photoSize.getFileId();
//            try {
//                files2.add(sendApiMethod(new GetFile(fileId)));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        return files2;
//    }
//
//    private SendMediaGroup preparePhotoMessage2(List<String> localPaths2, ImageOperation operation2, String chatId) throws Exception {
//        SendMediaGroup mediaGroup2 = new SendMediaGroup();
//        ArrayList<InputMedia> medias2 = new ArrayList<>();
//        for (String path2 : localPaths2) {
//            InputMedia inputMedia2 = new InputMediaPhoto();
////            try {
//            processingImage2(path2, operation2);
//            inputMedia2.setMedia(new java.io.File(path2), "path2");
////            inputMedia2.setNewMediaFile(new java.io.File(path2));
//            medias2.add(inputMedia2);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//
//        }
//        mediaGroup2.setMedias(medias2);
//        mediaGroup2.setChatId(chatId);
//
////        SendPhoto sendPhoto = new SendPhoto();
////        sendPhoto.setReplyMarkup(getKeyboard()); // Это две кнопки
////        sendPhoto.setChatId(chatId);
////        InputFile newFile = new InputFile();
////        newFile.setMedia(new java.io.File(localPath)); // TODO Здесь заменил File на java.io.File как в видеоуроке на мин 10 22
////        newFile.setMedia(new File(localPath)); // TODO Так было изначально
////        sendPhoto.setPhoto(newFile);
//        return mediaGroup2;
//
//    }
//
//
//
//
//
//    // TODO ДАЛЕЕ ВСЕ ИЗНАЧАЛЬНЫЕ МЕТОДЫ
//
//    private void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//
//    private SendPhoto preparePhotoMessage(String localFileName, String chatId) {
//        SendPhoto sendPhoto2 = new SendPhoto();
//        sendPhoto2.setPhoto(new InputFile(new File(localFileName)));
//        sendPhoto2.setChatId(chatId);
//        return sendPhoto2;
//    }
//
//    private ReplyKeyboardMarkup getKeyboard() {
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        ArrayList<KeyboardRow> allKeyboardRows = new ArrayList<>();
//        allKeyboardRows.addAll(getKeyboardRows(BotCommonCommands.class));
//        allKeyboardRows.addAll(getKeyboardRows(FilterOperation.class));
//
//        replyKeyboardMarkup.setKeyboard(allKeyboardRows);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        return replyKeyboardMarkup;
//    }
//
//    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
//        Method[] classMethods = someClass.getDeclaredMethods();
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                KeyboardButton button = new KeyboardButton();
//                button.setText(annotation.name());
//                row.add(button);
//            }
//        }
//
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//}
//// КОНЕЦ ПРИМЕРА 8




//// ПРИМЕР 7 _Промежуточный. Всё работает, но пока приходит одно изображение. Пройдена 28 03 мин
//// Дорабатываем Пример 3 _РАБОТАЕТ! Создаёт 3 кнопки и обрабатывает их, и создаёт лишнюю надпись "Команда не из кнопки".
//// И обрабатывает только одно изображение _Использован бот №2
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
//import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import task9_7_1_part2.commands.AppBotCommand;
//import task9_7_1_part2.commands.BotCommonCommands;
//import task9_7_1_part2.functions.FilterOperation;
//import task9_7_1_part2.functions.ImageOperation;
//import task9_7_1_part2.utils.PhotoMessageUtils;
//
//import java.io.*;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import static task9_7_1.utils.PhotoMessageUtils.processingImage;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[] {BotCommonCommands.class};
//
//    @Override
//    public String getBotUsername() {
//        return "lgcyrrerrr_002_bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message2 = update.getMessage();
//        try {
//            SendMessage responseTextMessage2 = runCommonCommand(message2);
//            if (responseTextMessage2 != null) {
//                execute(responseTextMessage2);
//                return;
//            }
//        } catch (InvocationTargetException | IllegalAccessException | TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            SendMediaGroup responseMediaMessage2 = runPhotoFilter(message2);
//            if (responseMediaMessage2 != null) {
//                execute(responseMediaMessage2);
//                return;
//            }
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//
////        // TODO Написал уже в новом, но возможно придётся удалить
////        String chatId2 = message2.getChatId().toString();
////        try {
////            String response2 = runCommand(message2.getText());
////            SendMessage sendMessage2 = new SendMessage();
////            sendMessage2.setChatId(chatId2);
////            sendMessage2.setText(response2);
////            execute(sendMessage2);
////        } catch (/*InvocationTargetException | IllegalAccessException | */TelegramApiException e) {
////            e.printStackTrace();
////        }
//
////        try {
////            ArrayList<String> photoPaths = new ArrayList<>(PhotoMessageUtils.savePhotos(getFilesByMessage(message2), getBotToken()));
////            for (String path : photoPaths) {
////                PhotoMessageUtils.processingImage(path);
////                execute(preparePhotoMessage(path, chatId2));  // Здесь ссылка на этот метод: preparePhotoMessage (без двойки)
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//
//
//
//        // TODO Далее изначальные строки только этого метода
//        Message message = update.getMessage();
//        String chatId = message.getChatId().toString();
//
//        String response = null;
//        response = runCommand(message.getText());
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        final String localFileName = "src/main/java/task9_7_1_part2/" + "cloned_image.jpg";
//        PhotoSize photoSize = message.getPhoto().get(0);
//        response = runCommand(message.getText());
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);
//        } catch (TelegramApiException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            processingImage(localFileName);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
////        SendPhoto sendPhoto = preparePhotoMessage2(localFileName, message.getChatId().toString());
//        SendPhoto sendPhoto2 = preparePhotoMessage(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto2.setChatId(message.getChatId().toString());
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localFileName));
//        sendPhoto2.setPhoto(newFile);
//        sendPhoto2.setCaption("cloned_image");
//
//        try {
//            execute(sendPhoto2);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String runCommand(String text)/* throws InvocationTargetException, IllegalAccessException*/ {
//
//
//
//
//        // TODO Далее изначальные строки только этого метода
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                if (annotation.name().equals(text)) {
//                    try {
//                        method.setAccessible(true);
//                        return (String) method.invoke(commands);
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return "Команда не из кнопки";
//    }
//
//
//    // TODO Этого метода не было в ИЗНВЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private SendMessage runCommonCommand(Message message2) throws InvocationTargetException, IllegalAccessException {
//        String text2 = message2.getText();
//        BotCommonCommands commands2 = new BotCommonCommands();
//        Method[] classMethods2 = commands2.getClass().getDeclaredMethods();
//        for (Method method : classMethods2) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
//                if (command.name().equals(text2)) {
//                    method.setAccessible(true);
//                    String responseText2 = (String) method.invoke(commands2);
//                    if (responseText2 != null) {
//                        SendMessage sendMessage2 = new SendMessage();
//                        sendMessage2.setChatId(message2.getChatId().toString());
//                        sendMessage2.setText(responseText2);
//                        return sendMessage2;
//                    }
//                }
//            }
//        }
//
//        return null;
//    }
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private SendMediaGroup runPhotoFilter (Message message2) {
//        ImageOperation operation2 = FilterOperation::greyScale;
//        List<org.telegram.telegrambots.meta.api.objects.File> files2 = getFilesByMessage(message2);
//        try {
//            List<String> paths2 = PhotoMessageUtils.savePhotos(files2, getBotToken());
//            String chatId = message2.getChatId().toString();
//            return preparePhotoMessage2(paths2, operation2, chatId);
////            ArrayList<String> photoPaths = new ArrayList<>(paths2);
////            for (String path2 : paths2) {
//////                PhotoMessageUtils.processingImage2(path2, FilterOperation::greyScale);
////
////
////
////
////
//////                SendMediaGroup mediaGroup2 = new SendMediaGroup();
//////                InputMedia inputMedia2 = new InputMediaPhoto();
//////                inputMedia2.setNewMediaFile();
//////                mediaGroup2.setMedias();
////                execute(photo2);  // Здесь ссылка на этот метод: preparePhotoMessage2 (с двойкой)
////            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message2) { // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Так было изначально
//        List<PhotoSize> photoSizes = message2.getPhoto();
//        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Так было изначально
//        for (PhotoSize photoSize : photoSizes){
//            final String fileId = photoSize.getFileId();
//            try {
//                files2.add(sendApiMethod(new GetFile(fileId)));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        return files2;
//    }
//
//    private SendMediaGroup preparePhotoMessage2(List<String> localPaths2, ImageOperation operation2, String chatId) throws Exception {
//        SendMediaGroup mediaGroup2 = new SendMediaGroup();
//        ArrayList<InputMedia> medias2 = new ArrayList<>();
//        for (String path2 : localPaths2) {
//            InputMedia inputMedia2 = new InputMediaPhoto();
////            try {
//            PhotoMessageUtils.processingImage2(path2, operation2);
//            inputMedia2.setNewMediaFile(new java.io.File(path2));
//            medias2.add(inputMedia2);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//
//        }
//        mediaGroup2.setMedias(medias2);
//        mediaGroup2.setChatId(chatId);
//
////        SendPhoto sendPhoto = new SendPhoto();
////        sendPhoto.setReplyMarkup(getKeyboard()); // Это две кнопки
////        sendPhoto.setChatId(chatId);
////        InputFile newFile = new InputFile();
////        newFile.setMedia(new java.io.File(localPath)); // TODO Здесь заменил File на java.io.File как в видеоуроке на мин 10 22
////        newFile.setMedia(new File(localPath)); // TODO Так было изначально
////        sendPhoto.setPhoto(newFile);
//        return mediaGroup2;
//
//    }
//
//
//
//
//
//    // TODO ДАЛЕЕ ВСЕ ИЗНАЧАЛЬНЫЕ МЕТОДЫ
//
//    private void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//
//    private SendPhoto preparePhotoMessage(String localFileName, String chatId) {
//        SendPhoto sendPhoto2 = new SendPhoto();
//        sendPhoto2.setPhoto(new InputFile(new File(localFileName)));
//        sendPhoto2.setChatId(chatId);
//        return sendPhoto2;
//    }
//
//    private ReplyKeyboardMarkup getKeyboard() {
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        ArrayList<KeyboardRow> allKeyboardRows = new ArrayList<>();
//        allKeyboardRows.addAll(getKeyboardRows(BotCommonCommands.class));
//        allKeyboardRows.addAll(getKeyboardRows(FilterOperation.class));
//
//        replyKeyboardMarkup.setKeyboard(allKeyboardRows);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        return replyKeyboardMarkup;
//    }
//
//    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
//        Method[] classMethods = someClass.getDeclaredMethods();
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                KeyboardButton button = new KeyboardButton();
//                button.setText(annotation.name());
//                row.add(button);
//            }
//        }
//
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//}
//// КОНЕЦ ПРИМЕРА 7




//// ПРИМЕР 6 _Промежуточный. Всё работает. Пройдена 26 19 мин. Создаёт три кнопки, одно фото, одну надпись Команда не из кнопки
//// Дорабатываем Пример 3 _РАБОТАЕТ! Создаёт 3 кнопки и обрабатывает их, и создаёт лишнюю надпись "Команда не из кнопки".
//// И обрабатывает только одно изображение _Использован бот №2
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
//import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import task9_7_1_part2.commands.AppBotCommand;
//import task9_7_1_part2.commands.BotCommonCommands;
//import task9_7_1_part2.functions.FilterOperation;
//import task9_7_1_part2.functions.ImageOperation;
//import task9_7_1_part2.utils.PhotoMessageUtils;
//
//import java.io.*;
//        import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import static task9_7_1.utils.PhotoMessageUtils.processingImage;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[] {BotCommonCommands.class};
//
//    @Override
//    public String getBotUsername() {
//        return "lgcyrrerrr_002_bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message2 = update.getMessage();
//        try {
//            SendMessage responseTextMessage2 = runCommonCommand(message2);
//            if (responseTextMessage2 != null) {
//                execute(responseTextMessage2);
//                return;
//            }
//        } catch (InvocationTargetException | IllegalAccessException | TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        Message message = update.getMessage();
//        String chatId = message.getChatId().toString();
//
//        String response = null;
//        response = runCommand(message.getText());
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        final String localFileName = "src/main/java/task9_7_1_part2/" + "cloned_image.jpg";
//        PhotoSize photoSize = message.getPhoto().get(0);
//        response = runCommand(message.getText());
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);
//        } catch (TelegramApiException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            processingImage(localFileName);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        SendPhoto sendPhoto2 = preparePhotoMessage(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto2.setChatId(message.getChatId().toString());
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localFileName));
//        sendPhoto2.setPhoto(newFile);
//        sendPhoto2.setCaption("cloned_image");
//
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        SendPhoto sendPhoto = preparePhotoMessage2(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto.setChatId(message.getChatId().toString());
//        newFile.setMedia(new File(localFileName));
//        sendPhoto.setPhoto(newFile);
//        sendPhoto.setCaption("cloned_image");
//
//        try {
//            execute(sendPhoto);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String runCommand(String text)/* throws InvocationTargetException, IllegalAccessException*/ {
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                if (annotation.name().equals(text)) {
//                    try {
//                        method.setAccessible(true);
//                        return (String) method.invoke(commands);
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return "Команда не из кнопки";
//    }
//
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private SendMessage runCommonCommand(Message message2) throws InvocationTargetException, IllegalAccessException {
//        String text2 = message2.getText();
//        BotCommonCommands commands2 = new BotCommonCommands();
//        Method[] classMethods2 = commands2.getClass().getDeclaredMethods();
//        for (Method method : classMethods2) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
//                if (command.name().equals(text2)) {
//                    method.setAccessible(true);
//                    String responseText2 = (String) method.invoke(commands2);
//                    if (responseText2 != null) {
//                        SendMessage sendMessage2 = new SendMessage();
//                        sendMessage2.setChatId(message2.getChatId().toString());
//                        sendMessage2.setText(responseText2);
//                        return sendMessage2;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private SendMediaGroup runPhotoFilter (Message message2) {
//        ImageOperation operation2 = FilterOperation::greyScale;
//        List<org.telegram.telegrambots.meta.api.objects.File> files2 = getFilesByMessage(message2);
//        try {
//            List<String> paths2 = PhotoMessageUtils.savePhotos(files2, getBotToken());
//            String chatId = message2.getChatId().toString();
//            return preparePhotoMessage2(paths2, operation2, chatId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message2) { // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Так было изначально
//        List<PhotoSize> photoSizes = message2.getPhoto();
//        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Так было изначально
//        for (PhotoSize photoSize : photoSizes){
//            final String fileId = photoSize.getFileId();
//            try {
//                files2.add(sendApiMethod(new GetFile(fileId)));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        return files2;
//    }
//
//    private SendMediaGroup preparePhotoMessage2(List<String> localPaths2, ImageOperation operation2, String chatId) throws Exception {
//        SendMediaGroup mediaGroup2 = new SendMediaGroup();
//        ArrayList<InputMedia> medias2 = new ArrayList<>();
//        for (String path2 : localPaths2) {
//            InputMedia inputMedia2 = new InputMediaPhoto();
////            try {
//            PhotoMessageUtils.processingImage2(path2, operation2);
//            inputMedia2.setNewMediaFile(new java.io.File(path2));
//            medias2.add(inputMedia2);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//
//        }
//        mediaGroup2.setMedias(medias2);
//        mediaGroup2.setChatId(chatId);
//        return mediaGroup2;
//
//    }
//
//    private void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//
//
//    private SendPhoto preparePhotoMessage2(String localPath, String chatId) {
//        SendPhoto sendPhoto = new SendPhoto();
//// TODO НАШЁЛ! ЭТА СТРОКА ОТВЕЧАЕТ ЗА СОЗДАНИЕ ТРЁХ КНОПОК в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6, в методе SendPhoto preparePhotoMessage2
//        sendPhoto.setReplyMarkup(getKeyboard()); // Это ТРИ кнопки  // TODO в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6 В методе preparePhotoMessage2. ДА! При комменте три кнопки не создадутся!
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new java.io.File(localPath)); // TODO Здесь заменил File на java.io.File как в видеоуроке на мин 10 22
////        newFile.setMedia(new File(localPath)); // TODO Так было изначально
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//    }
//
//
//    private SendPhoto preparePhotoMessage(String localFileName, String chatId) {
//        SendPhoto sendPhoto2 = new SendPhoto();
//        sendPhoto2.setPhoto(new InputFile(new File(localFileName)));
//        sendPhoto2.setChatId(chatId);
//        return sendPhoto2;
//    }
//
//    private ReplyKeyboardMarkup getKeyboard() {
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        ArrayList<KeyboardRow> allKeyboardRows = new ArrayList<>();
//        allKeyboardRows.addAll(getKeyboardRows(BotCommonCommands.class));
//        allKeyboardRows.addAll(getKeyboardRows(FilterOperation.class));
//
//        replyKeyboardMarkup.setKeyboard(allKeyboardRows);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        return replyKeyboardMarkup;
//    }
//
//    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
//        Method[] classMethods = someClass.getDeclaredMethods();
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                KeyboardButton button = new KeyboardButton();
//                button.setText(annotation.name());
//                row.add(button);
//            }
//        }
//
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//}
//// КОНЕЦ ПРИМЕРА 6




//// ПРИМЕР 5 _Промежуточный. Есть 2 ошибки. 15 12 мин Здесь мы можем заметить, что не можем
//// отправить несколько фоток сразу. Переходим к отправке неск. фоток
//// Дорабатываем Пример 3 _РАБОТАЕТ! Создаёт 3 кнопки и обрабатывает их, и создаёт лишнюю надпись "Команда не из кнопки".
//// И обрабатывает только одно изображение _Использован бот №2
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import task9_7_1_part2.commands.AppBotCommand;
//import task9_7_1_part2.commands.BotCommonCommands;
//import task9_7_1_part2.functions.FilterOperation;
//import task9_7_1_part2.utils.PhotoMessageUtils;
//
//import java.io.*;
//        import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import static task9_7_1.utils.PhotoMessageUtils.processingImage;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[] {BotCommonCommands.class};
//
//    @Override
//    public String getBotUsername() {
//        return "lgcyrrerrr_002_bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message2 = update.getMessage();
//        try {
//            SendMessage responseTextMessage2 = runCommonCommand(message2);
//            if (responseTextMessage2 != null) {
//                execute(responseTextMessage2);
//                return;
//            }
//        } catch (InvocationTargetException | IllegalAccessException | TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//
////        // TODO Написал уже в новом, но возможно придётся удалить
////        String chatId2 = message2.getChatId().toString();
////        try {
////            String response2 = runCommand(message2.getText());
////            SendMessage sendMessage2 = new SendMessage();
////            sendMessage2.setChatId(chatId2);
////            sendMessage2.setText(response2);
////            execute(sendMessage2);
////        } catch (/*InvocationTargetException | IllegalAccessException | */TelegramApiException e) {
////            e.printStackTrace();
////        }
//
////        try {
////            ArrayList<String> photoPaths = new ArrayList<>(PhotoMessageUtils.savePhotos(getFilesByMessage(message2), getBotToken()));
////            for (String path : photoPaths) {
////                PhotoMessageUtils.processingImage(path);
////                execute(preparePhotoMessage(path, chatId2));  // Здесь ссылка на этот метод: preparePhotoMessage (без двойки)
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//
//
//
//        // TODO Далее изначальные строки только этого метода
//        Message message = update.getMessage();
//        String chatId = message.getChatId().toString();
//
//        String response = null;
//        response = runCommand(message.getText());
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        final String localFileName = "src/main/java/task9_7_1_part2/" + "cloned_image.jpg";
//        PhotoSize photoSize = message.getPhoto().get(0);
//        response = runCommand(message.getText());
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);
//        } catch (TelegramApiException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            processingImage(localFileName);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        SendPhoto sendPhoto = preparePhotoMessage2(localFileName, message.getChatId().toString());
//        SendPhoto sendPhoto2 = preparePhotoMessage(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto.setChatId(message.getChatId().toString());
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localFileName));
//        sendPhoto.setPhoto(newFile);
//        sendPhoto.setCaption("cloned_image");
//
//        try {
//            execute(sendPhoto);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String runCommand(String text)/* throws InvocationTargetException, IllegalAccessException*/ {
//
//
//
//
//        // TODO Далее изначальные строки только этого метода
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                if (annotation.name().equals(text)) {
//                    try {
//                        method.setAccessible(true);
//                        return (String) method.invoke(commands);
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return "Команда не из кнопки";
//    }
//
//
//    // TODO Этого метода не было в ИЗНВЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private SendMessage runCommonCommand(Message message2) throws InvocationTargetException, IllegalAccessException {
//        String text2 = message2.getText();
//        BotCommonCommands commands2 = new BotCommonCommands();
//        Method[] classMethods2 = commands2.getClass().getDeclaredMethods();
//        for (Method method : classMethods2) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
//                if (command.name().equals(text2)) {
//                    method.setAccessible(true);
//                    String responseText2 = (String) method.invoke(commands2);
//                    if (responseText2 != null) {
//                        SendMessage sendMessage2 = new SendMessage();
//                        sendMessage2.setChatId(message2.getChatId().toString());
//                        sendMessage2.setText(responseText2);
//                        return sendMessage2;
//                    }
//                }
//            }
//        }
//
//        return null;
//    }
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private SendPhoto runPhotoFilter (Message message2) {
//        List<org.telegram.telegrambots.meta.api.objects.File> files2 = getFilesByMessage(message2);
//        try {
//            List<String> paths2 = PhotoMessageUtils.savePhotos(files2, getBotToken());
////            ArrayList<String> photoPaths = new ArrayList<>(paths2);
//            for (String path2 : paths2) {
//                PhotoMessageUtils.processingImage(path2);
//                SendPhoto photo2 = preparePhotoMessage2(path2, message2.getChatId().toString());
//                photo2.
//                        execute(photo2);  // Здесь ссылка на этот метод: preparePhotoMessage2 (с двойкой)
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message2) { // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Так было изначально
//        List<PhotoSize> photoSizes = message2.getPhoto();
//        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Так было изначально
//        for (PhotoSize photoSize : photoSizes){
//            final String fileId = photoSize.getFileId();
//            try {
//                files2.add(sendApiMethod(new GetFile(fileId)));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        return files2;
//    }
//
//
//
//
//
//
//
//    // TODO ДАЛЕЕ ВСЕ ИЗНАЧАЛЬНЫЕ МЕТОДЫ
//
//    private void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//
//    private SendPhoto preparePhotoMessage2(String localPath, String chatId) {
//        SendPhoto sendPhoto = new SendPhoto();
//        sendPhoto.setReplyMarkup(getKeyboard()); // Это две кнопки
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new java.io.File(localPath)); // TODO Здесь заменил File на java.io.File как в видеоуроке на мин 10 22
////        newFile.setMedia(new File(localPath)); // TODO Так было изначально
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//
//    }
//
//    private SendPhoto preparePhotoMessage(String localFileName, String chatId) {
//        SendPhoto sendPhoto2 = new SendPhoto();
//        sendPhoto2.setPhoto(new InputFile(new File(localFileName)));
//        sendPhoto2.setChatId(chatId);
//        return sendPhoto2;
//    }
//
//    private ReplyKeyboardMarkup getKeyboard() {
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        ArrayList<KeyboardRow> allKeyboardRows = new ArrayList<>();
//        allKeyboardRows.addAll(getKeyboardRows(BotCommonCommands.class));
//        allKeyboardRows.addAll(getKeyboardRows(FilterOperation.class));
//
//        replyKeyboardMarkup.setKeyboard(allKeyboardRows);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        return replyKeyboardMarkup;
//    }
//
//    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
//        Method[] classMethods = someClass.getDeclaredMethods();
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                KeyboardButton button = new KeyboardButton();
//                button.setText(annotation.name());
//                row.add(button);
//            }
//        }
//
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//}
//// КОНЕЦ ПРИМЕРА 5




//// ПРИМЕР 4 _Промежуточный. Всё работает, создаются три кнопки и обрабатывается одно изображения. До 10 58 мин в видео. До создания метода SendPhoto runPhotoFilter
//// Дорабатываем Пример 3 _РАБОТАЕТ! Создаёт 3 кнопки и обрабатывает их, и создаёт лишнюю надпись "Команда не из кнопки".
//// И обрабатывает только одно изображение _Использован бот №2
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import task9_7_1_part2.commands.AppBotCommand;
//import task9_7_1_part2.commands.BotCommonCommands;
//import task9_7_1_part2.functions.FilterOperation;
//
//import java.io.*;
//        import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import static task9_7_1.utils.PhotoMessageUtils.processingImage;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[] {BotCommonCommands.class};
//
//    @Override
//    public String getBotUsername() {
//        return "lgcyrrerrr_002_bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message2 = update.getMessage();
//        try {
//            SendMessage responseTextMessage2 = runCommonCommand(message2);
//            if (responseTextMessage2 != null) {
//                execute(responseTextMessage2);
//                return;
//            }
//        } catch (InvocationTargetException | IllegalAccessException | TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        // TODO Далее изначальные строки только этого метода
//        Message message = update.getMessage();
//        String chatId = message.getChatId().toString();
//
//        String response = null;
//        response = runCommand(message.getText());
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        final String localFileName = "src/main/java/task9_7_1_part2/" + "cloned_image.jpg";
//        PhotoSize photoSize = message.getPhoto().get(0);
//        response = runCommand(message.getText());
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);
//        } catch (TelegramApiException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            processingImage(localFileName);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        SendPhoto sendPhoto = preparePhotoMessage2(localFileName, message.getChatId().toString());
//        SendPhoto sendPhoto2 = preparePhotoMessage(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto.setChatId(message.getChatId().toString());
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localFileName));
//        sendPhoto.setPhoto(newFile);
//        sendPhoto.setCaption("cloned_image");
//
//        // TODO Проверяем, вернётся или нет одно изображение в ПРИМЕРЕ 4, в методе onUpdateReceived
//        try {
//            execute(sendPhoto);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String runCommand(String text)/* throws InvocationTargetException, IllegalAccessException*/ {
//
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                if (annotation.name().equals(text)) {
//                    try {
//                        method.setAccessible(true);
//                        return (String) method.invoke(commands);
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return "Команда не из кнопки";
//    }
//
//    // TODO Этого метода не было в ИЗНВЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private SendMessage runCommonCommand(Message message2) throws InvocationTargetException, IllegalAccessException {
//        String text2 = message2.getText();
//        BotCommonCommands commands2 = new BotCommonCommands();
//        Method[] classMethods2 = commands2.getClass().getDeclaredMethods();
//        for (Method method : classMethods2) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
//                if (command.name().equals(text2)) {
//                    method.setAccessible(true);
//                    String responseText2 = (String) method.invoke(commands2);
//                    if (responseText2 != null) {
//                        SendMessage sendMessage2 = new SendMessage();
//                        sendMessage2.setChatId(message2.getChatId().toString());
//                        sendMessage2.setText(responseText2);
//                        return sendMessage2;
//                    }
//                }
//            }
//        }
//
//        return null;
//    }
//
//    // TODO Этого метода не было в ИЗНАЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Так было изначально
//        List<PhotoSize> photoSizes = message.getPhoto();
//        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Так было изначально
//        for (PhotoSize photoSize : photoSizes){
//            final String fileId = photoSize.getFileId();
//            try {
//                files2.add(sendApiMethod(new GetFile(fileId)));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        return files2;
//    }
//
//    // TODO ДАЛЕЕ ВСЕ ИЗНАЧАЛЬНЫЕ МЕТОДЫ
//    private void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//
//    private SendPhoto preparePhotoMessage2(String localPath, String chatId) {
//        SendPhoto sendPhoto = new SendPhoto();
//// TODO НАШЁЛ! ЭТА СТРОКА ОТВЕЧАЕТ ЗА СОЗДАНИЕ ТРЁХ КНОПОК в ПРИМЕРЕ 4 в методе SendPhoto preparePhotoMessage2
//        sendPhoto.setReplyMarkup(getKeyboard()); // Это ТРИ кнопки  // TODO в ПРИМЕРЕ 4 В методе preparePhotoMessage2. ДА! При комменте три кнопки не создадутся!
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new java.io.File(localPath)); // TODO Здесь заменил File на java.io.File как в видеоуроке на мин 10 22
////        newFile.setMedia(new File(localPath)); // TODO Так было изначально
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//
//    }
//
//    private SendPhoto preparePhotoMessage(String localFileName, String chatId) {
//        SendPhoto sendPhoto2 = new SendPhoto();
//        sendPhoto2.setPhoto(new InputFile(new File(localFileName)));
//        sendPhoto2.setChatId(chatId);
//        return sendPhoto2;
//    }
//
//    //    TODO Далее в методах getKeyboard и getKeyboardRows создаются три кнопки: "/help", "/hello", "/bye"
//    private ReplyKeyboardMarkup getKeyboard() {
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        ArrayList<KeyboardRow> allKeyboardRows = new ArrayList<>();
//        allKeyboardRows.addAll(getKeyboardRows(BotCommonCommands.class));
//        allKeyboardRows.addAll(getKeyboardRows(FilterOperation.class));
//
//        replyKeyboardMarkup.setKeyboard(allKeyboardRows);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        return replyKeyboardMarkup;
//    }
//
//    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
//        Method[] classMethods = someClass.getDeclaredMethods();
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                KeyboardButton button = new KeyboardButton();
//                button.setText(annotation.name());
//                row.add(button);
//            }
//        }
//
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//}
//// КОНЕЦ ПРИМЕРА 4




//// ПРИМЕР 3 _РАБОТАЕТ! Создаёт 3 кнопки и обрабатывает их, и создаёт лишнюю надпись "Команда не из кнопки".
//// И обрабатывает только одно изображение _Использован бот №2
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import task9_7_1.commands.AppBotCommand;
//import task9_7_1.commands.BotCommonCommands;
//import task9_7_1.functions.FilterOperation;
//
//import java.io.*;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//
//import static task9_7_1.utils.PhotoMessageUtils.processingImage;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[] {BotCommonCommands.class};
//
//    @Override
//    public String getBotUsername() {
//        return "lgcyrrerrr_002_bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message = update.getMessage();
//        String chatId = message.getChatId().toString();
//
//        String response = null;
//        response = runCommand(message.getText());
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        final String localFileName = "src/main/java/task9_7_1_part2/" + "cloned_image.jpg";
//        PhotoSize photoSize = message.getPhoto().get(0);
//        response = runCommand(message.getText());
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);
//        } catch (TelegramApiException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            processingImage(localFileName);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        SendPhoto sendPhoto = preparePhotoMessage(localFileName, message.getChatId().toString());
//        SendPhoto sendPhoto2 = preparePhotoMessage2(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto.setChatId(message.getChatId().toString());
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localFileName));
//        sendPhoto.setPhoto(newFile);
//        sendPhoto.setCaption("cloned_image");
//
//        try {
//            execute(sendPhoto);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String runCommand(String text) {
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                if (annotation.name().equals(text)) {
//                    try {
//                        method.setAccessible(true);
//                        return (String) method.invoke(commands);
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return "Команда не из кнопки";
//    }
//
//    private void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//
//    private SendPhoto preparePhotoMessage(String localPath, String chatId) {
//        SendPhoto sendPhoto = new SendPhoto();
//        sendPhoto.setReplyMarkup(getKeyboard()); // Это две кнопки
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath));
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//
//    }
//
//    private SendPhoto preparePhotoMessage2(String localFileName, String chatId) {
//        SendPhoto sendPhoto2 = new SendPhoto();
//        sendPhoto2.setPhoto(new InputFile(new File(localFileName)));
//        sendPhoto2.setChatId(chatId);
//        return sendPhoto2;
//    }
//
//    private ReplyKeyboardMarkup getKeyboard() {
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        ArrayList<KeyboardRow> allKeyboardRows = new ArrayList<>();
//        allKeyboardRows.addAll(getKeyboardRows(BotCommonCommands.class));
//        allKeyboardRows.addAll(getKeyboardRows(FilterOperation.class));
//
//        replyKeyboardMarkup.setKeyboard(allKeyboardRows);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        return replyKeyboardMarkup;
//    }
//
//    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
//        Method[] classMethods = someClass.getDeclaredMethods();
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                KeyboardButton button = new KeyboardButton();
//                button.setText(annotation.name());
//                row.add(button);
//            }
//        }
//
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//}
//// КОНЕЦ ПРИМЕРА 3




//// ПРИМЕР 2 _Это изначальный код, взятый из task9_5_1 _Всё работает, создаётся 9 кнопок (5+4)
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//import java.io.*;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//
//import static task9_7_1.utils.PhotoMessageUtils.processingImage;
//
//public class Bot extends TelegramLongPollingBot {
//
//    @Override
//    public String getBotUsername() {
//        return "lgcyrrerrr_002_bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        final String localFileName = "src/main/java/task9_7_1_part2/" + "cloned_image.jpg";
//        Message message = update.getMessage();
//        PhotoSize photoSize = message.getPhoto().get(0);
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);
//        } catch (TelegramApiException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            processingImage(localFileName);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        // TODO Если закомментировать следующий фрагмент кода в Примере 2, до конца метода onUpdateReceived, т.е. до e.printStackTrace(); },
//        //  то одно изображение не будет возвращаться. ПРОВЕРЕНО! Работает.
//        SendPhoto sendPhoto = preparePhotoMessage(localFileName, message.getChatId().toString()); //  TODO Закомментировать, чтобы не возвращалось одно фото
//        ///
//        sendPhoto.setChatId(message.getChatId().toString()); //  TODO Закомментировать, чтобы не возвращалось одно фото
//        InputFile newFile = new InputFile(); //  TODO Закомментировать, чтобы не возвращалось одно фото
//        newFile.setMedia(new File(localFileName)); //  TODO Закомментировать, чтобы не возвращалось одно фото
//        sendPhoto.setPhoto(newFile); //  TODO Закомментировать, чтобы не возвращалось одно фото
//        sendPhoto.setCaption("cloned_image"); //  TODO Закомментировать, чтобы не возвращалось одно фото
//
//// TODO НАШЁЛ! Для невозврата одного фото. В ПРИМЕРЕ 2 Вот ЭТО ЗАКОММЕНТИРОВАТЬ в методе onUpdateReceived(Update update), и не будет возвращать одно изображение
//        try { //  TODO Закомментировать, чтобы не возвращалось одно фото
//            execute(sendPhoto); //  TODO Закомментировать, чтобы не возвращалось одно фото
//        } catch (TelegramApiException e) { //  TODO Закомментировать, чтобы не возвращалось одно фото
//            e.printStackTrace(); //  TODO Закомментировать, чтобы не возвращалось одно фото
//        } //  TODO Закомментировать, чтобы не возвращалось одно фото
//    }
//
//    private void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//
//    // TODO Здесь, в методе preparePhotoMessage, девять кнопок для клавиатуры создаются в двойном цикле,
//    //  где каждая кнопка определяется на основе условий rowIndex и columnIndex
//    private SendPhoto preparePhotoMessage(String localPath, String chatId) {
//        SendPhoto sendPhoto = new SendPhoto();
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();  //  TODO отсюда 240522 12.37 Закомментировать, чтобы 9 кнопок не возвращалось
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        int rowCount = 3;
//        int columnCount = 3;
//        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
//            KeyboardRow row = new KeyboardRow();
//            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
//                if (rowIndex == 0 && columnIndex == 0) {
//                    try {
//                        Class<?> filterOperationClass = Class.forName("task9_7_1_part2.functions.FilterOperation");
//                        String methodName = "greyScale";
//                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
//                        float[] rgbArray = new float[3]; // Creating an RGB array
//                        Object result = method.invoke(null, rgbArray);
//                        KeyboardButton keyboardButton1 = new KeyboardButton(methodName);
//                        row.add(keyboardButton1);
//                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
//                        ex.printStackTrace();
//                    }
//
//                } else if (rowIndex == 0 && columnIndex == 1) {
//                    try {
//                        Class<?> filterOperationClass = Class.forName("task9_7_1_part2.functions.FilterOperation");
//                        String methodName = "onlyRed";
//                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
//                        float[] rgbArray = new float[3]; // Creating an RGB array
//                        Object result = method.invoke(null, rgbArray);
//                        KeyboardButton keyboardButton2 = new KeyboardButton(methodName);
//                        row.add(keyboardButton2);
//                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
//                        ex.printStackTrace();
//                    }
//
//                } else if (rowIndex == 0 && columnIndex == 2) {
//                    try {
//                        Class<?> filterOperationClass = Class.forName("task9_7_1_part2.functions.FilterOperation");
//                        String methodName = "onlyGreen";
//                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
//                        float[] rgbArray = new float[3]; // Creating an RGB array
//                        Object result = method.invoke(null, rgbArray);
//                        KeyboardButton keyboardButton3 = new KeyboardButton(methodName);
//                        row.add(keyboardButton3);
//                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
//                        ex.printStackTrace();
//                    }
//
//                } else if (rowIndex == 1 && columnIndex == 0) {
//                    try {
//                        Class<?> filterOperationClass = Class.forName("task9_7_1_part2.functions.FilterOperation");
//                        String methodName = "onlyBlue";
//                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
//                        float[] rgbArray = new float[3]; // Creating an RGB array
//                        Object result = method.invoke(null, rgbArray);
//                        KeyboardButton keyboardButton4 = new KeyboardButton(methodName);
//                        row.add(keyboardButton4);
//                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
//                        ex.printStackTrace();
//                    }
//
//                } else if (rowIndex == 1 && columnIndex == 1) {
//                    try {
//                        Class<?> filterOperationClass = Class.forName("task9_7_1_part2.functions.FilterOperation");
//                        String methodName = "sepia";
//                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
//                        float[] rgbArray = new float[3]; // Creating an RGB array
//                        Object result = method.invoke(null, rgbArray);
//                        KeyboardButton keyboardButton5 = new KeyboardButton(methodName);
//                        row.add(keyboardButton5);
//                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
//                        ex.printStackTrace();
//                    }
//
//                } else {
//                    KeyboardButton keyboardButton = new KeyboardButton("button" + (rowIndex*3+columnIndex+1));
//                    row.add(keyboardButton);
//                }
//            }
//            keyboardRows.add(row);
//        }
//        replyKeyboardMarkup.setKeyboard(keyboardRows);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);  //  TODO досюда 240522 12.37 Закомментировать, чтобы 9 кнопок не возвращалось
//
//        sendPhoto.setChatId(chatId); //  TODO Закомментировать, чтобы не возвращалось одно фото
//        sendPhoto.setReplyMarkup(replyKeyboardMarkup); //  TODO НАШЁЛ! ИМЕННО ЭТА СТРОКА ОТВЕЧАЕТ ЗА СОЗДАНИЕ 9-ти КНОПОК! Если её закомментировать, 9 кнопок не создадутся!
//        InputFile newFile = new InputFile(); //  TODO Закомментировать, чтобы не возвращалось одно фото
//        newFile.setMedia(new File(localPath)); //  TODO Закомментировать, чтобы не возвращалось одно фото
//        sendPhoto.setPhoto(newFile); //  TODO Закомментировать, чтобы не возвращалось одно фото
//        return sendPhoto; //  TODO ВОЗМОЖНО, А МОЖЕТ И НЕТ! Закомментировать, чтобы не возвращалось одно фото. НЕ НАДО КОММЕНТИРОВАТЬ, ФОТО НЕ ВОЗВРАЩАЕТСЯ
//    }
//}
//// КОНЕЦ ПРИМЕРА 2