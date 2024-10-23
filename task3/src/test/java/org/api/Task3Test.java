package org.api;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.api.models.GetEventsResponse;
import org.api.utils.JsonUtil;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.api.utils.JsonUtil.prettyPrint;

@Slf4j
public class Task3Test extends BaseTest {

    private static final String ENDPOINT = "/services/evapi/event/GetEvents";

    @Test
    public void verifyJsonSchemaForGetEventsEndpoint() throws IOException, ProcessingException {
        var response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(ENDPOINT)
                .then()
                .extract()
                .response();
//      4. Use serialization/ deserialization for request/ response
        var responseAsDto = response.as(GetEventsResponse.class);

        var responseAsString = response.asString();
        log.info("Response:\n" + prettyPrint(responseAsString));
        //todo need to fix this verification, do not work now...
//      3. Validate JSON schema for this API call
        JsonUtil.validateJsonAgainstSchema(responseAsString, "/schema.json");
    }

}
