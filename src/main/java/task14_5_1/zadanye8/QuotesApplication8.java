package task14_5_1.zadanye8;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

@SpringBootApplication
public class QuotesApplication8 implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(QuotesApplication8.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
//
//        QuotesApplication8 bot = new QuotesApplication8();
//        SendMessage message = new SendMessage();
//        message.setText("Bot started");
//        message.setChatId("5799431854"); // Укажите chatId для отправки сообщения о запуске бота
//        try {
//            bot.execute(message);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message = update.getMessage();
//        User user = message.getFrom();
//
//        if (message.hasText()) {
//            String text = message.getText();
//
//            if ("/hello".equals(text)) {
//                SendMessage response = new SendMessage();
//                response.setChatId(message.getChatId().toString());
//                response.setText("Hello, " + user.getFirstName() + "!");
//
//                try {
//                    execute(response);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    @Override
//    public String getBotUsername() {
//        return "kkkllll_005_bot"; // замените на username вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "6882256834:AAH5Fg-wUdKw7Rdqj8s9kXDgVt0R08tDnlY"; // замените на token вашего бота
//    }
//
}










//package task14_5_1.zadanye8;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.User;
//
//@SpringBootApplication
//public class QuotesApplication8 extends TelegramLongPollingBot {
//
//    public static void main(String[] args) {
//        SpringApplication.run(QuotesApplication8.class, args);
//
//        QuotesApplication8 bot = new QuotesApplication8();
//        SendMessage message = new SendMessage();
//        message.setText("Bot started");
//        message.setChatId("5799431854"); // Укажите chatId для отправки сообщения о запуске бота
//        try {
//            bot.execute(message);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message = update.getMessage();
//        User user = message.getFrom();
//
//        if (message.hasText()) {
//            String text = message.getText();
//
//            if ("/hello".equals(text)) {
//                SendMessage response = new SendMessage();
//                response.setChatId(message.getChatId().toString());
//                response.setText("Hello, " + user.getFirstName() + "!");
//
//                try {
//                    execute(response);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    @Override
//    public String getBotUsername() {
//        return "kkkllll_005_bot"; // замените на username вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "6882256834:AAH5Fg-wUdKw7Rdqj8s9kXDgVt0R08tDnlY"; // замените на token вашего бота
//    }
//
//}
