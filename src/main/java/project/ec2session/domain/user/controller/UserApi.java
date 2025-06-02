package project.ec2session.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.ec2session.common.auth.CustomUserDetails;
import project.ec2session.domain.user.dto.UserReq;

public interface UserApi {

    @Operation(summary = "로그인한 사용자 조회", description = "현재 로그인한 사용자의 정보 반환 시도")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인한 사용자 정보 조회 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                                    {
                                     "userId": 1,
                                     "username": "test",
                                     "nickname": "tester"
                                    }
                                    """)
                    })),
            @ApiResponse(responseCode = "403", description = "조회 실패: Authorization 미 인증시",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                                    {
                                    "timestamp": "2025-06-01T08:54:42.091+00:00",
                                    "status": 403,
                                    "error": "Forbidden",
                                    "path": "/api/v1/users/me"
                                    }
                                    """)
                    })),
            @ApiResponse(responseCode = "404", description = "정보 조회 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    {
                                        "status": "404",
                                        "message": "존재하지 않는 회원입니다."
                                    }
                                    """)
                    })),
            @ApiResponse(responseCode = "500", description = "조회 실패: Authorization 인증 실패시",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                                    {
                                    "timestamp": "2025-06-01T08:54:42.091+00:00",
                                    "status": 500,
                                    "error": "Internal Server Error",
                                    "path": "/api/v1/users/me"
                                    }
                                    """)
                    }))
//            @ApiResponse(responseCode = "500", description = "조회 실패: 잘못된 요청",
//                    content = @Content(mediaType = "application/json", examples = {
//                            @ExampleObject("""
//                                    {
//                                    "status" : "500",
//                                    "message" : "서버 에러입니다. 서버 팀에 연락주세요."
//                                    }
//                                    """)
//                    }))
    })
    @GetMapping("/me")
    ResponseEntity<?> getUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails);

    @Operation(summary = "전체 사용자 정보 조회", description = "전체 사용자 정보 조회 시도(인증된 사용자만 접근 가능)\n" +
            "Header(Key, Value) : (Authorization : Bearer <accessKey>)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "전체 사용자 조회 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                                    [
                                      {
                                       "userId": 1,
                                       "username": "test1",
                                       "nickname": "tester1"
                                      },
                                      {
                                       "userId": 2,
                                       "username": "test2",
                                       "nickname": "tester2"
                                      }
                                    ]
                                    """)
                    })),
            @ApiResponse(responseCode = "403", description = "조회 실패: Authorization와 함께 요청을 보내지 않았을 경우",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                                    {
                                    "timestamp": "2025-06-01T08:54:42.091+00:00",
                                    "status": 403,
                                    "error": "Forbidden",
                                    "path": "/api/v1/users"
                                    }
                                    """)
                    })),
            @ApiResponse(responseCode = "500", description = "조회 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name = "Authorization 인증 실패시(잘못된 accessKey)", value = """
                                    {
                                    "timestamp": "2025-06-01T08:54:42.091+00:00",
                                    "status": 500,
                                    "error": "Internal Server Error",
                                    "path": "/api/v1/users"
                                    }
                                    """),
                            @ExampleObject(name = "잘못된 Body 요청, 잘못된 api주소로 요청시", value = """
                                    {
                                    "status" : "500",
                                    "message" : "서버 에러입니다. 서버 팀에 연락주세요."
                                    }
                                    """)
                    }))
    })
    @GetMapping
    ResponseEntity<?> getAllUser();

    @Operation(summary = "로그인한 사용자 정보 수정", description = "정보 수정 시도(인증된 사용자만 접근 가능)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "사용자 정보 수정 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                                    요청 성공""")
                    })),
            @ApiResponse(responseCode = "400", description = "유효성 검사 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                            {
                            "nickname": "닉네임은 필수 입력 값입니다."
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "403", description = "수정 실패: Authorization 미 인증시",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                            {
                            "timestamp": "2025-06-01T08:54:42.091+00:00",
                            "status": 403,
                            "error": "Forbidden",
                            "path": "/api/v1/users"
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "404", description = "정보 조회 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    {
                                        "status": "404",
                                        "message": "존재하지 않는 회원입니다."
                                    }
                                    """)
                    })),
            @ApiResponse(responseCode = "500", description = "수정 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name = "Authorization 인증 실패시(잘못된 accessKey)", value = """
                                    {
                                    "timestamp": "2025-06-01T08:54:42.091+00:00",
                                    "status": 500,
                                    "error": "Internal Server Error",
                                    "path": "/api/v1/users"
                                    }
                                    """),
                            @ExampleObject(name = "잘못된 Body 요청, 잘못된 api주소로 요청시", value = """
                                    {
                                    "status" : "500",
                                    "message" : "서버 에러입니다. 서버 팀에 연락주세요."
                                    }
                                    """)
                    }))
    })
    @PutMapping
    public ResponseEntity<?> updateUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails,
                                            @RequestBody @Valid UserReq.UpdateInfo request);
}
