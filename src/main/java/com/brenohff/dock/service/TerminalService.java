package com.brenohff.dock.service;

import com.brenohff.dock.beans.JsonSchemaBean;
import com.brenohff.dock.entity.TerminalEntity;
import com.brenohff.dock.exception.DuplicatedTerminalException;
import com.brenohff.dock.exception.JsonValidationException;
import com.brenohff.dock.exception.ObjectNotFound;
import com.brenohff.dock.repository.TerminalRepository;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerminalService {

    @Autowired
    private JsonSchemaBean jsonBean;

    @Autowired
    private TerminalRepository terminalRepository;

    public TerminalEntity register(String payload) {
        JSONObject jsonObject = jsonBean.parseStringToJSONObject(payload);
        jsonBean.validateJson(jsonObject);

        TerminalEntity terminalEntity = parseJsonObjectToEntity(jsonObject);

        if (existsTerminal(terminalEntity.getLogic())) {
            throw new DuplicatedTerminalException("Terminal already exists with logic: " + terminalEntity.getLogic());
        }

        terminalRepository.save(terminalEntity);

        return terminalEntity;
    }

    public TerminalEntity updateTerminal(int logic, TerminalEntity terminal) {
        jsonBean.validateJson(parseEntityToJsonObject(terminal));

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
        return terminalRepository.findById(logic).orElseThrow(() -> new ObjectNotFound("Terminal not founded with logic: " + logic));
    }

    private TerminalEntity parseJsonObjectToEntity(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), TerminalEntity.class);
    }

    private JSONObject parseEntityToJsonObject(TerminalEntity terminal) {
        try {
            return new JSONObject(new Gson().toJson(terminal));
        } catch (Exception e) {
            throw new JsonValidationException("Couldn't parse request");
        }
    }
}
