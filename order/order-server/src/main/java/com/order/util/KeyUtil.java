package com.order.util;

import java.util.Random;

public class KeyUtil {
    public static synchronized String generateKey() {
        Random random = new Random();
        int id = random.nextInt(99999) + 100000;
        return System.currentTimeMillis() + String.valueOf(id);
    }
}
