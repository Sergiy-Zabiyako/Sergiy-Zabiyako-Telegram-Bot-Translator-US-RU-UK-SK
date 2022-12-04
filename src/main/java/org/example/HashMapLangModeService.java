package org.example;

import java.util.HashMap;
import java.util.Map;

public class HashMapLangModeService implements LangModeService {
    private final Map<Long, Language> originalLanguage = new HashMap<>();
    private final Map<Long, Language> targetLanguage = new HashMap<>();

    public void HashMapCurrencyModeService() {
        System.out.println("HASHMAP MODE is created");
    }

    @Override
    public Language getOriginalLanguage(long chatId) {
        return originalLanguage.getOrDefault(chatId,Language.EN);
    }

    @Override
    public Language getTargetLanguage(long chatId) {
        return targetLanguage.getOrDefault(chatId,Language.RU);
    }

    @Override
    public void setOriginalLanguage(long chatId, Language language) {
        originalLanguage.put(chatId,language);
    }

    @Override
    public void setTargetLanguage(long chatId, Language language) {
        targetLanguage.put(chatId,language);
    }
}
