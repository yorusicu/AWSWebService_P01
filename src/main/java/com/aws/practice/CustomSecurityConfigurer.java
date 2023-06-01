package com.aws.practice;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class CustomSecurityConfigurer extends AbstractHttpConfigurer<CustomSecurityConfigurer, HttpSecurity> {

    private Boolean isSecure;

    // 초기화, 설정에서 인증, 인가에 필요한 설정을 할 수 있음
    @Override
    public void init(HttpSecurity builder) throws Exception {
        super.init(builder);
        System.out.println("init method started...");

    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        super.configure(builder);
        System.out.println("configure method started...");
        if (isSecure) {
            System.out.println("https is required");
        } else {
            System.out.println("https is optional");
        }
    }

    // 반환이유 : securityConfig.apply에 설정
    public CustomSecurityConfigurer setFlag(boolean isSecure) {
        this.isSecure = isSecure;
        return this;
    }

}
