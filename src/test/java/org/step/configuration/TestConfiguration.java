package org.step.configuration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.step.model.User;
import org.step.repository.UserRepository;
import org.step.service.UserService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfiguration.class)
public class TestConfiguration {

    private DataSource dataSource;
    private UserService userService;
    private UserRepository userRepository;

    @Test
    public void injectDependenciesShouldNotBeNull() {
        Assert.assertNotNull(dataSource);
        Assert.assertNotNull(userService);
        Assert.assertNotNull(userRepository);
    }

    @Test
    public void dataSourceShouldBeCorrectlyInitialized() throws SQLException {
        Connection connection = dataSource.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        String url = metaData.getURL();
        String userName = metaData.getUserName();

        Assert.assertNotNull(connection);
        Assert.assertTrue(url.contains("postgre"));
        Assert.assertTrue(url.endsWith("social"));
        Assert.assertEquals("user", userName);
    }

    @Test
    public void userListShouldNotBeEmpty() {
        List<User> allUsers = userRepository.findAllUsers();

        Assert.assertFalse(allUsers.isEmpty());
    }

    @Test
    public void userListFromServiceShouldNotBeEmpty() {
        List<User> allUsers = userService.findAllUsers();

        Assert.assertFalse(allUsers.isEmpty());
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
