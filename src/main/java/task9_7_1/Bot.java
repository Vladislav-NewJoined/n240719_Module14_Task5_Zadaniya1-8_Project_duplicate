package task9_7_1;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import task9_7_1.commands.AppBotCommand;
import task9_7_1.commands.BotCommonCommands;
import task9_7_1.functions.FilterOperation;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;

import static task9_7_1.utils.PhotoMessageUtils.processingImage;

public class Bot extends TelegramLongPollingBot {

    Class[] commandClasses = new Class[] {BotCommonCommands.class};

    @Override
    public String getBotUsername() {
        return "jhyvfhek_bot"; // Название вашего бота
    }

    @Override
    public String getBotToken() {
        return "7066205045:AAGzacbZfn8y-dOJ1NnWJHRH9HdPxYQpFPU"; // Токен вашего бота
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();

        String response = null;
        response = runCommand(message.getText());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(response);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        final String localFileName = "src/main/java/task9_7_1/" + "cloned_image.jpg";
        PhotoSize photoSize = message.getPhoto().get(0);
        response = runCommand(message.getText());
        final String fileId = photoSize.getFileId();
        try {
            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
            saveImage(imageUrl, localFileName);
        } catch (TelegramApiException | IOException e) {
            throw new RuntimeException(e);
        }

        try {
            processingImage(localFileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        SendPhoto sendPhoto = preparePhotoMessage(localFileName, message.getChatId().toString());
        SendPhoto sendPhoto2 = preparePhotoMessage2(localFileName, message.getChatId().toString());
        ///
        sendPhoto.setChatId(message.getChatId().toString());
        InputFile newFile = new InputFile();
        newFile.setMedia(new File(localFileName));
        sendPhoto.setPhoto(newFile);
        sendPhoto.setCaption("cloned_image");

        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        sendMessage.setChatId(chatId);
        sendMessage.setText(response);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }

    private String runCommand(String text) {
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

    private SendPhoto preparePhotoMessage(String localPath, String chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setReplyMarkup(getKeyboard()); // Это две кнопки
        sendPhoto.setChatId(chatId);
        InputFile newFile = new InputFile();
        newFile.setMedia(new File(localPath));
        sendPhoto.setPhoto(newFile);
        return sendPhoto;

    }

    private SendPhoto preparePhotoMessage2(String localFileName, String chatId) {
        SendPhoto sendPhoto2 = new SendPhoto();
        sendPhoto2.setPhoto(new InputFile(new File(localFileName)));
        sendPhoto2.setChatId(chatId);
        return sendPhoto2;
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




//// ПРИМЕР 15 _РАБОТАЕТ! Создаёт 3 кнопки и обрабатывает их, и создаёт лишнюю надпись "Команда не из кнопки".
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
//        import java.lang.reflect.InvocationTargetException;
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
//        return "jhyvfhek_bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7066205045:AAGzacbZfn8y-dOJ1NnWJHRH9HdPxYQpFPU"; // Токен вашего бота
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
//        final String localFileName = "src/main/java/task9_7_1/" + "cloned_image.jpg";
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
//
//
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
//
//}
//// КОНЕЦ ПРИМЕРА 15




//// ПРИМЕР 14 _Совместил создание кнопок и обработку изображений. Не работает. Изображение обрабатывает,
//// но команды не выполняет. _Совместил с ПРИМЕРОМ 10, там отвечает на команды.
//// В следующем примере надо исправлять.
//// _Создались три кнопки (они не обрабатываются) и одно изображение обрабатывает
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
//        import java.lang.reflect.InvocationTargetException;
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
//        return "qytewqwww_Bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        final String localFileName = "src/main/java/task9_7_1/" + "cloned_image.jpg";
//        Message message = update.getMessage();
//        PhotoSize photoSize = message.getPhoto().get(0);
//        String chatId = message.getChatId().toString();
//        String response = null;
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
//        SendMessage sendMessage = new SendMessage();
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
//        return "Unknown command";
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
//    }
//
//    private ReplyKeyboardMarkup getKeyboard() {  // две кнопки
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        ArrayList<KeyboardRow> allKeyboardRows = new ArrayList<>();  // 2 кнопки
//        allKeyboardRows.addAll(getKeyboardRows(BotCommonCommands.class)); // 2 кнопки
//        allKeyboardRows.addAll(getKeyboardRows(FilterOperation.class)); // 2 кнопки
//        replyKeyboardMarkup.setKeyboard(allKeyboardRows);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        return replyKeyboardMarkup;
//    }
//
//    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
//        Method[] classMethods = someClass.getDeclaredMethods(); // заменим getMethods() на getDeclaredMethods()
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                KeyboardButton button = new KeyboardButton();
//                button.setText(annotation.name());
//                row.add(button);
//
//                if (annotation.showInKeyboard()) {  // Check if method should be displayed in keyboard
//                    KeyboardRow helpRow = new KeyboardRow();
//                    KeyboardButton helpButton = new KeyboardButton("/help");
//                    helpRow.add(helpButton);
//                    keyboardRows.add(helpRow);
//                }
//            }
//        }
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//}
//// КОНЕЦ ПРИМЕРА 14




//// ПРИМЕР 13 _Создались три кнопки и одно изображение обрабатывает
////// _Создались кнопки, как У ПРЕПОДАВАТЕЛЯ!!! В 5 СТРОК, В 3 СТОЛБЦА!!!
////// _Восстановить строку 43 в PhotoMessageUtils
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
//    @Override
//    public String getBotUsername() {
//        return "qytewqwww_Bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        final String localFileName = "src/main/java/task9_7_1/" + "cloned_image.jpg";
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
//        SendPhoto sendPhoto = preparePhotoMessage(localFileName, message.getChatId().toString());
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
//
////        sendPhoto.setReplyMarkup(getKeyboard(FilterOperation.class));  // Это 12 кнопок
//        sendPhoto.setReplyMarkup(getKeyboard()); // Это две кнопки
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath));
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//
////        // Удалить создание клавиатуры с кнопками фильтров
////        // и оставить только отправку изображения без кнопок
////        sendPhoto.setChatId(chatId);
////        InputFile newFile = new InputFile();
////        newFile.setMedia(new File(localPath));
////        sendPhoto.setPhoto(newFile);
////        sendPhoto.setCaption("Choose a filter:");
////
////        return sendPhoto;
//    }
//
//    //    private ReplyKeyboardMarkup getKeyboard(Class someClass) { // 12 кнопок
//    private ReplyKeyboardMarkup getKeyboard() {  // две кнопки
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
////        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();  // 12 кнопок
//        ArrayList<KeyboardRow> allKeyboardRows = new ArrayList<>();  // 2 кнопки
////        Method[] methods = someClass.getMethods();  // 12 кнопок
////        int columnCount = 3;  // 12 кнопок
////        int rowsCount = methods.length / columnCount + ((methods.length % columnCount == 0) ? 0 : 1);  // 12 кнопок
////        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {  // 12 кнопок
////            KeyboardRow row = new KeyboardRow();  // 12 кнопок
////            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {  // 12 кнопок
////                int index = rowIndex * columnCount + columnIndex;  // 12 кнопок
////                if (index >= methods.length) continue;  // 12 кнопок
////                Method method = methods[rowIndex * columnCount + columnIndex];   // 12 кнопок
////                KeyboardButton keyboardButton = new KeyboardButton(method.getName());  // 12 кнопок
////                row.add(keyboardButton);  // 12 кнопок
////            }  // 12 кнопок
////            keyboardRows.add(  // 12 кнопокrow);
////        }  // 12 кнопок
////        replyKeyboardMarkup.setKeyboard(keyboardRows);  // 12 кнопок
////        replyKeyboardMarkup.setOneTimeKeyboard(true);  // 12 кнопок
//        allKeyboardRows.addAll(getKeyboardRows(BotCommonCommands.class)); // 2 кнопки
//        allKeyboardRows.addAll(getKeyboardRows(FilterOperation.class)); // 2 кнопки
//        replyKeyboardMarkup.setKeyboard(allKeyboardRows);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        return replyKeyboardMarkup;
//    }
//
//    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
//        Method[] classMethods = someClass.getDeclaredMethods(); // заменим getMethods() на getDeclaredMethods()
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
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//
//}
//// КОНЕЦ ПРИМЕРА 13




//// ПРИМЕР 12 _По итогу просмотра всего ролика 08. Не работает.
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
//import org.telegram.telegrambots.meta.api.objects.File;
//
//import task9_7_1.commands.AppBotCommand;
//import task9_7_1.commands.BotCommonCommands;
//import task9_7_1.functions.FilterOperation;
//import task9_7_1.functions.ImageOperation;
//import task9_7_1.utils.ImageUtils;
//import task9_7_1.utils.PhotoMessageUtils;
//import task9_7_1.utils.RgbMaster;
//
//import java.awt.image.BufferedImage;
//import java.io.*;
////import java.io.File;
//import java.lang.reflect.Array;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[] {BotCommonCommands.class};
//
//    @Override
//    public String getBotUsername() {
//        return "qytewqwww_Bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message = update.getMessage();
//        SendMessage responseTextMessage = runCommonCommand(message);
//        if (responseTextMessage != null) {
//            try {
//                execute(responseTextMessage);
//            } catch (TelegramApiException e) {
//                throw new RuntimeException(e);
//            }
//            return;
//        }
//
//        try {
//            SendMediaGroup responseMediaMessage = runPhotoFilter(message);
//            if (responseMediaMessage != null) {
//                execute(responseMediaMessage);
//                return;
//            }
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private SendMessage runCommonCommand(Message message) {
//        String text = message.getText();
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                if (annotation.name().equals(text)) {
//                    try {
//                        method.setAccessible(true);
//                        String responseText = (String) method.invoke(commands);
//                        if (responseText!=null) {
//                            SendMessage sendMessage = new SendMessage();
//                            sendMessage.setChatId(message.getChatId().toString());
//                            sendMessage.setText(responseText);
//                            return sendMessage;
//                        }
//
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    private SendMediaGroup runPhotoFilter(Message message) {
//        ImageOperation operation = FilterOperation::greyScale;
//        List<File> photoSizes = getFilesByMessage(message); // Исправление ошибки
//        try {
//            List<String> paths = PhotoMessageUtils.savePhotos(photoSizes, getBotToken());
//            String chatId = message.getChatId().toString();
//            return preparePhotoMessage(paths, operation, chatId);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private List<File> getFilesByMessage (Message message) {
//        List<PhotoSize> photoSizes = message.getPhoto();
//        ArrayList<File> files = new ArrayList<>();
//        for (PhotoSize photoSize : photoSizes) {
//            final String fileId = photoSize.getFileId();
//            try {
//                files.add(sendApiMethod(new GetFile(fileId)));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        return files;
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
//    private SendMediaGroup preparePhotoMessage(List<String> localPaths, ImageOperation operation, String chatId) throws Exception {
//        SendMediaGroup mediaGroup = new SendMediaGroup();
//        ArrayList<InputMedia> medias = new ArrayList<>();
//        for (String path : localPaths) {
//            InputMedia inputMedia = new InputMediaPhoto();
//            PhotoMessageUtils.processingImage(path, operation);
//            inputMedia.setMedia(new java.io.File(path), "path");
//            medias.add(inputMedia);
//        }
//        mediaGroup.setMedias(medias);
//        mediaGroup.setChatId(chatId);
//        return mediaGroup;
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
//
//                if (annotation.showInKeyboard()) {  // Check if method should be displayed in keyboard
//                    KeyboardRow helpRow = new KeyboardRow();
//                    KeyboardButton helpButton = new KeyboardButton("/help");
//                    helpRow.add(helpButton);
//                    keyboardRows.add(helpRow);
//                }
//            }
//        }
//
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//
//    public static void processingImage(String fileName, ImageOperation operation) throws Exception {
//        final BufferedImage image = ImageUtils.getImage(fileName);
//        final RgbMaster rgbMaster = new RgbMaster(image);
//        rgbMaster.changeImage(operation);
//        ImageUtils.saveImage(rgbMaster.getImage(), fileName);
//    }
//
//}
//// КОНЕЦ ПРИМЕРА 12



//// ПРИМЕР 11 _Сохраняю перед перепиской с нейросетью
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
//import org.telegram.telegrambots.meta.api.objects.File;
//
//import task9_7_1.commands.AppBotCommand;
//import task9_7_1.commands.BotCommonCommands;
//import task9_7_1.functions.FilterOperation;
//import task9_7_1.functions.ImageOperation;
//import task9_7_1.utils.ImageUtils;
//import task9_7_1.utils.PhotoMessageUtils;
//import task9_7_1.utils.RgbMaster;
//
//import java.awt.image.BufferedImage;
//import java.io.*;
////import java.io.File;
//        import java.lang.reflect.Array;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[] {BotCommonCommands.class};
//
//    @Override
//    public String getBotUsername() {
//        return "qytewqwww_Bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message = update.getMessage();
//        SendMessage responseTextMessage = runCommonCommand(message);
//        if (responseTextMessage != null) {
//            try {
//                execute(responseTextMessage);
//            } catch (TelegramApiException e) {
//                throw new RuntimeException(e);
//            }
//            return;
//        }
//
//        try {
//            SendMediaGroup responseMediaMessage = runPhotoFilter(message);
//            if (responseMediaMessage != null) {
//                execute(responseMediaMessage);
//                return;
//            }
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private SendMessage runCommonCommand(Message message) {
//        String text = message.getText();
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                if (annotation.name().equals(text)) {
//                    try {
//                        method.setAccessible(true);
//                        String responseText = (String) method.invoke(commands);
//                        if (responseText!=null) {
//                            SendMessage sendMessage = new SendMessage();
//                            sendMessage.setChatId(message.getChatId().toString());
//                            sendMessage.setText(responseText);
//                            return sendMessage;
//                        }
//
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    private SendMediaGroup runPhotoFilter(Message message) {
//        ImageOperation operation = FilterOperation::greyScale;
//        List<File> files = getFilesByMessage(message);
//        try {
////            List<String> paths = PhotoMessageUtils.savePhotos(files, getBotToken());
//            List<String> paths = PhotoMessageUtils.savePhotos(files, getBotToken());
//            String chatId = message.getChatId().toString();
//            return preparePhotoMessage(paths, operation, chatId);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//    private List<File> getFilesByMessage (Message message) {
//        List<PhotoSize> photoSizes = message.getPhoto();
//        ArrayList<File> files = new ArrayList<>();
//        for (PhotoSize photoSize : photoSizes) {
//            final String fileId = photoSize.getFileId();
//            try {
//                files.add(sendApiMethod(new GetFile(fileId)));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        return files;
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
//    private SendMediaGroup preparePhotoMessage(List<String> localPaths, ImageOperation operation, String chatId) throws Exception {
//        SendMediaGroup mediaGroup = new SendMediaGroup();
//        ArrayList<InputMedia> medias = new ArrayList<>();
//        for (String path : localPaths) {
//            InputMedia inputMedia = new InputMediaPhoto();
//            PhotoMessageUtils.processingImage(path, operation);
//            inputMedia.setMedia(new java.io.File(path), "path");
////            inputMedia.setNewMediaFile(new java.io.File(path));
//            medias.add(inputMedia);
//        }
//        mediaGroup.setMedias(medias);
//        mediaGroup.setChatId(chatId);
////        newFile.setMedia(String.valueOf(new File()));
////        newFile.setMedia(new File(localPath)); //TODO _у преподавателя было так
//        return mediaGroup;
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
//
//                if (annotation.showInKeyboard()) {  // Check if method should be displayed in keyboard
//                    KeyboardRow helpRow = new KeyboardRow();
//                    KeyboardButton helpButton = new KeyboardButton("/help");
//                    helpRow.add(helpButton);
//                    keyboardRows.add(helpRow);
//                }
//            }
//        }
//
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//
//    public static void processingImage(String fileName, ImageOperation operation) throws Exception {
//        final BufferedImage image = ImageUtils.getImage(fileName);
//        final RgbMaster rgbMaster = new RgbMaster(image);
//        rgbMaster.changeImage(operation);
//        ImageUtils.saveImage(rgbMaster.getImage(), fileName);
//    }
//
//}
//// КОНЕЦ ПРИМЕРА 11




//// ПРИМЕР 10 _Итог по видео 07. Всё отлично работает. Приходят в ответ сообщения на команды /hello, /bye и /help. Переход
//// к следующему видео: 08
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
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
//        import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[] {BotCommonCommands.class};
//
//    @Override
//    public String getBotUsername() {
//        return "qytewqwww_Bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk"; // Токен вашего бота
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
//    }
//
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
//        return "Unknown command";
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
//        sendPhoto.setReplyMarkup(getKeyboard());
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath));
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//
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
//
//                if (annotation.showInKeyboard()) {  // Check if method should be displayed in keyboard
//                    KeyboardRow helpRow = new KeyboardRow();
//                    KeyboardButton helpButton = new KeyboardButton("/help");
//                    helpRow.add(helpButton);
//                    keyboardRows.add(helpRow);
//                }
//            }
//        }
//
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//
//}
//// КОНЕЦ ПРИМЕРА 10




//// ПРИМЕР 9 _35 35 мин на видео У НАС ПОЛУЧИЛОСЬ ВЫВЕСТИ HEEEELP, а у нас НЕ ПОЛУЧИЛОСЬ
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
//import task9_7_1.utils.PhotoMessageUtils;
//
//import java.io.*;
//        import java.lang.reflect.Constructor;
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
//        return "qytewqwww_Bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message = update.getMessage();
//        String chatId = message.getChatId().toString();
//
//        String response = null;
//        try {
//            response = runCommand(message.getText());
//        } catch (InvocationTargetException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        } catch (InstantiationException e) {
//            throw new RuntimeException(e);
//        }
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private String runCommand(String text) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
//        for (int i = 0; i < commandClasses.length; i++) {
//            Constructor<Class> constructor =
//                    Class.class.getDeclaredConstructor();
//
//            constructor.setAccessible(true);
//            Class<?> ourInstance = constructor.newInstance();
//            Method[] classMethods = ourInstance.getClass().getDeclaredMethods();
//
//            for (Method method : classMethods) {
//                if (method.isAnnotationPresent(AppBotCommand.class)) {
//                    AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                    if (annotation.name().equals(text)) {
//                        try {
//                            method.setAccessible(true);
//                            return (String) method.invoke(ourInstance);
//                        } catch (IllegalAccessException | InvocationTargetException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//
//        }
//        BotCommonCommands commands = new BotCommonCommands();
//        return "Unknown command";
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
//    private SendPhoto preparePhotoMessage(String localPath, String chatId) {
//        SendPhoto sendPhoto = new SendPhoto();
//        sendPhoto.setReplyMarkup(getKeyboard());
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath));
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//
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
//        Method[] classMethods = someClass.getDeclaredMethods(); // заменим getMethods() на getDeclaredMethods()
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
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//
//}
//// КОНЕЦ ПРИМЕРА 9




//// ПРИМЕР 8 _ПОМЕНЯТЬ НА ИЗНАЧАЛЬНЫЙ ТЕЛЕГРАМ БОТ. Всё заработало! Пришёл ответ: "Hello, User".
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
//import task9_7_1.utils.PhotoMessageUtils;
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
//    @Override
//    public String getBotUsername() {
//        return "qytewqwww_Bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message = update.getMessage();
//        String chatId = message.getChatId().toString();
//
//        String response = null;
//        try {
//            response = runCommand(message.getText());
//        } catch (InvocationTargetException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private String runCommand(String text) throws InvocationTargetException, IllegalAccessException {
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
//        return "Unknown command";
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
//    private SendPhoto preparePhotoMessage(String localPath, String chatId) {
//        SendPhoto sendPhoto = new SendPhoto();
//        sendPhoto.setReplyMarkup(getKeyboard());
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath));
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//
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
//        Method[] classMethods = someClass.getDeclaredMethods(); // заменим getMethods() на getDeclaredMethods()
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
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//
//}
//// КОНЕЦ ПРИМЕРА 8





//// ПРИМЕР 7 _ Записывал за преп-лем. Но не срабатывает: Запистили , Бум!
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
//import task9_7_1.utils.PhotoMessageUtils;
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
//    @Override
//    public String getBotUsername() {
//        return "qytewqwww_Bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
////        Message message = update.getMessage();
////        String chatId = message.getChatId().toString();
////
////
////        try {
////            ArrayList<String> photoPaths = new ArrayList<>(PhotoMessageUtils.savePhotos(getFilesByMessage(message), getBotToken()));
////            for (String path : photoPaths) {
////                PhotoMessageUtils.processingImage(path);
////                execute(preparePhotoMessage(path, chatId));
////            }
////        } catch (TelegramApiException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//
//
//
//
//
////         Это старый текст:
//        final String localFileName = "src/main/java/task9_7_1/" + "cloned_image.jpg";
//        Message message = update.getMessage();
//        String chatId = message.getChatId().toString();
//        PhotoSize photoSize = message.getPhoto().get(0);
//        final String fileId = photoSize.getFileId();
//        try {
//            String response = runCommand(message.getText());
//            SendMessage sendMessage = new SendMessage();
//            sendMessage.setChatId(chatId);
//            sendMessage.setText(response);
//            execute(sendMessage);
//        } catch (InvocationTargetException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
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
//    }
//
//
//    private String runCommand(String text) throws InvocationTargetException, IllegalAccessException {
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods(); // заменим getMethods() на getDeclaredMethods()
////        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
//                if (command.name().equals(text)) {
//                    method.setAccessible(true);
//                    return (String) method.invoke(commands);
//                }
//
////                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
////                KeyboardButton button = new KeyboardButton();
////                button.setText(annotation.name());
////                row.add(button);
//            }
//        }
//        return null;
//    }
//
//
////    private ArrayList<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) {
////        List<PhotoSize> photoSizes = message.getPhoto();
////        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files = new ArrayList<>();
////        for (PhotoSize photoSize : photoSizes) {
////            final String fileId = photoSize.getFileId();
////            try {
////                files.add(sendApiMethod(new GetFile(fileId)));
////            } catch (TelegramApiException e) {
////                e.printStackTrace();
////            }
////        }
////        return files;
////    }
//
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
//        sendPhoto.setReplyMarkup(getKeyboard());
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath));
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//
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
//        Method[] classMethods = someClass.getDeclaredMethods(); // заменим getMethods() на getDeclaredMethods()
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
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//
//}
//// КОНЕЦ ПРИМЕРА 7





//// ПРИМЕР 6 _Создались две кнопки, НО ОЧЕНЬ БОЛЬШИЕ ПО ВЫСОТЕ!    _17 10 - мин на видеоуроке
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
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
//    @Override
//    public String getBotUsername() {
//        return "qytewqwww_Bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        final String localFileName = "src/main/java/task9_7_1/" + "cloned_image.jpg";
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
//        SendPhoto sendPhoto = preparePhotoMessage(localFileName, message.getChatId().toString());
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
//        sendPhoto.setReplyMarkup(getKeyboard());
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath));
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//
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
//        Method[] classMethods = someClass.getDeclaredMethods(); // заменим getMethods() на getDeclaredMethods()
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
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//
//}
//// КОНЕЦ ПРИМЕРА 6





//// ПРИМЕР 5 _Записано в точности за преподав-лем. Но у него создались две кнопки, а у меня нет
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import task9_7_1.commands.AppBotCommand;
//import task9_7_1.commands.BotCommonCommands;
//import task9_7_1.functions.FilterOperation;
//
//import java.io.*;
//        import java.lang.reflect.InvocationTargetException;
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
//        return "rrrrruxlkj_Bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7020913847:AAHLqnflqzlX3JCiEvZnWpTDK7dCXUH6XlA"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        final String localFileName = "src/main/java/task9_7_1/" + "cloned_image.jpg";
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
//        SendPhoto sendPhoto = preparePhotoMessage(localFileName, message.getChatId().toString());
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
//        sendPhoto.setReplyMarkup(getKeyboard());
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath));
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//
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
//        Method[] classMethods = someClass.getMethods();
//        ArrayList<AppBotCommand> commands = new ArrayList<>();
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                commands.add(method.getAnnotation(AppBotCommand.class));
//            }
//        }
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        int columnCount = 3;
//        int rowsCount = commands.size() / columnCount + ((commands.size() % columnCount == 0) ? 0 : 1);
//        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
//            KeyboardRow row = new KeyboardRow();
//            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
//                int index = rowIndex * columnCount + columnIndex;
//                if (index >= commands.size()) continue;
//                AppBotCommand command = commands.get(rowIndex * columnCount + columnIndex);
//                KeyboardButton keyboardButton = new KeyboardButton(command.name());
//                row.add(keyboardButton);
//            }
//            keyboardRows.add(row);
//        }
//        return keyboardRows;
//    }
//
//}
//// КОНЕЦ ПРИМЕРА 5




//// ПРИМЕР 4 _Создались кнопки, как У ПРЕПОДАВАТЕЛЯ!!! В 5 СТРОК, В 3 СТОЛБЦА!!!
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
//    @Override
//    public String getBotUsername() {
//        return "qytewqwww_Bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        final String localFileName = "src/main/java/task9_7_1/" + "cloned_image.jpg";
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
//        SendPhoto sendPhoto = preparePhotoMessage(localFileName, message.getChatId().toString());
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
//
//        sendPhoto.setReplyMarkup(getKeyboard(FilterOperation.class));
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath));
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//
//
//
////        // Удалить создание клавиатуры с кнопками фильтров
////        // и оставить только отправку изображения без кнопок
////        sendPhoto.setChatId(chatId);
////        InputFile newFile = new InputFile();
////        newFile.setMedia(new File(localPath));
////        sendPhoto.setPhoto(newFile);
////        sendPhoto.setCaption("Choose a filter:");
////
////        return sendPhoto;
//    }
//
//    private ReplyKeyboardMarkup getKeyboard(Class someClass) {
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        Method[] methods = someClass.getMethods();
//        int columnCount = 3;
//        int rowsCount = methods.length / columnCount + ((methods.length % columnCount == 0) ? 0 : 1);
//        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
//            KeyboardRow row = new KeyboardRow();
//            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
//                int index = rowIndex * columnCount + columnIndex;
//                if (index >= methods.length) continue;
//                Method method = methods[rowIndex * columnCount + columnIndex];
//                KeyboardButton keyboardButton = new KeyboardButton(method.getName());
//                row.add(keyboardButton);
//            }
//            keyboardRows.add(row);
//        }
//        replyKeyboardMarkup.setKeyboard(keyboardRows);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        return replyKeyboardMarkup;
//    }
//
//}
//// КОНЕЦ ПРИМЕРА 4





//// ПРИМЕР 3 _Создание кнопок закомментировано. Они больше не создаются
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
//        import java.lang.reflect.InvocationTargetException;
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
//        return "qytewqwww_Bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        final String localFileName = "src/main/java/task9_7_1/" + "cloned_image.jpg";
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
//        SendPhoto sendPhoto = preparePhotoMessage(localFileName, message.getChatId().toString());
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
//
//        // Удалить создание клавиатуры с кнопками фильтров
//        // и оставить только отправку изображения без кнопок
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath));
//        sendPhoto.setPhoto(newFile);
//        sendPhoto.setCaption("Choose a filter:");
//
//        return sendPhoto;
//
//
//
//
//
//
////        // Это я закомменнтировал, чтобы кнопки не создавались:
////        SendPhoto sendPhoto = new SendPhoto();
////        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
////        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
////        int rowCount = 3;
////        int columnCount = 3;
////        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
////            KeyboardRow row = new KeyboardRow();
////            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
////                if (rowIndex == 0 && columnIndex == 0) {
////                    try {
////                        Class<?> filterOperationClass = Class.forName("task9_7_1.functions.FilterOperation");
////                        String methodName = "greyScale";
////                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
////                        float[] rgbArray = new float[3]; // Creating an RGB array
////                        Object result = method.invoke(null, rgbArray);
////                        KeyboardButton keyboardButton1 = new KeyboardButton(methodName);
////                        row.add(keyboardButton1);
////                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
////                        ex.printStackTrace();
////                    }
////
////                } else if (rowIndex == 0 && columnIndex == 1) {
////                    try {
////                        Class<?> filterOperationClass = Class.forName("task9_7_1.functions.FilterOperation");
////                        String methodName = "onlyRed";
////                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
////                        float[] rgbArray = new float[3]; // Creating an RGB array
////                        Object result = method.invoke(null, rgbArray);
////                        KeyboardButton keyboardButton2 = new KeyboardButton(methodName);
////                        row.add(keyboardButton2);
////                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
////                        ex.printStackTrace();
////                    }
////
////                } else if (rowIndex == 0 && columnIndex == 2) {
////                    try {
////                        Class<?> filterOperationClass = Class.forName("task9_7_1.functions.FilterOperation");
////                        String methodName = "onlyGreen";
////                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
////                        float[] rgbArray = new float[3]; // Creating an RGB array
////                        Object result = method.invoke(null, rgbArray);
////                        KeyboardButton keyboardButton3 = new KeyboardButton(methodName);
////                        row.add(keyboardButton3);
////                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
////                        ex.printStackTrace();
////                    }
////
////                } else if (rowIndex == 1 && columnIndex == 0) {
////                    try {
////                        Class<?> filterOperationClass = Class.forName("task9_7_1.functions.FilterOperation");
////                        String methodName = "onlyBlue";
////                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
////                        float[] rgbArray = new float[3]; // Creating an RGB array
////                        Object result = method.invoke(null, rgbArray);
////                        KeyboardButton keyboardButton4 = new KeyboardButton(methodName);
////                        row.add(keyboardButton4);
////                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
////                        ex.printStackTrace();
////                    }
////
////                } else if (rowIndex == 1 && columnIndex == 1) {
////                    try {
////                        Class<?> filterOperationClass = Class.forName("task9_7_1.functions.FilterOperation");
////                        String methodName = "sepia";
////                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
////                        float[] rgbArray = new float[3]; // Creating an RGB array
////                        Object result = method.invoke(null, rgbArray);
////                        KeyboardButton keyboardButton5 = new KeyboardButton(methodName);
////                        row.add(keyboardButton5);
////                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
////                        ex.printStackTrace();
////                    }
////
////                } else {
////                    KeyboardButton keyboardButton = new KeyboardButton("button" + (rowIndex*3+columnIndex+1));
////                    row.add(keyboardButton);
////                }
////            }
////            keyboardRows.add(row);
////        }
////        replyKeyboardMarkup.setKeyboard(keyboardRows);
////        replyKeyboardMarkup.setOneTimeKeyboard(true);
////        sendPhoto.setChatId(chatId);
////        sendPhoto.setReplyMarkup(replyKeyboardMarkup);
////        InputFile newFile = new InputFile();
////        newFile.setMedia(new File(localPath));
////        sendPhoto.setPhoto(newFile);
////        return sendPhoto;
//    }
//}
//// КОНЕЦ ПРИМЕРА 3



//// ПРИМЕР 2 _Это изначальный код, взятый из task9_5_1 _Всё работает, создаются кнопки 5+4
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
//        import java.lang.reflect.InvocationTargetException;
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
//        return "qytewqwww_Bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        final String localFileName = "src/main/java/task9_7_1/" + "cloned_image.jpg";
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
//        SendPhoto sendPhoto = preparePhotoMessage(localFileName, message.getChatId().toString());
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
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        int rowCount = 3;
//        int columnCount = 3;
//        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
//            KeyboardRow row = new KeyboardRow();
//            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
//                if (rowIndex == 0 && columnIndex == 0) {
//                    try {
//                        Class<?> filterOperationClass = Class.forName("task9_7_1.functions.FilterOperation");
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
//                        Class<?> filterOperationClass = Class.forName("task9_7_1.functions.FilterOperation");
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
//                        Class<?> filterOperationClass = Class.forName("task9_7_1.functions.FilterOperation");
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
//                        Class<?> filterOperationClass = Class.forName("task9_7_1.functions.FilterOperation");
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
//                        Class<?> filterOperationClass = Class.forName("task9_7_1.functions.FilterOperation");
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
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        sendPhoto.setChatId(chatId);
//        sendPhoto.setReplyMarkup(replyKeyboardMarkup);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath));
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//    }
//}
//// КОНЕЦ ПРИМЕРА 2