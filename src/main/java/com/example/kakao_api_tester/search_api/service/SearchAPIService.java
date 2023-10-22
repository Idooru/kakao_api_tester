package com.example.kakao_api_tester.search_api.service;

import com.example.kakao_api_tester.beans.components.RestTemplateRequestBuilder;
import com.example.kakao_api_tester.beans.components.SearchResponseBuilder;
import com.example.kakao_api_tester.data.dto.SearchRequestDto;
import com.example.kakao_api_tester.data.dto.SearchResponseDto;
import com.example.kakao_api_tester.data.type.SearchResponseJSON;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class SearchAPIService {

    private final HttpHeaders httpHeaders;
    private final SearchResponseBuilder<SearchResponseJSON> searchResponseBuilder;
    private final RestTemplateRequestBuilder<SearchResponseJSON> requestBuilder;

    public SearchResponseJSON callSearchAPI(SearchRequestDto searchRequestDto) {
        String x = searchRequestDto.getX();
        String y = searchRequestDto.getY();
        String keyword = searchRequestDto.getKeyword();
        int radius = searchRequestDto.getRadius();
        int size = searchRequestDto.getSize();
        int page = searchRequestDto.getPage();

        UriComponents uri = UriComponentsBuilder.fromHttpUrl("https://dapi.kakao.com/v2/local/search/keyword.json")
                .queryParam("y", y)
                .queryParam("x", x)
                .queryParam("query", keyword)
                .queryParam("radius", radius)
                .queryParam("size", size)
                .queryParam("page", page)
                .queryParam("category_group_code", "FD6")
                .build();

        httpHeaders.set("Authorization", "KakaoAK e2a97497252d13a304751d99a85ea67c");

        return requestBuilder
                .setUri(uri.toUriString())
                .setHttpMethod(HttpMethod.GET)
                .setHttpEntity(new HttpEntity<>(httpHeaders))
                .setType(SearchResponseJSON.class)
                .build();
    }

    public ResponseEntity<SearchResponseDto<SearchResponseJSON>> buildResponse(SearchResponseJSON json) {
        SearchResponseDto<SearchResponseJSON> response = searchResponseBuilder
                .setSuccess(true)
                .setMessage("성공입니다.")
                .setResult(json)
                .build();

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }
}
