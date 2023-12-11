package cholog;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ResponseJsonTest {

    @Test
    void responseJson() {
        var response = RestAssured
            .given().log().all()
            .when().get("/person")
            .then().log().all().extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.as(Person.class).getName()).isEqualTo("Brown");
        assertThat(response.as(Person.class).getAge()).isEqualTo(20);
    }

}
