package com.vbeffa.smrtprm;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vlad.beffa
 */
public class FormatterTest {

    @Test
    public void testToJson() {
        String message = "howdy";
        byte[] signature = "fake signature".getBytes();
        byte[] publicKey = "fake public key 123456789012345678901234567890123456789012345678901234567890".getBytes();
        String expResult = "{\n"
                + "    \"message\":\"howdy\",\n"
                + "    \"signature\":\"ZmFrZSBzaWduYXR1cmU=\"\n"
                + "    \"pubkey\":\"-----BEGIN PUBLIC KEY-----\\nZmFrZSBwdWJsaWMga2V5IDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTIzNDU2Nzg5MDEy\\nMzQ1Njc4OTAxMjM0NTY3ODkwMTIzNDU2Nzg5MA==\\n-----END PUBLIC KEY-----\\n\"\n"
                + "}";
        String result = Formatter.toJson(message, signature, publicKey);
        assertEquals(expResult, result);
    }

}
