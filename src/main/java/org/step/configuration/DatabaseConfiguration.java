package org.step.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

/*
Environment ->
BeanDefinition (Бежит по пакетам и собирает имена классов) ->
BeanFactory (Создает бины) ->
Inject beans (Внедрение зависимостей) ->
BeanPostProcessor.postProcessBeforeInitialization() (первичная настройка бина) ->
init method () ->
BeanPostProcessor.postProcessAfterInitialization() (создание proxy объектов, дополнение кода если нужно) ->
Context

Destroying context -> destroy method -> close context
 */
@Configuration
@ComponentScan(basePackages = {"org.step"})
@PropertySources({
        @PropertySource("classpath:db.properties")
})
public class DatabaseConfiguration {

//    @Autowired
    private final Environment environment;

    @Value("${db.url}")
    private String url;

    @Autowired
    public DatabaseConfiguration(Environment environment) {
        this.environment = environment;
    }

    /*
    Scope - session, request, globalSession
     */
    @Bean("dataSource")
    @Scope("singleton")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.username"));
        dataSource.setPassword(environment.getProperty("db.password"));
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("db.driver")));

        return dataSource;
    }

    @Bean("customDataSource")
    @Scope("prototype")
    public DataSource customDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl("bla");
        dataSource.setUsername("bla");
        dataSource.setPassword("bla");
        dataSource.setDriverClassName("bla");

        return dataSource;
    }

//    @Autowired
//    public void setEnvironment(Environment environment) {
//        this.environment = environment;
//    }
}
