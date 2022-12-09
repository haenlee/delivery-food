package com.deliveryfood.mapper;

import com.deliveryfood.dao.model.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    void register(MemberDto userDto);

    MemberDto findByEmail(String email);

    MemberDto findByUserId(String userId);

    void updateStatus(MemberDto userDto);

    void updateRole(MemberDto userDto);

    void updateMember(MemberDto userDto);

    void deleteMemberByUserId(String userId);
}
