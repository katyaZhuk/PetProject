import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojos.CreateUserRequest;
import pojos.CreateUserResponse;
import pojos.UsersPojoFull;
import utils.RestWrapper;
import utils.UserGenerator;

import static org.assertj.core.api.Assertions.assertThat;

public class RestTest {

    private static RestWrapper api;

    @BeforeAll
    public static void prepareClient() {
        api = RestWrapper.loginAs("eve.holt@reqres.in", "cityslicks");
    }

    @Test
    public void getUsers() {

        assertThat(api.getUsers()).extracting(UsersPojoFull::getEmail).contains("george.bluth@reqres.in");
    }

    @Test
    public void createUser() {

        CreateUserRequest rq = UserGenerator.getSimpleUser();
        CreateUserResponse rs = api.createUser(rq);

        assertThat(rs).isNotNull()
                .extracting(CreateUserResponse::getName)
                .isEqualTo(rq.getName());

    }
}
