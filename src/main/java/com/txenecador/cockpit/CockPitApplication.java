package com.txenecador.cockpit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaAuditing
@SpringBootApplication
public class CockPitApplication {

    public static void main(String[] args) {
        SpringApplication.run(CockPitApplication.class, args);
    }
}
