package com.deliveryfood.service;

import com.deliveryfood.controller.model.request.UserRequest;
import com.deliveryfood.dao.model.MemberDto;
import com.deliveryfood.service.impl.MemberService.CertificationResult;
import com.deliveryfood.service.model.MemberRegisterVO;
import com.deliveryfood.service.model.MemberUpdateVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IMemberService extends UserDetailsService {

    CertificationResult certification(String userId, String code);

    boolean register(MemberRegisterVO registerVO, String uuid, MemberDto.Role role);

    boolean withdraw(UserRequest userRequest);

    MemberDto findMemberByEmail(String email);

    String getUserIdByEmail(String email);

    boolean modifyMember(String userId, MemberUpdateVO updateVO);

    void deleteMemberByUserId(String email);
}
