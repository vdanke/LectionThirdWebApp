package org.step.security;

import org.step.model.User;

import java.util.ResourceBundle;

public class TokenCreator {

    private static final ResourceBundle SECURITY = ResourceBundle.getBundle("security");

    public static String createToken(User user) {
        String secretKey = SECURITY.getString("token");

        return secretKey.substring(secretKey.length() / 2).concat(user.getId().toString()).concat(user.getUsername());
    }

    public static boolean validateToken(String token, User user) {
        String secretKey = SECURITY.getString("token");

        String userToken = user.getUsername().concat(user.getId().toString());

        String concat = secretKey.substring(secretKey.length() / 2).concat(userToken);

        return concat.length() == token.length();
    }
}
