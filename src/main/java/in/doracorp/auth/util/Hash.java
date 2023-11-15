package in.doracorp.auth.util;

import java.util.Objects;

public class Hash {
    public static String createHash(String input){
        return input;
    }

    public static boolean compareHash(String hash1, String hash2){
        return Objects.equals(hash2, hash1);
    }
}
