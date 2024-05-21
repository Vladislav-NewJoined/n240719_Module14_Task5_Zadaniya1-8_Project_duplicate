package task9_7_1_part2;

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
import task9_7_1_part2.commands.AppBotCommand;
import task9_7_1_part2.commands.BotCommonCommands;
import task9_7_1_part2.functions.FilterOperation;

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
        return "kgazjmticv_002_bot"; // Название вашего бота
    }

    @Override
    public String getBotToken() {
        return "6836351278:AAFBiCCKKkPERrimdzIoRQbSFUQJm1GZ1Ds"; // Токен вашего бота
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message2 = update.getMessage();
        try {
            SendMessage responseTextMessage2 = runCommonCommand(message2);
            if (responseTextMessage2 != null) {
                execute(responseTextMessage2);
                return;
            }
        } catch (InvocationTargetException | IllegalAccessException | TelegramApiException e) {
            e.printStackTrace();
        }


//        // TODO Написал уже в новом, но возможно придётся удалить
//        String chatId2 = message2.getChatId().toString();
//        try {
//            String response2 = runCommand(message2.getText());
//            SendMessage sendMessage2 = new SendMessage();
//            sendMessage2.setChatId(chatId2);
//            sendMessage2.setText(response2);
//            execute(sendMessage2);
//        } catch (/*InvocationTargetException | IllegalAccessException | */TelegramApiException e) {
//            e.printStackTrace();
//        }

//        try {
//            ArrayList<String> photoPaths = new ArrayList<>(PhotoMessageUtils.savePhotos(getFilesByMessage(message2), getBotToken()));
//            for (String path : photoPaths) {
//                PhotoMessageUtils.processingImage(path);
//                execute(preparePhotoMessage(path, chatId2));  // Здесь ссылка на этот метод: preparePhotoMessage (без двойки)
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }




        // TODO Далее изначальные строки только этого метода
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
        final String localFileName = "src/main/java/task9_7_1_part2/" + "cloned_image.jpg";
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

        SendPhoto sendPhoto = preparePhotoMessage2(localFileName, message.getChatId().toString());
        SendPhoto sendPhoto2 = preparePhotoMessage(localFileName, message.getChatId().toString());
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

    private String runCommand(String text)/* throws InvocationTargetException, IllegalAccessException*/ {











        // TODO Далее изначальные строки только этого метода
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


    // TODO Этого метода не было в ИЗНВЧАЛЬНЫХ МЕТОДАХ, НАПИСАН ПОЛНОСТЬЮ ЗАНОВО
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
    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Так было изначально
        List<PhotoSize> photoSizes = message.getPhoto();
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



















    // TODO ДАЛЕЕ ВСЕ ИЗНАЧАЛЬНЫЕ МЕТОДЫ

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
        sendPhoto.setReplyMarkup(getKeyboard()); // Это две кнопки
        sendPhoto.setChatId(chatId);
        InputFile newFile = new InputFile();
        newFile.setMedia(new File(localPath));
        sendPhoto.setPhoto(newFile);
        return sendPhoto;

    }

    private SendPhoto preparePhotoMessage(String localFileName, String chatId) {
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
//        return "kgazjmticv_002_bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "6836351278:AAFBiCCKKkPERrimdzIoRQbSFUQJm1GZ1Ds"; // Токен вашего бота
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
//        return "kgazjmticv_002_bot"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "6836351278:AAFBiCCKKkPERrimdzIoRQbSFUQJm1GZ1Ds"; // Токен вашего бота
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