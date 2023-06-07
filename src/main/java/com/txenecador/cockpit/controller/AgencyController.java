package com.txenecador.cockpit.controller;

import com.txenecador.cockpit.controller.dto.AgencyJson;
import com.txenecador.cockpit.controller.dto.AgencyJsonCreate;
import com.txenecador.cockpit.controller.mapper.AgencyMapper;
import com.txenecador.cockpit.service.AgencyService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/agency")
@Transactional
@RequiredArgsConstructor
class AgencyController {

    private final AgencyMapper mapper;
    private final AgencyService service;

    @GetMapping
    public ResponseEntity<List<AgencyJson>> findAll() {
        var agencies = service.findAll().stream()
                .map(mapper::mapTo)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(agencies);
    }

    @PostMapping
    public ResponseEntity<AgencyJson> create(@RequestBody @Valid final AgencyJsonCreate input) {
        var agency = service.create(mapper.mapTo(input));
        return ResponseEntity.accepted().body(mapper.mapTo(agency));
    }

    @PutMapping("/{code}")
    public ResponseEntity<AgencyJson> update(@PathVariable String code, @RequestBody @Valid AgencyJsonCreate input) {
        var agency = service.update(code, mapper.mapTo(input));
        return ResponseEntity.accepted().body(mapper.mapTo(agency));
    }

    @GetMapping("/{code}")
    public ResponseEntity<AgencyJson> get(@PathVariable final String code) {
        var agency = service.get(code);
        return ResponseEntity.ok().body(mapper.mapTo(agency));
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteByCode(@PathVariable final String code) {
        service.delete(code);
    }
}
