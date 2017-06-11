package com.vbeffa.smrtprm;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyPair;
import java.util.Properties;

/**
 * Main class for the application. If a key pair does not yet exist in the configured directory (see key_store_path in
 * config.props in the project root directory), creates it and then loads it from disk. Otherwise loads the key pair,
 * signs the message, and prints the result to standard out.
 */
class RsaSignerApp {

    private static final Properties CONFIG = new Properties();
    private static final File CONFIG_FILE = new File("./config.props");

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: RsaSignerApp <message>");
            System.exit(-1);
        }

        CONFIG.load(new FileInputStream(CONFIG_FILE));
        int keySize = Integer.parseInt(CONFIG.getProperty("key_size"));

        String message = args[0];
        if (!KeyStore.keysExist(publicKeyFile(), privateKeyFile())) {
            System.out.println("Key pair not found on disk. Creating.");
            KeyStore.save(KeyGen.generate(keySize), publicKeyFile(), privateKeyFile());
        }
        KeyPair keys = KeyStore.load(publicKeyFile(), privateKeyFile());
        String json = Formatter.toJson(message, Signer.sign(message, keys.getPrivate()), keys.getPublic().getEncoded());
        System.out.println(json);
    }

    private static File privateKeyFile() {
        return new File(CONFIG.getProperty("key_store_path") + "/id_rsa");
    }

    private static File publicKeyFile() {
        return new File(CONFIG.getProperty("key_store_path") + "/id_rsa.pub");
    }
}
