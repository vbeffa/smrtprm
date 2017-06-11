package com.vbeffa.smrtprm;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeyGen {
    /**
     * Generates an RSA key pair of the specified key size.
     *
     * @param keySize key size in bits
     * @return the generated key pair
     * @throws NoSuchAlgorithmException
     */
    static KeyPair generate(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(keySize);
        return kpg.generateKeyPair();
    }
}
