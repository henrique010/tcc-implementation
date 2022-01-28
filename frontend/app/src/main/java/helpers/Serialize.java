package helpers;

import com.example.tcc_implementation.paillier.PaillierPublicKey;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.apache.commons.codec.binary.Base64;

import java.math.BigInteger;

public class Serialize {
    public static String homomorphicPublicKey(PaillierPublicKey publicKey) {
        ObjectNode data;
        ObjectMapper mapper = new ObjectMapper();

        data = mapper.createObjectNode();
        data.put("alg", "PAI-GN1");
        data.put("kty", "DAJ");

        // Convert n to base64 encode
        String encodedModulus = new String(Base64.encodeBase64(publicKey.getModulus().toByteArray()));
        data.put("n", encodedModulus);

        ArrayNode an = data.putArray("key_ops");
        an.add("encrypt");
        return data.toString();
    }
}
