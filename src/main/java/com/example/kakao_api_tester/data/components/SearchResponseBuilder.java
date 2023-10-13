package com.example.kakao_api_tester.data.components;

import com.example.kakao_api_tester.data.dto.SearchResponseDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class SearchResponseBuilder<T> {

    public boolean success;
    public String message;
    public T result;


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
