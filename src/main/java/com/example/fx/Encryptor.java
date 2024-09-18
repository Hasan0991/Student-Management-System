package com.example.fx;

import org.mindrot.jbcrypt.BCrypt;

public class Encryptor {
    public static String encryptString(String input) {
        return BCrypt.hashpw(input, BCrypt.gensalt());

    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {

        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public static void main(String[] args) {
        String password = "ramil1";
        String hashedPass = encryptString(password);

        System.out.println("Hashed Password: " + hashedPass);

        boolean isPasswordCorrect = checkPassword("ramil1V", hashedPass);
        System.out.println("Password correct: " + isPasswordCorrect);
    }
}

