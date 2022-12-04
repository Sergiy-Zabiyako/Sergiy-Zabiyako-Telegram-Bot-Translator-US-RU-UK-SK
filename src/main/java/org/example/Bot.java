package org.example;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.*;

public class Bot extends TelegramLongPollingBot {

    private final LangModeService langModeService = LangModeService.getInstance();

    private String getLanguageButton(Language saved, Language language){
//        return saved == language ? language + "\uD83C\uDFC0" : language.name();
        if (saved == language){
            System.out.print(language+"\uD83C\uDFC0");
            return language + "\uD83C\uDFC0";
        } else {
            System.out.println(language.name());
            return language.name();
        }


    }
    @Override
    public String getBotUsername() {
        return "@RaffyTranslationbot";
    }

    @Override
    public String getBotToken() {
        return "5855631389:AAF5l4Gstw73vAzxE8XbFWEdGOT3A7NABIk";
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()){
            handleCallback(update.getCallbackQuery());
        } else
        if (update.hasMessage()) {
            handleMessege(update.getMessage());
        }
    }
    @SneakyThrows
    private void handleCallback(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String[] param = callbackQuery.getData().split(":");
        String action = param[0];
        Language newLanguage = Language.valueOf(param[1]);
        switch (action){
            case "ORIGINAL":
                langModeService.setOriginalLanguage(message.getChatId(),newLanguage);
                break;
            case "TARGET":
                langModeService.setTargetLanguage(message.getChatId(),newLanguage);
                break;
        }

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        Language originalLanguage = langModeService.getOriginalLanguage(message.getChatId());
        Language targetLanguage = langModeService.getTargetLanguage(message.getChatId());
        for (Language language :
                Language.values()) {
            buttons.add(
                    Arrays.asList(
                            InlineKeyboardButton.builder()
                                    .text(getLanguageButton(originalLanguage, language))
                                    .callbackData("ORIGINAL:"+language)
                                    .build(),
                            InlineKeyboardButton.builder()
                                    .text(getLanguageButton(targetLanguage,language))
                                    .callbackData("TARGET:"+language)
                                    .build()));
        }
            execute(
                    EditMessageReplyMarkup.builder()
                    .chatId(message.getChatId().toString())
                    .messageId(message.getMessageId())
                    .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                    .build());
    }
    @SneakyThrows
    private void handleMessege(Message message) {
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity =
                    message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                switch (command) {
                    case "/set_translate":
                        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
                        Language originalLanguage = langModeService.getOriginalLanguage(message.getChatId());
                        Language targetLanguage = langModeService.getTargetLanguage(message.getChatId());
                        for (Language language :
                                Language.values()) {
                            buttons.add(
                                    Arrays.asList(
                            InlineKeyboardButton.builder()
                                    .text(getLanguageButton(originalLanguage, language))
                                    .callbackData("ORIGINAL:"+language)
                                    .build(),
                            InlineKeyboardButton.builder()
                                    .text(getLanguageButton(targetLanguage,language))
                                    .callbackData("TARGET:"+language)
                                    .build()));
                        }
                            execute(
                                    SendMessage.builder()
                                            .text("Please choose language Original and Target")
                                            .chatId(message.getChatId().toString())
                                            .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                                    .build());
                            return;


                }
            }
        }
        if (message.hasText() & !message.getText().equals("/set_translate")){
            String messageText = message.getText();
            String originalLang = String.valueOf(langModeService.getOriginalLanguage(message.getChatId()));
            String targetLang = String.valueOf(langModeService.getTargetLanguage(message.getChatId()));
                String translator = Translator.translate(originalLang,targetLang,messageText);
                execute(
                        SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text(
                                String.format("%s is %s",messageText,translator))
                        .build());
        }
    }
}
