package com.deliveryfood.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@RequiredArgsConstructor
@SuperBuilder
@Getter
@Setter
public class MemberInput {

    public String name;
    public String email;
    public String password;
    public String phone;
}
