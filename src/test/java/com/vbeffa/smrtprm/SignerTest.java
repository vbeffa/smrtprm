package com.vbeffa.smrtprm;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import org.junit.Test;
import static org.junit.Assert.*;

public class SignerTest {
    @Test
    public void testSign() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair keys = kpg.generateKeyPair();
        byte[] signed = Signer.sign("howdy", keys.getPrivate());
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(keys.getPublic());
        signature.update("howdy".getBytes());
        assertTrue(signature.verify(signed));
    }
}
