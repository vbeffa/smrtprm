package com.vbeffa.smrtprm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

public class KeyStore {

    /**
     * Checks if the key files are not null and exist on disk.
     *
     * @param publicKeyFile
     * @param privateKeyFile
     * @return true if both key files exist; false otherwise
     */
    static boolean keysExist(File publicKeyFile, File privateKeyFile) {
        return privateKeyFile != null && privateKeyFile.exists() && publicKeyFile != null && publicKeyFile.exists();
    }

    /**
     * Saves a key pair to disk. Overwrites any existing files. The private key file is written with 600 permissions
     * (i.e., -rw-------).
     *
     * @param keyPair
     * @param publicKeyFile
     * @param privateKeyFile
     * @throws IOException
     */
    static void save(KeyPair keyPair, File publicKeyFile, File privateKeyFile) throws IOException {
        Files.write(publicKeyFile.toPath(), Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()).getBytes());
        Files.write(privateKeyFile.toPath(), Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()).getBytes());
        Set<PosixFilePermission> perms = new HashSet<>();
        perms.add(PosixFilePermission.OWNER_READ);
        perms.add(PosixFilePermission.OWNER_WRITE);
        Files.setPosixFilePermissions(privateKeyFile.toPath(), perms);
    }

    /**
     * Loads a key pair from disk.
     *
     * @param publicKeyFile
     * @param privateKeyFile
     * @return the loaded key pair
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws IOException
     */
    static KeyPair load(File publicKeyFile, File privateKeyFile) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        Path p;
        byte[] rawBytes, decodedBytes;
        PKCS8EncodedKeySpec privateSpec;
        X509EncodedKeySpec publicSpec;
        PublicKey publicKey;
        PrivateKey privateKey;

        p = Paths.get(publicKeyFile.toString());
        rawBytes = Files.readAllBytes(p);
        decodedBytes = Base64.getDecoder().decode(rawBytes);
        publicSpec = new X509EncodedKeySpec(decodedBytes);
        publicKey = kf.generatePublic(publicSpec);

        p = Paths.get(privateKeyFile.toString());
        rawBytes = Files.readAllBytes(p);
        decodedBytes = Base64.getDecoder().decode(rawBytes);
        privateSpec = new PKCS8EncodedKeySpec(decodedBytes);
        privateKey = kf.generatePrivate(privateSpec);

        return new KeyPair(publicKey, privateKey);
    }
}
