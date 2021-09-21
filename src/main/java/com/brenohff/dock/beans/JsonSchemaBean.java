package com.brenohff.dock.beans;

import com.brenohff.dock.exception.JsonValidationException;
import com.brenohff.dock.exception.SchemaNotFoundException;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class JsonSchemaBean {

    public Schema getJsonSchema(String schema) {
        try (InputStream inputStream = getClass().getResourceAsStream(schema)) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));

            return SchemaLoader.load(rawSchema);
        } catch (IOException e) {
            throw new SchemaNotFoundException("Could not locate schema with name: " + schema);
        }
    }

    public void validateJson(JSONObject jsonObject, String schema) {
        try {
            getJsonSchema(schema).validate(jsonObject);
        } catch (ValidationException e) {
            throw new JsonValidationException(e.getMessage());
        }
    }

}
