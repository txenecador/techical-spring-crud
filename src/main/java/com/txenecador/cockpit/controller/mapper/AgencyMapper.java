package com.txenecador.cockpit.controller.mapper;

import com.txenecador.cockpit.controller.dto.AgencyJson;
import com.txenecador.cockpit.controller.dto.AgencyJsonCreate;
import com.txenecador.cockpit.entities.Agency;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AgencyMapper {

    AgencyJson mapTo(Agency agency);

    Agency mapTo(AgencyJsonCreate agency);
}
