package com.get.hyphenbackendinquiry.domain.inquiry.presentation;

import com.get.hyphenbackendinquiry.domain.inquiry.presentation.dto.request.AnswerRequest;
import com.get.hyphenbackendinquiry.domain.inquiry.presentation.dto.response.*;
import com.get.hyphenbackendinquiry.domain.inquiry.service.InquiryAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

// RestController("/api/inquiry")
@Tag(name = "InquiryController", description = "문의 API")
@RestController
@RequestMapping("/api/inquiry")
@RequiredArgsConstructor
public class InquiryAdminController {

    private final InquiryAdminService inquiryAdminService;

    // GET("/inquirys/all")
    @Operation(method = "GET", summary = "문의 조회 API", description = "서비스 문의 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetAllInquiryResponse.class),
                            examples = @ExampleObject(value = "{\"inquiries\":[" +
                                    "{\"createdDateTime\":\"2023-09-10 16:44:42\",\"modifiedDateTime\":\"2023-09-10 16:44:42\",\"id\":2,\"uid\":\"root_admin\",\"inquiryStatus\":\"NO_ANSWER\",\"title\":\"test\",\"content\":\"testtest\",\"answer\":null}," +
                                    "{\"createdDateTime\":\"2023-09-10 16:47:10\",\"modifiedDateTime\":\"2023-09-10 16:47:10\",\"id\":3,\"uid\":\"root_admin\",\"inquiryStatus\":\"NO_ANSWER\",\"title\":\"test\",\"content\":\"testtest\",\"answer\":null}" +
                                    "]}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @GetMapping("/inquirys/all")
    public ResponseEntity<GetAllInquiryResponse> gerAllInquiry(@RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(inquiryAdminService.getAllInquiry(authorizationHeader));
    }

    // PATCH("/inquirys/{id}/answer")
    @Operation(method = "PATCH", summary = "문의 답변 API", description = "서비스 문의 답변 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AnswerResponse.class),
                            examples = @ExampleObject(value = "{\"uid\":\"root_admin\",\"inquiryStatus\":\"ANSWERS_PRESENT\",\"status\":\"문의 성공\"}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "문의 답변 정보",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AnswerRequest.class),
                    examples = @ExampleObject(value = "{\"answer\":\"hello\"}")
            )
    )
    @PatchMapping("/inquirys/{id}/answer")
    public ResponseEntity<AnswerResponse> answering(@PathVariable("id") String id, @Validated @RequestBody AnswerRequest answerRequest, @RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(inquiryAdminService.answering(id, answerRequest, authorizationHeader));
    }
}

