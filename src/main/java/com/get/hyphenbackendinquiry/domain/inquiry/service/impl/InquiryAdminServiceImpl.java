package com.get.hyphenbackendinquiry.domain.inquiry.service.impl;

import com.get.hyphenbackendinquiry.domain.inquiry.domain.Inquiry;
import com.get.hyphenbackendinquiry.domain.inquiry.domain.repository.InquiryRepository;
import com.get.hyphenbackendinquiry.domain.inquiry.enums.InquiryStatus;
import com.get.hyphenbackendinquiry.domain.inquiry.presentation.dto.request.AnswerRequest;
import com.get.hyphenbackendinquiry.domain.inquiry.presentation.dto.response.*;
import com.get.hyphenbackendinquiry.domain.inquiry.service.InquiryAdminService;
import com.get.hyphenbackendinquiry.global.util.ValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InquiryAdminServiceImpl implements InquiryAdminService {

    private final InquiryRepository inquiryRepository;
    private final ValidateUtil validateUtil;

    @Transactional
    public GetAllInquiryResponse getAllInquiry(String authorizationHeader) {
        String role = validateUtil.validate(validateUtil.getTokenByHeader(authorizationHeader)).getUserRole();
        if (role.equals("ADMIN")) {
            return GetAllInquiryResponse.builder()
                    .inquiries(inquiryRepository.findAll())
                    .build();
        }
        return GetAllInquiryResponse.builder().build();
    }

    @Transactional
    public AnswerResponse answering(String id, AnswerRequest answerRequest, String authorizationHeader) {
        String role = validateUtil.validate(validateUtil.getTokenByHeader(authorizationHeader)).getUserRole();
        if (role.equals("ADMIN")) {
            Inquiry inquiry = inquiryRepository.findById(Long.valueOf(id)).get();
            inquiry.setAnswer(answerRequest.getAnswer());
            inquiry.setInquiryStatus(InquiryStatus.ANSWERS_PRESENT);
            inquiryRepository.save(inquiry);
            return AnswerResponse.builder()
                    .uid(inquiry.getUid())
                    .inquiryStatus(inquiry.getInquiryStatus())
                    .status("답변 성공")
                    .build();
        }
        return AnswerResponse.builder()
                .status("답변 실패")
                .build();
    }
}

