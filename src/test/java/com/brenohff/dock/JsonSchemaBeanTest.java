package com.brenohff.dock;

import com.brenohff.dock.beans.JsonSchemaBean;
import com.brenohff.dock.exception.JsonValidationException;
import com.brenohff.dock.exception.SchemaNotFoundException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static com.brenohff.dock.consts.DockConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JsonSchemaBeanTest {

    @Autowired
    private JsonSchemaBean jsonSchemaBean;

    private static final String PAYLOAD = "44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN";

    @Test
    public void getJsonSchema() {
        Exception exception = assertThrows(SchemaNotFoundException.class, () -> {
            jsonSchemaBean.getJsonSchema("/file.json");
        });

        String expectedMessage = "Could not locate schema with name: /file.json";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void validateJson() {
        Exception exception = assertThrows(JsonValidationException.class, () -> {
            jsonSchemaBean.validateJson(buildJsonObject(), "/terminal.json");
        });

        String expectedMessage = "#: 5 schema violations found";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    private JSONObject buildJsonObject() {
        JSONObject jsonObject = new JSONObject();

        String[] splitPayload = PAYLOAD.split(SPLIT_DELIMITER);

        jsonObject.put(JSON_OBJECT_LOGIC, splitPayload[JSON_OBJECT_INDEX_LOGIC]);
        jsonObject.put(JSON_OBJECT_SERIAL, splitPayload[JSON_OBJECT_INDEX_SERIAL]);
        jsonObject.put(JSON_OBJECT_MODEL, splitPayload[JSON_OBJECT_INDEX_MODEL]);
        jsonObject.put(JSON_OBJECT_SAM, splitPayload[JSON_OBJECT_INDEX_SAM]);
        jsonObject.put(JSON_OBJECT_PTID, splitPayload[JSON_OBJECT_INDEX_PTID]);
        jsonObject.put(JSON_OBJECT_PLAT, splitPayload[JSON_OBJECT_INDEX_PLAT]);
        jsonObject.put(JSON_OBJECT_VERSION, splitPayload[JSON_OBJECT_INDEX_VERSION]);
        jsonObject.put(JSON_OBJECT_MXR, splitPayload[JSON_OBJECT_INDEX_MXR]);
        jsonObject.put(JSON_OBJECT_MXF, splitPayload[JSON_OBJECT_INDEX_MXF]);
        jsonObject.put(JSON_OBJECT_VERFM, splitPayload[JSON_OBJECT_INDEX_VERFM]);

        return jsonObject;
    }


}
