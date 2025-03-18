package com.example.eternity_bridge_backend.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {

    // 고유 trxKey를 생성한다.
    public static String generateTrxKey() {
        String randomNumber = CommonUtils.generateRandom4Digit();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        return formatter.format(new Date()) + randomNumber;
    }

}
