package org.step.repository.connection.pool;

import java.util.ResourceBundle;

public class DBResourceManager {

    private static DBResourceManager instance = null;

    private DBResourceManager() {
    }

    private ResourceBundle bundle = ResourceBundle.getBundle("db");

    public static DBResourceManager getInstance() {
        if (instance == null) {
            instance = new DBResourceManager();
        }
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
