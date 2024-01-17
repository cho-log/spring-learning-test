package cholog.auth.ui;

import cholog.auth.application.AuthService;
import cholog.auth.application.AuthorizationException;
import cholog.auth.dto.AuthInfo;
import cholog.auth.dto.MemberResponse;
import cholog.auth.dto.TokenRequest;
import cholog.auth.dto.TokenResponse;
import cholog.auth.infrastructure.AuthorizationExtractor;
import cholog.auth.infrastructure.BasicAuthorizationExtractor;
import cholog.auth.infrastructure.BearerAuthorizationExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {
    private static final String SESSION_KEY = "USER";
    private static final String USERNAME_FIELD = "email";
    private static final String PASSWORD_FIELD = "password";

    private final AuthorizationExtractor<String> bearerAuthorizationExtractor = new BearerAuthorizationExtractor();
    private final AuthorizationExtractor<AuthInfo> basicAuthorizationExtractor = new BasicAuthorizationExtractor();
    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login/session")
    public ResponseEntity<Void> sessionLogin(HttpServletRequest request, HttpSession session) {
        Map<String, String[]> paramMap = request.getParameterMap();
        String email = paramMap.get(USERNAME_FIELD)[0];
        String password = paramMap.get(PASSWORD_FIELD)[0];

        if (authService.checkInvalidLogin(email, password)) {
            throw new AuthorizationException();
        }

        session.setAttribute(SESSION_KEY, email);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/members/me")
    public ResponseEntity<MemberResponse> findMyInfo(HttpSession session) {
        String email = (String) session.getAttribute(SESSION_KEY);
        MemberResponse member = authService.findMember(email);
        return ResponseEntity.ok().body(member);
    }

    @PostMapping("/login/token")
    public ResponseEntity<TokenResponse> tokenLogin(@RequestBody TokenRequest tokenRequest) {
        TokenResponse tokenResponse = authService.createToken(tokenRequest);
        return ResponseEntity.ok().body(tokenResponse);
    }

    @GetMapping("/members/you")
    public ResponseEntity<MemberResponse> findYourInfo(HttpServletRequest request) {
        String token = bearerAuthorizationExtractor.extract(request);
        MemberResponse member = authService.findMemberByToken(token);
        return ResponseEntity.ok().body(member);
    }

    /**
     * ex) request sample
     * <p>
     * GET /members/my HTTP/1.1
     * authorization: Basic ZW1haWxAZW1haWwuY29tOjEyMzQ=
     * accept: application/json
     */
    @GetMapping("/members/my")
    public ResponseEntity<MemberResponse> findMyInfo(HttpServletRequest request) {
        AuthInfo authInfo = basicAuthorizationExtractor.extract(request);
        String email = authInfo.getEmail();
        String password = authInfo.getPassword();

        if (authService.checkInvalidLogin(email, password)) {
            throw new AuthorizationException();
        }

        MemberResponse member = authService.findMember(email);
        return ResponseEntity.ok().body(member);
    }
}
