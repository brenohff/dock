package com.brenohff.dock.beans;

import com.brenohff.dock.exception.JsonValidationException;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

import static com.brenohff.dock.consts.DockConstants.*;
import static com.brenohff.dock.consts.DockConstants.JSON_OBJECT_INDEX_VERFM;

@Configuration
public class JsonSchemaBean {

    @Bean
    public Schema getJsonSchema() {
        try (InputStream inputStream = getClass().getResourceAsStream("/terminal.json")) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));

            return SchemaLoader.load(rawSchema);
        } catch (IOException e) {
            throw new JsonValidationException(e.getMessage());
        }
    }

    public JSONObject parseStringToJSONObject(String payload) {
        String[] splitPayload = payload.split(SPLIT_DELIMITER);

        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put(JSON_OBJECT_LOGIC, Integer.parseInt(splitPayload[JSON_OBJECT_INDEX_LOGIC]));
            jsonObject.put(JSON_OBJECT_SERIAL, splitPayload[JSON_OBJECT_INDEX_SERIAL]);
            jsonObject.put(JSON_OBJECT_MODEL, splitPayload[JSON_OBJECT_INDEX_MODEL]);
            jsonObject.put(JSON_OBJECT_SAM, Integer.parseInt(splitPayload[JSON_OBJECT_INDEX_SAM]));
            jsonObject.put(JSON_OBJECT_PTID, splitPayload[JSON_OBJECT_INDEX_PTID]);
            jsonObject.put(JSON_OBJECT_PLAT, Integer.parseInt(splitPayload[JSON_OBJECT_INDEX_PLAT]));
            jsonObject.put(JSON_OBJECT_VERSION, splitPayload[JSON_OBJECT_INDEX_VERSION]);
            jsonObject.put(JSON_OBJECT_MXR, Integer.parseInt(splitPayload[JSON_OBJECT_INDEX_MXR]));
            jsonObject.put(JSON_OBJECT_MXF, Integer.parseInt(splitPayload[JSON_OBJECT_INDEX_MXF]));
            jsonObject.put(JSON_OBJECT_VERFM, splitPayload[JSON_OBJECT_INDEX_VERFM]);

            return jsonObject;
        } catch (Exception e) {
            throw new JsonValidationException(e.getMessage());
        }
    }

    public JSONObject validateJson(String payload) {
        JSONObject jsonObject = parseStringToJSONObject(payload);

        try {
            getJsonSchema().validate(jsonObject);
        } catch (ValidationException e) {
            throw new JsonValidationException(e.getMessage());
        }

        return jsonObject;
    }

}
