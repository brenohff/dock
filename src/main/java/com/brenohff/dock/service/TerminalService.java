package com.brenohff.dock.service;

import com.brenohff.dock.beans.JsonSchemaBean;
import com.brenohff.dock.entity.TerminalEntity;
import com.brenohff.dock.exception.DuplicatedTerminalException;
import com.brenohff.dock.exception.JsonValidationException;
import com.brenohff.dock.exception.ObjectNotFoundException;
import com.brenohff.dock.repository.TerminalRepository;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.brenohff.dock.consts.DockConstants.*;
import static com.brenohff.dock.consts.DockConstants.JSON_OBJECT_INDEX_VERFM;

@Service
public class TerminalService {

    @Autowired
    private JsonSchemaBean jsonBean;

    @Autowired
    private TerminalRepository terminalRepository;

    public TerminalEntity register(String payload) {
        JSONObject jsonObject = parseStringToJsonObject(payload);
        jsonBean.validateJson(jsonObject, TERMINAL_SCHEMA);

        TerminalEntity terminalEntity = parseJsonObjectToEntity(jsonObject);

        if (existsTerminal(terminalEntity.getLogic())) {
            throw new DuplicatedTerminalException("Terminal already exists with logic: " + terminalEntity.getLogic());
        }

        terminalRepository.save(terminalEntity);

        return terminalEntity;
    }

    public TerminalEntity updateTerminal(int logic, TerminalEntity terminal) {
        jsonBean.validateJson(parseEntityToJsonObject(terminal), TERMINAL_SCHEMA);

        TerminalEntity terminalEntity = findTerminalsByLogic(logic);

        terminalEntity.setSerial(terminal.getSerial());
        terminalEntity.setModel(terminal.getModel());
        terminalEntity.setSam(terminal.getSam());
        terminalEntity.setPtid(terminal.getPtid());
        terminalEntity.setPlat(terminal.getPlat());
        terminalEntity.setVersion(terminal.getVersion());
        terminalEntity.setMxr(terminal.getMxr());
        terminalEntity.setMxf(terminal.getMxf());
        terminalEntity.setVerfm(terminal.getVerfm());

        terminalRepository.save(terminalEntity);

        return terminalEntity;
    }

    public boolean existsTerminal(int logic) {
        return terminalRepository.findById(logic).isPresent();
    }

    public TerminalEntity findTerminalsByLogic(int logic) {
        return terminalRepository.findById(logic).orElseThrow(() -> new ObjectNotFoundException("Terminal not founded with logic: " + logic));
    }

    private TerminalEntity parseJsonObjectToEntity(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), TerminalEntity.class);
    }

    private JSONObject parseStringToJsonObject(String payload) {
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

    private JSONObject parseEntityToJsonObject(TerminalEntity terminal) {
        try {
            return new JSONObject(new Gson().toJson(terminal));
        } catch (Exception e) {
            throw new JsonValidationException("Couldn't parse request");
        }
    }
}
