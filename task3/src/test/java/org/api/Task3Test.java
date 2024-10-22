package org.api;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.api.models.GetEventsResponse;
import org.api.utils.JsonUtil;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

@Slf4j
public class Task3Test extends BaseTest {

    private static final String ENDPOINT = "/services/evapi/event/GetEvents";

    @Test
    public void verifyJsonSchemaForGetEventsEndpoint() throws IOException, ProcessingException {
        var response1 = given()
                .contentType(ContentType.JSON)
                .when()
                .get(ENDPOINT)
                .then()
                .extract()
                .response();
        var responseAsDto = response1.as(GetEventsResponse.class);
        log.info("Response: " + responseAsDto);

        var responseAsString = response1.asString();
        JsonUtil.validateJsonAgainstSchema(responseAsString, "/schema.json");
    }
}
