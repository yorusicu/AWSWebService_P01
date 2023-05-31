package com.aws.practice.api;

import com.aws.practice.domain.Members;
import com.aws.practice.service.MemberService;
import com.aws.practice.web.dto.CreateMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/members")
    public CreateMemberResponse saveMember(@RequestBody @Validated Members members) {
//        memberService.save(members);
        return null;
    }
}
