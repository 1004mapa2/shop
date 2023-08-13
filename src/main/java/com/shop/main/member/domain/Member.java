package com.shop.main.member.domain;

import lombok.Data;

@Data
public class Member {

    private int memIdx;
    private String memId;
    private String memPassword;
    private String memName;
    private String memGender;
    private String memProfile;
}