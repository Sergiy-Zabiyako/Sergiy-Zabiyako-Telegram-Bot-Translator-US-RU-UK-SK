package org.example;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

public class TestBot extends DefaultAbsSender {
    protected TestBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotToken() {
        return "5855631389:AAF5l4Gstw73vAzxE8XbFWEdGOT3A7NABIk";
    }
}
