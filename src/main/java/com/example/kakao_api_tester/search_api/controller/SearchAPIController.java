package com.example.kakao_api_tester.search_api.controller;

import com.example.kakao_api_tester.data.dto.SearchRequestDto;
import com.example.kakao_api_tester.data.dto.SearchResponseDto;
import com.example.kakao_api_tester.data.type.SearchResponseJSON;
import com.example.kakao_api_tester.search_api.service.SearchAPIService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SearchAPIController {

    private final SearchAPIService searchAPIService;

    @GetMapping(value = "/search-api")
    public ResponseEntity<SearchResponseDto<SearchResponseJSON>> searchApi(SearchRequestDto searchRequestDto) {
        SearchResponseJSON searchResponseJSON = searchAPIService.callSearchAPI(searchRequestDto);

        return searchAPIService.buildResponse(searchResponseJSON);
    }


}
