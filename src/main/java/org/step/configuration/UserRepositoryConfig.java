package org.step.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.step.repository.UserRepository;
import org.step.repository.impl.UserRepositoryImpl;

@Configuration
public class UserRepositoryConfig {

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl();
    }
}
