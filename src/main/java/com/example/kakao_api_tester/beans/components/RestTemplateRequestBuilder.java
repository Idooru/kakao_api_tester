package com.example.kakao_api_tester.beans.components;

import com.example.kakao_api_tester.data.type.KakaoLocalResponseJSON;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RestTemplateRequestBuilder<T> {

    private String uri;
    private HttpMethod httpMethod;
    private HttpEntity<?> httpEntity;
    private Class<T> type;

    private final RestTemplate restTemplate;

    public RestTemplateRequestBuilder<T> setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public RestTemplateRequestBuilder<T> setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public RestTemplateRequestBuilder<T> setHttpEntity(HttpEntity<?> httpEntity) {
        this.httpEntity = httpEntity;
        return this;
    }

    public RestTemplateRequestBuilder<T> setType(Class<T> type) {
        this.type = type;
        return this;
    }

    public KakaoLocalResponseJSON build() {
        return (KakaoLocalResponseJSON) restTemplate.exchange(uri, httpMethod, httpEntity, type).getBody();
    }

}
