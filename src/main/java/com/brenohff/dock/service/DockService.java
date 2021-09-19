package com.brenohff.dock.service;

import com.brenohff.dock.beans.JsonSchemaBean;
import com.brenohff.dock.entity.TerminalEntity;
import com.brenohff.dock.repository.TerminalRepository;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DockService {

    @Autowired
    private JsonSchemaBean jsonBean;

    @Autowired
    private TerminalRepository terminalRepository;

    public TerminalEntity register(String payload) {
        JSONObject jsonObject = jsonBean.validateJson(payload);

        TerminalEntity terminalEntity = parseJsonObjectToEntity(jsonObject);

        terminalRepository.save(terminalEntity);

        return terminalEntity;
    }

    private TerminalEntity parseJsonObjectToEntity(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), TerminalEntity.class);
    }
}
