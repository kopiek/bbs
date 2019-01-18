package com.top.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
@EnableAutoConfiguration
@EnableAsync
@EnableJpaRepositories("com.top.sample.infrastructrue.repository")
public class SampleApplication {

    public static void main(String[] args) {
        setupLocaleAndTimeZone();
        SpringApplication.run(SampleApplication.class, args);
    }

    private static void setupLocaleAndTimeZone() {
        Locale.setDefault(Locale.CHINA);
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    @Bean
    public LoadTimeWeaver loadTimeWeaver() {
        return new InstrumentationLoadTimeWeaver();
    }
}

