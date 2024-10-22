package org.api.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.testng.Assert;

import java.io.IOException;

public class JsonUtil {

    public static void validateJsonAgainstSchema(String jsonFile, String schemaPath) throws IOException, ProcessingException {
        JsonNode fileNode;
        fileNode = getMapper().readTree(jsonFile);
        var schemaNode = JsonLoader.fromResource(schemaPath);
        var schema = JsonSchemaFactory.byDefault().getJsonSchema(schemaNode);
        var validatedSchema = schema.validate(fileNode);
        Assert.assertTrue(validatedSchema.isSuccess());
    }

    public static ObjectMapper getMapper() {
        return JsonMapper.builder()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
                .addModule(new JavaTimeModule())
                .build();
    }

}
