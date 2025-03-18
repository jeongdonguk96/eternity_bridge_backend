package com.example.eternity_bridge_backend.utils;

import java.security.SecureRandom;

public class CommonUtils {

    // 4자리의 랜덤 숫자를 생성한다.
    public static String generateRandom4Digit() {
        SecureRandom random = new SecureRandom();
        int num = (random.nextInt() & 0x7FFFFFFF) % 9000 + 1000;
        return String.valueOf(num);
    }

}
