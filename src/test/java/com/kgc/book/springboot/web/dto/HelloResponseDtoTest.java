package com.kgc.book.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void lombokTest() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name); // assertj 라는 테스트 검증 라이브러리 메소드, isEqualTo - 동등 비교 메소드
        assertThat(dto.getAmount()).isEqualTo(amount);

    }
}
