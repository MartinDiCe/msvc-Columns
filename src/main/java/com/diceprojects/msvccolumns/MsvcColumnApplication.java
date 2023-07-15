package com.diceprojects.msvccolumns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@EnableFeignClients
@EnableJpaRepositories("com.diceprojects.msvccolumns.persistences.repositories")
@EntityScan("com.diceprojects.msvccolumns.persistences.models.entities")
@EnableCaching
public class MsvcColumnApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcColumnApplication.class, args);
    }

}
