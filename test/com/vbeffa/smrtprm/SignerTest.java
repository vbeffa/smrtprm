package com.vbeffa.smrtprm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.vbeffa.smrtprm.Signer;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.Signature;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vlad.beffa
 */
public class SignerTest {
    
    @Test
    public void testSign() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair keys = kpg.generateKeyPair();
        byte[] signed = Signer.sign(keys.getPrivate(), "howdy");
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(keys.getPublic());
        signature.update("howdy".getBytes());
        assertTrue(signature.verify(signed));
    }
    
}
