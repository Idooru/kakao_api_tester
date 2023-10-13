package com.example.kakao_api_tester.data.dto;

import lombok.Data;

@Data
public class SearchResponseDto<T> {
    public boolean success;
    public String message;
    public T result;

    public SearchResponseDto(boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

}
