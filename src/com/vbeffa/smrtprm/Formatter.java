package com.vbeffa.smrtprm;


import java.util.Base64;


/**
 *
 * @author vlad.beffa
 */
public class Formatter {
    static String toJson(String message, byte[] signature, byte[] publicKey) {
        return "{\n"
                + "    \"message\":\"" + message + "\",\n"
                + "    \"signature\":\"" + Base64.getEncoder().encodeToString(signature) + "\"\n"
                + "    \"pubkey\":\"-----BEGIN PUBLIC KEY-----\\n"
                + Base64.getEncoder().encodeToString(publicKey).replaceAll("(.{64})", "$1\\\\n")
                + "\\n-----END PUBLIC KEY-----\\n\"\n"
                + "}";        
    }
}
