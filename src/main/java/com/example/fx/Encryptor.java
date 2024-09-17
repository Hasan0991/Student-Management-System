package com.example.fx;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class Encryptor {
    public String encryptString(String input) throws NoSuchAlgorithmException {

        
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] messageDigest = md.digest(input.getBytes());

        BigInteger bigInt = new BigInteger(1,messageDigest);

        return bigInt.toString(16);
    }
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Encryptor encryptor = new Encryptor();

        String password = "hasan132";
        String hashedPass = encryptor.encryptString(password);

        System.out.println("Hashed Password: " + hashedPass);

    }
}

