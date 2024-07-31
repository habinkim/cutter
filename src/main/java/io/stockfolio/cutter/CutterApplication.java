package io.stockfolio.cutter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CutterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CutterApplication.class, args);
    }

}
