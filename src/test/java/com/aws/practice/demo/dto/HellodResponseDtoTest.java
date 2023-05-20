package com.aws.practice.demo.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
class HellodResponseDtoTest {
    @Test
    void Test_lombok(){
        // given(지정)
        String name = "test";
        int amount = 1000;

        // when(언제)
        HellodResponseDto dto = new HellodResponseDto(name, amount);

        // then(그러고 나서)
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}