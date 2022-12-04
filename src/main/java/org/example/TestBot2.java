package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class TestBot2 extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "@RaffyTranslationbot";
    }

    @Override
    public String getBotToken() {
        return "5855631389:AAF5l4Gstw73vAzxE8XbFWEdGOT3A7NABIk";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            Message message = update.getMessage();
            if (message.hasText()){
                try {
                    execute(
                            SendMessage.builder()
                            .chatId(message.getChatId().toString())
                            .text("You Send: \n\n"+message.getText())
                            .build());
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
