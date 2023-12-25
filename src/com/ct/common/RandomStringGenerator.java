package com.ct.common;

import java.util.Random;

public class RandomStringGenerator {
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int desiredLength = 8; // 你可以根据需要更改长度
        String randomString = generateRandomString(desiredLength);
        System.out.println("Random String: " + randomString);
    }
}
