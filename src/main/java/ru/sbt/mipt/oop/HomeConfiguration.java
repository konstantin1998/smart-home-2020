package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.homeAndComponents.SmartHome;

@Configuration
public class HomeConfiguration {
    private SmartHome home;
    @Bean
    public SmartHome getHome() {
        if (home == null) {
            home = HomeReader.createHome("smart-home-1.js");
            return home;
        } else {
            return home;
        }
    }
}
