package dev.peertosir.littlemanager.utils;

public class AuthValidator {
    public static boolean validate(String username, int minValue) {
        return username.length() >= minValue;
    }
}
