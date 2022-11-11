package com.deliveryfood.service;

import com.deliveryfood.dto.MemberDto;
import com.deliveryfood.controller.model.request.UserRequest;
import com.deliveryfood.service.model.MemberRegisterVO;
import com.deliveryfood.service.model.MemberUpdateVO;
import com.deliveryfood.service.model.UserRegisterVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IMemberService extends UserDetailsService {

    boolean certification(String userId, String code);

    boolean register(MemberRegisterVO registerVO, String uuid, MemberDto.Role role);

    boolean withdraw(UserRequest userRequest);

    MemberDto findMemberByEmail(String email);

    String getUserIdByEmail(String email);

    boolean modifyMember(String userId, MemberUpdateVO updateVO);

    void deleteMemberByUserId(String email);
}
