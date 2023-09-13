package com.get.hyphenbackendinquiry.domain.inquiry.service;

import com.get.hyphenbackendinquiry.domain.inquiry.presentation.dto.request.MakeInquiryRequest;
import com.get.hyphenbackendinquiry.domain.inquiry.presentation.dto.response.*;

public interface InquiryService {

    MakeInquiryResponse makeInquiry(MakeInquiryRequest makeInquiryRequest, String authorizationHeader);
    GetInquiryResponse getInquiry(String id, String authorizationHeader);
    GetMyInquirysResponse getMyInquirys(String authorizationHeader);
}
