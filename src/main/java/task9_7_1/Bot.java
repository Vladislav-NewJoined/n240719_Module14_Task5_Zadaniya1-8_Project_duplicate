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
import task9_7_1.functions.FilterOperation;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;

import static task9_7_1.utils.PhotoMessageUtils.processingImage;

public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "qytewqwww_Bot"; // Название вашего бота
    }

    @Override
    public String getBotToken() {
        return "7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk"; // Токен вашего бота
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

        sendPhoto.setReplyMarkup(getKeyboard(FilterOperation.class));
        sendPhoto.setChatId(chatId);
        InputFile newFile = new InputFile();
        newFile.setMedia(new File(localPath));
        sendPhoto.setPhoto(newFile);
        return sendPhoto;



//        // Удалить создание клавиатуры с кнопками фильтров
//        // и оставить только отправку изображения без кнопок
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath));
//        sendPhoto.setPhoto(newFile);
//        sendPhoto.setCaption("Choose a filter:");
//
//        return sendPhoto;
    }

    private ReplyKeyboardMarkup getKeyboard(Class someClass) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        Method[] methods = someClass.getMethods();
        int columnCount = 3;
        int rowsCount = methods.length / columnCount + ((methods.length % columnCount == 0) ? 0 : 1);
        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
            KeyboardRow row = new KeyboardRow();
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                int index = rowIndex * columnCount + columnIndex;
                if (index >= methods.length) continue;
                Method method = methods[rowIndex * columnCount + columnIndex];
                KeyboardButton keyboardButton = new KeyboardButton(method.getName());
                row.add(keyboardButton);
            }
            keyboardRows.add(row);
        }
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

}




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