package com.example.kakao_api_tester.beans.components;

import com.example.kakao_api_tester.data.dto.SearchRequestDto;
import com.example.kakao_api_tester.data.type.SearchResponseJSON;
import org.springframework.stereotype.Component;

@Component
public class HttpTransactionLogger {

    public void logRequestDto(SearchRequestDto dto) {
        System.out.println("검색 요청 데이터");
        System.out.println("[위도]: " + dto.getX());
        System.out.println("[경도]: " + dto.getY());
        System.out.println("[키워드]: " + dto.getKeyword());
        System.out.println("[반경]: " + dto.getRadius());
        System.out.println("[식당 개수]: " + dto.getSize());
    }

    public void logResponseJson(SearchResponseJSON json) {
        System.out.println("검색 응답 데이터");
        json.documents.forEach((shop) -> System.out.println("[식당 정보]: " + shop));
    }
}
