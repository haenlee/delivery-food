package com.deliveryfood.service;

import com.deliveryfood.dto.MemberDto;
import com.deliveryfood.model.request.UserRequest;
import com.deliveryfood.vo.MemberRegisterVO;
import com.deliveryfood.vo.UserRegisterVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IMemberService extends UserDetailsService {

    boolean certification(String userId, String code);

    boolean register(MemberRegisterVO registerVO, String uuid, MemberDto.Role role);

    boolean withdraw(UserRequest userRequest);

    MemberDto findMemberByEmail(String email);

    boolean modifyUser(UserRegisterVO registerVO);
}
