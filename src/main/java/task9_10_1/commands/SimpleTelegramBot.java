package task9_10_1.commands;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

public class SimpleTelegramBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        User user = message.getFrom();

        if (message.hasText()) {
            String text = message.getText();

            if ("/hello".equals(text)) {
                SendMessage response = new SendMessage();
                response.setChatId(message.getChatId().toString());
                response.setText("Hello, " + user.getFirstName() + "!");

                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "kkkllll_005_bot"; // замените на username вашего бота
    }

    @Override
    public String getBotToken() {
        return "6882256834:AAH5Fg-wUdKw7Rdqj8s9kXDgVt0R08tDnlY"; // замените на token вашего бота
    }

    public static void main(String[] args) {
        SimpleTelegramBot bot = new SimpleTelegramBot();
        bot.startPolling();
    }
}
