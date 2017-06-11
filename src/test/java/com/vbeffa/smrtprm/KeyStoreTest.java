package com.vbeffa.smrtprm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Key;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.junit.Test;
import static org.junit.Assert.*;

public class KeyStoreTest {

    @Test
    public void testKeysExist() throws IOException {
        File publicKeyFile = null;
        File privateKeyFile = null;
        assertFalse(KeyStore.keysExist(publicKeyFile, privateKeyFile));

        publicKeyFile = new File("/tmp/foo.pub");
        privateKeyFile = new File("/tmp/foo");
        publicKeyFile.delete();
        privateKeyFile.delete();
        assertFalse(KeyStore.keysExist(publicKeyFile, privateKeyFile));

        publicKeyFile.createNewFile();
        privateKeyFile.createNewFile();
        assertTrue(KeyStore.keysExist(publicKeyFile, privateKeyFile));
    }

    @Test
    public void testSave() throws NoSuchAlgorithmException, IOException {
        File publicKeyFile = new File("/tmp/foo.pub");
        File privateKeyFile = new File("/tmp/foo");
        Files.deleteIfExists(publicKeyFile.toPath());
        Files.deleteIfExists(privateKeyFile.toPath());

        class TestKey implements Key {
            @Override public String getAlgorithm() { return "testAlgorithm"; }
            @Override public String getFormat() { return "testFormat"; }
            @Override public byte[] getEncoded() { return new byte[0]; }
        }
        class TestPublicKey extends TestKey implements PublicKey {}
        class TestPrivateKey extends TestKey implements PrivateKey {}

        PublicKey publicKey = new TestPublicKey();
        PrivateKey privateKey = new TestPrivateKey();
        KeyPair keyPair = new KeyPair(publicKey, privateKey);
        KeyStore.save(keyPair, publicKeyFile, privateKeyFile);

        assertTrue(Files.exists(publicKeyFile.toPath()));
        assertTrue(Files.exists(privateKeyFile.toPath()));
    }
}
