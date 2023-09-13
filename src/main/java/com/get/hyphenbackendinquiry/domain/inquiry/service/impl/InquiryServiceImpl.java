package com.get.hyphenbackendinquiry.domain.inquiry.service.impl;

import com.get.hyphenbackendinquiry.domain.inquiry.domain.repository.InquiryRepository;
import com.get.hyphenbackendinquiry.domain.inquiry.presentation.dto.request.MakeInquiryRequest;
import com.get.hyphenbackendinquiry.domain.inquiry.presentation.dto.response.*;
import com.get.hyphenbackendinquiry.domain.inquiry.service.InquiryService;
import com.get.hyphenbackendinquiry.global.util.ValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final ValidateUtil validateUtil;

    @Transactional
    public MakeInquiryResponse makeInquiry(MakeInquiryRequest makeInquiryRequest, String authorizationHeader) {
        if (inquiryRepository.save(makeInquiryRequest.toEntity(validateUtil.validate(validateUtil.getTokenByHeader(authorizationHeader)).getUid())) != null) {
            return MakeInquiryResponse.builder()
                    .status("문의 성공")
                    .build();
        } else {
            return MakeInquiryResponse.builder()
                    .status("문의 실패")
                    .build();
        }
    }

    @Transactional
    public GetInquiryResponse getInquiry(String id, String authorizationHeader) {
        validateUtil.validate(validateUtil.getTokenByHeader(authorizationHeader));
        return GetInquiryResponse.builder()
                .inquiry(inquiryRepository.findById(Long.valueOf(id)).get())
                .build();
    }

    @Transactional
    public GetMyInquirysResponse getMyInquirys(String authorizationHeader) {
        String uid = validateUtil.validate(validateUtil.getTokenByHeader(authorizationHeader)).getUid();
        return GetMyInquirysResponse.builder()
                .inquiries(inquiryRepository.findAllByUid(uid).get())
                .build();
    }
}

