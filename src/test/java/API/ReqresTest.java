package API;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RegressTest {
    private static final String URL = "https://reqres.in";
    @Test
    public void checkAvatarAndIdTest(){
        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL+"/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.stream().forEach(x->Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        
        Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@regres.in")));
        }
    }

