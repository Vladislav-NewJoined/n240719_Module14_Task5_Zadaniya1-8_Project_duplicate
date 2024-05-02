package task9_5_1;

//// ПРИМЕР   _ВСЁ РАБОТАЕТ, КАРТИНКИ ПЕРЕСЫЛАЮТСЯ
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
import task9_5_1.functions.FilterOperation;
import task9_5_1.utils.ImageUtils;
import task9_5_1.utils.RgbMaster;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "my_gfjhfghfjhgfjhgfghfhgfjf_bot"; // название самого бота такое: javatestbot
    }

    @Override
    public String getBotToken() {
        return "6750924950:AAGOE5XBnuJDlNIKHYq61S7bMupKXhKRZZo";
    }

    @Override
    public void onUpdateReceived(Update update) {
        final String localFileName = "src/main/java/task9_5_1/" + "cloned_image.jpg";
        Message message = update.getMessage();
        String response = message.getFrom().getId().toString();
        PhotoSize photoSize = message.getPhoto().get(0);
        final String fileId = photoSize.getFileId();
        try {
            final org.telegram.telegrambots.meta.api.objects.File file = sendApiMethod(new GetFile(fileId));
            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
            saveImage(imageUrl, localFileName);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ///
        try {
            processingImage(localFileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        ///
//        System.out.println(message.getText());
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId().toString());
        InputFile newFile = new InputFile();
        newFile.setMedia(new File(localFileName));
        sendPhoto.setPhoto(newFile);
        sendPhoto.setCaption("Edited image");

//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(message.getChatId().toString());
//        sendMessage.setText("Your message: " + response);
        try {
//            execute(sendMessage);
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private static void processingImage(String fileName) throws Exception {
        ///
        final BufferedImage image = ImageUtils.getImage(fileName);
//        final BufferedImage image = ImageUtils.getImage("src/main/java/task9_5_1/logoJAVA.jpg");
        final RgbMaster rgbMaster = new RgbMaster(image);
        rgbMaster.changeImage(FilterOperation::greyScale);
        ImageUtils.saveImage(rgbMaster.getImage(),"src/main/java/task9_5_1/cloned_image.jpg");
    }

    public void saveImage(String url, String fileName) throws IOException {
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
//        keyboardRows.add(new KeyboardRow());
        for (int i = 0; i < 3; i++) {
            KeyboardButton keyboardButton = new KeyboardButton("button" + i);
            keyboardRows.add(new KeyboardRow());
        }
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        sendPhoto.setReplyMarkup();
        sendPhoto.setChatId(chatId);
        InputFile newFile = new InputFile();
        newFile.setMedia(new File(localPath));
        sendPhoto.setPhoto(newFile);
        return sendPhoto;
    }
}
//// КОНЕЦ ПРИМЕРА




//// ПРИМЕР 2
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import task9_5_1.functions.FilterOperation;
//import task9_5_1.utils.ImageUtils;
//import task9_5_1.utils.RgbMaster;
//
//import java.awt.image.BufferedImage;
//import java.io.*;
//        import java.net.URL;
//
//public class Bot extends TelegramLongPollingBot {
//    @Override
//    public String getBotUsername() {
//        return "my_gfjhfghfjhgfjhgfghfhgfjf_bot"; // название самого бота такое: javatestbot
//    }
//
//    @Override
//    public String getBotToken() {
//        return "6750924950:AAGOE5XBnuJDlNIKHYq61S7bMupKXhKRZZo";
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        final String localFileName = "src/main/java/task9_5_1/" + "cloned_image.jpg";
//        Message message = update.getMessage();
//        String response = message.getFrom().getId().toString();
//        PhotoSize photoSize = message.getPhoto().get(0);
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = sendApiMethod(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        ///
//        try {
//            processingImage(localFileName);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//
//        ///
////        System.out.println(message.getText());
//        SendPhoto sendPhoto = new SendPhoto();
//        sendPhoto.setChatId(message.getChatId().toString());
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localFileName));
//        sendPhoto.setPhoto(newFile);
//        sendPhoto.setCaption("Edited image");
//
////        SendMessage sendMessage = new SendMessage();
////        sendMessage.setChatId(message.getChatId().toString());
////        sendMessage.setText("Your message: " + response);
//        try {
////            execute(sendMessage);
//            execute(sendPhoto);
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static void processingImage(String fileName) throws Exception {
//        ///
//        final BufferedImage image = ImageUtils.getImage("src/main/java/task9_5_1/logoJAVA.jpg");
//        final RgbMaster rgbMaster = new RgbMaster(image);
//        rgbMaster.changeImage(FilterOperation::greyScale);
//        ImageUtils.saveImage(rgbMaster.getImage(),"src/main/java/task9_5_1/cloned_image.jpg");
//    }
//
//    public void saveImage(String url, String fileName) throws IOException {
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
//}
//// КОНЕЦ ПРИМЕРА 2