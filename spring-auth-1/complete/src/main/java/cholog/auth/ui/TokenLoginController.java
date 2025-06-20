package cholog.auth.ui;

import cholog.auth.application.AuthService;
import cholog.auth.dto.MemberResponse;
import cholog.auth.dto.TokenRequest;
import cholog.auth.dto.TokenResponse;
import cholog.auth.infrastructure.AuthorizationExtractor;
import cholog.auth.infrastructure.BearerAuthorizationExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenLoginController {
    private final AuthService authService;
    private final AuthorizationExtractor<String> authorizationExtractor;

    public TokenLoginController(AuthService authService) {
        this.authService = authService;
        this.authorizationExtractor = new BearerAuthorizationExtractor();
    }

    /**
     * ex) request sample
     * <p>
     * POST /login/token HTTP/1.1
     * accept: application/json
     * content-type: application/json; charset=UTF-8
     * <p>
     * {
     * "email": "email@email.com",
     * "password": "1234"
     * }
     */
    @PostMapping("/login/token")
    public ResponseEntity<TokenResponse> tokenLogin(@RequestBody TokenRequest tokenRequest) {
        TokenResponse tokenResponse = authService.createToken(tokenRequest);
        return ResponseEntity.ok().body(tokenResponse);
    }

    /**
     * ex) request sample
     * <p>
     * GET /members/me/token HTTP/1.1
     * authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbWFpbEBlbWFpbC5jb20iLCJpYXQiOjE3NTA0MTY1MzksImV4cCI6MTc1MDQyMDEzOX0.mhaPDrphpRE9mE1YEZreOmDjZCapBrFe-K4SZvXMlAY
     * accept: application/json
     */
    @GetMapping("/members/me/token")
    public ResponseEntity<MemberResponse> findMyInfo(HttpServletRequest request) {
        String token = authorizationExtractor.extract(request);
        MemberResponse member = authService.findMemberByToken(token);
        return ResponseEntity.ok().body(member);
    }
}
