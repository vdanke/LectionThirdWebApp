package org.step.repository;

import org.step.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserData {

    private static final Map<Integer, User> USER_MAP = new HashMap<>();

    static {
        USER_MAP.put(1, new User(1, "First", "first", "first"));
        USER_MAP.put(2, new User(2, "Second", "second", "first"));
        USER_MAP.put(3, new User(3, "Third", "third", "first"));
        USER_MAP.put(4, new User(4, "Fourth", "fourth", "first"));
        USER_MAP.put(5, new User(5, "Fifth", "fifth", "first"));
    }

    public static Map<Integer, User> getUserMap() {
        return USER_MAP;
    }
}
