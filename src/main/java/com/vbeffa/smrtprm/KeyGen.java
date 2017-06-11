package com.vbeffa.smrtprm;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author vlad.beffa
 */
public class KeyGen {

    static KeyPair generate(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(keySize);
        return kpg.generateKeyPair();
    }
}
