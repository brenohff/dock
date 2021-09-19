package com.brenohff.dock.controller;

import com.brenohff.dock.entity.TerminalEntity;
import com.brenohff.dock.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/terminal/")
public class TerminalController {

    @Autowired
    private TerminalService dockService;

    @PostMapping(consumes = MediaType.TEXT_HTML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TerminalEntity> register(@RequestBody String payload) {
        return ResponseEntity.ok().body(dockService.register(payload));
    }

}
