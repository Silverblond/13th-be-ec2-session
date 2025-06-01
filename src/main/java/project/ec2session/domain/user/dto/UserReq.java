package project.ec2session.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import project.ec2session.domain.user.entity.User;

public class UserReq {

    public record SignUpDto(
            @NotBlank(message = "아이디는 필수 입력 값입니다.")
            @Schema(description = "사용자 아이디", example = "test")
            String username,
            @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
            @Schema(description = "사용자 비밀번호", example = "password123@")
            String password,
            @NotBlank(message = "닉네임은 필수 입력 값입니다.")
            @Schema(description = "사용자 닉네임", example = "hello")
            String nickname
    ) {
        public User toEntity(String encodedPassword) {
            return User.builder()
                    .username(username)
                    .password(encodedPassword)
                    .nickname(nickname)
                    .build();
        }
    }

    public record SignInDto(
            @NotBlank(message = "아이디는 필수 입력 값입니다.")
            @Schema(description = "사용자 아이디", example = "test")
            String username,
            @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
            @Schema(description = "사용자 비밀번호", example = "password123@")
            String password
    ) { }

    public record UpdateInfo(
            @NotBlank(message = "닉네임은 필수 입력 값입니다.")
            @Schema(description = "사용자 닉네임", example = "hello")
            String nickname
    ) { }
}
