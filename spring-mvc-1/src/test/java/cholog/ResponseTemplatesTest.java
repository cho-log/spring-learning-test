package cholog;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ResponseTemplatesTest {

    @Test
    void responseTemplatesPage() {
        var response = RestAssured
                .given().log().all()
                .when().get("/template?name=Brie")
                .then().log().all().extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.asString()).contains("Hello, Brie!");
    }

    @Test
    void responseTemplatesHelloPage() {
        var response = RestAssured
            .given().log().all()
            .when().get("/hello")
            .then().log().all().extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
