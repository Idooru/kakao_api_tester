package com.example.kakao_api_tester.local_api.service;

import com.example.kakao_api_tester.beans.components.RestTemplateRequestBuilder;
import com.example.kakao_api_tester.beans.components.SearchResponseBuilder;
import com.example.kakao_api_tester.data.dto.KeywordRequestDto;
import com.example.kakao_api_tester.data.dto.SearchResponseDto;
import com.example.kakao_api_tester.data.type.KeywordResponseJson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class LocalAPIService {

    private final HttpHeaders httpHeaders;
    private final SearchResponseBuilder<KeywordResponseJson> searchResponseBuilder;
    private final RestTemplateRequestBuilder<KeywordResponseJson> requestBuilder;

    public KeywordResponseJson callKeyword(KeywordRequestDto KeywordRequestDto) {
        String x = KeywordRequestDto.getX();
        String y = KeywordRequestDto.getY();
        String keyword = KeywordRequestDto.getKeyword();
        int radius = KeywordRequestDto.getRadius();
        int size = KeywordRequestDto.getSize();
        int page = KeywordRequestDto.getPage();

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

        final KeywordResponseJson build = requestBuilder
                .setUri(uri.toUriString())
                .setHttpMethod(HttpMethod.GET)
                .setHttpEntity(new HttpEntity<>(httpHeaders))
                .setType(KeywordResponseJson.class)
                .build();

        build.keyword = keyword;

        return build;
    }

    public ResponseEntity<SearchResponseDto<KeywordResponseJson>> buildResponse(KeywordResponseJson json) {
        SearchResponseDto<KeywordResponseJson> response = searchResponseBuilder
                .setSuccess(true)
                .setMessage("성공입니다.")
                .setResult(json)
                .build();

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }
}
