package com.example.kakao_api_tester.local_api.controller;

import com.example.kakao_api_tester.beans.components.HttpTransactionLogger;
import com.example.kakao_api_tester.data.dto.KeywordRequestDto;
import com.example.kakao_api_tester.data.dto.SearchResponseDto;
import com.example.kakao_api_tester.data.type.KeywordResponseJson;
import com.example.kakao_api_tester.local_api.service.LocalAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class LocalAPIController {

    private final LocalAPIService localAPIService;
    private final HttpTransactionLogger logger;

    @GetMapping(value = "/keyword-api")
    public ResponseEntity<SearchResponseDto<KeywordResponseJson>> callKeyword(KeywordRequestDto KeywordRequestDto) {
        logger.logKeywordRequestDto(KeywordRequestDto);
        KeywordRequestDto.validateDto();

        KeywordResponseJson searchResponseJSON = localAPIService.callKeyword(KeywordRequestDto);
        logger.logKeywordResponseJson(searchResponseJSON);

        return localAPIService.buildResponse(searchResponseJSON);
    }


}
