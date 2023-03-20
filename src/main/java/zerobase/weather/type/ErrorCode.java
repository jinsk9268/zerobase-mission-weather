package zerobase.weather.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다."),
    INVALID_DATE_VALUE("너무 과거 혹은 미래의 날짜입니다. (현재 시점으로부터 앞 뒤로 200년전 일기는 작성 및 조회할 수 없습니다.)"),
    INVALID_DATE_TYPE("날짜 형식이 yyyy-MM-dd 형식에 맞지 않습니다."),
    INVALID_PERIOD_DATE("시작날짜가 종료날짜보다 큽니다."),
    JSON_PARSE_ERROR("Json 파싱중 에러가 발생했습니다."),
    OPEN_API_DATA_IMPORTING_ERROR("openweathermap에서 데이터 가져오기에 실패했습니다");
    private final String errorMsg;
}
