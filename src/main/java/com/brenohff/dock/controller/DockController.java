package com.brenohff.dock.controller;

import com.brenohff.dock.service.DockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/")
public class DockController {

    @Autowired
    private DockService dockService;

    @PostMapping(consumes = MediaType.TEXT_HTML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@RequestBody String body) {
        dockService.register(body);

        return ResponseEntity.ok().build();
    }

}
