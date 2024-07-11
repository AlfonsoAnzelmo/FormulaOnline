package unisa.it.formulaonline.utility;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class PassHash {
    public static String PasswordHasher(String pass) {
        try {
            MessageDigest digest =
                    MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(pass.getBytes(StandardCharsets.UTF_8));
            return String.format("%040x", new
                    BigInteger(1, digest.digest()));
        } catch (
                NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
