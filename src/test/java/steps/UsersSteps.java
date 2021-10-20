package steps;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojos.CreateUserRequest;
import pojos.CreateUserResponse;
import pojos.UsersPojoFull;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UsersSteps {

    public static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://reqres.in/api")
                    .setBasePath("/users")
                    .setContentType(ContentType.JSON)
                    .build();

    private CreateUserResponse user;

    public CreateUserResponse createUser(CreateUserRequest rq) {
        user = given()
                .spec(REQ_SPEC)
                .body(rq)
                .post().as(CreateUserResponse.class);
        return user;
    }

    public UsersPojoFull getUser() {
        return given().get("/" + user.getId()).as(UsersPojoFull.class);
    }

    public static List<UsersPojoFull> getUsers() {
        return given().spec(REQ_SPEC)
                .get()
                .jsonPath()
                .getList("data", UsersPojoFull.class);
    }

    public static UsersPojoFull getUser(int id) {
        return given().get("/" + id).as(UsersPojoFull.class);
    }

}
