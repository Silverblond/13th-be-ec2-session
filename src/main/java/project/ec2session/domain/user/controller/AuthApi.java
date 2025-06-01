package project.ec2session.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.ec2session.domain.user.dto.UserReq;

public interface AuthApi {

    @Operation(summary = "로그인", description = "로그인 시도")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                            {
                            "accessToken" : "<accessToken>"
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "400", description = "로그인 실패: 잘못된 매개변수",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                            {
                            "status" : "404",
                            "message" : "아이디는 필수 입력 값입니다."
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "400", description = "로그인 실패: 잘못된 매개변수",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                            {
                            "status" : "404",
                            "message" : "비밀번호는 필수 입력 값입니다."
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "400", description = "로그인 실패: 잘못된 매개변수",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                            {
                            "status" : "404",
                            "message" : "비밀번호는 필수 입력 값입니다.",
                            "message" : "아이디는 필수 입력 값입니다."
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "404", description = "로그인 실패: 잘못된 정보",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                            {
                            "status" : "404",
                            "message" : "정보를 정확히 입력해주세요."
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "404", description = "로그인 실패: 잘못된 비밀번호",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                            {
                            "status" : "404",
                            "message" : "잘못된 비밀번호입니다."
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "500", description = "로그인 실패: 잘못된 요청",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                            {
                            "status" : "500",
                            "message" : "서버 에러입니다. 서버 팀에 연락주세요."
                            }
                            """)
                    }))
    })
    @PostMapping("/sign-in")
    ResponseEntity<?> signIn(@RequestBody @Valid UserReq.SignInDto request);

    @Operation(summary = "회원가입", description = "회원가입 시도")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "회원가입 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                            {
                            "userId" : "1"
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "400", description = "회원가입 실패: 잘못된 매개변수",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                            {
                            "password" : "비밀번호는 필수 입력 값입니다."
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "400", description = "회원가입 실패: 잘못된 매개변수",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                            {
                            "nickname" : "닉네임은 필수 입력 값입니다."
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "400", description = "회원가입 실패: 잘못된 매개변수",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                            {
                            "username" : "아이디는 필수 입력 값입니다."
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "500", description = "회원가입 실패: 잘못된 요청",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                            {
                            "status" : "500",
                            "message" : "서버 에러입니다. 서버 팀에 연락주세요."
                            }
                            """)
                    }))
    })
    @PostMapping("/sign-up")
    ResponseEntity<?> signUp(@RequestBody @Valid UserReq.SignUpDto request);
}
