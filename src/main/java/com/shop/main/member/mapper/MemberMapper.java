package com.shop.main.member.mapper;

import com.shop.main.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    public int memberRegister(Member member);

    public Member memberLogin(Member member);

}
