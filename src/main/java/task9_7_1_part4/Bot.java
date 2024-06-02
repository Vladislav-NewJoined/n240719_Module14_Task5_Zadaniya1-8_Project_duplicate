package task9_7_1_part4;

//// ПРИМЕР _ 240531 0000 Взято из примера 4. Всё делает, но присылает одно лишнее изображение. Пытаюсь его убрать
//// надо менять метод private SendPhoto preparePhotoMessage2(String localPath, String chatId) - с двумя параметрами
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
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
//import task9_7_1_part4.commands.BotCommonCommands;
//import task9_7_1_part4.commands.AppBotCommand;
//import task9_7_1_part4.functions.FilterOperation;
//import task9_7_1_part4.functions.ImageOperation;
//import task9_7_1_part4.utils.PhotoMessageUtils;
//
//import java.io.*;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[]{BotCommonCommands.class};
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
//                execute(responseTextMessage2);     // TODO ОСТАВЛЯЕМ! ответ на кнопки help hello bye, вызываем библиотечный класс SendMessage
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
//        response = runCommand(message.getText());     // TODO ОСТАВЛЯЕМ! возврат изображениий (1-го и 4-х), вызывает метод runCommand
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        final String localFileName = "src/main/java/task9_7_1_part4/" + "cloned_image.jpg";
//        PhotoSize photoSize = message.getPhoto().get(0);
//        response = runCommand(message.getText());
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);     // TODO ОСТАВЛЯЕМ! ответ на кнопки и возврат изображений и сохранение их, вызов метода saveImage из класса preparePhotoMessage2
//        } catch (TelegramApiException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localFileName));
//
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//
//        try {
//            execute(sendMessage);     // TODO ВНИМАНИЕ: ОСТАВЛЯЕМ, но с оговоркой!  отменило надпись: "Команда не из кнопки" НАДО ПЕРЕНЕСТИ на возврат неск. изображений
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        SendPhoto sendPhoto = preparePhotoMessage2(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto.setPhoto(newFile);      // TODO ОСТАВЛЯЕМ!
//
//        try {
//            execute(sendPhoto);     // TODO ВАЖНО: ХОРОШО! УДАЛЯЕМ применит. к неск. изображениям! ЭТО ОБРАЩЕНИЕ К КЛАССУ preparePhotoMessage2 С ДВУМЯ АРГУМЕНТАМИ! Без него не возвращает одно изображение, на кнопки отвечает, кнопки правда НЕ СОЗДАЁТ, возвращает неск. изображений (но без надписи "cloned image), с ним не отвечает на кнопки! без него сохранило, но не вернуло одно ЦВЕТНОЕ изображение, вызов метода preparePhotoMessage с двумя параметрами
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        // TODO После этого:
//        // TODO Возвращает надпись "Команда не из кнопки"
//        // TODO Создаёт 4 кнопки, отвечает на них
//        // TODO Возвращает 4 изображения, сохраняет их
//        // TODO Возвращает надпись "cloned_image", только не в том месте
//        // TODO ОТРИЦАТЕЛЬНЫЙ: Возвращает одно ЦВЕТНОЕ изображение
//
//
//
//// Пишу для 7-го Примера
//        try {
//            SendMediaGroup responseMediaMessage2 = runPhotoFilter(message2);
//            if (responseMediaMessage2 != null) {
//                execute(responseMediaMessage2);     // TODO ОСТАВЛЯЕМ! т.к. без него не возвращает и не сохраняет неск. изображений. Кнопки создаёт, отвечает, НО возвращает ЦВЕТНОЕ изобр.
//                return;
//            }
//        } catch (/*InvocationTargetException | IllegalAccessException | */TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private SendPhoto preparePhotoMessage2(String localPath, String chatId) {
////// TODO НАШЁЛ! ЭТА СТРОКА ОТВЕЧАЕТ ЗА СОЗДАНИЕ ТРЁХ КНОПОК в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6, в методе SendPhoto preparePhotoMessage2
//        SendPhoto sendPhoto = new SendPhoto();
//// TODO НАШЁЛ! ЭТА СТРОКА ОТВЕЧАЕТ ЗА СОЗДАНИЕ ТРЁХ КНОПОК в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6, в методе SendPhoto preparePhotoMessage2
//        sendPhoto.setReplyMarkup(getKeyboard()); // TODO Это ТРИ кнопки; В ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6 В методе preparePhotoMessage2 с двумя параметрами. ДА! При комменте три кнопки не создадутся!
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath)); // TODO Здесь заменил File на java.io.File как в видеоуроке на мин 10 22
////        newFile.setMedia(new File(localPath)); // TODO Так было изначально
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//    }
//
//    // TODO 240529 1438 Здесь искать создание кнопок, Команда не из кнопки, возврат 1-го цветного изображения, cloned_image
//    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
//        Method[] classMethods = someClass.getDeclaredMethods();
//        ArrayList<AppBotCommand> commands = new ArrayList<>();
//        for (Method method : classMethods) {
//            AppBotCommand commandAnnotation = method.getAnnotation(AppBotCommand.class);
//            if (commandAnnotation != null) {
//                commands.add(commandAnnotation);
//            }
//        }
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        int columnCount = 3;
//        int rowsCount = commands.size() / columnCount + ((commands.size() % columnCount == 0) ? 0 : 1);
//        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
//            KeyboardRow row = new KeyboardRow();
//            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
//                int index = rowIndex * columnCount + columnIndex;
//                if (index >= commands.size()) continue;  // TODO 240529 1605 Ни на что не влияет
//                AppBotCommand command = commands.get(index);
//                KeyboardButton keyboardButton = new KeyboardButton(command.name());
//                row.add(keyboardButton);
//            }
//            keyboardRows.add(row);
//        }
//        return keyboardRows;
//    }
//
//
//// TODO 240529 1142 Ниже - это было до того как начал приводить в порядок ЗАПИСАННОЕ ВСЛЕД ЗА ВИДЕОУРОКОМ. Выше - это добавленное вновь.
//// TODO ВСЁ ЧТО НИЖЕ ЗАКОММЕНТИРОВАНО, В ИЗНАЧАЛЬНОМ ВАРИАНТЕ БЫЛО РАСКОММЕНТИРОВАНО
//
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
//                        method.setAccessible(true);     // TODO ОСТАВЛЯЕМ! Т.к. без него в консоли появляются красные строки, но на телеграм бот не влияет
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
//    private SendMessage runCommonCommand(Message message) throws InvocationTargetException, IllegalAccessException {
//        String text = message.getText();
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
//                if (command.name().equals(text)) {
//                    method.setAccessible(true);
//                    String responseText = (String) method.invoke(commands);
//                    if (responseText != null) {
//                        SendMessage sendMessage = new SendMessage();
//                        sendMessage.setChatId(message.getChatId().toString());
//                        sendMessage.setText(responseText);
//                        return sendMessage;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    private SendMediaGroup runPhotoFilter(Message message) {
//        ImageOperation operation = FilterOperation::greyScale;
//        List<org.telegram.telegrambots.meta.api.objects.File> files = getFilesByMessage(message);       //  TODO ВОЗМОЖНО НАДО СДЕЛАТЬ ПРОСТО File
////        List<org.telegram.telegrambots.meta.api.objects.File> files = getFilesByMessage(message);
//        try {
//            List<String> paths = PhotoMessageUtils.savePhotos(files, getBotToken());       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            String chatId = message.getChatId().toString();       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            return preparePhotoMessage(paths, operation, chatId);       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Так было изначально
//        List<PhotoSize> photoSizes = message.getPhoto();
//        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files = new ArrayList<>(); // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Так было изначально
//        for (PhotoSize photoSize : photoSizes) {
//            final String fileId = photoSize.getFileId();
//            try {
//                files.add(sendApiMethod(new GetFile(fileId)));       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        return files;
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
//
//        SendMediaGroup mediaGroup2 = new SendMediaGroup();
//        ArrayList<InputMedia> medias2 = new ArrayList<>();
//        for (String path : localPaths) {
//            InputMediaPhoto inputMedia = new InputMediaPhoto();
//            PhotoMessageUtils.processingImage(path, operation);
//            inputMedia.setMedia(new File(path), "path");
//            medias2.add(inputMedia);
//        }
//        mediaGroup2.setMedias(medias2);
//        mediaGroup2.setChatId(chatId);
//
//        // Отправка текстового сообщения "clonedimages"
//        SendMessage sendClonedImageMessage = new SendMessage();
//        sendClonedImageMessage.setChatId(chatId);
//        sendClonedImageMessage.setText("Ниже Вам отправлены обработанные изображения:");
//        execute(sendClonedImageMessage);
//        return mediaGroup2;
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
//
//    private void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);         //  TODO ВНИМАНИЕ: ВСЁ ТАКИ ООСТАВЛЯЕМ, т.к. без него кнопки не создаёт!  УДАЛЯЕМ или? И без него кнопки не создаёт! ЗДЕСЬ ПРАВИЛЬНО: одно изобр. не возвращает, группу - возвращает. Но не возвращает надпись: "cloned_image"
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//}




//// ПРИМЕР 6 ОБРАЗЕЦ. Присылает одно лишнее цветное изображение. Осталось от него избавиться.
//// надо менять метод private SendPhoto preparePhotoMessage2(String localPath, String chatId) - с двумя параметрами
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
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
//import task9_7_1_part4.commands.BotCommonCommands;
//import task9_7_1_part4.commands.AppBotCommand;
//import task9_7_1_part4.functions.FilterOperation;
//import task9_7_1_part4.functions.ImageOperation;
//import task9_7_1_part4.utils.PhotoMessageUtils;
//
//import java.io.*;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[]{BotCommonCommands.class};
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
//                execute(responseTextMessage2);     // TODO ОСТАВЛЯЕМ! ответ на кнопки help hello bye, вызываем библиотечный класс SendMessage
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
//        response = runCommand(message.getText());     // TODO ОСТАВЛЯЕМ! возврат изображениий (1-го и 4-х), вызывает метод runCommand
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        final String localFileName = "src/main/java/task9_7_1_part4/" + "cloned_image.jpg";
//        PhotoSize photoSize = message.getPhoto().get(0);
//        response = runCommand(message.getText());
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);     // TODO ОСТАВЛЯЕМ! ответ на кнопки и возврат изображений и сохранение их, вызов метода saveImage из класса preparePhotoMessage2
//        } catch (TelegramApiException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localFileName));
//
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//
//        try {
//            execute(sendMessage);     // TODO ВНИМАНИЕ: ОСТАВЛЯЕМ, но с оговоркой!  отменило надпись: "Команда не из кнопки" НАДО ПЕРЕНЕСТИ на возврат неск. изображений
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        SendPhoto sendPhoto = preparePhotoMessage2(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto.setPhoto(newFile);      // TODO ОСТАВЛЯЕМ!
//
//        try {
//            execute(sendPhoto);     // TODO ВАЖНО: ХОРОШО! УДАЛЯЕМ применит. к неск. изображениям! ЭТО ОБРАЩЕНИЕ К КЛАССУ preparePhotoMessage2 С ДВУМЯ АРГУМЕНТАМИ! Без него не возвращает одно изображение, на кнопки отвечает, кнопки правда НЕ СОЗДАЁТ, возвращает неск. изображений (но без надписи "cloned image), с ним не отвечает на кнопки! без него сохранило, но не вернуло одно ЦВЕТНОЕ изображение, вызов метода preparePhotoMessage с двумя параметрами
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        // TODO После этого:
//        // TODO Возвращает надпись "Команда не из кнопки"
//        // TODO Создаёт 4 кнопки, отвечает на них
//        // TODO Возвращает 4 изображения, сохраняет их
//        // TODO Возвращает надпись "cloned_image", только не в том месте
//        // TODO ОТРИЦАТЕЛЬНЫЙ: Возвращает одно ЦВЕТНОЕ изображение
//
//
//
//// Пишу для 7-го Примера
//        try {
//            SendMediaGroup responseMediaMessage2 = runPhotoFilter(message2);
//            if (responseMediaMessage2 != null) {
//                execute(responseMediaMessage2);     // TODO ОСТАВЛЯЕМ! т.к. без него не возвращает и не сохраняет неск. изображений. Кнопки создаёт, отвечает, НО возвращает ЦВЕТНОЕ изобр.
//                return;
//            }
//        } catch (/*InvocationTargetException | IllegalAccessException | */TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private SendPhoto preparePhotoMessage2(String localPath, String chatId) {
////// TODO НАШЁЛ! ЭТА СТРОКА ОТВЕЧАЕТ ЗА СОЗДАНИЕ ТРЁХ КНОПОК в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6, в методе SendPhoto preparePhotoMessage2
//        SendPhoto sendPhoto = new SendPhoto();
//// TODO НАШЁЛ! ЭТА СТРОКА ОТВЕЧАЕТ ЗА СОЗДАНИЕ ТРЁХ КНОПОК в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6, в методе SendPhoto preparePhotoMessage2
//        sendPhoto.setReplyMarkup(getKeyboard()); // TODO Это ТРИ кнопки; В ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6 В методе preparePhotoMessage2 с двумя параметрами. ДА! При комменте три кнопки не создадутся!
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath)); // TODO Здесь заменил File на java.io.File как в видеоуроке на мин 10 22
////        newFile.setMedia(new File(localPath)); // TODO Так было изначально
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//    }
//
//    // TODO 240529 1438 Здесь искать создание кнопок, Команда не из кнопки, возврат 1-го цветного изображения, cloned_image
//    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
//        Method[] classMethods = someClass.getDeclaredMethods();
//        ArrayList<AppBotCommand> commands = new ArrayList<>();
//        for (Method method : classMethods) {
//            AppBotCommand commandAnnotation = method.getAnnotation(AppBotCommand.class);
//            if (commandAnnotation != null) {
//                commands.add(commandAnnotation);
//            }
//        }
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        int columnCount = 3;
//        int rowsCount = commands.size() / columnCount + ((commands.size() % columnCount == 0) ? 0 : 1);
//        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
//            KeyboardRow row = new KeyboardRow();
//            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
//                int index = rowIndex * columnCount + columnIndex;
//                if (index >= commands.size()) continue;  // TODO 240529 1605 Ни на что не влияет
//                AppBotCommand command = commands.get(index);
//                KeyboardButton keyboardButton = new KeyboardButton(command.name());
//                row.add(keyboardButton);
//            }
//            keyboardRows.add(row);
//        }
//        return keyboardRows;
//    }
//
//
//// TODO 240529 1142 Ниже - это было до того как начал приводить в порядок ЗАПИСАННОЕ ВСЛЕД ЗА ВИДЕОУРОКОМ. Выше - это добавленное вновь.
//// TODO ВСЁ ЧТО НИЖЕ ЗАКОММЕНТИРОВАНО, В ИЗНАЧАЛЬНОМ ВАРИАНТЕ БЫЛО РАСКОММЕНТИРОВАНО
//
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
//                        method.setAccessible(true);     // TODO ОСТАВЛЯЕМ! Т.к. без него в консоли появляются красные строки, но на телеграм бот не влияет
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
//    private SendMessage runCommonCommand(Message message) throws InvocationTargetException, IllegalAccessException {
//        String text = message.getText();
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
//                if (command.name().equals(text)) {
//                    method.setAccessible(true);
//                    String responseText = (String) method.invoke(commands);
//                    if (responseText != null) {
//                        SendMessage sendMessage = new SendMessage();
//                        sendMessage.setChatId(message.getChatId().toString());
//                        sendMessage.setText(responseText);
//                        return sendMessage;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    private SendMediaGroup runPhotoFilter(Message message) {
//        ImageOperation operation = FilterOperation::greyScale;
//        List<org.telegram.telegrambots.meta.api.objects.File> files = getFilesByMessage(message);       //  TODO ВОЗМОЖНО НАДО СДЕЛАТЬ ПРОСТО File
////        List<org.telegram.telegrambots.meta.api.objects.File> files = getFilesByMessage(message);
//        try {
//            List<String> paths = PhotoMessageUtils.savePhotos(files, getBotToken());       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            String chatId = message.getChatId().toString();       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            return preparePhotoMessage(paths, operation, chatId);       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Так было изначально
//        List<PhotoSize> photoSizes = message.getPhoto();
//        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files = new ArrayList<>(); // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Так было изначально
//        for (PhotoSize photoSize : photoSizes) {
//            final String fileId = photoSize.getFileId();
//            try {
//                files.add(sendApiMethod(new GetFile(fileId)));       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        return files;
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
//
//        SendMediaGroup mediaGroup2 = new SendMediaGroup();
//        ArrayList<InputMedia> medias2 = new ArrayList<>();
//        for (String path : localPaths) {
//            InputMediaPhoto inputMedia = new InputMediaPhoto();
//            PhotoMessageUtils.processingImage(path, operation);
//            inputMedia.setMedia(new File(path), "path");
//            medias2.add(inputMedia);
//        }
//        mediaGroup2.setMedias(medias2);
//        mediaGroup2.setChatId(chatId);
//
//        // Отправка текстового сообщения "clonedimages"
//        SendMessage sendClonedImageMessage = new SendMessage();
//        sendClonedImageMessage.setChatId(chatId);
//        sendClonedImageMessage.setText("Ниже Вам отправлены обработанные изображения:");
//        execute(sendClonedImageMessage);
//        return mediaGroup2;
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
//
//    private void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);         //  TODO ВНИМАНИЕ: ВСЁ ТАКИ ООСТАВЛЯЕМ, т.к. без него кнопки не создаёт!  УДАЛЯЕМ или? И без него кнопки не создаёт! ЗДЕСЬ ПРАВИЛЬНО: одно изобр. не возвращает, группу - возвращает. Но не возвращает надпись: "cloned_image"
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//}
//// КОНЕЦ ПРИМЕРА 6




//// ПРИМЕР 5 240531 1522 Всё делает правильно, НО 3 КНОПКИ НЕ СОЗДАЁТ. Закмментирован фрагмент начиная со стр SendPhoto sendPhoto = preparePhotoMessage2(localFileName, message.getChatId().toString());
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
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
//import task9_7_1_part4.commands.BotCommonCommands;
//import task9_7_1_part4.commands.AppBotCommand;
//import task9_7_1_part4.functions.FilterOperation;
//import task9_7_1_part4.functions.ImageOperation;
//import task9_7_1_part4.utils.PhotoMessageUtils;
//
//import java.io.*;
//        import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URI;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[]{BotCommonCommands.class};
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
//                execute(responseTextMessage2);     // TODO ОСТАВЛЯЕМ! ответ на кнопки help hello bye, вызываем библиотечный класс SendMessage
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
//        response = runCommand(message.getText());     // TODO ОСТАВЛЯЕМ! возврат изображениий (1-го и 4-х), вызывает метод runCommand
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        final String localFileName = "src/main/java/task9_7_1_part4/" + "cloned_image.jpg";
//        PhotoSize photoSize = message.getPhoto().get(0);
//        response = runCommand(message.getText());
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);     // TODO ОСТАВЛЯЕМ! ответ на кнопки и возврат изображений и сохранение их, вызов метода saveImage из класса preparePhotoMessage2
//        } catch (TelegramApiException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localFileName));
//
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//
//        try {
//            execute(sendMessage);     // TODO ВНИМАНИЕ: ОСТАВЛЯЕМ, но с оговоркой!  отменило надпись: "Команда не из кнопки" НАДО ПЕРЕНЕСТИ на возврат неск. изображений
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
////        SendPhoto sendPhoto = preparePhotoMessage2(localFileName, message.getChatId().toString());
////        ///
////        sendPhoto.setPhoto(newFile);      // TODO ОСТАВЛЯЕМ!
////
////        try {
////            execute(sendPhoto);     // TODO ВАЖНО: ХОРОШО! УДАЛЯЕМ применит. к неск. изображениям! ЭТО ОБРАЩЕНИЕ К КЛАССУ preparePhotoMessage2 С ДВУМЯ АРГУМЕНТАМИ! Без него не возвращает одно изображение, на кнопки отвечает, кнопки правда НЕ СОЗДАЁТ, возвращает неск. изображений (но без надписи "cloned image), с ним не отвечает на кнопки! без него сохранило, но не вернуло одно ЦВЕТНОЕ изображение, вызов метода preparePhotoMessage с двумя параметрами
////        } catch (TelegramApiException e) {
////            e.printStackTrace();
////        }
//        // TODO После этого:
//        // TODO Возвращает надпись "Команда не из кнопки"
//        // TODO Создаёт 4 кнопки, отвечает на них
//        // TODO Возвращает 4 изображения, сохраняет их
//        // TODO Возвращает надпись "cloned_image", только не в том месте
//        // TODO ОТРИЦАТЕЛЬНЫЙ: Возвращает одно ЦВЕТНОЕ изображение
//
//
//
//
//// Пишу для 7-го Примера
//        try {
//            SendMediaGroup responseMediaMessage2 = runPhotoFilter(message2);
//            if (responseMediaMessage2 != null) {
//                execute(responseMediaMessage2);     // TODO ОСТАВЛЯЕМ! т.к. без него не возвращает и не сохраняет неск. изображений. Кнопки создаёт, отвечает, НО возвращает ЦВЕТНОЕ изобр.
//                return;
//            }
//        } catch (/*InvocationTargetException | IllegalAccessException | */TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private SendPhoto preparePhotoMessage2(String localPath, String chatId) {
//        SendPhoto sendPhoto = new SendPhoto();
//// TODO НАШЁЛ! ЭТА СТРОКА ОТВЕЧАЕТ ЗА СОЗДАНИЕ ТРЁХ КНОПОК в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6, в методе SendPhoto preparePhotoMessage2
//        sendPhoto.setReplyMarkup(getKeyboard()); // Это ТРИ кнопки  // TODO в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6 В методе preparePhotoMessage2. ДА! При комменте три кнопки не создадутся!
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath)); // TODO Здесь заменил File на java.io.File как в видеоуроке на мин 10 22
////        newFile.setMedia(new File(localPath)); // TODO Так было изначально
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//    }
//
//    // TODO 240529 1438 Здесь искать создание кнопок, Команда не из кнопки, возврат 1-го цветного изображения, cloned_image
//    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
//        Method[] classMethods = someClass.getDeclaredMethods();
//        ArrayList<AppBotCommand> commands = new ArrayList<>();
//        for (Method method : classMethods) {
//            AppBotCommand commandAnnotation = method.getAnnotation(AppBotCommand.class);
//            if (commandAnnotation != null) {
//                commands.add(commandAnnotation);
//            }
//        }
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        int columnCount = 3;
//        int rowsCount = commands.size() / columnCount + ((commands.size() % columnCount == 0) ? 0 : 1);
//        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
//            KeyboardRow row = new KeyboardRow();
//            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
//                int index = rowIndex * columnCount + columnIndex;
//                if (index >= commands.size()) continue;  // TODO 240529 1605 Ни на что не влияет
//                AppBotCommand command = commands.get(index);
//                KeyboardButton keyboardButton = new KeyboardButton(command.name());
//                row.add(keyboardButton);
//            }
//            keyboardRows.add(row);
//        }
//        return keyboardRows;
//    }
//
//
//
//
//
//
//// TODO 240529 1142 Ниже - это было до того как начал приводить в порядок ЗАПИСАННОЕ ВСЛЕД ЗА ВИДЕОУРОКОМ. Выше - это добавленное вновь.
//// TODO ВСЁ ЧТО НИЖЕ ЗАКОММЕНТИРОВАНО, В ИЗНАЧАЛЬНОМ ВАРИАНТЕ БЫЛО РАСКОММЕНТИРОВАНО
//
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
//                        method.setAccessible(true);     // TODO ОСТАВЛЯЕМ! Т.к. без него в консоли появляются красные строки, но на телеграм бот не влияет
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
//    private SendMessage runCommonCommand(Message message) throws InvocationTargetException, IllegalAccessException {
//        String text = message.getText();
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
//                if (command.name().equals(text)) {
//                    method.setAccessible(true);
//                    String responseText = (String) method.invoke(commands);
//                    if (responseText != null) {
//                        SendMessage sendMessage = new SendMessage();
//                        sendMessage.setChatId(message.getChatId().toString());
//                        sendMessage.setText(responseText);
//                        return sendMessage;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    private SendMediaGroup runPhotoFilter(Message message) {
//        ImageOperation operation = FilterOperation::greyScale;
//        List<org.telegram.telegrambots.meta.api.objects.File> files = getFilesByMessage(message);       //  TODO ВОЗМОЖНО НАДО СДЕЛАТЬ ПРОСТО File
//
////        List<File> files = getFilesByMessage(message);
//        try {
//            List<String> paths = PhotoMessageUtils.savePhotos(files, getBotToken());       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            String chatId = message.getChatId().toString();       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            return preparePhotoMessage(paths, operation, chatId);       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Так было изначально
//        List<PhotoSize> photoSizes = message.getPhoto();
//        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files = new ArrayList<>(); // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Так было изначально
//        for (PhotoSize photoSize : photoSizes) {
//            final String fileId = photoSize.getFileId();
//            try {
//                files.add(sendApiMethod(new GetFile(fileId)));       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        return files;
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
//
//
//
//        SendMediaGroup mediaGroup2 = new SendMediaGroup();
//        ArrayList<InputMedia> medias2 = new ArrayList<>();
//        for (String path : localPaths) {
//            InputMediaPhoto inputMedia = new InputMediaPhoto();
//            PhotoMessageUtils.processingImage(path, operation);
//            inputMedia.setMedia(new File(path), "path");
//            medias2.add(inputMedia);
//        }
//        mediaGroup2.setMedias(medias2);
//        mediaGroup2.setChatId(chatId);
//
//        // Отправка текстового сообщения "clonedimages"
//        SendMessage sendClonedImageMessage = new SendMessage();
//        sendClonedImageMessage.setChatId(chatId);
//        sendClonedImageMessage.setText("Ниже Вам отправлены обработанные изображения:");
//        execute(sendClonedImageMessage);
//        return mediaGroup2;
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
//
//    private void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);         //  TODO ВНИМАНИЕ: ВСЁ ТАКИ ООСТАВЛЯЕМ, т.к. без него кнопки не создаёт!  УДАЛЯЕМ или? И без него кнопки не создаёт! ЗДЕСЬ ПРАВИЛЬНО: одно изобр. не возвращает, группу - возвращает. Но не возвращает надпись: "cloned_image"
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//}
//// КОНЕЦ ПРИМЕРА 5




//// ПРИМЕР 4 240530 1447 БРАТЬ КАК ОБРАЗЕЦ _Удалить одно лишнее изображение
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
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
//import task9_7_1_part4.commands.BotCommonCommands;
//import task9_7_1_part4.commands.AppBotCommand;
//import task9_7_1_part4.functions.FilterOperation;
//import task9_7_1_part4.functions.ImageOperation;
//import task9_7_1_part4.utils.PhotoMessageUtils;
//
//import java.io.*;
//        import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[]{BotCommonCommands.class};
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
//                execute(responseTextMessage2);     // TODO ОСТАВЛЯЕМ! ответ на кнопки help hello bye, вызываем библиотечный класс SendMessage
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
//        response = runCommand(message.getText());     // TODO ОСТАВЛЯЕМ! возврат изображениий (1-го и 4-х), вызывает метод runCommand
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        final String localFileName = "src/main/java/task9_7_1_part4/" + "cloned_image.jpg";
//        PhotoSize photoSize = message.getPhoto().get(0);
//        response = runCommand(message.getText());
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);     // TODO ОСТАВЛЯЕМ! ответ на кнопки и возврат изображений и сохранение их, вызов метода saveImage из класса preparePhotoMessage2
//        } catch (TelegramApiException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localFileName));
//
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//
//        try {
//            execute(sendMessage);     // TODO ВНИМАНИЕ: ОСТАВЛЯЕМ, но с оговоркой!  отменило надпись: "Команда не из кнопки" НАДО ПЕРЕНЕСТИ на возврат неск. изображений
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        SendPhoto sendPhoto = preparePhotoMessage2(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto.setPhoto(newFile);      // TODO ОСТАВЛЯЕМ!
//
//        try {
//            execute(sendPhoto);     // TODO ВАЖНО: ХОРОШО! УДАЛЯЕМ применит. к неск. изображениям! ЭТО ОБРАЩЕНИЕ К КЛАССУ preparePhotoMessage2 С ДВУМЯ АРГУМЕНТАМИ! Без него не возвращает одно изображение, на кнопки отвечает, кнопки правда НЕ СОЗДАЁТ, возвращает неск. изображений (но без надписи "cloned image), с ним не отвечает на кнопки! без него сохранило, но не вернуло одно ЦВЕТНОЕ изображение, вызов метода preparePhotoMessage с двумя параметрами
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        // TODO После этого:
//        // TODO Возвращает надпись "Команда не из кнопки"
//        // TODO Создаёт 4 кнопки, отвечает на них
//        // TODO Возвращает 4 изображения, сохраняет их
//        // TODO Возвращает надпись "cloned_image", только не в том месте
//        // TODO ОТРИЦАТЕЛЬНЫЙ: Возвращает одно ЦВЕТНОЕ изображение
//
//
//
//
//// Пишу для 7-го Примера
//        try {
//            SendMediaGroup responseMediaMessage2 = runPhotoFilter(message2);
//            if (responseMediaMessage2 != null) {
//                execute(responseMediaMessage2);     // TODO ОСТАВЛЯЕМ! т.к. без него не возвращает и не сохраняет неск. изображений. Кнопки создаёт, отвечает, НО возвращает ЦВЕТНОЕ изобр.
//                return;
//            }
//        } catch (/*InvocationTargetException | IllegalAccessException | */TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private SendPhoto preparePhotoMessage2(String localPath, String chatId) {
//        SendPhoto sendPhoto = new SendPhoto();
//// TODO НАШЁЛ! ЭТА СТРОКА ОТВЕЧАЕТ ЗА СОЗДАНИЕ ТРЁХ КНОПОК в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6, в методе SendPhoto preparePhotoMessage2
//        sendPhoto.setReplyMarkup(getKeyboard()); // Это ТРИ кнопки  // TODO в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6 В методе preparePhotoMessage2. ДА! При комменте три кнопки не создадутся!
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath)); // TODO Здесь заменил File на java.io.File как в видеоуроке на мин 10 22
////        newFile.setMedia(new File(localPath)); // TODO Так было изначально
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//    }
//
//    // TODO 240529 1438 Здесь искать создание кнопок, Команда не из кнопки, возврат 1-го цветного изображения, cloned_image
//    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
//        Method[] classMethods = someClass.getDeclaredMethods();
//        ArrayList<AppBotCommand> commands = new ArrayList<>();
//        for (Method method : classMethods) {
//            AppBotCommand commandAnnotation = method.getAnnotation(AppBotCommand.class);
//            if (commandAnnotation != null) {
//                commands.add(commandAnnotation);
//            }
//        }
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        int columnCount = 3;
//        int rowsCount = commands.size() / columnCount + ((commands.size() % columnCount == 0) ? 0 : 1);
//        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
//            KeyboardRow row = new KeyboardRow();
//            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
//                int index = rowIndex * columnCount + columnIndex;
//                if (index >= commands.size()) continue;  // TODO 240529 1605 Ни на что не влияет
//                AppBotCommand command = commands.get(index);
//                KeyboardButton keyboardButton = new KeyboardButton(command.name());
//                row.add(keyboardButton);
//            }
//            keyboardRows.add(row);
//        }
//        return keyboardRows;
//    }
//
//
//
//
//
//
//// TODO 240529 1142 Ниже - это было до того как начал приводить в порядок ЗАПИСАННОЕ ВСЛЕД ЗА ВИДЕОУРОКОМ. Выше - это добавленное вновь.
//// TODO ВСЁ ЧТО НИЖЕ ЗАКОММЕНТИРОВАНО, В ИЗНАЧАЛЬНОМ ВАРИАНТЕ БЫЛО РАСКОММЕНТИРОВАНО
//
////@Override
////public void onUpdateReceived(Update update) {
////    Message message = update.getMessage();
////    try {
////        SendMessage responseTestMessage = runCommonCommand(message);
////        if (responseTestMessage != null) {
////            execute(responseTestMessage);
////            return;
////        }
////    } catch (InvocationTargetException | IllegalAccessException | TelegramApiException e) {
////        e.printStackTrace();
////    }
////    try {
////        SendMediaGroup responseMediaMessage = runPhotoFilter(message);
////        if (responseMediaMessage != null) {
////            execute(responseMediaMessage);
////            return;
////        }
////    } catch (TelegramApiException e) {
////        e.printStackTrace();
////    }
////}
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
//                        method.setAccessible(true);     // TODO ОСТАВЛЯЕМ! Т.к. без него в консоли появляются красные строки, но на телеграм бот не влияет
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
//    private SendMessage runCommonCommand(Message message) throws InvocationTargetException, IllegalAccessException {
//        String text = message.getText();
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
//                if (command.name().equals(text)) {
//                    method.setAccessible(true);
//                    String responseText = (String) method.invoke(commands);
//                    if (responseText != null) {
//                        SendMessage sendMessage = new SendMessage();
//                        sendMessage.setChatId(message.getChatId().toString());
//                        sendMessage.setText(responseText);
//                        return sendMessage;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    private SendMediaGroup runPhotoFilter(Message message) {
//        ImageOperation operation = FilterOperation::greyScale;
//        List<org.telegram.telegrambots.meta.api.objects.File> files = getFilesByMessage(message);       //  TODO ВОЗМОЖНО НАДО СДЕЛАТЬ ПРОСТО File
////        List<org.telegram.telegrambots.meta.api.objects.File> files = getFilesByMessage(message);
//        try {
//            List<String> paths = PhotoMessageUtils.savePhotos(files, getBotToken());       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            String chatId = message.getChatId().toString();       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            return preparePhotoMessage(paths, operation, chatId);       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Так было изначально
//        List<PhotoSize> photoSizes = message.getPhoto();
//        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files = new ArrayList<>(); // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Так было изначально
//        for (PhotoSize photoSize : photoSizes) {
//            final String fileId = photoSize.getFileId();
//            try {
//                files.add(sendApiMethod(new GetFile(fileId)));       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        return files;
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
//
//
//
//        SendMediaGroup mediaGroup2 = new SendMediaGroup();
//        ArrayList<InputMedia> medias2 = new ArrayList<>();
//        for (String path : localPaths) {
//            InputMediaPhoto inputMedia = new InputMediaPhoto();
//            PhotoMessageUtils.processingImage(path, operation);
//            inputMedia.setMedia(new File(path), "path");
//            medias2.add(inputMedia);
//        }
//        mediaGroup2.setMedias(medias2);
//        mediaGroup2.setChatId(chatId);
//
//        // Отправка текстового сообщения "clonedimages"
//        SendMessage sendClonedImageMessage = new SendMessage();
//        sendClonedImageMessage.setChatId(chatId);
//        sendClonedImageMessage.setText("Ниже Вам отправлены обработанные изображения:");
//        execute(sendClonedImageMessage);
//        return mediaGroup2;
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
////    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
////        Method[] classMethods = someClass.getDeclaredMethods();
////        ArrayList<AppBotCommand> commands = new ArrayList<>();
////        for (Method method : classMethods) {
////            if (method.isAnnotationPresent(AppBotCommand.class)) {
////                commands.add(method.getAnnotation(AppBotCommand.class));
////            }
////        }
////        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
////        int columnCount = 3;
////        int rowsCount = commands.size() / columnCount + ((commands.size() % columnCount == 0) ? 0 : 1);
////        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
////            KeyboardRow row = new KeyboardRow();
////            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
////                int index = rowIndex * columnCount + columnIndex;
////                if (index >= commands.size()) continue;
////                AppBotCommand command = commands.get(rowIndex * columnCount + columnIndex);
////                KeyboardButton keyboardButton = new KeyboardButton(command.name());
////                row.add(keyboardButton);
////            }
////            keyboardRows.add(row);
////        }
////        return keyboardRows;
////    }
//
//    private void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);         //  TODO ВНИМАНИЕ: ВСЁ ТАКИ ООСТАВЛЯЕМ, т.к. без него кнопки не создаёт!  УДАЛЯЕМ или? И без него кнопки не создаёт! ЗДЕСЬ ПРАВИЛЬНО: одно изобр. не возвращает, группу - возвращает. Но не возвращает надпись: "cloned_image"
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//}
//// КОНЕЦ ПРИМЕРА 4




//// ПРИМЕР 2 240530 0837 Осталось удалить возврат одного изображения и переместить надпись "cloned_image"
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
//import task9_7_1_part4.commands.BotCommonCommands;
//import task9_7_1_part4.commands.AppBotCommand;
//import task9_7_1_part4.functions.FilterOperation;
//import task9_7_1_part4.functions.ImageOperation;
//import task9_7_1_part4.utils.PhotoMessageUtils;
//
//import java.io.*;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[]{BotCommonCommands.class};
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
//                execute(responseTextMessage2);     // TODO ОСТАВЛЯЕМ! ответ на кнопки help hello bye, вызываем библиотечный класс SendMessage
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
//        response = runCommand(message.getText());     // TODO ОСТАВЛЯЕМ! возврат изображениий (1-го и 4-х), вызывает метод runCommand
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        final String localFileName = "src/main/java/task9_7_1_part4/" + "cloned_image.jpg";
//        PhotoSize photoSize = message.getPhoto().get(0);
//        response = runCommand(message.getText());
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);     // TODO ОСТАВЛЯЕМ! ответ на кнопки и возврат изображений и сохранение их, вызов метода saveImage из класса preparePhotoMessage2
//        } catch (TelegramApiException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localFileName));
//
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//
//        try {
//            execute(sendMessage);     // TODO ВНИМАНИЕ: ОСТАВЛЯЕМ, но с оговоркой!  отменило надпись: "Команда не из кнопки" НАДО ПЕРЕНЕСТИ на возврат неск. изображений
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        SendPhoto sendPhoto = preparePhotoMessage2(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto.setPhoto(newFile);      // TODO ОСТАВЛЯЕМ!
//        sendPhoto.setCaption("cloned_image");      // TODO ПЕРЕНОСИМ ВО ФРАГМЕНТ С ВОЗВРАТОМ НЕСКОЛЬКИХ ИЗОБРАЖЕНИЙ
//
//        try {
//            execute(sendPhoto);     // TODO ВАЖНО: ХОРОШО! УДАЛЯЕМ применит. к неск. изображениям! ЭТО ОБРАЩЕНИЕ К КЛАССУ preparePhotoMessage2 С ДВУМЯ АРГУМЕНТАМИ! Без него не возвращает одно изображение, на кнопки отвечает, кнопки правда НЕ СОЗДАЁТ, возвращает неск. изображений (но без надписи "cloned image), с ним не отвечает на кнопки! без него сохранило, но не вернуло одно ЦВЕТНОЕ изображение, вызов метода preparePhotoMessage с двумя параметрами
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        // TODO После этого:
//        // TODO Возвращает надпись "Команда не из кнопки"
//        // TODO Создаёт 4 кнопки, отвечает на них
//        // TODO Возвращает 4 изображения, сохраняет их
//        // TODO Возвращает надпись "cloned_image", только не в том месте
//        // TODO ОТРИЦАТЕЛЬНЫЙ: Возвращает одно ЦВЕТНОЕ изображение
//
//
//
//
//// Пишу для 7-го Примера
//        try {
//            SendMediaGroup responseMediaMessage2 = runPhotoFilter(message2);
//            if (responseMediaMessage2 != null) {
//                execute(responseMediaMessage2);     // TODO ОСТАВЛЯЕМ! т.к. без него не возвращает и не сохраняет неск. изображений. Кнопки создаёт, отвечает, НО возвращает ЦВЕТНОЕ изобр.
//                return;
//            }
//        } catch (/*InvocationTargetException | IllegalAccessException | */TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private SendPhoto preparePhotoMessage2(String localPath, String chatId) {
//        SendPhoto sendPhoto = new SendPhoto();
//// TODO НАШЁЛ! ЭТА СТРОКА ОТВЕЧАЕТ ЗА СОЗДАНИЕ ТРЁХ КНОПОК в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6, в методе SendPhoto preparePhotoMessage2
//        sendPhoto.setReplyMarkup(getKeyboard()); // Это ТРИ кнопки  // TODO в ПРИМЕРЕ СНАЧАЛА 4, ТЕПЕРЬ 6 В методе preparePhotoMessage2. ДА! При комменте три кнопки не создадутся!
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath)); // TODO Здесь заменил File на java.io.File как в видеоуроке на мин 10 22
////        newFile.setMedia(new File(localPath)); // TODO Так было изначально
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//    }
//
//    // TODO 240529 1438 Здесь искать создание кнопок, Команда не из кнопки, возврат 1-го цветного изображения, cloned_image
//    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
//        Method[] classMethods = someClass.getDeclaredMethods();
//        ArrayList<AppBotCommand> commands = new ArrayList<>();
//        for (Method method : classMethods) {
//            AppBotCommand commandAnnotation = method.getAnnotation(AppBotCommand.class);
//            if (commandAnnotation != null) {
//                commands.add(commandAnnotation);
//            }
//        }
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        int columnCount = 3;
//        int rowsCount = commands.size() / columnCount + ((commands.size() % columnCount == 0) ? 0 : 1);
//        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
//            KeyboardRow row = new KeyboardRow();
//            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
//                int index = rowIndex * columnCount + columnIndex;
//                if (index >= commands.size()) continue;  // TODO 240529 1605 Ни на что не влияет
//                AppBotCommand command = commands.get(index);
//                KeyboardButton keyboardButton = new KeyboardButton(command.name());
//                row.add(keyboardButton);
//            }
//            keyboardRows.add(row);
//        }
//        return keyboardRows;
//    }
//
//
//
//
//
//
//// TODO 240529 1142 Ниже - это было до того как начал приводить в порядок ЗАПИСАННОЕ ВСЛЕД ЗА ВИДЕОУРОКОМ. Выше - это добавленное вновь.
//// TODO ВСЁ ЧТО НИЖЕ ЗАКОММЕНТИРОВАНО, В ИЗНАЧАЛЬНОМ ВАРИАНТЕ БЫЛО РАСКОММЕНТИРОВАНО
//
////@Override
////public void onUpdateReceived(Update update) {
////    Message message = update.getMessage();
////    try {
////        SendMessage responseTestMessage = runCommonCommand(message);
////        if (responseTestMessage != null) {
////            execute(responseTestMessage);
////            return;
////        }
////    } catch (InvocationTargetException | IllegalAccessException | TelegramApiException e) {
////        e.printStackTrace();
////    }
////    try {
////        SendMediaGroup responseMediaMessage = runPhotoFilter(message);
////        if (responseMediaMessage != null) {
////            execute(responseMediaMessage);
////            return;
////        }
////    } catch (TelegramApiException e) {
////        e.printStackTrace();
////    }
////}
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
//                        method.setAccessible(true);     // TODO ОСТАВЛЯЕМ! Т.к. без него в консоли появляются красные строки, но на телеграм бот не влияет
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
//    private SendMessage runCommonCommand(Message message) throws InvocationTargetException, IllegalAccessException {
//        String text = message.getText();
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
//                if (command.name().equals(text)) {
//                    method.setAccessible(true);
//                    String responseText = (String) method.invoke(commands);
//                    if (responseText != null) {
//                        SendMessage sendMessage = new SendMessage();
//                        sendMessage.setChatId(message.getChatId().toString());
//                        sendMessage.setText(responseText);
//                        return sendMessage;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    private SendMediaGroup runPhotoFilter(Message message) {
//        ImageOperation operation = FilterOperation::greyScale;
//        List<org.telegram.telegrambots.meta.api.objects.File> files = getFilesByMessage(message);       //  TODO ВОЗМОЖНО НАДО СДЕЛАТЬ ПРОСТО File
////        List<org.telegram.telegrambots.meta.api.objects.File> files = getFilesByMessage(message);
//        try {
//            List<String> paths = PhotoMessageUtils.savePhotos(files, getBotToken());       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            String chatId = message.getChatId().toString();       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            return preparePhotoMessage(paths, operation, chatId);       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Так было изначально
//        List<PhotoSize> photoSizes = message.getPhoto();
//        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files = new ArrayList<>(); // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Так было изначально
//        for (PhotoSize photoSize : photoSizes) {
//            final String fileId = photoSize.getFileId();
//            try {
//                files.add(sendApiMethod(new GetFile(fileId)));       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        return files;
//    }
//
//    private SendMediaGroup preparePhotoMessage(List<String> localPaths, ImageOperation operation, String chatId) throws Exception {
//        SendMediaGroup mediaGroup = new SendMediaGroup();
//        ArrayList<InputMedia> medias = new ArrayList<>();
//        for (String path : localPaths) {
//            InputMedia inputMedia = new InputMediaPhoto();
//            PhotoMessageUtils.processingImage(path, operation);
////            inputMedia.setNewMediaFile(new java.io.File(path));
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
////    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
////        Method[] classMethods = someClass.getDeclaredMethods();
////        ArrayList<AppBotCommand> commands = new ArrayList<>();
////        for (Method method : classMethods) {
////            if (method.isAnnotationPresent(AppBotCommand.class)) {
////                commands.add(method.getAnnotation(AppBotCommand.class));
////            }
////        }
////        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
////        int columnCount = 3;
////        int rowsCount = commands.size() / columnCount + ((commands.size() % columnCount == 0) ? 0 : 1);
////        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
////            KeyboardRow row = new KeyboardRow();
////            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
////                int index = rowIndex * columnCount + columnIndex;
////                if (index >= commands.size()) continue;
////                AppBotCommand command = commands.get(rowIndex * columnCount + columnIndex);
////                KeyboardButton keyboardButton = new KeyboardButton(command.name());
////                row.add(keyboardButton);
////            }
////            keyboardRows.add(row);
////        }
////        return keyboardRows;
////    }
//
//    private void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);         //  TODO ВНИМАНИЕ: ВСЁ ТАКИ ООСТАВЛЯЕМ, т.к. без него кнопки не создаёт!  УДАЛЯЕМ или? И без него кнопки не создаёт! ЗДЕСЬ ПРАВИЛЬНО: одно изобр. не возвращает, группу - возвращает. Но не возвращает надпись: "cloned_image"
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//}
//// КОНЕЦ ПРИМЕРА 2



//// ПРИМЕР 0 _ТОЧНО, КАК В ВИДЕОУРОКЕ. ВСЁ РАБОТАЕТ. ВОЗВРАЩАЮТСЯ (И СОХРАНЯЮТСЯ) 4 ИЗОБРАЖЕНИЯ И ОТВЕТЫ НА КОМАНДЫ. НО КНОПКИ НЕ СОЗДАЮТСЯ
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
//import task9_7_1_part4.commands.BotCommonCommands;
//import task9_7_1_part4.commands.AppBotCommand;
//import task9_7_1_part4.functions.FilterOperation;
//import task9_7_1_part4.functions.ImageOperation;
//import task9_7_1_part4.utils.PhotoMessageUtils;
//
//import java.io.*;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[]{BotCommonCommands.class};
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
//        try {
//            SendMessage responseTestMessage = runCommonCommand(message);
//            if (responseTestMessage != null) {
//                execute(responseTestMessage);
//                return;
//            }
//        } catch (InvocationTargetException | IllegalAccessException | TelegramApiException e) {
//            e.printStackTrace();
//        }
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
//    private String runCommand(String text)/* throws InvocationTargetException, IllegalAccessException*/ {
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                if (annotation.name().equals(text)) {
//                    try {
//                        method.setAccessible(true);     // TODO ОСТАВЛЯЕМ! Т.к. без него в консоли появляются красные строки, но на телеграм бот не влияет
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
//    private SendMessage runCommonCommand(Message message) throws InvocationTargetException, IllegalAccessException {
//        String text = message.getText();
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand command = method.getAnnotation(AppBotCommand.class);
//                if (command.name().equals(text)) {
//                    method.setAccessible(true);
//                    String responseText = (String) method.invoke(commands);
//                    if (responseText != null) {
//                        SendMessage sendMessage = new SendMessage();
//                        sendMessage.setChatId(message.getChatId().toString());
//                        sendMessage.setText(responseText);
//                        return sendMessage;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    private SendMediaGroup runPhotoFilter(Message message) {
//        ImageOperation operation = FilterOperation::greyScale;
//        List<org.telegram.telegrambots.meta.api.objects.File> files = getFilesByMessage(message);       //  TODO ВОЗМОЖНО НАДО СДЕЛАТЬ ПРОСТО File
////        List<org.telegram.telegrambots.meta.api.objects.File> files = getFilesByMessage(message);
//        try {
//            List<String> paths = PhotoMessageUtils.savePhotos(files, getBotToken());       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            String chatId = message.getChatId().toString();       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            return preparePhotoMessage(paths, operation, chatId);       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////    private List<org.telegram.telegrambots.meta.api.objects.File> getFilesByMessage(Message message) { // TODO Так было изначально
//        List<PhotoSize> photoSizes = message.getPhoto();
//        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files = new ArrayList<>(); // TODO Это новое, но пока не стал менять на просто File (или java.io.File)/ в видеоуроке 09 52 мин
////        ArrayList<org.telegram.telegrambots.meta.api.objects.File> files2 = new ArrayList<>(); // TODO Так было изначально
//        for (PhotoSize photoSize : photoSizes) {
//            final String fileId = photoSize.getFileId();
//            try {
//                files.add(sendApiMethod(new GetFile(fileId)));       //  TODO ОСТАВЛЯЕМ! Без него 4 изображения не возвращает
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        return files;
//    }
//
//    private SendMediaGroup preparePhotoMessage(List<String> localPaths, ImageOperation operation, String chatId) throws Exception {
//        SendMediaGroup mediaGroup = new SendMediaGroup();
//        ArrayList<InputMedia> medias = new ArrayList<>();
//        for (String path : localPaths) {
//            InputMedia inputMedia = new InputMediaPhoto();
//            PhotoMessageUtils.processingImage(path, operation);
////            inputMedia.setNewMediaFile(new java.io.File(path));
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
//    private void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);         //  TODO ВНИМАНИЕ: ВСЁ ТАКИ ООСТАВЛЯЕМ, т.к. без него кнопки не создаёт!  УДАЛЯЕМ или? И без него кнопки не создаёт! ЗДЕСЬ ПРАВИЛЬНО: одно изобр. не возвращает, группу - возвращает. Но не возвращает надпись: "cloned_image"
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//}
// КОНЕЦ ПРИМЕРа 0




//// ПРИМЕР 2 _Это изначальный код, взятый из task9_5_1 _Всё работает, создаётся 9 кнопок (5+4)
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
    public String getBotUsername() {
        return "lgcyrrerrr_002_bot"; // Название вашего бота
    }

    @Override
    public String getBotToken() {
        return "7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo"; // Токен вашего бота
    }

    @Override
    public void onUpdateReceived(Update update) {
        final String localFileName = "src/main/java/task9_7_1_part4/" + "cloned_image.jpg";
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

        // TODO Если закомментировать следующий фрагмент кода в Примере 2, до конца метода onUpdateReceived, т.е. до e.printStackTrace(); },
        //  то одно изображение не будет возвращаться. ПРОВЕРЕНО! Работает.
        SendPhoto sendPhoto = preparePhotoMessage(localFileName, message.getChatId().toString()); //  TODO Закомментировать, чтобы не возвращалось одно фото
        ///
        sendPhoto.setChatId(message.getChatId().toString()); //  TODO Закомментировать, чтобы не возвращалось одно фото
        InputFile newFile = new InputFile(); //  TODO Закомментировать, чтобы не возвращалось одно фото
        newFile.setMedia(new File(localFileName)); //  TODO Закомментировать, чтобы не возвращалось одно фото
        sendPhoto.setPhoto(newFile); //  TODO Закомментировать, чтобы не возвращалось одно фото
        sendPhoto.setCaption("cloned_image"); //  TODO Закомментировать, чтобы не возвращалось одно фото

// TODO НАШЁЛ! Для невозврата одного фото. В ПРИМЕРЕ 2 Вот ЭТО ЗАКОММЕНТИРОВАТЬ в методе onUpdateReceived(Update update), и не будет возвращать одно изображение
        try { //  TODO Закомментировать, чтобы не возвращалось одно фото
            execute(sendPhoto); //  TODO Закомментировать, чтобы не возвращалось одно фото
        } catch (TelegramApiException e) { //  TODO Закомментировать, чтобы не возвращалось одно фото
            e.printStackTrace(); //  TODO Закомментировать, чтобы не возвращалось одно фото
        } //  TODO Закомментировать, чтобы не возвращалось одно фото
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

    // TODO Здесь, в методе preparePhotoMessage, девять кнопок для клавиатуры создаются в двойном цикле,
    //  где каждая кнопка определяется на основе условий rowIndex и columnIndex
    private SendPhoto preparePhotoMessage(String localPath, String chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();  //  TODO отсюда 240522 12.37 Закомментировать, чтобы 9 кнопок не возвращалось
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        int rowCount = 3;
        int columnCount = 3;
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            KeyboardRow row = new KeyboardRow();
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                if (rowIndex == 0 && columnIndex == 0) {
                    try {
                        Class<?> filterOperationClass = Class.forName("task9_7_1_part4.functions.FilterOperation");
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
                        Class<?> filterOperationClass = Class.forName("task9_7_1_part4.functions.FilterOperation");
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
                        Class<?> filterOperationClass = Class.forName("task9_7_1_part4.functions.FilterOperation");
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
                        Class<?> filterOperationClass = Class.forName("task9_7_1_part4.functions.FilterOperation");
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
                        Class<?> filterOperationClass = Class.forName("task9_7_1_part4.functions.FilterOperation");
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
        replyKeyboardMarkup.setOneTimeKeyboard(true);  //  TODO досюда 240522 12.37 Закомментировать, чтобы 9 кнопок не возвращалось

        sendPhoto.setChatId(chatId); //  TODO Закомментировать, чтобы не возвращалось одно фото
        sendPhoto.setReplyMarkup(replyKeyboardMarkup); //  TODO НАШЁЛ! ИМЕННО ЭТА СТРОКА ОТВЕЧАЕТ ЗА СОЗДАНИЕ 9-ти КНОПОК! Если её закомментировать, 9 кнопок не создадутся!
        InputFile newFile = new InputFile(); //  TODO Закомментировать, чтобы не возвращалось одно фото
        newFile.setMedia(new File(localPath)); //  TODO Закомментировать, чтобы не возвращалось одно фото
        sendPhoto.setPhoto(newFile); //  TODO Закомментировать, чтобы не возвращалось одно фото
        return sendPhoto; //  TODO ВОЗМОЖНО, А МОЖЕТ И НЕТ! Закомментировать, чтобы не возвращалось одно фото. НЕ НАДО КОММЕНТИРОВАТЬ, ФОТО НЕ ВОЗВРАЩАЕТСЯ
    }
}
//// КОНЕЦ ПРИМЕРА 2