package com.txenecador.cockpit.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgencyJsonCreate {

    @NotNull(message = "Name cannot be null")
    private String name;
}
