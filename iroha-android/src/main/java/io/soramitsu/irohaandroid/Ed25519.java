package io.soramitsu.irohaandroid;

import java.util.ArrayList;
import java.util.List;


/**
 * This class can be generate keypair or create signature or verify the message.
 */
public class Ed25519 {
    public native static ArrayList<String> GenerateKeyPair();
    public native static String Signature(String message, String priKey, String pubKey);
    public native static boolean Verify(String signatureb64, String message, String pubKeyb64);

    static {
        System.loadLibrary("native-lib");
    }

    /**
     * Create KeyPair.
     *
     * @return KeyPair (public key and private key are encoded by base64)
     */
    public static KeyPair createKeyPair() {
        List<String> generatedKeyPair = GenerateKeyPair();
        return new KeyPair(generatedKeyPair.get(0), generatedKeyPair.get(1));
    }

    /**
     * Create signature from the message with KeyPair.
     *
     * @param message target message
     * @param keyPair using converted to signature
     * @return signature
     */
    public static String sign(String message, KeyPair keyPair) {
        return Signature(message, keyPair.getPrivateKey(), keyPair.getPublicKey());
    }

    /**
     * Check the message by signature.
     *
     * @param signature signature (encoded by base64)
     * @param message   target message
     * @param publicKey ed25519 public key (encoded by base64)
     * @return true if the correct message
     */
    public static boolean verify(String signature, String message, String publicKey) {
        return Verify(signature, message, publicKey);
    }
}
