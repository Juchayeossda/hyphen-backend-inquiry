package com.get.hyphenbackendinquiry.domain.inquiry.service;

import com.get.hyphenbackendinquiry.domain.inquiry.presentation.dto.request.AnswerRequest;
import com.get.hyphenbackendinquiry.domain.inquiry.presentation.dto.response.*;

public interface InquiryAdminService {

    GetAllInquiryResponse getAllInquiry(String authorizationHeader);
    AnswerResponse answering(String id, AnswerRequest answerRequest, String authorizationHeader);
}
