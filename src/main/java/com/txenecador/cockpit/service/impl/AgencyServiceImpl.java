package com.txenecador.cockpit.service.impl;

import com.txenecador.cockpit.entities.Agency;
import com.txenecador.cockpit.repositories.AgencyRepository;
import com.txenecador.cockpit.service.AgencyService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository repository;

    @Override
    public List<Agency> findAll() {
        return repository.findAll();
    }

    @Override
    public Agency create(Agency agency) {
        agency.setCode(UUID.randomUUID().toString());
        return repository.save(agency);
    }

    @SneakyThrows
    @Override
    public Agency update(String code, Agency agency) {
        var  one = repository.findByCode(code)
                .orElseThrow(() -> new Exception(String.format("Agency with code %s does not exist", code)));

        one.setName(agency.getName());

        return repository.save(one);
    }

    @SneakyThrows
    @Override
    public Agency get(String code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new Exception(String.format("Agency with code %s does not exist", code)));
    }

    @Override
    public void delete(String code) {
        var agency = get(code);
        repository.deleteById(agency.getId());
    }
}
