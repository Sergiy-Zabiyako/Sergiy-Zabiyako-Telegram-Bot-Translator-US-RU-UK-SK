package org.example;

public interface LangModeService {
    static LangModeService getInstance(){
        return new HashMapLangModeService();
    }
    Language getOriginalLanguage(long chatId);
    Language getTargetLanguage(long chatId);
    void setOriginalLanguage(long chatId, Language language);
    void setTargetLanguage(long chatId, Language language);
}
