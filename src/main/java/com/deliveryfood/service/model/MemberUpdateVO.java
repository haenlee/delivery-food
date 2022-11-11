package com.deliveryfood.service.model;

import com.deliveryfood.controller.model.request.MemberUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter

public class MemberUpdateVO {

    private String phone;

    public static MemberUpdateVO convert(MemberUpdateRequest updateRequest) {
        return MemberUpdateVO.builder()
                .phone(updateRequest.getPhone())
                .build();
    }
}
