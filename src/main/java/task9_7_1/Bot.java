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
    public String getBotUsername() { return "hkjhgkjfdgdfgfgdyou_Bot"; // Название вашего бота
    }

    @Override
//    public String getBotToken() { return "6750924950:AAGOE5XBnuJDlNIKHYq61S7bMupKXhKRZZo"; // Токен вашего бота
    public String getBotToken() { return "6488095456:AAEpQj-SUQO1MA4wosjrxBsvYMfp_WXwckE"; // Токен вашего бота
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