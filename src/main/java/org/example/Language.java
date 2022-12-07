package org.example;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Language {
    EN(1),RU(2),UK(3),SK(4),ES(5);
    private final int id;
}
