package com.txenecador.cockpit.service;

import com.txenecador.cockpit.entities.Agency;

import java.util.List;

public interface AgencyService {

    List<Agency> findAll();

    Agency create(Agency agency);

    Agency update(String code, Agency agency);

    Agency get(String code);

    void delete(String code);
}
