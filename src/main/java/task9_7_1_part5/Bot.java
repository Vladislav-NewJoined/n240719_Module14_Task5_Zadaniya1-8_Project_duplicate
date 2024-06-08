package task9_7_1_part5;

//// ПРИМЕР _ ОБРАЗЕЦ. Присылает одно лишнее цветное изображение. Осталось от него избавиться.
//// надо менять метод private SendPhoto preparePhotoMessage2(String localPath, String chatId) - с двумя параметрами
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
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
import task9_7_1_part5.commands.BotCommonCommands;
import task9_7_1_part5.commands.AppBotCommand;
import task9_7_1_part5.functions.FilterOperation;
import task9_7_1_part5.functions.ImageOperation;
import task9_7_1_part5.utils.PhotoMessageUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    Class[] commandClasses = new Class[]{BotCommonCommands.class};

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
            SendMessage responseTextMessage2 = runCommonCommand(message2);
            if (responseTextMessage2 != null) {
                execute(responseTextMessage2);     // TODO ОСТАВЛЯЕМ! ответ на кнопки help hello bye, вызываем библиотечный класс SendMessage
                return;
            }
        } catch (InvocationTargetException | IllegalAccessException | TelegramApiException e) {
            e.printStackTrace();
        }

        Message message = update.getMessage();
        String chatId = message.getChatId().toString();

        String response = null;
        response = runCommand(message.getText());     // TODO ОСТАВЛЯЕМ! возврат изображениий (1-го и 4-х), вызывает метод runCommand
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(response);
        final String localFileName = "src/main/java/task9_7_1_part5/" + "cloned_image.jpg";
        PhotoSize photoSize = message.getPhoto().get(0);
        response = runCommand(message.getText());
        final String fileId = photoSize.getFileId();
        try {
            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
            saveImage(imageUrl, localFileName);     // TODO ОСТАВЛЯЕМ! ответ на кнопки и возврат изображений и сохранение их, вызов метода saveImage из класса preparePhotoMessage2
        } catch (TelegramApiException | IOException e) {
            throw new RuntimeException(e);
        }

        InputFile newFile = new InputFile();
        newFile.setMedia(new File(localFileName));

        sendMessage.setChatId(chatId);
        sendMessage.setText(response);

        try {
            execute(sendMessage);     // TODO ВНИМАНИЕ: ОСТАВЛЯЕМ, но с оговоркой!  отменило надпись: "Команда не из кнопки" НАДО ПЕРЕНЕСТИ на возврат неск. изображений
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        SendPhoto sendPhoto = preparePhotoMessage2(localFileName, message.getChatId().toString());
        ///
        sendPhoto.setPhoto(newFile);      // TODO ОСТАВЛЯЕМ!

        try {
            execute(sendPhoto);     // TODO ВАЖНО: ХОРОШО! УДАЛЯЕМ применит. к неск. изображениям! ЭТО ОБРАЩЕНИЕ К КЛАССУ preparePhotoMessage2 С ДВУМЯ АРГУМЕНТАМИ! Без него не возвращает одно изображение, на кнопки отвечает, кнопки правда НЕ СОЗДАЁТ, возвращает неск. изображений (но без надписи "cloned image), с ним не отвечает на кнопки! без него сохранило, но не вернуло одно ЦВЕТНОЕ изображение, вызов метода preparePhotoMessage с двумя параметрами
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        // TODO После этого:
        // TODO Возвращает надпись "Команда не из кнопки"
        // TODO Создаёт 4 кнопки, отвечает на них
        // TODO Возвращает 4 изображения, сохраняет их
        // TODO Возвращает надпись "cloned_image", только не в том месте
        // TODO ОТРИЦАТЕЛЬНЫЙ: Возвращает одно ЦВЕТНОЕ изображение



// Пишу для 7-го Примера
        try {
            SendMediaGroup responseMediaMessage2 = runPhotoFilter(message2);
            if (responseMediaMessage2 != null) {
                execute(responseMediaMessage2);     // TODO ОСТАВЛЯЕМ! т.к. без него не возвращает и не сохраняет неск. изображений. Кнопки создаёт, отвечает, НО возвращает ЦВЕТНОЕ изобр.
                return;
            }
        } catch (/*InvocationTargetException | IllegalAccessException | */TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private SendPhoto preparePhotoMessage2(String localPath, String chatId) {
//// TODO НАШЁЛ! ЭТА СТРОКА ОТВЕЧАЕТ ЗА СОЗДАНИЕ ТРЁХ КНОПОК в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6, в методе SendPhoto preparePhotoMessage2
        SendPhoto sendPhoto = new SendPhoto();
// TODO НАШЁЛ! ЭТА СТРОКА ОТВЕЧАЕТ ЗА СОЗДАНИЕ ТРЁХ КНОПОК в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6, в методе SendPhoto preparePhotoMessage2
        sendPhoto.setReplyMarkup(getKeyboard()); // TODO Это ТРИ кнопки; В ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6 В методе preparePhotoMessage2 с двумя параметрами. ДА! При комменте три кнопки не создадутся!
        sendPhoto.setChatId(chatId);
        InputFile newFile = new InputFile();
        newFile.setMedia(new File(localPath)); // TODO Здесь заменил File на java.io.File как в видеоуроке на мин 10 22
//        newFile.setMedia(new File(localPath)); // TODO Так было изначально
        sendPhoto.setPhoto(newFile);
        return sendPhoto;
    }

    // TODO 240529 1438 Здесь искать создание кнопок, Команда не из кнопки, возврат 1-го цветного изображения, cloned_image
    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
        Method[] classMethods = someClass.getDeclaredMethods();
        ArrayList<AppBotCommand> commands = new ArrayList<>();
        for (Method method : classMethods) {
            AppBotCommand commandAnnotation = method.getAnnotation(AppBotCommand.class);
            if (commandAnnotation != null) {
                commands.add(commandAnnotation);
            }
        }
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        int columnCount = 3;
        int rowsCount = commands.size() / columnCount + ((commands.size() % columnCount == 0) ? 0 : 1);
        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
            KeyboardRow row = new KeyboardRow();
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                int index = rowIndex * columnCount + columnIndex;
                if (index >= commands.size()) continue;  // TODO 240529 1605 Ни на что не влияет
                AppBotCommand command = commands.get(index);
                KeyboardButton keyboardButton = new KeyboardButton(command.name());
                row.add(keyboardButton);
            }
            keyboardRows.add(row);
        }
        return keyboardRows;
    }


// TODO 240529 1142 Ниже - это было до того как начал приводить в порядок ЗАПИСАННОЕ ВСЛЕД ЗА ВИДЕОУРОКОМ. Выше - это добавленное вновь.
// TODO ВСЁ ЧТО НИЖЕ ЗАКОММЕНТИРОВАНО, В ИЗНАЧАЛЬНОМ ВАРИАНТЕ БЫЛО РАСКОММЕНТИРОВАНО


    private String runCommand(String text)/* throws InvocationTargetException, IllegalAccessException*/ {
        BotCommonCommands commands = new BotCommonCommands();
        Method[] classMethods = commands.getClass().getDeclaredMethods();

        for (Method method : classMethods) {
            if (method.isAnnotationPresent(AppBotCommand.class)) {
                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
                if (annotation.name().equals(text)) {
                    try {
                        method.setAccessible(true);     // TODO ОСТАВЛЯЕМ! Т.к. без него в консоли появляются красные строки, но на телеграм бот не влияет
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
    private SendMessage runCommonCommand(Message message) throws InvocationTargetException, IllegalAccessException {
        String text = message.getText();
        BotCommonCommands commands = new BotCommonCommands();
        Method[] classMethods = commands.getClass().getDeclaredMethods();
        for (Method method : classMethods) {
            if (method.isAnnotationPresent(AppBotCommand.class)) {
                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
                if (command.name().equals(text)) {
                    method.setAccessible(true);
                    String responseText = (String) method.invoke(commands);
                    if (responseText != null) {
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(message.getChatId().toString());
                        sendMessage.setText(responseText);
                        return sendMessage;
                    }
                }
            }
        }
        return null;
    }

    private SendMediaGroup runPhotoFilter(Message message) {
        ImageOperation operation = FilterOperation::greyScale;
        List<org.telegram.telegrambots.meta.api.objects.File> files = getFilesByMessage(message);       //  TODO ВОЗМОЖНО НАДО СДЕЛАТЬ ПРОСТО File
//        List<org.telegram.telegrambots.meta.api.objects.File> files = getFilesByMessage(message);
        try {
            List<String> paths = PhotoMessageUtils.savePhotos(files, getBotToken());       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
            String chatId = message.getChatId().toString();       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
            return preparePhotoMessage(paths, operation, chatId);       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Так было изначально
        List<PhotoSize> photoSizes = message.getPhoto();
        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files = new ArrayList<>(); // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
//        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Так было изначально
        for (PhotoSize photoSize : photoSizes) {
            final String fileId = photoSize.getFileId();
            try {
                files.add(sendApiMethod(new GetFile(fileId)));       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        return files;
    }

    private SendMediaGroup preparePhotoMessage(List<String> localPaths, ImageOperation operation, String chatId) throws Exception {
        SendMediaGroup mediaGroup = new SendMediaGroup();
        ArrayList<InputMedia> medias = new ArrayList<>();
        for (String path : localPaths) {
            InputMedia inputMedia = new InputMediaPhoto();
            PhotoMessageUtils.processingImage(path, operation);
            inputMedia.setMedia(new java.io.File(path), "path");
            medias.add(inputMedia);
        }
        mediaGroup.setMedias(medias);
        mediaGroup.setChatId(chatId);

        SendMediaGroup mediaGroup2 = new SendMediaGroup();
        ArrayList<InputMedia> medias2 = new ArrayList<>();
        for (String path : localPaths) {
            InputMediaPhoto inputMedia = new InputMediaPhoto();
            PhotoMessageUtils.processingImage(path, operation);
            inputMedia.setMedia(new File(path), "path");
            medias2.add(inputMedia);
        }
        mediaGroup2.setMedias(medias2);
        mediaGroup2.setChatId(chatId);

        // Отправка текстового сообщения "clonedimages"
        SendMessage sendClonedImageMessage = new SendMessage();
        sendClonedImageMessage.setChatId(chatId);
        sendClonedImageMessage.setText("Ниже Вам отправлены обработанные изображения:");
        execute(sendClonedImageMessage);
        return mediaGroup2;
    }

    private ReplyKeyboardMarkup getKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> allKeyboardRows = new ArrayList<>();
        allKeyboardRows.addAll(getKeyboardRows(BotCommonCommands.class));
        allKeyboardRows.addAll(getKeyboardRows(FilterOperation.class));

        replyKeyboardMarkup.setKeyboard(allKeyboardRows);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }


    private void saveImage(String url, String fileName) throws IOException {
        URL urlModel = new URL(url);
        InputStream inputStream = urlModel.openStream();
        OutputStream outputStream = new FileOutputStream(fileName);
        byte[] b = new byte[2048];
        int length;
        while ((length = inputStream.read(b)) != -1) {
            outputStream.write(b, 0, length);         //  TODO ВНИМАНИЕ: ВСЁ ТАКИ ООСТАВЛЯЕМ, т.к. без него кнопки не создаёт!  УДАЛЯЕМ или? И без него кнопки не создаёт! ЗДЕСЬ ПРАВИЛЬНО: одно изобр. не возвращает, группу - возвращает. Но не возвращает надпись: "cloned_image"
        }
        inputStream.close();
        outputStream.close();
    }
}
//// КОНЕЦ ПРИМЕРА _
