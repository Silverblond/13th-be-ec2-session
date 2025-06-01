package project.ec2session.domain.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.ec2session.domain.user.dto.TokenDto;
import project.ec2session.domain.user.dto.UserReq;
import project.ec2session.domain.user.service.AuthService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "[인증 관련 API]", description = "인증 관련 API")
public class AuthController implements AuthApi{
    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(
            @RequestBody @Valid UserReq.SignInDto request
    ) {
        TokenDto response = authService.signIn(request);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid UserReq.SignUpDto request) {
        Long userId = authService.signUp(request);

        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        return ResponseEntity.ok(response);
    }
}
