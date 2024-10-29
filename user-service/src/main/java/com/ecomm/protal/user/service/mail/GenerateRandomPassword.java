package com.ecomm.protal.user.service.mail;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GenerateRandomPassword {
    public String generatePassword(String email){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder otp = new StringBuilder(4);
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(alphabet.length());
            otp.append(alphabet.charAt(index));
        }

        return otp.toString();

    }
}
