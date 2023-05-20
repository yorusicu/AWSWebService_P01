package com.aws.web.demo.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HellodResponseDto {
    private final String name;
    private final int amount;
}
