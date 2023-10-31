package com.example.kakao_api_tester.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchResponseDto<T> {
    public boolean success;
    public String message;
    public T result;
}
