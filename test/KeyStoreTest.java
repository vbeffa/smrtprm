/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vlad.beffa
 */
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

        PublicKey publicKey = new TestPublicKey();
        PrivateKey privateKey = new TestPrivateKey();
        KeyPair keyPair = new KeyPair(publicKey, privateKey);
        KeyStore.save(keyPair, publicKeyFile, privateKeyFile);

        assertTrue(Files.exists(publicKeyFile.toPath()));
        assertTrue(Files.exists(privateKeyFile.toPath()));
    }
}
