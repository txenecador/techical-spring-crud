package com.txenecador.cockpit.controller.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AgencyJson {
    private String name;
    private String code;
    private Date creationDate;
    private Date lastUpdate;
}
