
import java.security.Key;

/**
 *
 * @author vlad.beffa
 */
class TestKey implements Key {

    @Override
    public String getAlgorithm() {
        return "testAlgorithm";
    }

    @Override
    public String getFormat() {
        return "testFormat";
    }

    @Override
    public byte[] getEncoded() {
        return new byte[0];
    }
}
