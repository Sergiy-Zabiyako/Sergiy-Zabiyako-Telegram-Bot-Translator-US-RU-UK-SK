package org.example;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws TelegramApiException {

        Bot bot1 = new Bot();
        TelegramBotsApi telegramBotsApiBot = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApiBot.registerBot(bot1);
    }

}