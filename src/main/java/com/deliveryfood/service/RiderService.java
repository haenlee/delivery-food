package com.deliveryfood.service;

import com.deliveryfood.Util.MemberSession;
import com.deliveryfood.dao.MemberDao;
import com.deliveryfood.dao.RiderDao;
import com.deliveryfood.dto.RiderDto;
import com.deliveryfood.model.LoginResult;
import com.deliveryfood.model.RiderInput;
import com.deliveryfood.model.UserRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RiderService extends MemberService implements IRiderService {

    private final RiderDao riderDao;
    private final MemberSession session;

    public RiderService(MemberDao memberDao, MemberSession session, RiderDao riderDao) {
        super(memberDao, session);
        this.riderDao = riderDao;
        this.session = session;
    }

    @Override
    public boolean certification(String code) {
        // REGISTER_CODE 와 일치하면 인증 완료

        if(!super.certification(code)) {
            // 멤버 이슈가 있음
            return false;
        }

        RiderDto riderDto = riderDao.findByUserId(session.getLoginUserId());
        if(riderDto == null) {
            // 유저가 존재하지 않음
            return false;
        }

        return true;
    }

    @Override
    public boolean register(RiderInput riderInput) {
        String uuid = UUID.randomUUID().toString();
        if(!super.register(riderInput, uuid)) {
            // 멤버 이슈가 있음
            return false;
        }

        if(riderDao.findByUserId(uuid) != null) {
            // 중복 유저 존재
            return false;
        }

        RiderDto riderDto = RiderDto.builder()
                .userId(uuid)
                .commission(riderInput.getCommission())
                .status(RiderDto.Status.NONE)
                .regDt(LocalDateTime.now())
                .build();

        riderDao.register(riderDto);
        return true;
    }

    @Override
    public boolean withdraw(UserRequest userRequest) {
        if(!super.withdraw(userRequest)) {
            // 멤버 이슈가 있음
            return false;
        }

        RiderDto riderDto = riderDao.findByEmail(userRequest.getEmail());
        if(riderDto == null) {
            // 유저가 존재하지 않음
            return false;
        }

        return true;
    }

    @Override
    public LoginResult login(UserRequest userRequest) {
        return super.login(userRequest);
    }

    @Override
    public boolean modifyRider(RiderInput riderInput) {
        RiderDto riderDto = riderDao.findByEmail(riderInput.getEmail());
        if(riderDto == null) {
            // 유저가 존재하지 않음
            return false;
        }

        riderDto.setCommission(riderInput.getCommission());
        riderDao.updateRider(riderDto);
        return true;
    }
}
