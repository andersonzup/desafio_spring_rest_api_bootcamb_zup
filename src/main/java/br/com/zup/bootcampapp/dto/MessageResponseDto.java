package br.com.zup.bootcampapp.dto;

import java.util.Objects;

public class MessageResponseDto {
    private String message;

    public MessageResponseDto() {
    }

    public MessageResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageResponseDto)) return false;
        MessageResponseDto that = (MessageResponseDto) o;
        return Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage());
    }
}
