package com.vbeffa.smrtprm;

import java.util.Base64;

public class Formatter {
    /**
     * Formats a message, signature, and public key in JSON format.
     *
     * @param message message being signed
     * @param signature message signature
     * @param publicKey public key of the private key used to sign the message
     * @return string in JSON format
     */
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
