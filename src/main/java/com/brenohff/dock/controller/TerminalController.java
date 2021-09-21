package com.brenohff.dock.controller;

import com.brenohff.dock.entity.TerminalEntity;
import com.brenohff.dock.service.TerminalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController()
@RequestMapping("/v1/terminal")
@Api(value = "DOCK API")
public class TerminalController {

    @Autowired
    private TerminalService dockService;

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @ApiOperation(value = "Save a new terminal")
    @PostMapping(consumes = MediaType.TEXT_HTML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TerminalEntity> register(@RequestBody String payload) {
        TerminalEntity terminal = dockService.register(payload);
        return ResponseEntity.created(URI.create("/v1/terminal/" + terminal.getLogic())).body(terminal);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Not Found"),
    })
    @ApiOperation(value = "Search an existing terminal")
    @GetMapping("/{logic}")
    public ResponseEntity<TerminalEntity> findTerminalsByLogic(@PathVariable("logic") int logic) {
        return ResponseEntity.ok().body(dockService.findTerminalsByLogic(logic));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @ApiOperation(value = "Update an existing terminal")
    @PutMapping(path = "/{logic}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TerminalEntity> updateTerminal(@PathVariable("logic") int logic, @RequestBody TerminalEntity terminal) {
        return ResponseEntity.ok().body(dockService.updateTerminal(logic, terminal));
    }

}