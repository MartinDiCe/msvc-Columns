package com.diceprojects.msvccolumns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories("com.diceprojects.msvccolumns.persistences.repositories")
@EntityScan("com.diceprojects.msvccolumns.persistences.models.entities")
public class MsvcColumnApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcColumnApplication.class, args);
    }

}
