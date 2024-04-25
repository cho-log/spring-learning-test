package cholog;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ExceptionTest {
    @Test
    void handleExceptionUsingExceptionHandler() {
        var response = RestAssured
                .given().log().all()
                .when().get("/products?keyword=apple")
                .then().log().all().extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void handleExceptionUsingControllerAdvice() {
        var responseForProduct = RestAssured
                .given().log().all()
                .when().get("/products/1")
                .then().log().all().extract();

        assertThat(responseForProduct.statusCode()).isEqualTo(HttpStatus.NOT_FOUND.value());

        var responseForMember = RestAssured
                .given().log().all()
                .when().get("/members/1")
                .then().log().all().extract();

        assertThat(responseForMember.statusCode()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}
