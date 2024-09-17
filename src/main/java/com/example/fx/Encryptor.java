package com.example.fx;

import org.mindrot.jbcrypt.BCrypt;

public class Encryptor {
    public static String encryptString(String input) {

<<<<<<< HEAD
        return BCrypt.hashpw(input, BCrypt.gensalt());
=======
        
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] messageDigest = md.digest(input.getBytes());

        BigInteger bigInt = new BigInteger(1,messageDigest);

        return bigInt.toString(16);
>>>>>>> 143e7989afd218d837d4731e352b622f53bcf481
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

