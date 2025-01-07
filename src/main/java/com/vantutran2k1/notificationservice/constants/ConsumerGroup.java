package com.vantutran2k1.notificationservice.constants;

import lombok.Getter;

@Getter
public enum ConsumerGroup {
    USER_REGISTRATION("user_registration");

    private final String value;

    ConsumerGroup(String value) {
        this.value = value;
    }
}
