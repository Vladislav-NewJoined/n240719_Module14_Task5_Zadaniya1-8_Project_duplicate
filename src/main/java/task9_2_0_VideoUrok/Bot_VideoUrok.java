package task9_2_0_VideoUrok;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot_VideoUrok extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "javatestttbottt_kjllkjlkjl_bot";
    }

    @Override
    public String getBotToken() {
        return "7000104813:AAFnPxRsc9poy4-mta3d8uy5hLCnlk1mh1I";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        System.out.println(message.getText());
    }
}