package com.apper;

import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.Random;

@Service
public class IdGeneratorService {

    private final Random random = new Random();

    public String generateRandomCharacters(int length) {
        StringBuilder sb = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int charactersLength = characters.length();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charactersLength);
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public String getNextId() {
        return UUID.randomUUID().toString();
    }
}
