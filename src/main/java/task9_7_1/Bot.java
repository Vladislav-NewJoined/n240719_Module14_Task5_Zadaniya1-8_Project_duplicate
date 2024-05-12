package task9_7_1;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;

import static task9_7_1.utils.PhotoMessageUtils.processingImage;

public class Bot extends TelegramLongPollingBot {

    @Override
//    public String getBotUsername() { return "my_gfjhfghfjhgfjhgfghfhgfjf_bot"; // Название вашего бота
    public String getBotUsername() { return "qytewqwww_Bot"; // Название вашего бота
    }

    @Override
//    public String getBotToken() { return "6750924950:AAGOE5XBnuJDlNIKHYq61S7bMupKXhKRZZo"; // Токен вашего бота
    public String getBotToken() { return "7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk"; // Токен вашего бота
    }

    @Override
    public void onUpdateReceived(Update update) {
        final String localFileName = "src/main/java/task9_7_1/" + "cloned_image.jpg";
        Message message = update.getMessage();
        PhotoSize photoSize = message.getPhoto().get(0);
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
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        int rowCount = 3;
        int columnCount = 3;
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            KeyboardRow row = new KeyboardRow();
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                if (rowIndex == 0 && columnIndex == 0) {
                    try {
                        Class<?> filterOperationClass = Class.forName("task9_7_1.functions.FilterOperation");
                        String methodName = "greyScale";
                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
                        float[] rgbArray = new float[3]; // Creating an RGB array
                        Object result = method.invoke(null, rgbArray);
                        KeyboardButton keyboardButton1 = new KeyboardButton(methodName);
                        row.add(keyboardButton1);
                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                        ex.printStackTrace();
                    }

                } else if (rowIndex == 0 && columnIndex == 1) {
                    try {
                        Class<?> filterOperationClass = Class.forName("task9_7_1.functions.FilterOperation");
                        String methodName = "onlyRed";
                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
                        float[] rgbArray = new float[3]; // Creating an RGB array
                        Object result = method.invoke(null, rgbArray);
                        KeyboardButton keyboardButton2 = new KeyboardButton(methodName);
                        row.add(keyboardButton2);
                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                        ex.printStackTrace();
                    }

                } else if (rowIndex == 0 && columnIndex == 2) {
                    try {
                        Class<?> filterOperationClass = Class.forName("task9_7_1.functions.FilterOperation");
                        String methodName = "onlyGreen";
                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
                        float[] rgbArray = new float[3]; // Creating an RGB array
                        Object result = method.invoke(null, rgbArray);
                        KeyboardButton keyboardButton3 = new KeyboardButton(methodName);
                        row.add(keyboardButton3);
                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                        ex.printStackTrace();
                    }

                } else if (rowIndex == 1 && columnIndex == 0) {
                    try {
                        Class<?> filterOperationClass = Class.forName("task9_7_1.functions.FilterOperation");
                        String methodName = "onlyBlue";
                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
                        float[] rgbArray = new float[3]; // Creating an RGB array
                        Object result = method.invoke(null, rgbArray);
                        KeyboardButton keyboardButton4 = new KeyboardButton(methodName);
                        row.add(keyboardButton4);
                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                        ex.printStackTrace();
                    }

                } else if (rowIndex == 1 && columnIndex == 1) {
                    try {
                        Class<?> filterOperationClass = Class.forName("task9_7_1.functions.FilterOperation");
                        String methodName = "sepia";
                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
                        float[] rgbArray = new float[3]; // Creating an RGB array
                        Object result = method.invoke(null, rgbArray);
                        KeyboardButton keyboardButton5 = new KeyboardButton(methodName);
                        row.add(keyboardButton5);
                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                        ex.printStackTrace();
                    }

                } else {
                    KeyboardButton keyboardButton = new KeyboardButton("button" + (rowIndex*3+columnIndex+1));
                    row.add(keyboardButton);
                }
            }
            keyboardRows.add(row);
        }
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        sendPhoto.setChatId(chatId);
        sendPhoto.setReplyMarkup(replyKeyboardMarkup);
        InputFile newFile = new InputFile();
        newFile.setMedia(new File(localPath));
        sendPhoto.setPhoto(newFile);
        return sendPhoto;
    }
}


//// ПРИМЕР 8 _Здесь я прервался 11.05.2024. Этот пример не сработал,
//// в следующем примере возьму изначальный код из task9_5_1 (который здесь в самом низу записан, а ПРИМЕРЕ 2
//// и буду записывать точно вслед за преподавателем и пытаться работать с кодом из видеоурока
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
//import task9_4_1.functions.FilterOperation;
//import task9_7_1.commands.AppBotCommand;
//import task9_7_1.commands.BotCommonCommands;
//
//import java.io.*;
//        import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//
//import static task9_7_1.utils.PhotoMessageUtils.processingImage;
//import task9_7_1.commands.*;
//
//// Создан тестовый Telegram бот:
//// MyTestBot_001 - название Telegram бота
//// @qytewqwww_Bot  - юзернейм бота
//// 7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk - токен бота
//
//public class Bot extends TelegramLongPollingBot {
//
//    @Override
//    public String getBotUsername() { return "qytewqwww_Bot"; // Название вашего бота _ТЕСТОВОГО
//    }
//
//    @Override
//    public String getBotToken() { return "7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk"; // Токен вашего бота _ТЕСТОВОГО
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        final String localFileName = "src/main/java/task9_7_1/" + "cloned_image.jpg";
//        Message message = update.getMessage();
//        String chatId = message.getChatId().toString(); // TODO возможно надо будет потом удалить
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
//
//
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
//    private String runCommand(String text) throws InvocationTargetException, IllegalAccessException {
//        Method[] classMethods = BotCommonCommands.class.getDeclaredMethods();
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
//                if (command.name().equals(text)) {
//                    return (String) method.invoke(null);
//                }
//            }
//        }
//        return null;
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
//    private ArrayList<KeyboardRow> getKeyboardRows(Class<?> cls) {
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//
//        try {
//            for (Method method : cls.getDeclaredMethods()) {
//                if (method.isAnnotationPresent(AppBotCommand.class)) {
//                    AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                    KeyboardButton button = new KeyboardButton();
//                    button.setText(annotation.name());
//
//                    if (annotation.name().equals("/bye")) {
//                        row.add(button); // добавляем кнопку '/bye' в первую строку
//                    } else if (annotation.name().equals("/hello")) {
//                        row.add(button); // добавляем кнопку '/hello' в первую строку
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        keyboardRows.add(row);
//
//        return keyboardRows;
//    }
//}
//// КОНЕЦ ПРИМЕРА 8



//// ПРИМЕР 7 _Всё верно, создаются две кнопки, именно в нужной последовательности, но высота кнопое ЧРЕЗМЕРНО большая.
//// С комментарием:     // реализация остальных методов
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
//import task9_4_1.functions.FilterOperation;
//import task9_7_1.commands.AppBotCommand;
//import task9_7_1.commands.BotCommonCommands;
//
//import java.io.*;
//        import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//
//import static task9_7_1.utils.PhotoMessageUtils.processingImage;
//import task9_7_1.commands.*;
//
//// Создан тестовый Telegram бот:
//// MyTestBot_001 - название Telegram бота
//// @qytewqwww_Bot  - юзернейм бота
//// 7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk - токен бота
//
//public class Bot extends TelegramLongPollingBot {
//
//    @Override
//    public String getBotUsername() { return "qytewqwww_Bot"; // Название вашего бота _ТЕСТОВОГО
//    }
//
//    @Override
//    public String getBotToken() { return "7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk"; // Токен вашего бота _ТЕСТОВОГО
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
//    private ArrayList<KeyboardRow> getKeyboardRows(Class<?> cls) {
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//
//        try {
//            for (Method method : cls.getDeclaredMethods()) {
//                if (method.isAnnotationPresent(AppBotCommand.class)) {
//                    AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                    KeyboardButton button = new KeyboardButton();
//                    button.setText(annotation.name());
//
//                    if (annotation.name().equals("/bye")) {
//                        row.add(button); // добавляем кнопку '/bye' в первую строку
//                    } else if (annotation.name().equals("/hello")) {
//                        row.add(button); // добавляем кнопку '/hello' в первую строку
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        keyboardRows.add(row);
//
//        return keyboardRows;
//    }
//}
//// КОНЕЦ ПРИМЕРА 7




//// ПРИМЕР 6 _Я убрал все кнопки и создал новый тестовый бот
//NEW_Test_Bot
//sadaafddsfgdhf_bot
//7133233442:AAEhVHRwoxL--FG-dt_u1P1XoVQvCFg6DkU
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
//import task9_4_1.functions.FilterOperation;
//import task9_7_1.commands.AppBotCommand;
//import task9_7_1.commands.BotCommonCommands;
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
//    public String getBotUsername() { return "sadaafddsfgdhf_bot"; // Название вашего бота _ТЕСТОВОГО
//    }
//
//    @Override
//    public String getBotToken() { return "7133233442:AAEhVHRwoxL--FG-dt_u1P1XoVQvCFg6DkU"; // Токен вашего бота _ТЕСТОВОГО
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
//}
//// КОНЕЦ ПРИМЕРА 6




//// ПРИМЕР 5 _мин 17 45 видеоурок Задание №7 _Написано в точности за преподавателем, но не сработало.
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
//import task9_4_1.functions.FilterOperation;
//import task9_7_1.commands.AppBotCommand;
//import task9_7_1.commands.BotCommonCommands;
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
////    public String getBotUsername() { return "my_gfjhfghfjhgfjhgfghfhgfjf_bot"; // Название вашего бота
//    public String getBotUsername() { return "hkjhgkjfdgdfgfgdyou_Bot"; // Название вашего бота _ТЕСТОВОГО
//    }
//
//    @Override
////    public String getBotToken() { return "6750924950:AAGOE5XBnuJDlNIKHYq61S7bMupKXhKRZZo"; // Токен вашего бота
//    public String getBotToken() { return "6488095456:AAEpQj-SUQO1MA4wosjrxBsvYMfp_WXwckE"; // Токен вашего бота _ТЕСТОВОГО
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
//        sendPhoto.setReplyMarkup(getKeyboard());
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath));
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
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
//}
//// КОНЕЦ ПРИМЕРА 5




//// ПРИМЕР 4 _мин 16 01 видеоурок Модуль 9. UI и использование готовых SDK. Задание №7 _ВСЁ РАБОТАЕТ.
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
////    public String getBotUsername() { return "my_gfjhfghfjhgfjhgfghfhgfjf_bot"; // Название вашего бота
//    public String getBotUsername() { return "hkjhgkjfdgdfgfgdyou_Bot"; // Название вашего бота _ТЕСТОВОГО
//    }
//
//    @Override
////    public String getBotToken() { return "6750924950:AAGOE5XBnuJDlNIKHYq61S7bMupKXhKRZZo"; // Токен вашего бота
//    public String getBotToken() { return "6488095456:AAEpQj-SUQO1MA4wosjrxBsvYMfp_WXwckE"; // Токен вашего бота _ТЕСТОВОГО
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
//    private ArrayList<KeyboardRow> getKeyboardRow(Class someClass) {
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
//}
//// КОНЕЦ ПРИМЕРА




//// ПРИМЕР 4 _мин 14 44 видеоурок Модуль 9. UI и использование готовых SDK. Задание №7 _ВСЁ РАБОТАЕТ.
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
////    public String getBotUsername() { return "my_gfjhfghfjhgfjhgfghfhgfjf_bot"; // Название вашего бота
//    public String getBotUsername() { return "hkjhgkjfdgdfgfgdyou_Bot"; // Название вашего бота _ТЕСТОВОГО
//    }
//
//    @Override
////    public String getBotToken() { return "6750924950:AAGOE5XBnuJDlNIKHYq61S7bMupKXhKRZZo"; // Токен вашего бота
//    public String getBotToken() { return "6488095456:AAEpQj-SUQO1MA4wosjrxBsvYMfp_WXwckE"; // Токен вашего бота _ТЕСТОВОГО
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
//    private ArrayList<KeyboardRow> getKeyboardRow(Class someClass) {
//        Method[] methods = someClass.getMethods();
//        ArrayList<AppBotCommand> commands = new ArrayList<>();
//        for (Method method : methods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                commands.add(method.getAnnotation(AppBotCommand.class));
//            }
//        }
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
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
//        return keyboardRows;
//    }
//}
//// КОНЕЦ ПРИМЕРА 4




//// ПРИМЕР 3 _мин 09 35 видеоурок Модуль 9. UI и использование готовых SDK. Задание №7 _ВСЁ РАБОТАЕТ.
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
////    public String getBotUsername() { return "my_gfjhfghfjhgfjhgfghfhgfjf_bot"; // Название вашего бота
//    public String getBotUsername() { return "hkjhgkjfdgdfgfgdyou_Bot"; // Название вашего бота
//    }
//
//    @Override
////    public String getBotToken() { return "6750924950:AAGOE5XBnuJDlNIKHYq61S7bMupKXhKRZZo"; // Токен вашего бота
//    public String getBotToken() { return "6488095456:AAEpQj-SUQO1MA4wosjrxBsvYMfp_WXwckE"; // Токен вашего бота
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
//}
//// КОНЕЦ ПРИМЕРА 3




//// ПРИМЕР 2 _Изначально так было. Взято из task9_5_1
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
////    public String getBotUsername() { return "my_gfjhfghfjhgfjhgfghfhgfjf_bot"; // Название вашего бота
//    public String getBotUsername() { return "hkjhgkjfdgdfgfgdyou_Bot"; // Название вашего бота
//    }
//
//    @Override
////    public String getBotToken() { return "6750924950:AAGOE5XBnuJDlNIKHYq61S7bMupKXhKRZZo"; // Токен вашего бота
//    public String getBotToken() { return "6488095456:AAEpQj-SUQO1MA4wosjrxBsvYMfp_WXwckE"; // Токен вашего бота
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