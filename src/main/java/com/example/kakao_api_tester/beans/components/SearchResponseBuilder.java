package com.example.kakao_api_tester.beans.components;

import com.example.kakao_api_tester.data.dto.SearchResponseDto;
import org.springframework.stereotype.Component;

@Component
public class SearchResponseBuilder<T> {

    private boolean success;
    private String message;
    private T result;


    public SearchResponseBuilder<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public SearchResponseBuilder<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public SearchResponseBuilder<T> setResult(T result) {
        this.result = result;
        return this;
    }

    public SearchResponseDto<T> build() {
        return new SearchResponseDto<>(success, message, result);
    }


}
