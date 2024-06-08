package task9_7_1_part6_KakVVideo;

//// ПРИМЕР 0 _ТОЧНО, КАК В ВИДЕОУРОКЕ. ВСЁ РАБОТАЕТ. ВОЗВРАЩАЮТСЯ (И СОХРАНЯЮТСЯ) 4 ИЗОБРАЖЕНИЯ И ОТВЕТЫ НА КОМАНДЫ. НО КНОПКИ НЕ СОЗДАЮТСЯ
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
import task9_7_1_part6_KakVVideo.commands.BotCommonCommands;
import task9_7_1_part6_KakVVideo.commands.AppBotCommand;
import task9_7_1_part6_KakVVideo.functions.FilterOperation;
import task9_7_1_part6_KakVVideo.functions.ImageOperation;
import task9_7_1_part6_KakVVideo.utils.PhotoMessageUtils;

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
        Message message = update.getMessage();
        try {
            SendMessage responseTestMessage = runCommonCommand(message);
            if (responseTestMessage != null) {
                execute(responseTestMessage);
                return;
            }
        } catch (InvocationTargetException | IllegalAccessException | TelegramApiException e) {
            e.printStackTrace();
        }
        try {
            SendMediaGroup responseMediaMessage = runPhotoFilter(message);
            if (responseMediaMessage != null) {
                execute(responseMediaMessage);
                return;
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


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
//            inputMedia.setNewMediaFile(new java.io.File(path));
            inputMedia.setMedia(new java.io.File(path), "path");
            medias.add(inputMedia);
        }
        mediaGroup.setMedias(medias);
        mediaGroup.setChatId(chatId);
        return mediaGroup;
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

    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
        Method[] classMethods = someClass.getDeclaredMethods();
        ArrayList<AppBotCommand> commands = new ArrayList<>();
        for (Method method : classMethods) {
            if (method.isAnnotationPresent(AppBotCommand.class)) {
                commands.add(method.getAnnotation(AppBotCommand.class));
            }
        }
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        int columnCount = 3;
        int rowsCount = commands.size() / columnCount + ((commands.size() % columnCount == 0) ? 0 : 1);
        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
            KeyboardRow row = new KeyboardRow();
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                int index = rowIndex * columnCount + columnIndex;
                if (index >= commands.size()) continue;
                AppBotCommand command = commands.get(rowIndex * columnCount + columnIndex);
                KeyboardButton keyboardButton = new KeyboardButton(command.name());
                row.add(keyboardButton);
            }
            keyboardRows.add(row);
        }
        return keyboardRows;
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
//// КОНЕЦ ПРИМЕРа 0
