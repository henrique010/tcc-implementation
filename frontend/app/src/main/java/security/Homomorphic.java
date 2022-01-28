package security;

import com.example.tcc_implementation.paillier.PaillierPrivateKey;
import com.example.tcc_implementation.paillier.PaillierPublicKey;
import java.math.BigInteger;

public class Homomorphic {
    private PaillierPrivateKey privateKey;
    private PaillierPublicKey publicKey;

    public Homomorphic(int keySize) {
        this.privateKey = PaillierPrivateKey.create(keySize);
        this.publicKey = this.privateKey.getPublicKey();
    }

    public PaillierPublicKey getPublicKey() {
        return this.publicKey;
    }

    public BigInteger encrypt(int value) {
        BigInteger bigValue = BigInteger.valueOf(value);

        return this.publicKey.raw_encrypt(bigValue);
    }

    public BigInteger encrypt(long value) {
        BigInteger bigValue = BigInteger.valueOf(value);

        return this.publicKey.raw_encrypt(bigValue);
    }

    public BigInteger decrypt(BigInteger encryptedNumber) {
        return this.privateKey.raw_decrypt(encryptedNumber);
    }

    public static BigInteger sum(BigInteger encryptNumberOne, BigInteger encryptNumberTwo, PaillierPublicKey publicKey) {
        return publicKey.raw_add(encryptNumberOne, encryptNumberTwo);
    }

    public static BigInteger multiply(BigInteger encryptNumber, BigInteger plainNumber, PaillierPublicKey publicKey) {
        return publicKey.raw_multiply(encryptNumber, plainNumber);
    }
}
