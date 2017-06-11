package com.vbeffa.smrtprm;

import java.security.PublicKey;

/**
 *
 * @author vlad.beffa
 */
class TestPublicKey extends TestKey implements PublicKey {
    @Override
    public byte[] getEncoded() {
        return "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDXEZeODIZ8jSlWPcAWv2iuqkPiQU26NWthFFFw2g1ohATYf6+QVX6h6eBChal/L4yyy1A2Ub0oqVEG83uOxSRQQD/5o/aQDlbcOAf3NhHAASVv/egwfRbZxkdVwWGFZ8X2Y8s/hfqhEW96LiY8Gvggrbh/wcHnkfTRIiX8DgoDLQIDAQAB".getBytes();
    }
}
