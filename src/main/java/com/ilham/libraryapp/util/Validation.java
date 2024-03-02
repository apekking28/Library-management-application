package com.ilham.libraryapp.util;

import java.util.regex.Pattern;

public class Validation {
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    // Validasi password
    public static boolean isValidPassword(String password) {
        // Minimal 8 karakter, setidaknya 1 huruf kapital, dan hanya alphanumeric
        String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z0-9]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }
}
