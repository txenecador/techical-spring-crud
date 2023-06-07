package com.txenecador.cockpit.service.impl;

import com.txenecador.cockpit.entities.Agency;
import com.txenecador.cockpit.repositories.AgencyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class AgencyImplTest {

    @Mock
    private AgencyRepository repository;

    @InjectMocks
    private AgencyServiceImpl agencyService;

    @Test
    void findAll() {
        var code = UUID.randomUUID().toString();
        // when
        Mockito.when(repository.findAll()).thenReturn(List.of(Agency.builder()
                .id(1L)
                .name("Agency A")
                .code(code)
                .creationDate(new Date())
                .lastUpdate(new Date())
                .build()));
        // then

        var respo = agencyService.findAll();

        Assertions.assertEquals(1, respo.size());
        Assertions.assertEquals(code, respo.get(0).getCode());
    }

    @Test
    void create() {
        // Given
        var agency = Agency.builder()
                .id(1L)
                .name("Agency A")
                .creationDate(new Date())
                .lastUpdate(new Date())
                .build();
        // when
        Mockito.when(repository.save(Mockito.any(Agency.class))).thenReturn(agency);
        // then

        var respo = agencyService.create(agency);
        Assertions.assertEquals("Agency A", respo.getName());
        Assertions.assertNotNull(respo.getCode());
    }

    @Test
    void update() {
        // Given
        var code = UUID.randomUUID().toString();
        var toSave = Agency.builder()
                .name("Agency B")
                .build();

        var agency = Agency.builder()
                .id(2L)
                .code(code)
                .name("Agency A")
                .creationDate(new Date())
                .lastUpdate(new Date())
                .build();
        // when
        Mockito.when(repository.findByCode(code)).thenReturn(Optional.of(agency));
        Mockito.when(repository.save(Mockito.any(Agency.class))).thenReturn(agency);
        // then

        var respo = agencyService.update(code, toSave);
        Assertions.assertEquals("Agency B", respo.getName());
        Assertions.assertEquals(code, respo.getCode());

    }

    @Test
    void get() {
        // Given
        var code = UUID.randomUUID().toString();

        var agency = Agency.builder()
                .id(2L)
                .code(code)
                .name("Agency A")
                .creationDate(new Date())
                .lastUpdate(new Date())
                .build();
        // when
        Mockito.when(repository.findByCode(code)).thenReturn(Optional.of(agency));
        // then

        var respo = agencyService.get(code);
        Assertions.assertEquals("Agency A", respo.getName());
        Assertions.assertEquals(code, respo.getCode());
    }

    @Test
    void delete() {
        // Given
        var code = UUID.randomUUID().toString();
        var agency = Agency.builder()
                .id(1L)
                .code(code)
                .name("Agency A")
                .creationDate(new Date())
                .lastUpdate(new Date())
                .build();
        // when
        Mockito.when(repository.findByCode(code)).thenReturn(Optional.of(agency));

        // then
        agencyService.delete(code);
        Mockito.verify(repository).deleteById(1L);
    }
}
