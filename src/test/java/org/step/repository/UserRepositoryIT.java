package org.step.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.step.configuration.DatabaseConfiguration;
import org.step.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class})
@Transactional
public class UserRepositoryIT {

    private final User first = new User("first", "first", "first");
    private final User second = new User("second", "second", "second");
    private final User third = new User("third", "third", "third");

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setup() {
        entityManager.persist(first);
        entityManager.persist(second);
        entityManager.persist(third);
    }

    @Test
    public void userListShouldNotBeEmpty() {
        List<User> users = entityManager.createQuery("from User u", User.class)
                .getResultList();

        Assert.assertFalse(users.isEmpty());
        Assert.assertTrue(users.contains(first));
    }

    @Test
    public void findUserById() {
        User user = entityManager.find(User.class, 1);

        Assert.assertNotNull(user);
        Assert.assertEquals(user.getId().toString(), "1");
    }

    @After
    public void clean() {
        entityManager.remove(first);
        entityManager.remove(second);
        entityManager.remove(third);
    }
}
