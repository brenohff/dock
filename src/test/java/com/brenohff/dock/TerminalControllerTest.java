package com.brenohff.dock;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@FixMethodOrder
public class TerminalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String PAYLOAD = "44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN";
    private static final String PAYLOAD_WRONG = "ABCDEF;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN";

    @Test
    void saveTerminal() throws Exception {
        mockMvc.perform(post("/v1/terminal")
                        .contentType(MediaType.TEXT_HTML_VALUE)
                        .content(PAYLOAD))
                .andExpect(status().isOk());
    }

    @Test
    void saveTerminalWithWrongPayload() throws Exception {
        mockMvc.perform(post("/v1/terminal")
                        .contentType(MediaType.TEXT_HTML_VALUE)
                        .content(PAYLOAD_WRONG))
                .andExpect(status().isBadRequest());
    }

    @Test
    void saveDuplicatedTerminal() throws Exception {
        mockMvc.perform(post("/v1/terminal")
                        .contentType(MediaType.TEXT_HTML_VALUE)
                        .content(PAYLOAD))
                .andExpect(status().isConflict());
    }

    @Test
    void findTerminalByLogic() throws Exception {
        mockMvc.perform(get("/v1/terminal/44332211")).andExpect(status().isOk());
    }

    @Test
    void findNonExistingTerminalByLogic() throws Exception {
        mockMvc.perform(get("/v1/terminal/44332212")).andExpect(status().isNotFound());
    }

    @Test
    void updateTerminal() throws Exception {
        mockMvc.perform(put("/v1/terminal/44332211")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(getPayloadUpdate()))
                .andExpect(status().isOk());
    }

    @Test
    void updateNonExistingTerminal() throws Exception {
        mockMvc.perform(put("/v1/terminal/44332212")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(getPayloadUpdate()))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveWithUnsuportedMediaType() throws Exception {
        mockMvc.perform(post("/v1/terminal")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(PAYLOAD))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void deleteTerminal() throws Exception {
        mockMvc.perform(delete("/v1/terminal")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(PAYLOAD))
                .andExpect(status().isMethodNotAllowed());
    }

    private String getPayloadUpdate() {
        return "{\n" +
                "    \"logic\": 44332211,\n" +
                "    \"serial\": \"123\",\n" +
                "    \"model\": \"PWWIN\",\n" +
                "    \"sam\": 0,\n" +
                "    \"ptid\": \"F04A2E4088B\",\n" +
                "    \"plat\": 4,\n" +
                "    \"version\": \"8.00b3\",\n" +
                "    \"mxr\": 0,\n" +
                "    \"mxf\": 16777217,\n" +
                "    \"VERFM\": \"PWWIN\"\n" +
                "}";
    }
}
