package zerobase.weather.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
class DiaryControllerTest {
    @MockBean
    private DiaryService diaryService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void successReadDiary() throws Exception {
        // given
        String date = "2023-03-01";
        LocalDate localDate = LocalDate.of(2023,3,1);

        List<Diary> diaryList = Arrays.asList(
                new Diary(1, "날씨", "아이콘", 69.5, "일기", localDate),
                new Diary(2, "날씨", "아이콘", 69.5, "일기", localDate)
        );

        given(diaryService.readDiary(localDate))
                .willReturn(diaryList);

        // when
        // then
        mockMvc.perform(get("/read/diary?date=" + date))
                .andDo(print())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].weather").value("날씨"))
                .andExpect(jsonPath("$[0].icon").value("아이콘"))
                .andExpect(jsonPath("$[0].temperature").value(69.5))
                .andExpect(jsonPath("$[0].text").value("일기"))
                .andExpect(jsonPath("$[0].date").value(date));
    }

    @Test
    void successReadDiaries() throws Exception {
        // given
        String startDate = "2023-03-01";
        String endDate = "2023-03-31";
        LocalDate startLocalDate = LocalDate.of(2023,3,1);
        LocalDate endLocalDate = LocalDate.of(2023,3,31);

        List<Diary> diaryList = Arrays.asList(
                new Diary(1, "날씨", "아이콘", 69.5, "일기", startLocalDate),
                new Diary(2, "날씨", "아이콘", 69.5, "일기", endLocalDate)
        );

        given(diaryService.readDiaries(startLocalDate, endLocalDate))
                .willReturn(diaryList);

        // when
        // then
        mockMvc.perform(get("/read/diaries?startDate=" + startDate + "&endDate=" + endDate))
                .andDo(print())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].weather").value("날씨"))
                .andExpect(jsonPath("$[0].icon").value("아이콘"))
                .andExpect(jsonPath("$[0].temperature").value(69.5))
                .andExpect(jsonPath("$[0].text").value("일기"))
                .andExpect(jsonPath("$[0].date").value(startDate))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].weather").value("날씨"))
                .andExpect(jsonPath("$[1].icon").value("아이콘"))
                .andExpect(jsonPath("$[1].temperature").value(69.5))
                .andExpect(jsonPath("$[1].text").value("일기"))
                .andExpect(jsonPath("$[1].date").value(endDate));
    }
}