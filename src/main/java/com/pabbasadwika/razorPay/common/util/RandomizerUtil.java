package com.pabbasadwika.razorPay.common.util;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

public class RandomizerUtil {

    private static final SecureRandom SECURE_RANDOM  = new SecureRandom();
    public static String randomBase64(int length){
        //UUID.randomUUID().toString().replace("-","");

        byte[] buf = new byte[length];
        SECURE_RANDOM.nextBytes(buf);
        // it will take i/p byte array and it will generate byte from range -128 to 127 and store in bytes array
        //why to use secure random -> because its thread safety
        //[4,12,100,-12] {-128,127}
        return Base64
                .getUrlEncoder() //this ensures output comes from the here is  safe for urls like no % or
                .withoutPadding() //in general base 64 keeps =='s at the end , without padding make sure to remove those
                .encodeToString(buf);

    }
}


