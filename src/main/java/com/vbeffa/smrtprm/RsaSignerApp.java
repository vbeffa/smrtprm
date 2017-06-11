package com.vbeffa.smrtprm;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyPair;
import java.util.Properties;

/**
 *
 * @author vlad.beffa
 */
class RsaSignerApp {

    private static final Properties CONFIG = new Properties();
    private static final File CONFIG_FILE = new File("./config.props");

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: Foo <message>");
            System.exit(-1);
        }

        CONFIG.load(new FileInputStream(CONFIG_FILE));
        int keySize = Integer.parseInt(CONFIG.getProperty("key_size"));

        String message = args[0];
        if (!KeyStore.keysExist(publicKeyFile(), privateKeyFile())) {
            KeyStore.save(KeyGen.generate(keySize), publicKeyFile(), privateKeyFile());
        }
        KeyPair keys = KeyStore.load(publicKeyFile(), privateKeyFile());
        String json = Formatter.toJson(message, Signer.sign(keys.getPrivate(), message), keys.getPublic().getEncoded());
        System.out.println(json);
    }

    private static File privateKeyFile() {
        return new File(CONFIG.getProperty("key_store_path") + "/id_rsa");
    }

    private static File publicKeyFile() {
        return new File(CONFIG.getProperty("key_store_path") + "/id_rsa.pub");
    }
}
