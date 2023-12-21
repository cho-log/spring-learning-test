package cholog;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CRUDTest {
    @Test
    void create() {
        var response = RestAssured
                .given().log().all()
                .body(new Member("brown", 20))
                .contentType(ContentType.JSON)
                .when().post("/members")
                .then().log().all().extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void read() {
        create();

        var response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .when().get("/members")
                .then().log().all().extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getList("", Member.class)).hasSize(1);
    }

    @Test
    void update() {
        create();

        var response = RestAssured
                .given().log().all()
                .body(new Member("brown", 30))
                .contentType(ContentType.JSON)
                .when().put("/members/1")
                .then().log().all().extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void delete() {
        create();

        var response = RestAssured
                .given().log().all()
                .when().delete("/members/1")
                .then().log().all().extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}
