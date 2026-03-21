package com.bramver.predict_sports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories("com.bramver.predict_sports.repository")
public class PredictSportsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PredictSportsApplication.class, args);
    }
}
