package org.vtb.practice.task05.controllers.model;

import lombok.Getter;

@Getter
public enum State {
    CLOSE(0, "CLOSE", "Закрыт"),
    OPEN(1, "OPEN", "Открыт"),
    RESERVE(2, "RESERVE", "Зарезервирован"),
    DELETE(3, "DELETE", "Удалён");

    private final int code;
    private final String state;
    private final String description;

    State(int code, String state, String description) {
        this.code = code;
        this.state = state;
        this.description = description;
    }
}