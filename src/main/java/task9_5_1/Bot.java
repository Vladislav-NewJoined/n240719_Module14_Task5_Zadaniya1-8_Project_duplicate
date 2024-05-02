package task9_5_1;

//// ПРИМЕР
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
////import org.telegram.telegrambots.meta.api.objects.File;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import task9_4_1.utils.PhotoMessageUtils;
//import java.io.File;
//import java.util.List;
////import org.telegram.telegrambots.meta.api.objects.File;
//
//import java.io.*;
//import java.io.File;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
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
////        final String localFileName = "src/main/java/task9_4_1/" + "cloned_image.jpg";
//        Message message = update.getMessage();
//        String chatId = message.getChatId().toString();
////        ArrayList<String> photoPaths = new ArrayList<>();
//        try {
//            ArrayList<String> photoPaths = new ArrayList<>(PhotoMessageUtils.savePhotos(getFilesByMessage(message), getBotToken()));
//            for (String path : photoPaths) {
//                PhotoMessageUtils.processingImage(path);
//                execute(preparePhotoMessage(path, chatId));
//            }
////        final String localFileName = "src/main/java/task9_4_1/" + "received_image.jpeg";
////        String response = message.getFrom().getId().toString();
////        } catch (TelegramApiException | IOException e) {
////            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();;
//        }
//
////        PhotoSize photoSize = message.getPhoto().get(0);
////        final String fileId = photoSize.getFileId();
////        try {
////            final org.telegram.telegrambots.meta.api.objects.File file = sendApiMethod(new GetFile(fileId));
////            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//////            saveImage(imageUrl, localFileName);
////        } catch (TelegramApiException | IOException e) {
////            e.printStackTrace();
//////        } catch (IOException e) {
//////            throw new RuntimeException(e);
////        }
//
//        ///
////        try {
////            processingImage(localFileName);
////        } catch (Exception e) {
////            throw new RuntimeException(e);
////        }
////
////
////        ///
////////        System.out.println(message.getText());
//////        SendPhoto sendPhoto = new SendPhoto();
//////        sendPhoto.setChatId(message.getChatId().toString());
//////        InputFile newFile = new InputFile();
//////        newFile.setMedia(new File(localFileName));
//////        sendPhoto.setPhoto(newFile);
//////        sendPhoto.setCaption("Edited image");
////
//////        SendMessage sendMessage = new SendMessage();
//////        sendMessage.setChatId(message.getChatId().toString());
//////        sendMessage.setText("Your message: " + response);
////        try {
//////            execute(sendMessage);
////            execute(sendPhoto);
////        } catch (TelegramApiException e) {
////            throw new RuntimeException(e);
////        }
//    }
//
////    private ArrayList<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) {
////        List<PhotoSize> photoSizes = message.getPhoto();
////        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files = new ArrayList<>();
////        for (PhotoSize photoSize: photoSizes) {
////            final String fileId = photoSize.getFileId();
////            try {
////                files.add(sendApiMethod(new GetFile(fileId)));
//////                final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//////            saveImage(imageUrl, localFileName);
////            } catch (TelegramApiException e) {
////                e.printStackTrace();
////            }
////        }
////
////        return files;
////    }
//
//
//    public List<File> getFilesByMessage(Message message) {
//        List<File> fileList = new ArrayList<>();
//        // код для получения файлов из сообщения и преобразования их в java.io.File
//
//        return fileList;
//    }
//
//
//
//    private SendPhoto preparePhotoMessage(String localPath, String chatId) {
//        SendPhoto sendPhoto = new SendPhoto();
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath));
//        sendPhoto.setPhoto(newFile);
////        sendPhoto.setCaption("Edited image");
//        return sendPhoto;
//    }
//
////    public void processingImage(String fileName) throws Exception {
////        ///
////        final BufferedImage image = ImageUtils.getImage("src/main/java/task9_4_1/logoJAVA.jpg");
////        final RgbMaster rgbMaster = new RgbMaster(image);
////        rgbMaster.changeImage(FilterOperation::greyScale);
////        ImageUtils.saveImage(rgbMaster.getImage(),"src/main/java/task9_4_1/cloned_image.jpg");
////    }
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
//// КОНЕЦ ПРИМЕРА



//// ПРИМЕР 4  _ВСЁ РАБОТАЕТ, КАРТИНКИ ПЕРЕСЫЛАЮТСЯ

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import task9_5_1.functions.FilterOperation;
import task9_5_1.utils.ImageUtils;
import task9_5_1.utils.RgbMaster;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

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
//        final String localFileName = "src/main/java/task9_4_1/" + "received_image.jpeg";
        Message message = update.getMessage();
//        String response = message.getFrom().getId().toString();
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
        final BufferedImage image = ImageUtils.getImage("src/main/java/task9_5_1/logoJAVA.jpg");
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
}
//// КОНЕЦ ПРИМЕРА 4




//// ПРИМЕР 3
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import task9_4_1.utils.PhotoMessageUtils;
//
//import java.io.*;
//        import java.util.ArrayList;
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
//}
//// КОНЕЦ ПРИМЕРА 3




//// ПРИМЕР 2 _ВСЁ РАбОТАЕТ
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//import java.io.*;
//import java.net.URL;
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
//        Message message = update.getMessage();
//        String response = message.getFrom().getId().toString();
//        PhotoSize photoSize = message.getPhoto().get(0);
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = sendApiMethod(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, "src/main/java/task9_3_1/" + "received_image.jpeg");
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println(message.getText());
//        SendPhoto sendPhoto = new SendPhoto();
//        sendPhoto.setChatId(message.getChatId().toString());
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File("src/main/java/task9_3_1/" + "1 JAVA.jpg"));
//        sendPhoto.setPhoto(newFile);
//        sendPhoto.setCaption("This is Java logotype");
//
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(message.getChatId().toString());
//        sendMessage.setText("Your message: " + response);
//        try {
//            execute(sendMessage);
//            execute(sendPhoto);
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
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