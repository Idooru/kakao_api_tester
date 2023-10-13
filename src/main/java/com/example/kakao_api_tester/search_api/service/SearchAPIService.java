package com.example.kakao_api_tester.search_api.service;

import com.example.kakao_api_tester.data.components.SearchResponseBuilder;
import com.example.kakao_api_tester.data.dto.SearchRequestDto;
import com.example.kakao_api_tester.data.dto.SearchResponseDto;
import com.example.kakao_api_tester.data.type.SearchResponseJSON;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@AllArgsConstructor
public class SearchAPIService {

    private final RestTemplate restTemplate;
    private final SearchResponseBuilder<SearchResponseJSON> searchResponseBuilder;

    public SearchResponseJSON callSearchAPI(SearchRequestDto searchRequestDto) {
        String x = searchRequestDto.getX();
        String y = searchRequestDto.getY();
        String keyword = searchRequestDto.getKeyword();

        UriComponents uri = UriComponentsBuilder.fromHttpUrl("https://dapi.kakao.com/v2/local/search/keyword.json")
                .queryParam("y", y)
                .queryParam("x", x)
                .queryParam("radius", 20000)
                .queryParam("size", 10)
                .queryParam("query", keyword)
                .queryParam("page", 45)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK e2a97497252d13a304751d99a85ea67c");

        SearchResponseJSON json;

        try {
            json = restTemplate.exchange(uri.toUriString(), HttpMethod.GET, new HttpEntity<>(headers), SearchResponseJSON.class).getBody();
        } catch (HttpClientErrorException exception) {
            json = null;
        }

        return json;
    }

    public ResponseEntity<SearchResponseDto<SearchResponseJSON>> buildResponse(SearchResponseJSON json) {
        SearchResponseDto<SearchResponseJSON> build;
        ResponseEntity<SearchResponseDto<SearchResponseJSON>> responseEntity;

        if (json != null) {
            build = searchResponseBuilder
                    .setSuccess(true)
                    .setMessage("성공입니다.")
                    .setResult(json)
                    .build();

            responseEntity = new ResponseEntity<SearchResponseDto<SearchResponseJSON>>(build, HttpStatusCode.valueOf(200));
        } else {
            build = searchResponseBuilder
                    .setSuccess(false)
                    .setMessage("실패입니다.")
                    .setResult(null)
                    .build();

            responseEntity = new ResponseEntity<SearchResponseDto<SearchResponseJSON>>(build, HttpStatusCode.valueOf(400));
        }

        return responseEntity;
    }
}
