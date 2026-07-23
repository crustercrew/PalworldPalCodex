package org.crustercrew.palworldpalcodex.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record APIResponse<T>(
        String Status,
        String message,
        T data,
        LocalDateTime timestamp
) {
    public static <T> APIResponse<T> success(String message, T data) {
        return new APIResponse<>("SUCCESS", message, data, LocalDateTime.now());
    }

    public static <T> APIResponse<T> error(String message) {
        return new APIResponse<>("ERROR", message, null, LocalDateTime.now());
    }
}
