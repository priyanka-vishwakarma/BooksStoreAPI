package utils;

public class EmailGenerator {
    public static String generateEmail() {
        long timestamp = System.currentTimeMillis();
        return "testuser" + timestamp + "@books.com";
    }
}
