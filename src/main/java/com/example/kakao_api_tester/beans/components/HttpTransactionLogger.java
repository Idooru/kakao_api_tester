package com.example.kakao_api_tester.beans.components;

import com.example.kakao_api_tester.data.dto.KeywordRequestDto;
import com.example.kakao_api_tester.data.type.KeywordResponseJson;
import org.springframework.stereotype.Component;

@Component
public class HttpTransactionLogger {

    public void logKeywordRequestDto(KeywordRequestDto dto) {
        System.out.println("검색 요청 데이터");
        System.out.println("[위도]: " + dto.getX());
        System.out.println("[경도]: " + dto.getY());
        System.out.println("[키워드]: " + dto.getKeyword());
        System.out.println("[반경]: " + dto.getRadius());
        System.out.println("[식당 개수]: " + dto.getSize());
        System.out.println("[페이지 개수]: " + dto.getPage());
    }

    public void logKeywordResponseJson(KeywordResponseJson json) {
        System.out.println("검색 응답 데이터");
        System.out.println("[총 응답 개수]: " + json.documents.size());
        json.documents.forEach((shop) -> System.out.println("[식당 정보]: " + shop));
    }
}
