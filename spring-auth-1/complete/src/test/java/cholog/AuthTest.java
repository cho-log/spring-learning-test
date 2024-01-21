package cholog;

import cholog.auth.dto.MemberResponse;
import cholog.auth.dto.TokenRequest;
import cholog.auth.dto.TokenResponse;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthTest {
    private static final String USERNAME_FIELD = "email";
    private static final String PASSWORD_FIELD = "password";
    private static final String EMAIL = "email@email.com";
    private static final String PASSWORD = "1234";

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void basicLogin() {
        MemberResponse member = RestAssured
                .given().log().all()
                .auth().preemptive().basic(EMAIL, PASSWORD)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/members/me/basic")
                .then().log().all()
                .statusCode(HttpStatus.OK.value()).extract().as(MemberResponse.class);

        assertThat(member.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    void sessionLogin() {
        String cookie = RestAssured
                .given().log().all()
                .param(USERNAME_FIELD, EMAIL)
                .param(PASSWORD_FIELD, PASSWORD)
                .when().post("/login/session")
                .then().log().all().extract().header("Set-Cookie").split(";")[0];

        MemberResponse member = RestAssured
                .given().log().all()
                .header("Cookie", cookie)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/members/me/session")
                .then().log().all()
                .statusCode(HttpStatus.OK.value()).extract().as(MemberResponse.class);

        assertThat(member.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    void tokenLogin() {
        String accessToken = RestAssured
                .given().log().all()
                .body(new TokenRequest(EMAIL, PASSWORD))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/login/token")
                .then().log().all().extract().as(TokenResponse.class).getAccessToken();

        MemberResponse member = RestAssured
                .given().log().all()
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/members/me/token")
                .then().log().all()
                .statusCode(HttpStatus.OK.value()).extract().as(MemberResponse.class);

        assertThat(member.getEmail()).isEqualTo(EMAIL);
    }
}