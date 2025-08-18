package utils;

import config.ConfigManager;

import java.util.Random;

public class TestDataManager {

    private static String email;
    private static String password;
    private static int userId;

    public static void init() {
        if (email == null) { // only once
            email = EmailGenerator.generateEmail();
            password = ConfigManager.getProperty("password");
            userId = new Random().nextInt(1000);
        }
    }

    public static String getEmail() { return email; }
    public static String getPassword() { return password; }
    public static int getUserId() { return userId; }

}
