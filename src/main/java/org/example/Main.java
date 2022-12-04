package org.example;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TestBot bot = new TestBot(new DefaultBotOptions());
        try {
            bot.execute(SendMessage.builder().chatId("276570256").text("Hello world from java").build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
//        TestBot2 bot2 = new TestBot2();
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
//        telegramBotsApi.registerBot(bot2);

        Bot bot1 = new Bot();
        TelegramBotsApi telegramBotsApiBot = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApiBot.registerBot(bot1);
    }

}