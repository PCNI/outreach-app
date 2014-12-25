package com.innoppl.outreach.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Jerlad Mejarla
 */
public class HashUtils {

    /**
     *
     * @param message
     * @return
     */
    public static String getMd5Hash(final String message) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(message.getBytes(), 0, message.length());
            String hashedPass = new BigInteger(1, messageDigest.digest()).toString(16);
            if (hashedPass.length() < 32) {
                hashedPass = "0" + hashedPass;
            }
            return hashedPass;
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
}
