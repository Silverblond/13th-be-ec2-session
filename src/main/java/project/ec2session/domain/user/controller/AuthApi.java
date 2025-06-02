package project.ec2session.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
            @ApiResponse(responseCode = "400", description = "유효성 검사 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                            {
                            "password" : "비밀번호는 필수 입력 값입니다.",
                            "username" : "아이디는 필수 입력 값입니다."
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "404", description = "로그인 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name = "잘못된 정보(username)", value = """
                            {
                            "status" : "404",
                            "message" : "정보를 정확히 입력해주세요."
                            }
                            """),
                            @ExampleObject(name = "잘못된 비밀번호", value = """
                            {
                            "status" : "404",
                            "message" : "잘못된 비밀번호입니다."
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "500", description = "잘못된 요청",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name ="잘못된 Body 요청, 잘못된 api주소로 요청시", value = """
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
            @ApiResponse(responseCode = "400", description = "유효성 검사 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                            {
                            "password" : "비밀번호는 필수 입력 값입니다.",
                            "nickname" : "닉네임은 필수 입력 값입니다.",
                            "username" : "아이디는 필수 입력 값입니다."
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "409", description = "중복 아이디로 회원가입 요청",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject("""
                            {
                            "status": 409,
                            "message": "이미 사용 중인 username 입니다."
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "500", description = "잘못된 요청",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name ="잘못된 Body 요청, 잘못된 api주소로 요청시", value = """
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
